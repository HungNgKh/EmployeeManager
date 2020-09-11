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

/**
 *
 * @author Admins
 */
public class Employee extends support.Record implements Cloneable{
    private int ID;
    public String Name;
    public int DepartmentID;
    public int Salary;
    public String Address;
    public int Gender;
    public long Birthday;
    public String BankAccount;
    public String IdentityCard;
    public String PhoneNumber;
    public String Position;
    
    public Employee(){}
    public Employee(int ID, String Name, int DepartmentID, int Salary, String Address, int Gender,
        long Birthday, String BankAccount, String IdentityCard, String PhoneNumber, String Position){
        this.ID = ID;
        this.Name = Name;
        this.DepartmentID = DepartmentID;
        this.Salary = Salary;
        this.Address = Address;
        this.Gender = Gender;
        this.Birthday = Birthday;
        this.BankAccount = BankAccount;
        this.IdentityCard = IdentityCard;
        this.PhoneNumber = PhoneNumber;
        this.Position = Position;
    }
    
    public int ID() { return ID; }

    @Override
    public boolean save() {
        String sql = "insert into Employee(Name, DepartmentID, Salary, Address, Gender, Birthday, BankAccount, IdentityCard"
            + ", PhoneNumber, Position) values('" + Name +"', '" + DepartmentID + "', '" + Salary + "', '" + Address +"', '"
            + Gender + "', '" + Birthday + "', '" + BankAccount + "', '" + IdentityCard + "', '" + PhoneNumber + "', '"
            + Position + "')";
        if(database.excuteDB(sql)) {
            try {
                ID = database.save_callback("Employee");
                return true;
            } catch(SQLException ex) { ex.printStackTrace(); return false; }
        } else return false;
    }

    @Override
    public boolean update() {
        String sql = "Update Employee set Name = '" + Name + "', DepartmentID = '" + DepartmentID + "', Salary = '" +
            Salary + "', Address = '" + Address + "', Gender = '" + Gender + "', Birthday = '" + Birthday +
            "', BankAccount = '" + BankAccount + "', IdentityCard = '" + IdentityCard + "', PhoneNumber = '" + PhoneNumber +
            "', Position = '" + Position + "' where ID = " + ID;
        return database.excuteDB(sql);
    }

    @Override
    public boolean destroy() {
        String sql = "Delete from Employee where ID = " + ID;
        return database.excuteDB(sql);
    }
    
    protected static Employee[] convertFromArrayList(ArrayList<Employee> ar)
    {
        Employee employee[] = new Employee[ar.size()];
        for(int i=0; i<ar.size(); i++)
            employee[i] = ar.get(i);
        return employee;
    }
    
    public static Employee find(int id)
    {
        Employee e = null;
        try {
            ResultSet set = support.DataBase.find("Employee", id);
            if(set.next())
                e = new Employee(set.getInt("ID"), set.getString("Name"), set.getInt("DepartmentID"),
                    set.getInt("Salary"), set.getString("Address"), set.getInt("Gender"), set.getLong("Birthday"),
                    set.getString("BankAccount"), set.getString("IdentityCard"), set.getString("PhoneNumber"), 
                    set.getString("Position"));
        } catch (SQLException ex) {}
        return e;
    }
    
    public static Employee[] find_by(Pair ...p)
    {
        try {
            ArrayList<Employee> ar = new ArrayList();
            ResultSet set = support.DataBase.find_by("Employee", p);
            while(set.next()) {
                Employee e = new Employee(set.getInt("ID"), set.getString("Name"), set.getInt("DepartmentID"),
                    set.getInt("Salary"), set.getString("Address"), set.getInt("Gender"), set.getLong("Birthday"),
                    set.getString("BankAccount"), set.getString("IdentityCard"), set.getString("PhoneNumber"), 
                    set.getString("Position"));
                ar.add(e);
            }
            return convertFromArrayList(ar);
        } catch (SQLException ex) { return new Employee[]{}; }
    }
    
    public static Employee[] all()
    {
        try {
            ArrayList<Employee> ar = new ArrayList();
            ResultSet set = support.DataBase.all("Employee");
            while(set.next()) {
                Employee e = new Employee(set.getInt("ID"), set.getString("Name"), set.getInt("DepartmentID"),
                    set.getInt("Salary"), set.getString("Address"), set.getInt("Gender"), set.getLong("Birthday"),
                    set.getString("BankAccount"), set.getString("IdentityCard"), set.getString("PhoneNumber"), 
                    set.getString("Position"));
                ar.add(e);
            }
            return convertFromArrayList(ar);
        } catch (SQLException ex) { return new Employee[]{}; }
    }
    
    public static Employee[] like(Pair ...p)
    {
        try {
            ArrayList<Employee> ar = new ArrayList();
            ResultSet set = support.DataBase.like("Employee", p);
            while(set.next()) {
                Employee e = new Employee(set.getInt("ID"), set.getString("Name"), set.getInt("DepartmentID"),
                    set.getInt("Salary"), set.getString("Address"), set.getInt("Gender"), set.getLong("Birthday"),
                    set.getString("BankAccount"), set.getString("IdentityCard"), set.getString("PhoneNumber"), 
                    set.getString("Position"));
                ar.add(e);
            }
            return convertFromArrayList(ar);
        } catch (SQLException ex) { return new Employee[]{}; }
    }
    
    public Department department()
    {
        String sql = "select * from Department inner join Employee on Department.ID = Employee.DepartmentID "
                + "where Employee.ID = " + ID;
        ResultSet set = database.select(sql);
        Department d = null;
        try{
            if(set.next()) 
                d = new Department(set.getInt(1), set.getString(2));
        } catch(SQLException ex) { return null; }
        return d;
    }
    
    public Project[] projects()
    {
        String sql = "select * from Project inner join Employee_Project inner join Employee " +
            "on Employee.ID = Employee_Project.EmployeeID and Employee_Project.ProjectID = Project.ID " +
            "where Employee.ID = " + ID;
        ArrayList<Project> ar = new ArrayList();
        ResultSet set = database.select(sql);
        try{
            while(set.next()) {
                Project p = new Project(set.getInt(1), set.getString(4), set.getString(5), 
                    set.getDate(2), set.getDate(3));
                ar.add(p);
            }
        } catch(SQLException ex) { return new Project[]{}; }
        return Project.convertFromArrayList(ar);
    }
    
    public static int count()
    {
        return support.DataBase.count("Employee");
    }
    
    public Employee clone() {
        try {
            return (Employee) super.clone();
        } catch (CloneNotSupportedException ex) { return null; }
    }
}
