/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.insurer;

/**
 *
 * @author SBZBN83
 */
public class InsurerNotFoundException extends RuntimeException {

	InsurerNotFoundException(int id) {
		super("Could not find insurer " + id);
	}
}
