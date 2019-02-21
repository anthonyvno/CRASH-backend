/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.AanrijdingsformulierRestURIConstants.*;
import domain.Aanrijdingsformulier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AVOBN94
 */
@RestController
public class AanrijdingsformulierController {
    
    
     //Map to store formekeraars, ideally we should use database
    Map<Integer, Aanrijdingsformulier> formData = new HashMap<>();

    @RequestMapping(value = DUMMY_FORM, method = RequestMethod.GET)
    public Aanrijdingsformulier getDummyAanrijdingsformulier() {
        Aanrijdingsformulier form = new Aanrijdingsformulier();
        form.setId(9999);
        form.setStraat("lange straat");
        form.setHuisnummer("5");
        form.setPostcode("9300");
        form.setGewonden(true);
        form.setVerzekeraarAId(9999);
        form.setVerzekeraarBId(123);
        form.setToedrachtA(new boolean[]{true,false});
        form.setToedrachtB(new boolean[]{true,false});
        form.setLand("België");
        formData.put(9999, form);
        
        Aanrijdingsformulier form2 = new Aanrijdingsformulier();
        form2.setId(111);
        form2.setStraat("korte straat");
        form2.setHuisnummer("5");
        form2.setPostcode("9300");
        form2.setGewonden(true);
        form2.setVerzekeraarAId(1);
        form2.setVerzekeraarBId(123);
        form2.setToedrachtA(new boolean[]{true,false});
        form2.setToedrachtB(new boolean[]{true,false});
        form2.setLand("België");
        formData.put(111, form2);
        
        Aanrijdingsformulier form3 = new Aanrijdingsformulier();
        form3.setId(5000);
        form3.setStraat("dikke straat");
        form3.setHuisnummer("5");
        form3.setPostcode("9300");
        form3.setGewonden(true);
        form3.setVerzekeraarAId(9999);
        form3.setVerzekeraarBId(9999);
        form3.setToedrachtA(new boolean[]{true,false});
        form3.setToedrachtB(new boolean[]{true,false});
        form3.setLand("België");
        formData.put(5000, form3);
        return form3;
    }

    @RequestMapping(value = GET_FORM, method = RequestMethod.GET)
    public Aanrijdingsformulier getAanrijdingsformulier(@PathVariable("id") int formId) {
        return formData.get(formId);
    }

    @RequestMapping(value = GET_ALL_FORM, method = RequestMethod.GET)
    public List<Aanrijdingsformulier> getAllAanrijdingsformuliers() {
        List<Aanrijdingsformulier> forms = new ArrayList<>();
        Set<Integer> formIdKeys = formData.keySet();
        for (Integer i : formIdKeys) {
            forms.add(formData.get(i));
        }
        return forms;
    }
    
    
    @RequestMapping(value = GET_VERZ_ALL_FORM, method = RequestMethod.GET)
    public List<Aanrijdingsformulier> getAllAanrijdingsformuliersVerzekeraar(@PathVariable("id") int verzId) {
        List<Aanrijdingsformulier> forms = new ArrayList<>();
        Set<Integer> formIdKeys = formData.keySet();
        for (Integer i : formIdKeys) {
            Aanrijdingsformulier af = formData.get(i);
            if(af.getVerzekeraarAId()== verzId || af.getVerzekeraarBId() == verzId){
                forms.add(af);
            }
        }
        return forms;
    }

    @RequestMapping(value = CREATE_FORM, method = RequestMethod.POST)
    public Aanrijdingsformulier createAanrijdingsformulier(Aanrijdingsformulier form) {
        formData.put(form.getId(), form);
        return form;
    }

    @RequestMapping(value = DELETE_FORM, method = RequestMethod.PUT)
    public Aanrijdingsformulier deleteAanrijdingsformulier(@PathVariable("id") int formId) {
        Aanrijdingsformulier form = formData.get(formId);
        formData.remove(formId);
        return form;
    }
    
}
