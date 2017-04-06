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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transferenciadatos.clsProyectoUsuarioDTO;

/**
 *
 * @author Kevin
 */
public class clsProyectoUsuarioDAO {
    
    /**
     * Consulta y lista los proyectos que tiene un determinado usuario segun su ID.
     * @param xUsuarioId el id de usuario para consultar con la BD.
     * @param conexion  conexion con la BD.
     * @return arrayProyectoUsuario listado con los proyectos encontrados.
     */
    public ArrayList SeleccionarProyectoUsuario(String xUsuarioId, Connection conexion) {
        ArrayList<clsProyectoUsuarioDTO> arrayProyectoUsuario = new ArrayList<>();
        try
        {
            PreparedStatement st = conexion.prepareStatement("select pu.proyecto_id, "
                    + "p.proyecto_nombre, p.proyecto_fecha_creacion, p.proyecto_estado \n" 
                    + "from proyectousuario as pu inner join proyecto as p "
                    + "on pu.proyecto_id = p.proyecto_id inner join usuario as u "
                    + "on pu.usuario_id = u.usuario_id where u.usuario_id = (?) "
                    + "order by p.proyecto_fecha_creacion,u.usuario_jefe_proyecto");
            st.setString(1, xUsuarioId);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                clsProyectoUsuarioDTO asignacion = new clsProyectoUsuarioDTO
                (
                        rs.getString("proyecto_id"),
                        "",
                        rs.getString("proyecto_nombre"),
                        rs.getString("proyecto_fecha_creacion"),
                        rs.getString("proyecto_estado"),
                        ""
                );                
                arrayProyectoUsuario.add(asignacion);
            }
            
        }
        catch(SQLException ex)
        {
           Logger.getLogger(clsProyectoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return arrayProyectoUsuario;
    }  
    
    /**
     * Inserta nuevo usuario a un proyecto creado.
     * @param xProyectoUsuarioDTO Se obtiene los id respectivos.
     * @param xConexion Conexion a la Base de datos.
     */
    public void CrearProyectoUsuario(clsProyectoUsuarioDTO xProyectoUsuarioDTO, Connection xConexion) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into proyectousuario "
                    + "(proyecto_id, usuario_id) values (?, ?)");
            st.setString(1, xProyectoUsuarioDTO.getProyecto_id());
            st.setString(2, xProyectoUsuarioDTO.getUsuario_id());
            st.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet SeleccionarProyectoEquipo(String xCodigoProyecto, Connection xConexion) throws Exception{
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select pu.usuario_id, "
                        + "u.usuario_nombre, u.usuario_apellido, pu.proyectousuario_id\n" 
                        + "from proyectousuario as pu inner join usuario as u \n" 
                        + "on pu.usuario_id = u.usuario_id \n" 
                        + "where pu.proyecto_id = (?)");
            st.setString(1, xCodigoProyecto);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    } 
    
    public void EliminarProyectoUsuario
                            (
                                String xCodigoUsuario, 
                                String xCodigoProyecto,
                                Connection xConexion
                            ) {
        
        try 
        {
            PreparedStatement st = xConexion.prepareStatement("delete from "
                    + "proyectousuario \n" 
                    + "where usuario_id = ? and proyecto_id = ? ");
            st.setString(1, xCodigoUsuario);
            st.setString(2, xCodigoProyecto);
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(clsProyectoUsuarioEntregableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    public String SeleccionarProyectousuarioId (
                                String xCodigoUsuario, 
                                String xCodigoProyecto, 
                                Connection xConexion
    ) throws Exception {
        
        ResultSet rs;
        String proyectoUsuarioId = "";
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select "
                    + "pu.proyectousuario_id from proyectousuario as pu\n" 
                    + "where pu.usuario_id = ? and pu.proyecto_id = ?");
            st.setString(1, xCodigoUsuario);
            st.setString(2, xCodigoProyecto);
            rs = st.executeQuery();            
            
            while(rs.next())
            {
                proyectoUsuarioId = rs.getString("proyectousuario_id");
            }
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
        
        return proyectoUsuarioId;
    } 
    
}
