/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.profile;


public class VehicleNotFoundException extends RuntimeException{
    
   VehicleNotFoundException(int id) {
		super("Could not find profile " + id);
	}
    
}
