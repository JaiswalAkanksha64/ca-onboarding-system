package com.cloudcounselage.ca_onboarding.repository;

import com.cloudcounselage.ca_onboarding.model.CommunityAmbassador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityAmbassadorRepository
        extends JpaRepository<CommunityAmbassador, Long> {

    Optional<CommunityAmbassador> findByEmail(String email);
    boolean existsByEmail(String email);
}