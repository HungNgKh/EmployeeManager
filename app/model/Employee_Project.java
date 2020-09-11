/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.SQLException;

/**
 *
 * @author Admins
 */
public class Employee_Project extends support.Record implements Cloneable{
    private int ID;
    public int ProjectID;
    public int EmployeeID;
    
    public int ID() { return ID; }
    
    @Override
    public boolean save() {
        String sql = "insert into Employee_Project(ProjectID, EmployeeID) values('" + ProjectID + "', '" 
            +EmployeeID +"')";
        if(database.excuteDB(sql))
        {
            try {
                ID = database.save_callback("Employee_Project");
                return true;
            } catch(SQLException ex) { return false; }
        }
        else return false;
    }

    @Override
    public boolean update() {
        String sql = "Update Employee_Project set ProjectID = '" + ProjectID + "', EmployeeID = '" + 
            EmployeeID + "' where ID = " + ID; 
        return database.excuteDB(sql);
    }

    @Override
    public boolean destroy() {
        String sql = "Delete from Employee_Project where ID = " + ID;
        return database.excuteDB(sql);
    }

    public Employee_Project clone() {
        try {
            return (Employee_Project) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
