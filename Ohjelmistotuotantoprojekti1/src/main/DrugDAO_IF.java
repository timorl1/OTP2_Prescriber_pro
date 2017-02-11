/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.SQLException;

/**
 *
 * @author joosiika
 */
public interface DrugDAO_IF {
    public abstract Drug readDrug(String SN) throws SQLException;
    public abstract Drugs readDrugs() throws SQLException;
}
