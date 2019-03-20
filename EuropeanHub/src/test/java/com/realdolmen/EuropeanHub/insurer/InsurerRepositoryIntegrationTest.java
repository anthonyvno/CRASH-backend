package com.realdolmen.EuropeanHub.insurer;

import java.util.List;
import junit.framework.Assert;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InsurerRepositoryIntegrationTest {
    
    @Autowired
    private InsurerRepository insurerRepository;
    
    @Autowired
    private TestEntityManager entityManager;

    
    @Test
    public void whenFindByCountryNotPresent_thenReturnEmpty(){
    Insurer agInsurer = new Insurer("AG","Germany");
    
    entityManager.persist(agInsurer);
    entityManager.flush();
    
    List<Insurer> found = insurerRepository.findByCountry("Belgium");
    Assert.assertFalse(found.contains(agInsurer));
    assertThat(found.size()).isEqualTo(0);
    }    
    
    @Test
    public void whenFindByCountry_thenReturnInsurer(){
    Insurer agInsurer = new Insurer("AG","Belgium");
    
    entityManager.persist(agInsurer);
    entityManager.flush();
    
    List<Insurer> found = insurerRepository.findByCountry("Belgium");
    Assert.assertTrue(found.contains(agInsurer));
    assertThat(found.size()).isEqualTo(1);
    }

}
