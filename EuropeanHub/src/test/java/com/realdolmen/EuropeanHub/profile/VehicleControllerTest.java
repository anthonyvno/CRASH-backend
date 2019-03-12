package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.common.NotFoundException;
import com.realdolmen.EuropeanHub.profile.License;
import com.realdolmen.EuropeanHub.profile.LicenseController;
import com.realdolmen.EuropeanHub.profile.LicenseService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VehicleControllerTest {

    @Mock
    private Vehicle vehicleMock;

    @Mock
    private VehicleService serviceMock;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private VehicleController controller;

    @Before
    public void setUp() {
        when(serviceMock.findVehicleById(any())).thenReturn(Optional.of(vehicleMock));

        controller = new VehicleController(serviceMock);
    }

    @Test
    public void findById() {
        Vehicle actual = controller.one(1);

        verify(serviceMock, times(1)).findVehicleById(1);
        verifyZeroInteractions(vehicleMock);
        Assert.assertEquals(vehicleMock, actual);
    }

    @Test
    public void findByIdEmpty() {
        when(serviceMock.findVehicleById(any())).thenReturn(Optional.empty());

        expectedException.expectMessage("Could not find resource 1");
        expectedException.expect(NotFoundException.class);

        controller.one(1);
    }
}