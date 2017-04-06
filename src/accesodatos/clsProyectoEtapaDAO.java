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

/**
 *
 * @author Kevin
 */
public class clsProyectoEtapaDAO {
    
    public ResultSet SeleccionarProyectoEtapa(String xCodigoProyecto, Connection xConexion) throws Exception{
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select distinct "
                    + "e.etapa_id, e.etapa_nombre, e.etapa_descripcion, "
                    + "e.metodologia_id from etapa as e inner join metodologia as m\n" 
                    + "on e.metodologia_id = m.metodologia_id\n" 
                    + "inner join entregable as en on en.etapa_id = e.etapa_id\n" 
                    + "inner join proyectoentregable as pe on pe.entregable_id = "
                    + "en.entregable_id where pe.proyecto_id = (?) ");
            st.setString(1, xCodigoProyecto);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    } 
    
}
