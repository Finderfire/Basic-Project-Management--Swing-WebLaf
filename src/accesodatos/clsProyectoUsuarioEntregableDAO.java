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
import transferenciadatos.clsProyectoEntregableDTO;
import transferenciadatos.clsProyectoUsuarioEntregableDTO;

/**
 * Esta clase hace referencia a la tabla: proyectousuario_proyectoentregable
 * donde se relaciona los participantes o usuarios que tendra un entregable
 * del proyecto.
 * @author Kevin
 */
public class clsProyectoUsuarioEntregableDAO {
    
    public ResultSet SeleccionarProyectoUsuarioEntregable
            (
                String xCodigoEntregable, Connection xConexion
            ) 
            throws Exception {
                
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select pu.usuario_id, "
                    + "u.usuario_apellido, u.usuario_nombre, pe.proyectoentregable_enlace, \n" 
                    + "pe.entregable_id, e.entregable_nombre,u.usuario_correo_electronico, \n" 
                    + "pp.proyectousuario_proyectoentregable_responsable,\n" 
                    + "pp.proyectousuario_proyectoentregable_descripcion,\n"
                    + "pp.proyectousuario_id,pp.proyectoentregable_id\n" 
                    + "from proyectousuario_proyectoentregable as pp\n" 
                    + "inner join proyectousuario as pu "
                    + "on pu.proyectousuario_id = pp.proyectousuario_id\n" 
                    + "inner join proyectoentregable as pe on "
                    + "pe.proyectoentregable_id = pp.proyectoentregable_id\n" 
                    + "inner join entregable as e on pe.entregable_id = e.entregable_id\n" 
                    + "inner join usuario as u on u.usuario_id = pu.usuario_id\n" 
                    + "where pe.proyectoentregable_id = (?)");
            st.setString(1, xCodigoEntregable);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    } 
            
    public void CrearProyectoUsuarioEntregable (
                clsProyectoUsuarioEntregableDTO xProyectoUsuarioEntregableDTO, 
                Connection xConexion
    ) {
        
        try 
        {
            
            PreparedStatement st = xConexion.prepareStatement("insert into "
                    + "proyectousuario_proyectoentregable \n" 
                    + "(proyectousuario_id,proyectoentregable_id,"
                    + "proyectousuario_proyectoentregable_responsable,"
                    + "proyectousuario_proyectoentregable_descripcion)\n" 
                    + "values (?, ?, ?, ?);");
            st.setString(1, xProyectoUsuarioEntregableDTO.getProyectousuario_id());
            st.setString(2, xProyectoUsuarioEntregableDTO.getProyectoentregable_id());
            st.setString(3, xProyectoUsuarioEntregableDTO.getProyectousuario_proyectoentregable_responsable());
            st.setString(4, xProyectoUsuarioEntregableDTO.getProyectousuario_proyectoentregable_descripcion());
            st.executeUpdate();
            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }        
    
    /**
     * Elimina todos los participantes de un determinado entregable.
     * @param xCodigoProyectoEntregable El id del Entregable.
     * @param xConexion Conexion a la BD.
     */
    public void EliminarProyectoUsuarioEntregable
                            (                               
                                String xCodigoProyectoEntregable, 
                                Connection xConexion
                            ) {
        
        try 
        {
            PreparedStatement st = xConexion.prepareStatement("delete from "
                        + "proyectousuario_proyectoentregable \n" 
                        + "where proyectoentregable_id = (?) ");            
            st.setString(1, xCodigoProyectoEntregable);
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(clsProyectoUsuarioEntregableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Actualiza el rol que desempeña un usuario.
     * @param xDescripcionRol
     * @param xProyectoUsuarioId
     * @param xProyectoEntregableId
     * @param xConexion 
     */
    public void ActualizarProyectoUsuarioEntregableDescripcionRol( 
                    String xDescripcionRol,
                    String xProyectoUsuarioId,
                    String xProyectoEntregableId,
                    Connection xConexion
    ) {
        
        try
        {
            PreparedStatement st = xConexion.prepareStatement("update "
                    + "proyectousuario_proyectoentregable \n" 
                    + "set proyectousuario_proyectoentregable_descripcion =  ? "                     
                    + "where proyectousuario_id = ? and proyectoentregable_id = ?");
            st.setString(1, xDescripcionRol);
            st.setString(2, xProyectoUsuarioId);
            st.setString(3, xProyectoEntregableId);
            st.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
