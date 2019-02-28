/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.report;

/**
 *
 * @author SBZBN83
 */
public class ReportNotFoundException extends RuntimeException {

	ReportNotFoundException(int id) {
		super("Could not find report " + id);
	}
}
