/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.prescription;

import resources.user.User_IF;

/**
 *
 * @author joosiika
 */
public interface PrescriptionFactory_IF {
    public abstract Prescription create(User_IF creator);
}
