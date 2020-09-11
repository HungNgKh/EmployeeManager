/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

/**
 *
 * @author Admins
 */
public abstract class Record{
    protected DataBase database;
    
    public Record()
    {
        database = new DataBase();
    }
    
    public abstract boolean save();
    public abstract boolean update();
    public abstract boolean destroy();
}
