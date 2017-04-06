/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transferenciadatos;

/**
 *
 * @author Kevin
 */
public class clsUsuarioDTO {
    
    private String usuario_id;
    private String usuario_nombre;
    private String usuario_apellido;
    private String usuario_telefono;
    private String usuario_dni;
    private String usuario_fecha_nacimiento;
    private String usuario_estado;
    private String usuario_contrasena;
    private String usuario_jefe_proyecto;
    private String usuario_correo_electronico;
    private String usuario_engargado_cambio;

    public clsUsuarioDTO() {
    }
    
    public clsUsuarioDTO(String usuario_id, String usuario_nombre, String usuario_apellido, String usuario_telefono, String usuario_dni, String usuario_fecha_nacimiento, String usuario_estado, String usuario_contrasena, String usuario_jefe_proyecto, String usuario_correo_electronico, String usuario_engargado_cambio) {
        this.usuario_id = usuario_id;
        this.usuario_nombre = usuario_nombre;
        this.usuario_apellido = usuario_apellido;
        this.usuario_telefono = usuario_telefono;
        this.usuario_dni = usuario_dni;
        this.usuario_fecha_nacimiento = usuario_fecha_nacimiento;
        this.usuario_estado = usuario_estado;
        this.usuario_contrasena = usuario_contrasena;
        this.usuario_jefe_proyecto = usuario_jefe_proyecto;
        this.usuario_correo_electronico = usuario_correo_electronico;
        this.usuario_engargado_cambio = usuario_engargado_cambio;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public String getUsuario_apellido() {
        return usuario_apellido;
    }

    public void setUsuario_apellido(String usuario_apellido) {
        this.usuario_apellido = usuario_apellido;
    }

    public String getUsuario_telefono() {
        return usuario_telefono;
    }

    public void setUsuario_telefono(String usuario_telefono) {
        this.usuario_telefono = usuario_telefono;
    }

    public String getUsuario_dni() {
        return usuario_dni;
    }

    public void setUsuario_dni(String usuario_dni) {
        this.usuario_dni = usuario_dni;
    }

    public String getUsuario_fecha_nacimiento() {
        return usuario_fecha_nacimiento;
    }

    public void setUsuario_fecha_nacimiento(String usuario_fecha_nacimiento) {
        this.usuario_fecha_nacimiento = usuario_fecha_nacimiento;
    }

    public String getUsuario_estado() {
        return usuario_estado;
    }

    public void setUsuario_estado(String usuario_estado) {
        this.usuario_estado = usuario_estado;
    }

    public String getUsuario_contrasena() {
        return usuario_contrasena;
    }

    public void setUsuario_contrasena(String usuario_contrasena) {
        this.usuario_contrasena = usuario_contrasena;
    }

    public String getUsuario_jefe_proyecto() {
        return usuario_jefe_proyecto;
    }

    public void setUsuario_jefe_proyecto(String usuario_jefe_proyecto) {
        this.usuario_jefe_proyecto = usuario_jefe_proyecto;
    }

    public String getUsuario_correo_electronico() {
        return usuario_correo_electronico;
    }

    public void setUsuario_correo_electronico(String usuario_correo_electronico) {
        this.usuario_correo_electronico = usuario_correo_electronico;
    }

    public String getUsuario_engargado_cambio() {
        return usuario_engargado_cambio;
    }

    public void setUsuario_engargado_cambio(String usuario_engargado_cambio) {
        this.usuario_engargado_cambio = usuario_engargado_cambio;
    }
        
}
