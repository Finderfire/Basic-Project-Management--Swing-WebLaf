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
import transferenciadatos.clsEntregableVersionDTO;

/**
 *
 * @author Kevin
 */
public class clsEntregableVersionDAO {
    
    public ArrayList SeleccionarEntregableVersion(String xEntregableId, Connection xConexion) {
        
        PreparedStatement st;
        PreparedStatement stTemporal;
        ResultSet rs;
        clsEntregableVersionDTO entregableVersionDTO;
        ArrayList<clsEntregableVersionDTO> arrayentregableVersion = new ArrayList<>();
        
        try 
        {
            stTemporal = xConexion.prepareStatement("create temporary table "
                    + "version_nombre as (select v.version_id, v.version_nombre "
                    + "as 'version_nombre_hijo' from version as v)");
            stTemporal.executeUpdate();
            
            st = xConexion.prepareStatement("select v.*, r.revision_nombre, "
                    + "vn.version_nombre_hijo from version as v inner join "
                    + "revision as r on r.revision_id = v.revision_id inner join "
                    + "version_nombre as vn on v.version_id_hijo = vn.version_id\n" 
                    + "inner join entregable as e on r.entregable_id = "
                    + "e.entregable_id where r.entregable_id = (?)");
            st.setString(1, xEntregableId);
            
            rs = st.executeQuery();
            while(rs.next())
            {
                entregableVersionDTO = new clsEntregableVersionDTO
                (   
                        rs.getString("version_id"),
                        rs.getString("version_nombre"),
                        rs.getString("version_fecha"),
                        rs.getString("version_enlace"),
                        rs.getString("version_descripcion"),
                        rs.getString("revision_id"),
                        rs.getString("revision_nombre"),
                        rs.getString("version_id_hijo"),
                        rs.getString("version_nombre_hijo")
                );
                arrayentregableVersion.add(entregableVersionDTO);
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(clsEntregableRevisionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayentregableVersion;
    }
    
    public void CrearEntregableVersion
        (
            clsEntregableVersionDTO xEntregableVersionDTO, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into version "
                    + "(version_nombre,version_fecha,version_enlace,version_descripcion,"
                    + "revision_id,version_id_hijo) values(?, (STR_TO_DATE(REPLACE(?,'/','.') ,"
                    + " GET_FORMAT(date,'EUR'))), ?, ?, ?, ?) ");
            st.setString(1, xEntregableVersionDTO.getVersion_nombre());
            st.setString(2, xEntregableVersionDTO.getVersion_fecha());
            st.setString(3, xEntregableVersionDTO.getVersion_enlace());
            st.setString(4, xEntregableVersionDTO.getVersion_descripcion());
            st.setString(5, xEntregableVersionDTO.getRevision_id());
            st.setString(6, xEntregableVersionDTO.getVersion_id_hijo());
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
