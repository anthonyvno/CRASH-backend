package com.realdolmen.EuropeanHub.insurer;

import com.realdolmen.EuropeanHub.common.NotFoundException;
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
public class InsurerControllerTest {

    @Mock
    private Insurer insurerMock;

    @Mock
    private InsurerService repositoryMock;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private InsurerController controller;

    @Before
    public void setUp() {
        when(repositoryMock.findInsurerById(any())).thenReturn(Optional.of(insurerMock));

        controller = new InsurerController(repositoryMock);
    }

    @Test
    public void findById() {
        Insurer actual = controller.one(1);
        
        verify(repositoryMock, times(1)).findInsurerById(1);
        verifyZeroInteractions(insurerMock);
        Assert.assertEquals(insurerMock, actual);
    }

    @Test
    public void findByIdEmpty() {
        when(repositoryMock.findInsurerById(any())).thenReturn(Optional.empty());

        expectedException.expectMessage("Could not find resource 1");
        expectedException.expect(NotFoundException.class);

        controller.one(1);
    }
}