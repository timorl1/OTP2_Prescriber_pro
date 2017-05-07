/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.prescription;

import java.sql.Date;
import java.time.LocalDate;
import resources.user.User_IF;

public class PrescriptionFactory implements PrescriptionFactory_IF {

    @Override
    public Prescription create(User_IF creator) {
        Prescription prescription = new Prescription();
        prescription.setDoctor(creator);
        prescription.setCreationDate(Date.valueOf(LocalDate.now()));
        return prescription;
    }
}
