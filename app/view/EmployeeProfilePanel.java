/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import app.model.Department;
import app.model.Employee;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import config.Config;
import gui.widgets.ColorTransitionButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author Admins
 */
public class EmployeeProfilePanel extends JPanel implements ActionListener{

    public EmployeeProfilePanel() {
        loadComponent();
        setProfileEditable(false);
        setEnableButtons(false);
    }

    private void loadComponent() {
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder(config.Config.EMPLOYEE_INFORMATION));
        JPanel panel1 = new JPanel(null);
        JPanel panel2 = new JPanel(null);
        JPanel panel3 = new JPanel(null);
        JPanel panel4 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder());
        panel2.setBorder(BorderFactory.createTitledBorder(config.Config.DEPARTMENT));
        panel3.setBorder(BorderFactory.createTitledBorder(config.Config.INFORMATION));
        panel4.setBorder(BorderFactory.createTitledBorder(config.Config.SALARY));
        panel1.setBounds(Config.resize(new Rectangle(50, 25, 500, 70)));
        panel2.setBounds(Config.resize(new Rectangle(50, 100, 500, 90)));
        panel3.setBounds(Config.resize(new Rectangle(50, 190, 500, 170)));
        panel4.setBounds(Config.resize(new Rectangle(50, 360, 500, 80)));
        JPanel panel5 = new JPanel(null);
        panel5.setBounds(Config.resize(new Rectangle(50, 450, 500, 60)));
        panel5.setBorder(BorderFactory.createEtchedBorder());
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);

        JLabel emIDlb = new JLabel(config.Config.EMPLOYEE_ID + ":");
        emIDlb.setBounds(Config.resize(new Rectangle(10, 10, 80, 50)));
        JLabel emNamelb = new JLabel(config.Config.EMPLOYEE_NAME + ":");
        emNamelb.setBounds(Config.resize(new Rectangle(240, 10, 90, 50)));
        employeeIdText = new JTextField(); employeeIdText.setEditable(false);
        employeeIdText.setBounds(Config.resize(new Rectangle(95, 20, 120, 30)));
        employeeNameText = new JTextField();
        employeeNameText.setBounds(Config.resize(new Rectangle(330, 20, 160, 30)));
        panel1.add(emIDlb);
        panel1.add(emNamelb);
        panel1.add(employeeIdText);
        panel1.add(employeeNameText);

        JLabel departIDlb = new JLabel(config.Config.DEPARTMENT_ID + ":");
        departIDlb.setBounds(Config.resize(new Rectangle(60, 15, 70, 30)));
        JLabel postionIDlb = new JLabel(config.Config.POSITION + ":");
        postionIDlb.setBounds(Config.resize(new Rectangle(60, 50, 70, 30)));
        employeePosition = new JTextField();
        employeePosition.setBounds(Config.resize(new Rectangle(150, 50, 260, 30)));
        employeeDepartment = new JTextField(); employeeDepartment.setEditable(false);
        employeeDepartment.setBounds(Config.resize(new Rectangle(260, 15, 150, 30)));
        comboxModel = new DefaultComboBoxModel();
        listDepartment = new JComboBox(comboxModel);
        listDepartment.addActionListener(this);
        listDepartment.setBounds(Config.resize(new Rectangle(150, 15, 100, 30)));
        panel2.add(departIDlb);
        panel2.add(postionIDlb);
        panel2.add(listDepartment);
        panel2.add(employeeDepartment);
        panel2.add(employeePosition);

        JLabel genderlbl = new JLabel(config.Config.GENDER);
        genderlbl.setBounds(Config.resize(new Rectangle(30, 10, 80, 50)));
        gender = new JComboBox(new String[]{"Nam", "Nữ"});
        gender.setBounds(Config.resize(new Rectangle(90, 20, 80, 30)));
        JLabel birthlbl = new JLabel(config.Config.BIRTHDAY);
        birthlbl.setBounds(Config.resize(new Rectangle(230, 10, 80, 50)));
        calendar = new JDateChooser();
        calendar.setBounds(Config.resize(new Rectangle(300, 20, 170, 30)));
        panel3.add(calendar);
        JLabel addresslbl = new JLabel(config.Config.ADDRESS + ":");
        addresslbl.setBounds(Config.resize(new Rectangle(30, 60, 80, 50)));
        address = new JTextField();
        address.setBounds(Config.resize(new Rectangle(90, 70, 380, 30)));
        panel3.add(address);
        JLabel cmndlbl = new JLabel(config.Config.IDENTITY_NUMBER + ":");
        cmndlbl.setBounds(Config.resize(new Rectangle(30, 110, 80, 50)));
        identity = new JTextField();
        identity.setBounds(Config.resize(new Rectangle(90, 120, 120, 30)));
        panel3.add(identity);
        JLabel phonelbl = new JLabel(config.Config.PHONE_NUMBER + ":");
        phonelbl.setBounds(Config.resize(new Rectangle(230, 110, 80, 50)));
        phoneNumber = new JTextField();
        phoneNumber.setBounds(Config.resize(new Rectangle(300, 120, 170, 30)));
        panel3.add(genderlbl);
        panel3.add(gender);
        panel3.add(birthlbl);
        panel3.add(phoneNumber);
        panel3.add(addresslbl);
        panel3.add(cmndlbl);
        panel3.add(phonelbl);

        JLabel salarylbl = new JLabel(config.Config.SALARY_WEIGHT + ":");
        salarylbl.setBounds(Config.resize(new Rectangle(30, 25, 80, 50)));
        JLabel banklbl = new JLabel(config.Config.BANK_ACC + ":");
        banklbl.setBounds(Config.resize(new Rectangle(210, 25, 80, 50)));
        salary = new JTextField();
        salary.setBounds(Config.resize(new Rectangle(110, 35, 80, 30)));
        panel4.add(salary);
        bank = new JTextField();
        bank.setBounds(Config.resize(new Rectangle(300, 35, 170, 30)));
        panel4.add(bank);
        panel4.add(salarylbl);
        panel4.add(banklbl);

        deleteBtn = new ColorTransitionButton("Xóa");
        deleteBtn.setBounds(Config.resize(new Rectangle(10, 10, 100, 40)));
        panel5.add(deleteBtn);
        addNewBtn = new ColorTransitionButton("Thêm mới");
        addNewBtn.setBounds(Config.resize(new Rectangle(200, 10, 100, 40)));
        panel5.add(addNewBtn);
        editBtn = new ColorTransitionButton("Sửa");
        editBtn.setBounds(Config.resize(new Rectangle(390, 10, 100, 40)));
        panel5.add(editBtn);
        cancelBtn = new ColorTransitionButton("Hủy");
        cancelBtn.setBounds(Config.resize(new Rectangle(10, 10, 100, 40)));
        panel5.add(cancelBtn);
        saveBtn = new ColorTransitionButton("Lưu");
        saveBtn.setBounds(Config.resize(new Rectangle(390, 10, 100, 40)));
        panel5.add(saveBtn);
    }

    public void setProfileEditable(boolean status)
    {
        employeeNameText.setEditable(status);
        employeePosition.setEditable(status); address.setEditable(status);
        identity.setEditable(status); gender.setEnabled(status);
        phoneNumber.setEditable(status); salary.setEditable(status);
        bank.setEditable(status); listDepartment.setEnabled(status);
        cancelBtn.setVisible(status); saveBtn.setVisible(status);
        deleteBtn.setVisible(!status); addNewBtn.setVisible(!status); editBtn.setVisible(!status);

        ((JTextFieldDateEditor) calendar.getComponent(1)).setEditable(status);
        calendar.getCalendarButton().setEnabled(status);
    }

    public void setProfile(Employee e)
    {
        employeeIdText.setText(""+e.ID()); employeeNameText.setText(e.Name);
        employeePosition.setText(e.Position); employeeDepartment.setText(e.department().Name);
        address.setText(e.Address);
        identity.setText(e.IdentityCard); phoneNumber.setText(e.PhoneNumber);
        salary.setText(""+ e.Salary); bank.setText(e.BankAccount);
        gender.setSelectedIndex(e.Gender);
        listDepartment.setSelectedItem(e.DepartmentID+"");

        Date date = new Date(e.Birthday);
        int year = date.getYear()-1900;
        int month = date.getMonth()-1;
        calendar.setDate(new Date(year, month, date.getDate()));
        setProfileEditable(false);
        setEnableButtons(true);
    }

    public void clearProfile()
    {
        employeeIdText.setText(""); employeeNameText.setText("");
        employeePosition.setText(""); employeeDepartment.setText("");
        address.setText("");
        identity.setText(""); phoneNumber.setText("");
        salary.setText(""); bank.setText("");
        gender.setSelectedIndex(0);
        ((JTextFieldDateEditor) calendar.getComponent(1)).setText("");
        listDepartment.setSelectedIndex(0);
        setProfileEditable(false);
        setEnableButtons(false);
    }

    public void setEnableButtons(boolean status)
    {
        cancelBtn.setEnabled(status); saveBtn.setEnabled(status);
        deleteBtn.setEnabled(status); editBtn.setEnabled(status);
    }

    public void addComboBox(String element) { comboxModel.addElement(element); }
    public void clearComboBox() { comboxModel.removeAllElements(); }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == listDepartment)
        {
            String id = (String) listDepartment.getSelectedItem();
            Department d = Department.find(id);
            employeeDepartment.setText(d.Name);
        }
    }

    private DefaultComboBoxModel comboxModel;
    private JTextField employeeIdText;
    public JTextField employeeNameText;
    public JTextField employeePosition;
    private JTextField employeeDepartment;
    public JTextField address;
    public JTextField identity;
    public JTextField phoneNumber;
    public JTextField salary;
    public JTextField bank;
    public JComboBox listDepartment;
    public JComboBox gender;
    public JDateChooser calendar;

    public JButton cancelBtn;
    public JButton saveBtn;
    public JButton editBtn;
    public JButton addNewBtn;
    public JButton deleteBtn;
}
