/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import app.controller.EmployeeManagerController;
import app.model.Employee;
import config.Config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author Admins
 */
public class AddNewEmployee extends JDialog implements ActionListener{
    private EmployeeProfilePanel profile;
    private EmployeeManagerController controller;
    public Employee employee = new Employee();
    
    public AddNewEmployee(JFrame frame, EmployeeManagerController controller) {
        super(frame);
        loadComponent();
        this.controller = controller;
        this.controller.loadForm(profile);
    }
    
    private void loadComponent()
    {
        profile = new EmployeeProfilePanel();
        setSize(Config.resize(578), Config.resize(557));
        setTitle(config.Config.ADD_NEW_EMPLOYEE);
        add(profile);
        profile.setProfileEditable(true);
        profile.setEnableButtons(true);
        setLocationRelativeTo(null);
        profile.cancelBtn.addActionListener(this);
        profile.saveBtn.addActionListener(this);
    }
    
    private void SaveButtonClick()
    {
        employee.Address = profile.address.getText();
        employee.BankAccount = profile.bank.getText();
        employee.DepartmentID = Integer.parseInt((String) profile.listDepartment.getSelectedItem());
        employee.Gender = profile.gender.getSelectedIndex();
        employee.IdentityCard = profile.identity.getText();
        employee.Name = profile.employeeNameText.getText();
        employee.PhoneNumber = profile.phoneNumber.getText();
        employee.Position = profile.employeePosition.getText();
        employee.Salary = Integer.parseInt(profile.salary.getText());
        Date calendar = profile.calendar.getDate();
        Date birthday = new Date(calendar.getYear()+1900, calendar.getMonth()+1, calendar.getDate());
        employee.Birthday = birthday.getTime();
        if(employee.save())
        {
            controller.AfterSaveSuccess(this);
            JOptionPane.showMessageDialog(null, config.Config.SAVE_SUCCESS);
            this.dispose();
        } else JOptionPane.showMessageDialog(null, config.Config.SAVE_ERROR);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == profile.cancelBtn)
            this.dispose();
        if(ae.getSource() == profile.saveBtn)
            SaveButtonClick();
    }
}
