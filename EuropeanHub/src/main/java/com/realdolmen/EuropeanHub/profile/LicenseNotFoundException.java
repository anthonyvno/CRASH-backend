package com.realdolmen.EuropeanHub.profile;

public class LicenseNotFoundException extends RuntimeException {

    LicenseNotFoundException(int id) {
        super("Could not find profile " + id);
    }

}
