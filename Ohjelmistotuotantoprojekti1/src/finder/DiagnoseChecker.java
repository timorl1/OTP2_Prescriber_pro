/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.prescription.Prescription;
import resources.diagnose.Diagnose;
import resources.diagnose.DiagnoseDAO;
import resources.diagnose.DiagnoseDAO_IF;
import resources.patient.Patient;

public class DiagnoseChecker extends Observable implements DiagnoseChecker_IF, Runnable {
    private DiagnoseDAO_IF diagnoseDAO;
    private AdverseEffectFinder_IF adverseEffectFinder;
    private Prescription prescription;
    private Patient patient;
    private List<Integer> diagnoseIDs;

    public DiagnoseChecker(Prescription prescription) {
        this.diagnoseDAO = new DiagnoseDAO();
        this.adverseEffectFinder = new AdverseEffectFinder();
        this.prescription = prescription;
        this.prescription.getPatient().getDiagnoses().forEach(d -> this.diagnoseIDs.add(d.getId()));
    }

    @Override
    public boolean hasNewDiagnose() {
        return this.diagnoseIDs.size() < this.diagnoseDAO.getPatientDiagnoseCount(this.prescription.getPatient());
    }

    @Override
    public List<Diagnose> getNewDiagnoses() {
        List<Diagnose> newDiagnoses = new ArrayList();
        for (Diagnose diagnose : this.diagnoseDAO.readPatientDiagnoses(this.prescription.getPatient())) {
            if (!this.diagnoseIDs.contains(diagnose.getId())) {
                newDiagnoses.add(diagnose);
            }
        }
        return newDiagnoses;
    }
    
    @Override
    public void performCheck() {
        List<Diagnose> matches = this.adverseEffectFinder.findMatch(prescription);
        if (matches.size() > 0) {
            this.setChanged();
            this.notifyObservers(matches);
        }
    }

    @Override
    public void run() {
        synchronized(this) {
            while(true) {
                if (this.hasNewDiagnose()) {
                    this.performCheck();
                }
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DiagnoseChecker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
