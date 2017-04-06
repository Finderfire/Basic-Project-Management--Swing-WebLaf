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
import transferenciadatos.clsUsuarioDTO;

/**
 *
 * @author Kevin
 */
public class clsUsuarioDAO {
    
    /**
     * Encargado de listar todos los usuarios del sistema.
     * @param xConexion conexion a la base de datos.
     * @return arrayUsuarioDTO arrayList con la lista de usuarios.
     */
    public ArrayList SeleccionarUsuario(Connection xConexion) {
        
        ArrayList<clsUsuarioDTO> arrayUsuarioDTO = new ArrayList<>();
        try
        {
            PreparedStatement st = xConexion.prepareStatement("select u.* , "
                    + "DATE_FORMAT(u.usuario_fecha_nacimiento, '%d/%m/%Y') "
                    + "as 'usuario_fecha_nacimiento_format' from usuario as u");            
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                clsUsuarioDTO usuarioDTO = new clsUsuarioDTO
                (
                        rs.getString("usuario_id"),
                        rs.getString("usuario_nombre"),
                        rs.getString("usuario_apellido"),                        
                        rs.getString("usuario_telefono"),
                        rs.getString("usuario_dni"),
                        rs.getString("usuario_fecha_nacimiento_format"),
                        rs.getString("usuario_estado"),
                        rs.getString("usuario_contrasena"),
                        rs.getString("usuario_jefe_proyecto"),
                        rs.getString("usuario_correo_electronico"),
                        rs.getString("usuario_encargado_cambio")
                );                
                arrayUsuarioDTO.add(usuarioDTO);
            }
            
        }
        catch(SQLException ex)
        {
           Logger.getLogger(clsProyectoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return arrayUsuarioDTO;
        
    }  
    
    /**
     * Permite buscar un usuario en la base de datos a través del campo codigo. 
     * @param xCodigo   codigo del usuario que iniciara sesion
     * @param xConexion conexion a la base de datos
     * @return  rs  retorna los valores encontrados en un ResultSet {@link constrasena},
     *              {@link nombre}, y {@link apellido}.
     * @throws Exception 
     */
    public ResultSet SeleccionarUsuarioCodigo(String xCodigo, Connection xConexion) throws Exception{
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select usuario_contrasena, "
                        + "usuario_nombre, usuario_apellido, usuario_jefe_proyecto, usuario_id "
                        + "from usuario where usuario_codigo = (?)");
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
