package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.common.NotFoundException;
import com.realdolmen.EuropeanHub.profile.License;
import com.realdolmen.EuropeanHub.profile.LicenseController;
import com.realdolmen.EuropeanHub.profile.LicenseRepository;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LicenseControllerTest {
    
    @Mock
    LicenseService licenseService;
    
    LicenseController licenseController;
    
    @Before
    public void setUp(){
        
        licenseController = new LicenseController(licenseService);

    }
    
    @Test
    public void testGetAll(){
        
        License license1 = new License(1,"C","1-AZE-196","20/02/2020");
        License license2 = new License(1,"B","1-AZE-196","20/02/2020");
        List<License> expectedLicenses = Stream.of(license1 ,license2).collect(Collectors.toList());
        Mockito.when(licenseService.findAll()).thenReturn(expectedLicenses);
        List<License> licenses = licenseController.all();
        
        Assert.assertNotNull(licenses);
        Assert.assertEquals(2, licenses.size());
        Assert.assertTrue(licenses.contains(license1));
        Assert.assertTrue(licenses.contains(license2));
        
        
    }

    
}