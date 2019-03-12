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
public class ProfileEUControllerTest {

    @Mock
    private ProfileEU profileEUMock;

    @Mock
    private ProfileEUService serviceMock;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ProfileEUController controller;

    @Before
    public void setUp() {
        when(serviceMock.findProfileById(any())).thenReturn(Optional.of(profileEUMock));

        controller = new ProfileEUController(serviceMock);
    }

    @Test
    public void findById() {
        ProfileEU actual = controller.one(1);

        verify(serviceMock, times(1)).findProfileById(1);
        verifyZeroInteractions(profileEUMock);
        Assert.assertEquals(profileEUMock, actual);
    }

    @Test
    public void findByIdEmpty() {
        when(serviceMock.findProfileById(any())).thenReturn(Optional.empty());

        expectedException.expectMessage("Could not find resource 1");
        expectedException.expect(NotFoundException.class);

        controller.one(1);
    }
}