/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.util.Pair;

/**
 *
 * @author Admins
 */
public class DataBase {
    private Connection con;
    private Statement state;
    private ResultSet resultSet;
    private PreparedStatement prstate;
    
    public DataBase(){
        con = config.Config.connection;
    }
    
    public static Connection ConnectDB(String db)
    {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + db);
            return con;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean excuteDB(String sql)
    {
        try {
            state = con.createStatement();
            state.execute(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public ResultSet select(String sql)
    {
        try{
            prstate = con.prepareStatement(sql);
            resultSet = prstate.executeQuery();
            return resultSet;
        } catch(SQLException ex) { 
            return null; 
        }
    }
    
    public ResultSet select(String sql, int limit, int offset)
    {
        return select(sql + " limit " + limit + " offset " + offset);
    }
    
    public int save_callback(String table) throws SQLException
    {
        String sql = "SELECT ID FROM "+ table + " ORDER BY ID DESC LIMIT 1";
        ResultSet set = select(sql);
        return set.getInt("ID");
    }
    
    public static int count(String table)
    {
        String sql = "select count() from " + table;
        try {
            PreparedStatement prstate = config.Config.connection.prepareStatement(sql);
            ResultSet set = prstate.executeQuery();
            return set.getInt("count()");
        } catch(SQLException ex) { return 0; }
    }
    
    public static ResultSet like(String table, Pair ...p) throws SQLException
    {
        String sql = "select * from " + table + " where ";
        for(int i=0; i<p.length; i++) {
            sql += p[i].getKey() + " like '%" + p[i].getValue()+"%'";
            if(i != p.length-1) sql += " and ";
        }
        return config.Config.connection.prepareStatement(sql).executeQuery();
    }
    
    public static ResultSet all(String table) throws SQLException
    {
        String sql = "select * from " + table;
        return config.Config.connection.prepareStatement(sql).executeQuery();
    }
    
    public static ResultSet find_by(String table, Pair ...p) throws SQLException
    {
        String sql = "select * from "+ table+ " where ";
        for(int i=0; i<p.length; i++) {
            sql += p[i].getKey() + " = '" + p[i].getValue()+"'";
            if(i != p.length-1) sql += " and ";
        }
        return config.Config.connection.prepareStatement(sql).executeQuery();
    }
    
    public static ResultSet find(String table, int id) throws SQLException
    {
        String sql = "select * from "+ table + " where ID = " + id;
        return config.Config.connection.prepareStatement(sql).executeQuery();
    }
}
