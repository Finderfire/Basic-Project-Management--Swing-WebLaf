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
import transferenciadatos.clsEvaluacionDTO;

/**
 *
 * @author Kevin
 */
public class clsEvaluacionDAO {
    
    public void CrearEvaluacion(clsEvaluacionDTO xEvaluacionDTO, Connection xConexion) {
        
        try 
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into evaluacion "
                    + "(evaluacion_tipo, evaluacion_descripcion_encargado, "
                    + "evaluacion_descripcion_jefe, evaluacion_estado, solicitud_id)\n" 
                    + "values (?, ?, ?, ?, ? )");
            st.setString(1, xEvaluacionDTO.getEvaluacion_tipo());
            st.setString(2, xEvaluacionDTO.getEvaluacion_descripcion_encargado());
            st.setString(3, xEvaluacionDTO.getEvaluacion_descripcion_jefe());
            st.setString(4, xEvaluacionDTO.getEvaluacion_estado());
            st.setString(5, xEvaluacionDTO.getSolicitud_id());
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error al procesar la solicitud. \n "+ ex, "Error", 
                                        JOptionPane.ERROR_MESSAGE);     
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ActualizarEvaluacion(clsEvaluacionDTO xEvaluacionDTO, Connection xConexion) {
        
        try 
        {            
            PreparedStatement st = xConexion.prepareStatement("update evaluacion as e set\n" 
                                + "e.evaluacion_tipo = ?,\n" 
                                + "e.evaluacion_descripcion_encargado = ?,\n" 
                                + "e.evaluacion_descripcion_jefe = ?,\n" 
                                + "e.evaluacion_estado = ?\n" 
                                + "where e.evaluacion_id = ?");
            st.setString(1, xEvaluacionDTO.getEvaluacion_tipo());
            st.setString(2, xEvaluacionDTO.getEvaluacion_descripcion_encargado());
            st.setString(3, xEvaluacionDTO.getEvaluacion_descripcion_jefe());
            st.setString(4, xEvaluacionDTO.getEvaluacion_estado());
            st.setString(5, xEvaluacionDTO.getEvaluacion_id());
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error al procesar la solicitud. \n "+ ex, "Error", 
                                        JOptionPane.ERROR_MESSAGE);     
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ResultSet SeleccionarCambioFinalizado(Connection xConexion) throws SQLException {
        
        ResultSet rsResultado;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select s.solicitud_id, "
                    + "p.proyecto_nombre, s.solicitud_cliente, s.solicitud_objetivo, \n" 
                    + "s.solicitud_descripcion, e.evaluacion_tipo, "
                    + "e.evaluacion_descripcion_encargado, e.evaluacion_descripcion_jefe,"
                    + "DATE_FORMAT(s.solicitud_fecha, '%d/%m/%Y') as 'solicitud_fecha'\n" 
                    + "from solicitud as s inner join evaluacion as e "
                    + "on s.solicitud_id = e.solicitud_id inner join proyecto as p "
                    + "on s.proyecto_id = p.proyecto_id order by e.evaluacion_estado");
            
            rsResultado = st.executeQuery();            
            return rsResultado;
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        
    }
    
}
