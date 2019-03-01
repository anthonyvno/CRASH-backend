/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.insurer;

import java.util.Arrays;
import java.util.List;
import static jdk.management.resource.internal.SimpleResourceContext.get;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InsurerController.class)
public class InsurerControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private InsurerRepository insurerRepository;
    
    @Test
public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
  throws Exception {
     
    Insurer agInsurance = new Insurer("Belgium","AG Insurance");
 
    List<Insurer> allInsurers = Arrays.asList(agInsurance);
 
    given(insurerRepository.findAll()).willReturn(allInsurers);
 /*
    mvc.perform(get("/insurers/")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].name", is(agInsurance.getName())));
*/
}
    
}
