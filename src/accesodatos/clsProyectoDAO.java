/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transferenciadatos.clsProyectoDTO;

/**
 *
 * @author Kevin
 */
public class clsProyectoDAO {
    
    /**
     * inserta un nuevo proyecto en la Base de datos.
     * @param xProyectoDTO Datos del proyecto.
     * @param xConexion Conexion a la Base de datos.
     */
    public void CrearProyecto(clsProyectoDTO xProyectoDTO, Connection xConexion) {
        
        try 
        {
            //xConexion.setAutoCommit(false);
            PreparedStatement st = xConexion.prepareStatement("insert into proyecto "
                    + "(proyecto_estado_descripcion, proyecto_nombre, proyecto_descripcion, proyecto_fecha_creacion)\n" +
                      "values ( 'Activo', ?, ?, "
                    + "(STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))))");
            st.setString(1, xProyectoDTO.getProyecto_nombre());
            st.setString(2, xProyectoDTO.getProyecto_descripcion());
            st.setString(3, xProyectoDTO.getProyecto_fecha_creacion());
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Selecciona los datos de todos los proyectos.
     * @param xConexion
     * @return arrayList de los datos obtenidos.
     */
    public ArrayList<clsProyectoDTO> SeleccionarProyecto(Connection xConexion)
    {
        ArrayList<clsProyectoDTO> arrayProyectoDTO = new ArrayList<>();
        try
        {
            PreparedStatement st = xConexion.prepareStatement("select * from proyecto");         
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                clsProyectoDTO proyectoDTO = new clsProyectoDTO(
                                    rs.getString("proyecto_id"),
                                    rs.getString("proyecto_estado"),
                                    rs.getString("proyecto_estado_descripcion"),
                                    rs.getString("proyecto_nombre"),
                                    rs.getString("proyecto_descripcion"),
                                    rs.getString("proyecto_fecha_creacion")
                                );                                
                arrayProyectoDTO.add(proyectoDTO);
            }           
            return arrayProyectoDTO;
        } 
       catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Actualiza el estado del proyecto, cambiado el estado_id.
     * @param xProyectoDTO Obtendra el proyecto_estado y el proyecto_id
     * @param xConexion Conexion a la base de datos.
     */
    public void ActualizarEstadoProyecto(clsProyectoDTO xProyectoDTO, Connection xConexion) {
        
        try
        {
            PreparedStatement st = xConexion.prepareStatement("update proyecto set"
                            + " proyecto_estado = ?, proyecto_estado_descripcion = ? "
                            + "where proyecto_id = ?");
            st.setString(1, xProyectoDTO.getProyecto_estado());
            st.setString(2, xProyectoDTO.getProyecto_estado_descripcion());
            st.setString(3, xProyectoDTO.getProyecto_id());
            st.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Actualiza los datos del proyecto.
     * @param xProyectoDTO
     * @param xConexion 
     */
    public void ActualizarProyecto(clsProyectoDTO xProyectoDTO, Connection xConexion) {
        try 
        {
            //xConexion.setAutoCommit(false);
            PreparedStatement st = xConexion.prepareStatement("update proyecto as p set\n" 
                    + "p.proyecto_nombre = (?),\n" 
                    + "p.proyecto_descripcion = (?)\n" 
                    + "where p.proyecto_id = (?)");
            st.setString(1, xProyectoDTO.getProyecto_nombre());
            st.setString(2, xProyectoDTO.getProyecto_descripcion());
            st.setString(3, xProyectoDTO.getProyecto_id());
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ResultSet SeleccionarProyecto(String xCodigoProyecto, Connection xConexion) throws SQLException {
        
        ResultSet rsResultado;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select p.proyecto_id,"
                    + "p.proyecto_estado,p.proyecto_estado_descripcion,p.proyecto_nombre,"
                    + "p.proyecto_estado_descripcion,proyecto_descripcion,\n"
                    + "DATE_FORMAT(p.proyecto_fecha_creacion, '%d/%m/%Y') as "
                    + "'proyecto_fecha_creacion' from proyecto as p where "
                    + "p.proyecto_id = (?) ");
            st.setString(1, xCodigoProyecto);
            rsResultado = st.executeQuery();            
            return rsResultado;
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        
    }
    
}
