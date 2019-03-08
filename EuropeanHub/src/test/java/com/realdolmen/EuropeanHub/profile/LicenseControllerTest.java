package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.common.NotFoundException;
import com.realdolmen.EuropeanHub.profile.License;
import com.realdolmen.EuropeanHub.profile.LicenseController;
import com.realdolmen.EuropeanHub.profile.LicenseRepository;
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
public class LicenseControllerTest {

    @Mock
    private License licenseMock;

    @Mock
    private LicenseRepository repositoryMock;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private LicenseController controller;

    @Before
    public void setUp() {
        when(repositoryMock.findById(any())).thenReturn(Optional.of(licenseMock));

        controller = new LicenseController(repositoryMock);
    }

    @Test
    public void findById() {
        License actual = controller.one(1);

        verify(repositoryMock, times(1)).findById(1);
        verifyZeroInteractions(licenseMock);
        Assert.assertEquals(licenseMock, actual);
    }

    @Test
    public void findByIdEmpty() {
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());

        expectedException.expectMessage("Could not find resource 1");
        expectedException.expect(NotFoundException.class);

        controller.one(1);
    }
}