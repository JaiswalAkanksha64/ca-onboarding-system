package com.cloudcounselage.ca_onboarding.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UtmService {

    @Value("${app.iam.base.url}")
    private String baseUrl;

    public String generateUtmLink(String firstName, String lastName) {
        String fullName = (firstName + lastName)
                .toLowerCase()
                .replaceAll("\\s+", "");

        return baseUrl
                + "?utm_source=ca"
                + "&utm_medium=referral"
                + "&utm_campaign=" + fullName;
    }
}
