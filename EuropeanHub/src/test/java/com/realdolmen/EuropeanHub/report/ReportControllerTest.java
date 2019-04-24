package com.realdolmen.EuropeanHub.report;

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
public class ReportControllerTest {

    @Mock
    private Report reportMock;

    @Mock
    private ReportRepository repositoryMock;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ReportController controller;

    @Before
    public void setUp() {
        when(repositoryMock.findById(any())).thenReturn(Optional.of(reportMock));

       // controller = new ReportController(repositoryMock);
    }

    @Test
    public void findById() {
        Report actual = controller.one(1);

        verify(repositoryMock, times(1)).findById(1);
        verifyZeroInteractions(reportMock);
        Assert.assertEquals(reportMock, actual);
    }

    @Test
    public void findByIdEmpty() {
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());

        expectedException.expectMessage("Could not find resource 1");
        expectedException.expect(NotFoundException.class);

        controller.one(1);
    }
}