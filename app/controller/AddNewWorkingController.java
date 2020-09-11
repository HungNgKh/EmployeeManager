/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.Employee;
import app.model.Project;
import app.view.NewWorkingPanel;
import java.util.HashMap;

/**
 *
 * @author Admins
 */
public class AddNewWorkingController {
    NewWorkingPanel domView;
    HashMap<Object[], Employee> employeesHM = new HashMap();
    HashMap<Object[], Project> projectHM = new HashMap();
    
    public AddNewWorkingController(NewWorkingPanel newWorkingView) {
        domView = newWorkingView;
        init();
    }
    
    private void init() 
    {
        domView.clearTable("Employee");
        Employee[] ems = Employee.all();
        for(int i=0; i < ems.length; i++) {
            Object row[] = {i+1+"", "AAAAXXXX", ems[i].Name, false};
            domView.addRow("Employee", row);
            employeesHM.put(row, ems[i]);
        }
        domView.clearTable("Project");
        Project[] pros = Project.all();
        for(int i=0; i < pros.length; i++) {
            Object row[] = {i+1+"", "AAAAXXXX", pros[i].Name};
            domView.addRow("Project", row);
            projectHM.put(row, pros[i]);
        }
    }
}
