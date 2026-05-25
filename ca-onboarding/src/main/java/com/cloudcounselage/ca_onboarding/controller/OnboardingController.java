package com.cloudcounselage.ca_onboarding.controller;

import com.cloudcounselage.ca_onboarding.model.CommunityAmbassador;
import com.cloudcounselage.ca_onboarding.service.DashboardService;
import com.cloudcounselage.ca_onboarding.service.EmailService;
import com.cloudcounselage.ca_onboarding.service.UtmService;
import com.cloudcounselage.ca_onboarding.repository.CommunityAmbassadorRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OnboardingController {

    private final CommunityAmbassadorRepository repository;
    private final UtmService utmService;
    private final EmailService emailService;
    private final DashboardService dashboardService;

    public OnboardingController(CommunityAmbassadorRepository repository,
                                UtmService utmService,
                                EmailService emailService,
                                DashboardService dashboardService) {
        this.repository = repository;
        this.utmService = utmService;
        this.emailService = emailService;
        this.dashboardService = dashboardService;
    }

    // 1. Onboard new CA
    @PostMapping("/onboard")
    public ResponseEntity<?> onboard(
            @Valid @RequestBody CommunityAmbassador ca) {

        // Check duplicate email
        if (repository.existsByEmail(ca.getEmail())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "A Community Ambassador with this email already exists!");
            return ResponseEntity.badRequest().body(error);
        }

        String utmLink = utmService.generateUtmLink(
                ca.getFirstName(), ca.getLastName());
        ca.setUtmLink(utmLink);
        CommunityAmbassador saved = repository.save(ca);
        emailService.sendWelcomeEmail(
                ca.getEmail(), ca.getFirstName(), utmLink);
        return ResponseEntity.ok(saved);
    }

    // 2. Get all CAs
    @GetMapping("/cas")
    public ResponseEntity<List<CommunityAmbassador>> getAllCAs() {
        return ResponseEntity.ok(dashboardService.getAllCAs());
    }

    // 3. Get CA by ID
    @GetMapping("/cas/{id}")
    public ResponseEntity<CommunityAmbassador> getCAById(
            @PathVariable Long id) {
        return ResponseEntity.ok(dashboardService.getCAById(id));
    }

    // 4. Dashboard stats
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    // 5. Delete CA
    @DeleteMapping("/cas/{id}")
    public ResponseEntity<String> deleteCA(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("CA deleted successfully!");
    }

    // 6. Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}