/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import static app.model.Employee.convertFromArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javafx.util.Pair;

/**
 *
 * @author Admins
 */
public class Project extends support.Record implements Cloneable{
    private int ID;
    public Date DateStart;
    public Date DateFinish;
    public String Name;
    public String Description;
    
    public Project(){}
    public Project(int ID, String Name, String Description, Date DateStart, Date DateFinish){
        this.ID = ID;
        this.DateFinish = DateFinish;
        this.DateStart = DateStart;
        this.Name = Name;
        this.Description = Description;
    }
    
    public int ID() { return ID; }
    
    @Override
    public boolean save() {
        String sql = "insert into Project(Name, DateStart, DateFinish, Description) values('" + Name + "', '" 
            +DateStart +"', '" + DateFinish +"' ,'" + Description +"')";
        if(database.excuteDB(sql)) {
            try {
                ID = database.save_callback("Project");
                return true;
            } catch(SQLException ex) { return false; }
        } else return false;
    }

    @Override
    public boolean update() {
        String sql = "Update Project set Name = '" + Name + "' where ID = " + ID; 
        return database.excuteDB(sql);
    }

    @Override
    public boolean destroy() {
        String sql = "Delete from Project where ID = " + ID;
        return database.excuteDB(sql);
    }
    
    protected static Project[] convertFromArrayList(ArrayList<Project> ar)
    {
        Project project[] = new Project[ar.size()];
        for(int i=0; i<ar.size(); i++)
            project[i] = ar.get(i);
        return project;
    }
    
    public static Project[] find_by(Pair ...p)
    {
        try {
            ArrayList<Project> ar = new ArrayList();
            ResultSet set = support.DataBase.find_by("Project", p);
            while(set.next()) {
                Project d = new Project(set.getInt("ID"), set.getString("Name"), set.getString("Description"),
                    set.getDate("DateStart"), set.getDate("DateFinish"));
                ar.add(d);
            }
            return convertFromArrayList(ar);
        } catch (SQLException ex) { return new Project[]{}; }
    }
    
    public static Project find(int id)
    {
        Project p = null;
        try {
            ResultSet set = support.DataBase.find("Project", id);
            if(set.next())
                p = new Project(set.getInt("ID"), set.getString("Name"), set.getString("Description"),
                    set.getDate("DateStart"), set.getDate("DateFinish"));
        } catch (SQLException ex) {}
        return p;
    }
    
    public static Project[] like(Pair ...p)
    {
        try {
            ArrayList<Project> ar = new ArrayList();
            ResultSet set = support.DataBase.like("Project", p);
            while(set.next()) {
                Project pr = new Project(set.getInt("ID"), set.getString("Name"), set.getString("Description"),
                    set.getDate("DateStart"), set.getDate("DateFinish"));
                ar.add(pr);
            }
            return convertFromArrayList(ar);
        } catch (SQLException ex) { return new Project[]{}; }
    }
    
    public static Project[] all()
    {
        try {
            ArrayList<Project> ar = new ArrayList();
            ResultSet set = support.DataBase.all("Project");
            while(set.next()) {
                Project d = new Project(set.getInt("ID"), set.getString("Name"), set.getString("Description"),
                    set.getDate("DateStart"), set.getDate("DateFinish"));
                ar.add(d);
            }
            return convertFromArrayList(ar);
        } catch (SQLException ex) { return new Project[]{}; }
    }
    
    public Employee[] employees()
    {
        String sql = "select * from Employee inner join Employee_Project inner join Project " +
            "on Employee.ID = Employee_Project.EmployeeID and Employee_Project.ProjectID = Project.ID " +
            "where Project.ID = " + ID;
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
        return support.DataBase.count("Project");
    }

    public Project clone() { 
        try { 
            return (Project) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
