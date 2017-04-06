/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de conexion con MySQL tipo DataSource.
 * @author      Kevin Montes De Oca V.
 * @version     %I%, %G%
 */
public class clsConexionDS {
    
    /**
     * Estable la conexion con el servidor MySQL con los
     * parámetros de conexion establecidos en la clase.
     * <p>
     * Este metodo retorna la conexion obtenida.
     * 
     * @author      Kevin Montes De Oca V.
     * @return      la conexion realizada con MySQL.
     */
    public static Connection AbrirConexion() {
        
        Connection conexion = null;
        try
        {
            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("127.0.0.1");
            ds.setPort(3306);
            ds.setDatabaseName("gestor_configuracion");
            conexion = ds.getConnection("root","");
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsConexionDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
        
    }
    
    /**
     * Cierra la conexion con MySQL.
     * <p>
     * @author      Kevin Montes De Oca V.
     * @param       xConexion       La conexion a cerrar.
     */
    public static void CerrarConexion(Connection xConexion) {
        
        try
        {
            xConexion.close();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsConexionDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
