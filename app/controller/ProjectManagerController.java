/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.Project;
import app.view.ProjectManagerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javafx.util.Pair;

/**
 *
 * @author Admins
 */
public class ProjectManagerController implements ActionListener{
    HashMap<String, Project> projectsHM = new HashMap();
    private ProjectManagerPanel view;
    private Project project_temp = null;
    
    public ProjectManagerController(ProjectManagerPanel projectManagerView)
    {
        this.view = projectManagerView;
        view.inforPanel.addNewBtn.addActionListener(this);
        view.inforPanel.cancelBtn.addActionListener(this);
        view.inforPanel.deleteBtn.addActionListener(this);
        view.inforPanel.editBtn.addActionListener(this);
        view.inforPanel.saveBtn.addActionListener(this);
        view.searchBtn.addActionListener(this);
    }
    
    public void loadTable(Project[] prs)
    {
        projectsHM.clear();
        view.clearTable();
        for(int i=0; i < prs.length; i++) {
            String id = ""+prs[i].ID();
            String row[] = {i+1+"", id, prs[i].Name};
            projectsHM.put(id, prs[i]);
            view.addRow(row);
        }
    }
    
    public void SearchButtonClick(String s)
    {
        Pair p = null;
        if(view.projectIdRadio.isSelected())
            p = new Pair("ID", s);
        if(view.projectNameRadio.isSelected())
            p = new Pair("Name", s);
        loadTable(Project.like(p));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
//        if(obj == view.profilePanel.editBtn)
//            EditButtonClick();
//        if(obj == view.profilePanel.cancelBtn)
//            CancelButtonClick();
//        if(obj == view.profilePanel.deleteBtn)
//            DeleteButtonClick();
//        if(obj == view.profilePanel.addNewBtn)
//            AddNewButtonClick();
//        if(obj == view.profilePanel.saveBtn)
//            DoneEditButtonClick();
        if(obj == view.searchBtn)
            SearchButtonClick(view.getTextSearch());
    }
}
