/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.insurer;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author SBZBN83
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class InsurerRepositoryIntegrationTest {
    
    @Autowired
    private InsurerRepository insurerRepository;
    
    @Autowired
    private TestEntityManager entityManager;
    
    
    @Test
    public void whenFindByCountry_thenReturnInsurer(){
    Insurer agInsurer = new Insurer("Belgium","AG Insurance");
    entityManager.persist(agInsurer);
    entityManager.flush();
    
    
    List<Insurer> found = insurerRepository.findByCountry("Belgium");
        System.out.println("testpurp"+found.get(0).getCountry());
    assertThat(found.get(1).getCountry()).isEqualTo(agInsurer.getCountry());
    }

}
