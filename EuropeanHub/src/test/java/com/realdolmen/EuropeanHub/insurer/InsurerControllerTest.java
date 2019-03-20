package com.realdolmen.EuropeanHub.insurer;

import com.realdolmen.EuropeanHub.common.NotFoundException;
import com.realdolmen.EuropeanHub.insurer.Insurer;
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
public class InsurerControllerTest {

    @Mock
    private Insurer insurerMock;

    @Mock
    private InsurerService serviceMock;
    
    @Mock
    private InsurerMapper insurerMapperMock;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private InsurerController controller;

    @Before
    public void setUp() {
        //when(serviceMock.findInsurerById(any())).thenReturn(Optional.of(insurerMock));

        controller = new InsurerController(serviceMock,insurerMapperMock);
    }
    
    @Test
    public void testGetAll(){
        
        Insurer insurer1 = new Insurer(1,"Belgium","AG");
        Insurer insurer2 = new Insurer(2,"Belgium","Ethias");
        InsurerDTO insurerDTO1 = new InsurerDTO(1,"Belgium","AG");
        InsurerDTO insurerDTO2 = new InsurerDTO(2,"Belgium","Ethias");
        Mockito.when(insurerMapperMock.mapInsurerToDTO(insurer1)).thenReturn(insurerDTO1);
        Mockito.when(insurerMapperMock.mapInsurerToDTO(insurer2)).thenReturn(insurerDTO2);
        List<Insurer> expectedInsurers = Stream.of(insurer1 ,insurer2).collect(Collectors.toList());
        Mockito.when(serviceMock.findAll()).thenReturn(expectedInsurers);
        List<InsurerDTO> insurers = controller.all();
        
        Assert.assertNotNull(insurers);
        Assert.assertEquals(2, insurers.size());
        Assert.assertTrue(insurers.contains(insurerDTO1));
        Assert.assertTrue(insurers.contains(insurerDTO2));
        
        
    }
/*
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
*/
}