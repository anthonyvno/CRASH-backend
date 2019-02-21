/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tverzlate file, choose Tools | Tverzlates
 * and open the tverzlate in the editor.
 */
package controller;

import static controller.VerzekeraarRestURIConstants.*;
import domain.Verzekeraar;
import java.util.ArrayList;
import java.util.Date;
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
public class VerzekeraarController {
    
    //Map to store verzekeraars, ideally we should use database
    Map<Integer, Verzekeraar> verzData = new HashMap<>();

    @RequestMapping(value = DUMMY_VERZ, method = RequestMethod.GET)
    public Verzekeraar getDummyVerzekeraar() {
        Verzekeraar verz = new Verzekeraar();
        verz.setId(9999);
        verz.setNaam("AG Insurance");
        verz.setLand("België");
        verzData.put(9999, verz);
        return verz;
    }

    @RequestMapping(value = GET_VERZ, method = RequestMethod.GET)
    public Verzekeraar getVerzekeraar(@PathVariable("id") int verzId) {
        return verzData.get(verzId);
    }

    @RequestMapping(value = GET_ALL_VERZ, method = RequestMethod.GET)
    public List<Verzekeraar> getAllVerzekeraars() {
        List<Verzekeraar> verzs = new ArrayList<>();
        Set<Integer> verzIdKeys = verzData.keySet();
        for (Integer i : verzIdKeys) {
            verzs.add(verzData.get(i));
        }
        return verzs;
    }

    @RequestMapping(value = CREATE_VERZ, method = RequestMethod.POST)
    public Verzekeraar createVerzekeraar(Verzekeraar verz) {
        verzData.put(verz.getId(), verz);
        return verz;
    }

    @RequestMapping(value = DELETE_VERZ, method = RequestMethod.PUT)
    public Verzekeraar deleteVerzekeraar(@PathVariable("id") int verzId) {
        Verzekeraar verz = verzData.get(verzId);
        verzData.remove(verzId);
        return verz;
    }
    
    
}
