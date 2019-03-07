/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.profile;

/**
 *
 * @author AVOBN94
 */
public class LicenseNotFoundException extends RuntimeException{
    
    LicenseNotFoundException(int id) {
		super("Could not find profile " + id);
	}
    
}
