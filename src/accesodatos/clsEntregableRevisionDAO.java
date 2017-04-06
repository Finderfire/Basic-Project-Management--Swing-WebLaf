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
import transferenciadatos.clsEntregableRevisionDTO;

/**
 *
 * @author Kevin
 */
public class clsEntregableRevisionDAO {
    
    /**
     * 
     * @param xEntregableId
     * @param xConexion
     * @return 
     */
    public ArrayList SeleccionarEntregableRevision(String xEntregableId, Connection xConexion) {
        
        PreparedStatement st;
        PreparedStatement stTemporal;
        ResultSet rs;
        clsEntregableRevisionDTO entregableRevisionDTO;
        ArrayList<clsEntregableRevisionDTO> arrayentregableRevision = new ArrayList<>();
        
        try 
        {
            stTemporal = xConexion.prepareStatement("create temporary table "
                    + "revision_nombre as (select r.revision_id, r.revision_nombre "
                    + "as 'revision_nombre_hijo' from revision as r)");
            stTemporal.executeUpdate();
            
            st = xConexion.prepareStatement("select r.*, e.entregable_nombre, "
                    + "rn.revision_nombre_hijo from revision as r inner join "
                    + "entregable as e on r.entregable_id = e.entregable_id " 
                    + "inner join revision_nombre as rn on r.revision_id_hijo = "
                    + "rn.revision_id where r.entregable_id = (?)");
            st.setString(1, xEntregableId);
            
            rs = st.executeQuery();
            while(rs.next())
            {
                entregableRevisionDTO = new clsEntregableRevisionDTO
                (   
                        rs.getString("revision_id"),
                        rs.getString("revision_nombre"),
                        rs.getString("revision_fecha"),
                        rs.getString("revision_descripcion"),
                        rs.getString("revision_enlace"),
                        rs.getString("entregable_id"),
                        rs.getString("entregable_nombre"),
                        rs.getString("revision_id_hijo"),
                        rs.getString("revision_nombre_hijo")
                );
                arrayentregableRevision.add(entregableRevisionDTO);
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(clsEntregableRevisionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayentregableRevision;
    }
    
    public void CrearEntregableRevision
        (
            clsEntregableRevisionDTO xEntregableRevisionDTO, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into revision "
                    + "(revision_nombre, revision_fecha, revision_descripcion, "
                    + "revision_enlace, entregable_id, revision_id_hijo)\n" 
                    + "values (?, (STR_TO_DATE(REPLACE(?,'/','.') , "
                    + "GET_FORMAT(date,'EUR'))), ?, ? ,? ,? )");
            st.setString(1, xEntregableRevisionDTO.getRevision_nombre());
            st.setString(2, xEntregableRevisionDTO.getRevision_fecha());
            st.setString(3, xEntregableRevisionDTO.getRevision_descripcion());
            st.setString(4, xEntregableRevisionDTO.getRevision_enlace());
            st.setString(5, xEntregableRevisionDTO.getEntregable_id());
            st.setString(6, xEntregableRevisionDTO.getRevision_id_hijo());
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(clsProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
