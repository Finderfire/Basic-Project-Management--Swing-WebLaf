/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vistacontrol.frmCambio;

/**
 *
 * @author Kevin
 */
public class clsCambioDAO {
    
    public ResultSet SeleccionarSolicitudCambio(Connection xConexion) {
        
        ResultSet rsResultado = null;
        PreparedStatement st;
        
        try 
        {
            st = xConexion.prepareStatement("select s.* , p.proyecto_nombre,\n" 
                    +"DATE_FORMAT(s.solicitud_fecha, '%d/%m/%Y') as "
                    + "'solicitud_fecha_format' from solicitud as s inner join "
                    + "proyecto as p on s.proyecto_id = p.proyecto_id "
                    + "order by s.solicitud_fecha");
            rsResultado = st.executeQuery();
        }
        catch (SQLException ex) 
        {                   
            Logger.getLogger(clsCambioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsResultado;
    }
    
    public ResultSet SeleccionarSolicitudCambioDerivada(Connection xConexion) {
        
        ResultSet rsResultado = null;
        PreparedStatement st;
        
        try 
        {
            st = xConexion.prepareStatement("select s.* , p.proyecto_nombre, \n" 
                    + "DATE_FORMAT(s.solicitud_fecha, '%d/%m/%Y') as 'solicitud_fecha_format',\n" 
                    + "e.evaluacion_tipo, e.evaluacion_descripcion_encargado, e.evaluacion_estado, \n" 
                    + "e.evaluacion_id from solicitud as s inner join proyecto as p \n" 
                    + "on s.proyecto_id = p.proyecto_id \n" 
                    + "inner join evaluacion as e\n" 
                    + "on s.solicitud_id = e.solicitud_id\n" 
                    + "where e.evaluacion_estado = '0'\n" 
                    + "order by s.solicitud_fecha");
            rsResultado = st.executeQuery();
        }
        catch (SQLException ex) 
        {                     
            Logger.getLogger(clsCambioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsResultado;
    }
    
    
}
