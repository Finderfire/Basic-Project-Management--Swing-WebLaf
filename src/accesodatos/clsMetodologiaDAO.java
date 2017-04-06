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
import transferenciadatos.clsMetodologiaDTO;

/**
 *
 * @author Kevin
 */
public class clsMetodologiaDAO {
    
    /**
     * Encargado de listar todas las metodologias de la BD.
     * @param xConexion conexion a la base de datos.
     * @return arrayMetodologiaDTO arrayList con la lista de metodologias.
     */
    public ArrayList SeleccionarMetodologia(Connection xConexion) {
        
        ArrayList<clsMetodologiaDTO> arrayMetodologiaDTO = new ArrayList<>();
        try
        {
            PreparedStatement st = xConexion.prepareStatement("select * "
                        + "from metodologia");            
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                clsMetodologiaDTO metodologiaDTO = new clsMetodologiaDTO
                (
                        rs.getString("metodologia_id"),
                        rs.getString("metodologia_nombre"),
                        rs.getString("metodologia_descripcion")                      
                );                
                arrayMetodologiaDTO.add(metodologiaDTO);
            }            
        }
        catch(SQLException ex)
        {
           Logger.getLogger(clsProyectoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return arrayMetodologiaDTO;
        
    } 
    
    /**
     * 
     * @param xTabla
     * @param xCampo
     * @param xCondicion
     * @param conexion
     * @return 
     */
    public Object [][] SeleccionarEtapaEntregableMetodologia (
                String xTabla, 
                String xCampo, 
                String xCondicion,
                Connection conexion
                ) {
        
        int registros = 0;
        String colname[] = xCampo.split(",");      
        String consulta1 ="SELECT " + xCampo + " FROM " + xTabla;
        String consulta2 = "SELECT count(*) as total FROM " + xTabla;
      
        if(xCondicion != null)
        {
            consulta1+= " WHERE " + xCondicion;
            consulta2+= " WHERE " + xCondicion;
        }
        //obtenemos la cantidad de registros existentes en la tabla
        try
        {
            PreparedStatement pstm = conexion.prepareStatement(consulta2);
            try (ResultSet res = pstm.executeQuery()) 
            {
                res.next();
                registros = res.getInt("total");
            }
        }catch(SQLException e){
         System.out.println(e);
        }
        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][xCampo.split(",").length];
        //realizamos la consulta sql y llenamos los datos en la matriz "Object"
            try
            {
                PreparedStatement pstm = conexion.prepareStatement(consulta1);
                try (ResultSet res = pstm.executeQuery()) 
                {
                    int i = 0;
                    while(res.next())
                    {
                        for(int j=0; j<=xCampo.split(",").length-1;j++)
                        {
                            data[i][j] = res.getString( colname[j].trim() );
                        }
                        i++;         
                    }
                }
            }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    
    }
    
    public Object [][] SeleccionarEntregableEtapaMetodologia (
                String xTabla, 
                String xCampo, 
                String xCondicion,
                Connection conexion
                ) {
        
        int registros = 0;
        String colname[] = xCampo.split(",");      
        String consulta1 ="SELECT " + xCampo + " FROM " + xTabla +" INNER JOIN entregable as e on e.entregable_id = pe.entregable_id";
        
        String consulta2 = "SELECT count(*) as total FROM " + xTabla +" INNER JOIN entregable as e on e.entregable_id = pe.entregable_id";
      
        if(xCondicion != null)
        {
            consulta1+= " WHERE " + xCondicion;
            consulta2+= " WHERE " + xCondicion;
        }
        //obtenemos la cantidad de registros existentes en la tabla
        System.out.println(consulta1);
        try
        {
            PreparedStatement pstm = conexion.prepareStatement(consulta2);
            try (ResultSet res = pstm.executeQuery()) 
            {
                res.next();
                registros = res.getInt("total");
            }
        }catch(SQLException e){
         System.out.println(e);
        }
        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][xCampo.split(",").length];
        //realizamos la consulta sql y llenamos los datos en la matriz "Object"
            try
            {
                PreparedStatement pstm = conexion.prepareStatement(consulta1);
                try (ResultSet res = pstm.executeQuery()) 
                {
                    int i = 0;
                    while(res.next())
                    {
                        for(int j=0; j<=xCampo.split(",").length-1;j++)
                        {
                            data[i][j] = res.getString( colname[j].trim() );
                        }
                        i++;         
                    }
                }
            }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    
    }
    
    
    public ResultSet SeleccionarMetodologiaProyecto(String xCodigo, Connection xConexion) throws Exception{
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select "
                    + "m.metodologia_id, m.metodologia_nombre\n" 
                    + "from proyectoentregable as pe\n" 
                    + "inner join entregable as e on pe.entregable_id = e.entregable_id\n" 
                    + "inner join etapa as et on e.etapa_id = et.etapa_id\n" 
                    + "inner join metodologia as m on et.metodologia_id = m.metodologia_id\n"
                    + "where pe.proyecto_id = (?) limit 1");
            st.setString(1, xCodigo);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    }    
    
}
