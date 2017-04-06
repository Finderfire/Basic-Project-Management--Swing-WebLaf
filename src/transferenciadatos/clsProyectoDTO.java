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
public class clsProyectoDTO {
    private String proyecto_id;
    private String proyecto_estado;
    private String proyecto_estado_descripcion;
    private String proyecto_nombre;
    private String proyecto_descripcion;
    private String proyecto_fecha_creacion;

    public clsProyectoDTO(String proyecto_id, String proyecto_estado, String proyecto_nombre, String proyecto_descripcion, String proyecto_fecha_creacion) {
        this.proyecto_id = proyecto_id;
        this.proyecto_estado = proyecto_estado;
        this.proyecto_nombre = proyecto_nombre;
        this.proyecto_descripcion = proyecto_descripcion;
        this.proyecto_fecha_creacion = proyecto_fecha_creacion;
    }

    public clsProyectoDTO(String proyecto_id, String proyecto_estado, String proyecto_estado_descripcion, String proyecto_nombre, String proyecto_descripcion, String proyecto_fecha_creacion) {
        this.proyecto_id = proyecto_id;
        this.proyecto_estado = proyecto_estado;
        this.proyecto_estado_descripcion = proyecto_estado_descripcion;
        this.proyecto_nombre = proyecto_nombre;
        this.proyecto_descripcion = proyecto_descripcion;
        this.proyecto_fecha_creacion = proyecto_fecha_creacion;
    }
    
    public clsProyectoDTO() {
        
    }

    public String getProyecto_estado_descripcion() {
        return proyecto_estado_descripcion;
    }

    public void setProyecto_estado_descripcion(String proyecto_estado_descripcion) {
        this.proyecto_estado_descripcion = proyecto_estado_descripcion;
    }

    
    public String getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(String proyecto_id) {
        this.proyecto_id = proyecto_id;
    }

    public String getProyecto_estado() {
        return proyecto_estado;
    }

    public void setProyecto_estado(String proyecto_estado) {
        this.proyecto_estado = proyecto_estado;
    }

    public String getProyecto_nombre() {
        return proyecto_nombre;
    }

    public void setProyecto_nombre(String proyecto_nombre) {
        this.proyecto_nombre = proyecto_nombre;
    }

    public String getProyecto_descripcion() {
        return proyecto_descripcion;
    }

    public void setProyecto_descripcion(String proyecto_descripcion) {
        this.proyecto_descripcion = proyecto_descripcion;
    }

    public String getProyecto_fecha_creacion() {
        return proyecto_fecha_creacion;
    }

    public void setProyecto_fecha_creacion(String proyecto_fecha_creacion) {
        this.proyecto_fecha_creacion = proyecto_fecha_creacion;
    }
            
}
