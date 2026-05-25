package com.cloudcounselage.ca_onboarding.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "community_ambassador")
public class CommunityAmbassador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "College name is required")
    private String collegeFullName;

    @NotBlank(message = "Residential address is required")
    private String residentialAddress;

    private String city;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @NotBlank(message = "State is required")
    private String stateRegion;

    private String dateOfBirth;

    @NotBlank(message = "Highest education is required")
    private String highestEducation;

    private String iacMentorName;
    private String utmLink;
    private LocalDateTime onboardedAt;

    @PrePersist
    public void prePersist() {
        this.onboardedAt = LocalDateTime.now();
    }
}