package com.cloudcounselage.ca_onboarding.service;

import com.cloudcounselage.ca_onboarding.model.CommunityAmbassador;
import com.cloudcounselage.ca_onboarding.repository.CommunityAmbassadorRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final CommunityAmbassadorRepository repository;

    public DashboardService(CommunityAmbassadorRepository repository) {
        this.repository = repository;
    }

    public List<CommunityAmbassador> getAllCAs() {
        return repository.findAll();
    }

    public CommunityAmbassador getCAById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CA not found with id: " + id));
    }

    public Map<String, Object> getDashboardStats() {
        List<CommunityAmbassador> allCAs = repository.findAll();
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCAs", allCAs.size());
        stats.put("caList", allCAs);
        return stats;
    }
}
