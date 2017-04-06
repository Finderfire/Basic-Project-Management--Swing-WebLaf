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
public class clsProyectoUsuarioEntregableDTO {
    
    private String proyectousuario_id;
    private String proyectoentregable_id;
    private String proyectousuario_proyectoentregable_responsable;
    private String proyectousuario_proyectoentregable_descripcion;

    public clsProyectoUsuarioEntregableDTO(String proyectousuario_id, String proyectoentregable_id, String proyectousuario_proyectoentregable_responsable, String proyectousuario_proyectoentregable_descripcion) {
        this.proyectousuario_id = proyectousuario_id;
        this.proyectoentregable_id = proyectoentregable_id;
        this.proyectousuario_proyectoentregable_responsable = proyectousuario_proyectoentregable_responsable;
        this.proyectousuario_proyectoentregable_descripcion = proyectousuario_proyectoentregable_descripcion;
    }
    
    public String getProyectousuario_id() {
        return proyectousuario_id;
    }

    public void setProyectousuario_id(String proyectousuario_id) {
        this.proyectousuario_id = proyectousuario_id;
    }

    public String getProyectoentregable_id() {
        return proyectoentregable_id;
    }

    public void setProyectoentregable_id(String proyectoentregable_id) {
        this.proyectoentregable_id = proyectoentregable_id;
    }

    public String getProyectousuario_proyectoentregable_responsable() {
        return proyectousuario_proyectoentregable_responsable;
    }

    public void setProyectousuario_proyectoentregable_responsable(String proyectousuario_proyectoentregable_responsable) {
        this.proyectousuario_proyectoentregable_responsable = proyectousuario_proyectoentregable_responsable;
    }

    public String getProyectousuario_proyectoentregable_descripcion() {
        return proyectousuario_proyectoentregable_descripcion;
    }

    public void setProyectousuario_proyectoentregable_descripcion(String proyectousuario_proyectoentregable_descripcion) {
        this.proyectousuario_proyectoentregable_descripcion = proyectousuario_proyectoentregable_descripcion;
    }
    
    
}
