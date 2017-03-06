/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author joosiika
 */
public interface DiagnoseBuilder_IF {
    public abstract Patient getDiagnosePatient(Diagnose diagnose);
    public abstract Doctor getDiagnoseDoctor(Diagnose diagnose);
    public abstract Disease getDiagnoseDisease(Diagnose diagnose);
    public abstract Diagnose buildDiagnose(Diagnose diagnose);
}
