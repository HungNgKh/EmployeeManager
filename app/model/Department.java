/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;
import support.DataBase;

/**
 *
 * @author Admins
 */
public class Department extends support.Record implements Cloneable{
    private int ID;
    public String Name;
    
    public Department() {}
    public Department(int ID, String Name)
    {
        this.ID = ID;
        this.Name = Name;
    }
    
    public int ID() { return ID; }

    @Override
    public boolean save() 
    {
        String sql = "insert into Department(Name) values('" + Name + "')";
        if(database.excuteDB(sql)) {
            try {
                ID = database.save_callback("Department");
                return true;
            } catch(SQLException ex) { return false; }
        } else return false;
    }

    @Override
    public boolean update() {
        String sql = "Update Department set Name = '" + Name + "' where ID = " + ID; 
        return database.excuteDB(sql);
    }

    @Override
    public boolean destroy() {
        String sql = "Delete from Department where ID = " + ID;
        return database.excuteDB(sql);
    }
    
    private static Department[] convertFromArrayList(ArrayList<Department> ar)
    {
        Department department[] = new Department[ar.size()];
        for(int i=0; i<ar.size(); i++)
            department[i] = ar.get(i);
        return department;
    }
    
    public static Department find(String id)
    {
        DataBase db = new DataBase();
        String sql = "select * from Department where ID = " + id;
        Department d = null;
        ResultSet set = db.select(sql);
        try {
            if(set.next())
                d = new Department(set.getInt("ID"), set.getString("Name"));
        } catch (SQLException ex) {}
        return d;
    }
    
    public static Department[] find_by(Pair ...p)
    {
        DataBase db = new DataBase();
        String sql = "select * from Department where ";
        ArrayList<Department> ar = new ArrayList();
        for(int i=0; i<p.length; i++) {
            sql += p[i].getKey() + " = '" + p[i].getValue()+"'";
            if(i != p.length-1) sql += " and ";
        }
        ResultSet set = db.select(sql);
        try {
            while(set.next()) {
                Department d = new Department(set.getInt("ID"), set.getString("Name"));
                ar.add(d);
            }
        } catch (SQLException ex) { return new Department[]{}; }
        return convertFromArrayList(ar);
    }
    
    public static Department[] all()
    {
        DataBase db = new DataBase();
        String sql = "select * from Department";
        ArrayList<Department> ar = new ArrayList();
        ResultSet set = db.select(sql);
        try {
            while(set.next()) {
                Department d = new Department(set.getInt("ID"), set.getString("Name"));
                ar.add(d);
            }
        } catch (SQLException ex) { return new Department[]{}; }
        return convertFromArrayList(ar);
    }
    
    public Employee[] employees()
    {
        String sql = "select * from Employee inner join Department on Employee.DepartmentID = Department.ID " +
            "where Department.ID = " + ID;
        ArrayList<Employee> ar = new ArrayList();
        ResultSet set = database.select(sql);
        try{
            while(set.next()) {
                Employee e = new Employee(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4), set.getString(5), 
                    set.getInt(6), set.getLong(7), set.getString(8), set.getString(9), set.getString(10), set.getString(11));
                ar.add(e);
            }
        } catch(SQLException ex) { return new Employee[]{}; }
        return Employee.convertFromArrayList(ar);
    }
    
    public static int count()
    {
        return DataBase.count("Department");
    }

    public Department clone() {
        try {
            return (Department) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
