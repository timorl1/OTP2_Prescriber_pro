/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finder;

import java.util.ArrayList;
import java.util.List;
import resources.client.Diagnose;
import resources.client.DiagnoseDAO;
import resources.client.DiagnoseDAO_IF;
import resources.client.Patient;


public class DiagnosePoller implements DiagnosePoller_IF {
    private DiagnoseDAO_IF diagnoseDAO;
    private Patient owner;
    private List<Integer> diagnoseIDs;

    public DiagnosePoller(Patient owner) {
        this.diagnoseDAO = new DiagnoseDAO();
        this.owner = owner;
        this.owner.getDiagnoses().forEach(d -> this.diagnoseIDs.add(d.getId()));
    }

    @Override
    public boolean hasNewDiagnose() {
        //Tee parempi tsekkaus PatientDAO:hon. Mielummin tee DiagnoseDAO ja sinne se tsekkaus.
        return this.diagnoseIDs.size() < this.diagnoseDAO.readPatientDiagnoses(this.owner).size();
    }

    @Override
    public List<Diagnose> getNewDiagnoses() {
        List<Diagnose> newDiagnoses = new ArrayList();
        for (Diagnose diagnose : this.diagnoseDAO.readPatientDiagnoses(this.owner)) {
            if (!this.diagnoseIDs.contains(diagnose.getId())) {
                newDiagnoses.add(diagnose);
            }
        }
        return newDiagnoses;
    }
    
}
