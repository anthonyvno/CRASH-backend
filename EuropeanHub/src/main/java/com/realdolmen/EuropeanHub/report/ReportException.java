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
public class ReportException extends RuntimeException {

	ReportException(int id) {
		super("Could not find report " + id);
	}
}
