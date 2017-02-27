package controller;

import gui.SideBarListViewGUI;
import model.ClientResources;
import model.DatabaseSetupTool;
import model.DrugResources;

/**
 *
 * @author joosiika
 */
public class SideBarListViewController implements SideBarListViewController_IF{
    private final String[] types = {"Patient", "Employee", "Drug", "Prescription", "User", "Database"};
    
    private SideBarListViewGUI gui;
    private ClientResources cres;
    private DrugResources dres;
    private DatabaseSetupTool dbtool;
    
    public SideBarListViewController(SideBarListViewGUI gui) {
        this.gui = gui;
        this.cres = new ClientResources();
        this.dres = new DrugResources();
        this.dbtool = new DatabaseSetupTool("patient");
    }

    @Override
    public void loadList(String type) {
        if (type.equals(types[0])) {
            this.gui.setList(this.cres.getPatients());
        }
        else if (type.equals(types[1])) {
            //not done
        }
        else if (type.equals(types[2])) {
            this.gui.setList(this.dres.getDrugs());
        }
        else if (type.equals(types[3])) {
            //not done
        }
        else if (type.equals(types[4])) {
            //not done
        }
        else if (type.equals(types[5])) {
            this.gui.setList(this.dbtool.getTypes());
        }
    }
    
}
