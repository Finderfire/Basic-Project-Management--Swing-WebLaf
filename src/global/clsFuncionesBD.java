/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kevin
 */
public class clsFuncionesBD {
    
    public static String ObtenerUltimoIdInsertado(Connection xConexion)  throws Exception {
        
        ResultSet rs;
        String cod="";
        try
        {
            Statement statement = xConexion.createStatement();
            rs = statement.executeQuery("select last_insert_id() as 'id_insertado';"); 
            while(rs.next()){
                cod = rs.getString("id_insertado");
            }
            return cod;            
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
    
}
