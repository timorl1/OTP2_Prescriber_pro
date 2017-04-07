/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import resources.client.Diagnose;
import resources.client.Patient;
import resources.client.PatientDAO;
import resources.client.PatientDAO_IF;


public class DiagnosePoller implements DiagnosePoller_IF {
    private PatientDAO_IF patientDAO;
    private Patient owner;
    private List<Integer> diagnoseIDs;

    public DiagnosePoller(Patient owner) {
        this.patientDAO = new PatientDAO();
        this.owner = owner;
        this.owner.getDiagnoses().forEach(d -> this.diagnoseIDs.add(d.getId()));
    }

    @Override
    public boolean hasNewDiagnose() {
        //Tee parempi tsekkaus PatientDAO:hon. Mielummin tee DiagnoseDAO ja sinne se tsekkaus.
        return this.diagnoseIDs.size() < this.patientDAO.readPatientDiagnoses(this.owner).size();
    }

    @Override
    public List<Diagnose> getNewDiagnoses() {
        List<Diagnose> newDiagnoses = new ArrayList();
        for (Diagnose diagnose : this.patientDAO.readPatientDiagnoses(this.owner)) {
            if (!this.diagnoseIDs.contains(diagnose.getId())) {
                newDiagnoses.add(diagnose);
            }
        }
        return newDiagnoses;
    }
    
}
