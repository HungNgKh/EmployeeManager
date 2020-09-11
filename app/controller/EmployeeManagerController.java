/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.Department;
import app.model.Employee;
import app.view.AddNewEmployee;
import app.view.EmployeeManagerPanel;
import app.view.EmployeeProfilePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import javafx.util.Pair;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 *
 * @author Admins
 */
public class EmployeeManagerController implements ActionListener{
    HashMap<String, Employee> employeesHM = new HashMap();
    private Employee temp_employee = null;
    private EmployeeManagerPanel view;
    
    public EmployeeManagerController(EmployeeManagerPanel employeeManagerView) {
        this.view = employeeManagerView;
        view.profilePanel.deleteBtn.addActionListener(this);
        view.profilePanel.cancelBtn.addActionListener(this);
        view.profilePanel.addNewBtn.addActionListener(this);
        view.profilePanel.editBtn.addActionListener(this);
        view.profilePanel.saveBtn.addActionListener(this);
        view.searchBtn.addActionListener(this);
        view.listEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableSelectListener(evt);
            }
        });
    }
    
    public void loadForm(EmployeeProfilePanel profile)
    {
        Department[] deps = Department.all();
        profile.clearComboBox();
        for(int i=0; i < deps.length; i++)
            profile.addComboBox(deps[i].ID()+"");
    }
    
    public void loadTable(Employee ems[])
    {
        employeesHM.clear();
        view.clearTable();
        for(int i=0; i < ems.length; i++) {
            String id = ""+ems[i].ID();
            String row[] = {i+1+"", id, ems[i].Name};
            employeesHM.put(id, ems[i]);
            view.addRow(row);
        }
    }
    
    private void tableSelectListener(java.awt.event.MouseEvent evt)
    {
        if(!view.profilePanel.editBtn.isVisible()) return;
        int row = view.listEmployee.getSelectedRow();
        String id = (String)view.listEmployee.getValueAt(row, 1);
        temp_employee = employeesHM.get(id).clone();
        view.profilePanel.setProfile(temp_employee);
    }
    
    private void CancelButtonClick()
    {
        view.profilePanel.setProfile(temp_employee);
        view.profilePanel.setProfileEditable(false);
        view.listEmployee.setEnabled(true);
    }
    
    private void EditButtonClick()
    {
        view.profilePanel.setProfileEditable(true);
        view.listEmployee.setEnabled(false);
    }
    
    private void DeleteButtonClick()
    {
        int yes_no = JOptionPane.showConfirmDialog(null, config.Config.DELETE_CONFIRM);
        if(yes_no !=0 || temp_employee == null) return;
        if(temp_employee.destroy())
        {
            JOptionPane.showMessageDialog(null, config.Config.DELETE_SUCCESS);
            int row = view.listEmployee.getSelectedRow();
            String id = (String)view.listEmployee.getValueAt(row, 1);
            view.deleteRow(row);
            employeesHM.remove(id);
            view.profilePanel.clearProfile();
            temp_employee = null;
            view.listEmployee.clearSelection();
        } else JOptionPane.showMessageDialog(null, config.Config.DELETE_ERROR);
    }
    
    private void DoneEditButtonClick()
    {
        temp_employee.Address = view.profilePanel.address.getText();
        temp_employee.BankAccount = view.profilePanel.bank.getText();
        temp_employee.DepartmentID = Integer.parseInt((String) view.profilePanel.listDepartment.getSelectedItem());
        temp_employee.Gender = view.profilePanel.gender.getSelectedIndex();
        temp_employee.IdentityCard = view.profilePanel.identity.getText();
        temp_employee.Name = view.profilePanel.employeeNameText.getText();
        temp_employee.PhoneNumber = view.profilePanel.phoneNumber.getText();
        temp_employee.Position = view.profilePanel.employeePosition.getText();
        temp_employee.Salary = Integer.parseInt(view.profilePanel.salary.getText());
        Date calendar = view.profilePanel.calendar.getDate();
        Date birthday = new Date(calendar.getYear()+1900, calendar.getMonth()+1, calendar.getDate());
        temp_employee.Birthday = birthday.getTime();
        if(temp_employee.update())
        {
            JOptionPane.showMessageDialog(null, config.Config.UPDATE_SUCCESS);
            view.profilePanel.setProfileEditable(false);
            view.listEmployee.setEnabled(true);
            int row = view.listEmployee.getSelectedRow();
            String id = (String)view.listEmployee.getValueAt(row, 1);
            employeesHM.remove(id);
            Employee temp_e = temp_employee.clone();
            employeesHM.put(id, temp_e);
            view.listEmployee.setValueAt(temp_e.Name, row, 2);
        } else JOptionPane.showMessageDialog(null, config.Config.UPDATE_ERROR);
    }
    
    private void AddNewButtonClick()
    {
        AddNewEmployee newForm = new AddNewEmployee((JFrame) SwingUtilities.getWindowAncestor(view), this);
        newForm.setVisible(true);
    }
    
    public void AfterSaveSuccess(AddNewEmployee newForm)
    {
        Employee employee = newForm.employee.clone();
        temp_employee = employee.clone();
        view.profilePanel.setProfile(temp_employee);
        String id = ""+temp_employee.ID();
        String row[] = {view.listEmployee.getRowCount()+1+"", id, employee.Name};
        employeesHM.put(id, employee);
        view.addRow(row);
    }
    
    private void SearchButtonClick(String s)
    {
        Pair p = null;
        if(view.employeeNameRadio.isSelected())
            p = new Pair("Name", s);
        if(view.employeeIdRadio.isSelected())
            p = new Pair("ID", s);
        loadTable(Employee.like(p));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        if(obj == view.profilePanel.editBtn)
            EditButtonClick();
        if(obj == view.profilePanel.cancelBtn)
            CancelButtonClick();
        if(obj == view.profilePanel.deleteBtn)
            DeleteButtonClick();
        if(obj == view.profilePanel.addNewBtn)
            AddNewButtonClick();
        if(obj == view.profilePanel.saveBtn)
            DoneEditButtonClick();
        if(obj == view.searchBtn)
            SearchButtonClick(view.getTextSearch());
    }
}
