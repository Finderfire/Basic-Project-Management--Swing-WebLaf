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

/**
 *
 * @author Kevin
 */
public class clsProyectoEntregableDAO {
    
    /**
     * 
     * @param xProyectoEntregableDTO
     * @param xConexion 
     */
    public void CrearProyectoEntregable
        (
            clsProyectoEntregableDTO xProyectoEntregableDTO, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into "
                    + "proyectoentregable (proyecto_id, entregable_id, "
                    + "proyectoentregable_fecha, proyectoentregable_enlace)\n" 
                    + "values (?, ?, (STR_TO_DATE(REPLACE(?,'/','.') ,"
                    + "GET_FORMAT(date,'EUR'))), ?)");
            st.setString(1, xProyectoEntregableDTO.getProyecto_id());
            st.setString(2, xProyectoEntregableDTO.getEntregable_id());
            st.setString(3, xProyectoEntregableDTO.getProyectoentregable_fecha());
            st.setString(4, xProyectoEntregableDTO.getProyectoentregable_enlace());
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
         
     public String SeleccionarProyectoEntregableId (
                                String xCodigoEntregable, 
                                String xCodigoProyecto, 
                                Connection xConexion
    ) throws Exception {
        
        ResultSet rs;
        String proyectoEntregableId = "";
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select pe.* from "
                    + "proyectoentregable as pe where pe.entregable_id = ? "
                    + "and pe.proyecto_id = ?");
            st.setString(1, xCodigoEntregable);
            st.setString(2, xCodigoProyecto);
            rs = st.executeQuery();            
            
            while(rs.next())
            {
                proyectoEntregableId = rs.getString("proyectoentregable_id");
            }
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
        
        return proyectoEntregableId;
    }
        
    public ResultSet SeleccionarProyectoEntregable(String xCodigoProyecto, Connection xConexion) throws Exception{
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select pe.*, "
                    + "e.entregable_nombre from proyectoentregable as pe\n" 
                    + "inner join entregable as e\n" 
                    + "on pe.entregable_id = e.entregable_id\n" 
                    + "where proyecto_id = (?)");
            st.setString(1, xCodigoProyecto);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    }  
    
    public ResultSet SeleccionarDatosProyectoEntregable
                    (
                        String xCodigoEntregable, 
                        String xCodigoProyecto,
                        Connection xConexion
                    ) throws Exception {
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select * from "
                    + "proyectoentregable as p\n" 
                    + "where p.proyecto_id = (?) and p.entregable_id = (?)");
            st.setString(1, xCodigoProyecto);
            st.setString(2, xCodigoEntregable);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    } 
    
    public ResultSet SeleccionarProyectoEntregableEtapa
                    (                        
                        String xCodigoEtapa,
                        String xCodigoProyecto,
                        Connection xConexion
                    ) 
                    throws Exception {
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select pe.*, e.entregable_nombre "
                    + "from proyectoentregable as pe inner join entregable as e on "
                    + "pe.entregable_id = e.entregable_id inner join etapa as et "
                    + "on e.etapa_id = et.etapa_id where et.etapa_id = (?) "
                    + "and pe.proyecto_id = (?)");
            st.setString(1, xCodigoEtapa);
            st.setString(2, xCodigoProyecto);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    } 
    
    public void ActualizarProyectoEntregableFecha(clsProyectoEntregableDTO xProyectoEntregableDTO, Connection xConexion) {
        
        try
        {
            PreparedStatement st = xConexion.prepareStatement("update proyectoentregable \n" 
                    + "set proyectoentregable_fecha =  "
                    + "(STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),\n"
                    + " proyectoentregable_enlace = ? " 
                    + "where proyectoentregable_id = ?");
            st.setString(1, xProyectoEntregableDTO.getProyectoentregable_fecha());
            st.setString(2, xProyectoEntregableDTO.getProyectoentregable_enlace());
            st.setString(3, xProyectoEntregableDTO.getProyectoentregable_id());
            st.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void EliminarProyectoEntregable
                            (                                
                                String xCodigoProyecto,
                                Connection xConexion
                            ) {
        
        try 
        {
            PreparedStatement st = xConexion.prepareStatement("delete from "
                    + "proyectoentregable \n" 
                    + "where proyecto_id = ? ");
            st.setString(1, xCodigoProyecto);
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(clsProyectoUsuarioEntregableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public ResultSet SeleccionarProyectoEntregableFecha
            (                
                String xCodigoProyectoEntregable,    
                Connection xConexion
            ) throws Exception {
                
        String fecha = null;        
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select "
                    + "DATE_FORMAT(pe.proyectoentregable_fecha, '%d/%m/%Y') "
                    + "as 'proyectoentregable_fecha', pe.proyectoentregable_enlace\n" 
                    + "from proyectoentregable as pe \n" 
                    + "where pe.proyectoentregable_id = (?)");
            st.setString(1, xCodigoProyectoEntregable);            
            rs = st.executeQuery();            
            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    } 
    
    /**
     * 
     * @param xCodigoProyecto
     * @param xCodigoEntregable
     * @param xConexion
     * @return
     * @throws Exception 
     */        
    public String SeleccionarProyectoEntregableEnlace
            (
                String xCodigoProyecto,
                String xCodigoEntregable,    
                Connection xConexion
            ) throws Exception {
                
        String enlace = null;
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select "                    
                    + "pe.proyectoentregable_enlace \n" 
                    + "from proyectoentregable as pe \n" 
                    + "where pe.entregable_id = (?) and pe.proyecto_id = (?)");
            st.setString(1, xCodigoEntregable);
            st.setString(2, xCodigoProyecto);
            rs = st.executeQuery();            
            
            while(rs.next())
            {
                enlace = rs.getString("proyectoentregable_enlace");
            }
            return enlace;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    }         
            
    public void ActualizarProyectoEntregableAprobadoResponsable(clsProyectoEntregableDTO xProyectoEntregableDTO, Connection xConexion) {

        try
        {
            PreparedStatement st = xConexion.prepareStatement("update proyectoentregable \n" 
                    + "set proyectoentregable_aprobado_responsable = (?)\n"
                    + "where entregable_id = ?");
            st.setString(1, xProyectoEntregableDTO.getProyectoentregable_aprobado_responsable()); 
            st.setString(2, xProyectoEntregableDTO.getEntregable_id());
            st.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ActualizarProyectoEntregableAprobadoJefe(clsProyectoEntregableDTO xProyectoEntregableDTO, Connection xConexion) {

        try
        {
            PreparedStatement st = xConexion.prepareStatement("update proyectoentregable \n"    
                    + "set proyectoentregable_aprobado_jefe = (?)\n" 
                    + "where entregable_id = ?");           
            st.setString(1, xProyectoEntregableDTO.getProyectoentregable_aprobado_jefe());
            st.setString(2, xProyectoEntregableDTO.getEntregable_id());
            st.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
}
