/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.insurer.Insurer;
import com.realdolmen.EuropeanHub.insurer.InsurerMapper;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.controller;


public class InsuranceControllerTest {
    @Mock
    InsuranceService insuranceServiceMock;
    
    @Mock
    Insurance insuranceMock;
    
    @Mock
    private InsuranceMapper insuranceMapperMock;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    InsuranceController insuranceController;
    
        @Before
    public void setUp(){
        
        insuranceController = new InsuranceController(insuranceServiceMock,insuranceMapperMock);

    }
    
        @Test
    public void testGetAll(){
        /*
        Insurance insurance1 = new Insurance(1, new Insurer(1,"Belgium","Ethias"),"23089472","9847204","info@peeters.be",new Date(),"0473878097");
        Insurance insurance2 = new Insurance(2, new Insurer(2,"Belgium","Baloise"),"23089472","9847204","info@janssens.be",new Date(),"0473878097");
        InsuranceDTO insuranceDTO1 = new InsuranceDTO(1, new Insurer(1,"Belgium","Ethias"),"23089472","9847204","info@peeters.be",new Date(),"0473878097");
        InsuranceDTO insuranceDTO2 = new InsuranceDTO(2, new Insurer(2,"Belgium","Baloise"),"23089472","9847204","info@janssens.be",new Date(),"0473878097");
        Mockito.when(insuranceMapperMock.mapInsuranceToDTO(insurance1)).thenReturn(insuranceDTO1);
        Mockito.when(insuranceMapperMock.mapInsuranceToDTO(insurance2)).thenReturn(insuranceDTO2);
        List<Insurance> expectedInsurances = Stream.of(insurance1 ,insurance2).collect(Collectors.toList());
        Mockito.when(insuranceServiceMock.findAll()).thenReturn(expectedInsurances);
        List<InsuranceDTO> insurances = insuranceController.all();
        
        Assert.assertNotNull(insurances);
        Assert.assertEquals(2, insurances.size());
        Assert.assertTrue(insurances.contains(insuranceDTO1));
        Assert.assertTrue(insurances.contains(insuranceDTO2));
        
        */
    }
}
