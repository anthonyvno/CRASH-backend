/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.insurer;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "insurers", collectionResourceRel = "insurers")
public interface InsurerRepository extends PagingAndSortingRepository<Insurer, Integer>{
    
        List<Insurer> findByCountry(@Param("country") String country);
    
}
