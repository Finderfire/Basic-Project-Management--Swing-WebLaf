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
public class clsProyectoEntregableDTO {
    
    private String proyectoentregable_id;
    private String proyecto_id;
    private String entregable_id;
    private String proyectoentregable_fecha;
    private String proyectoentregable_enlace;
    private String proyectoentregable_aprobado_responsable;
    private String proyectoentregable_aprobado_jefe;

    public clsProyectoEntregableDTO(String proyectoentregable_id, String proyecto_id, String entregable_id, String proyectoentregable_fecha, String proyectoentregable_enlace) {
        this.proyectoentregable_id = proyectoentregable_id;
        this.proyecto_id = proyecto_id;
        this.entregable_id = entregable_id;
        this.proyectoentregable_fecha = proyectoentregable_fecha;
        this.proyectoentregable_enlace = proyectoentregable_enlace;
    }

    public clsProyectoEntregableDTO(String entregable_id, String proyectoentregable_aprobado_responsable, String proyectoentregable_aprobado_jefe) {
        this.entregable_id = entregable_id;
        this.proyectoentregable_aprobado_responsable = proyectoentregable_aprobado_responsable;
        this.proyectoentregable_aprobado_jefe = proyectoentregable_aprobado_jefe;
    }

    public String getProyectoentregable_id() {
        return proyectoentregable_id;
    }

    public void setProyectoentregable_id(String proyectoentregable_id) {
        this.proyectoentregable_id = proyectoentregable_id;
    }
        
    public String getProyectoentregable_aprobado_responsable() {
        return proyectoentregable_aprobado_responsable;
    }

    public void setProyectoentregable_aprobado_responsable(String proyectoentregable_aprobado_responsable) {
        this.proyectoentregable_aprobado_responsable = proyectoentregable_aprobado_responsable;
    }

    public String getProyectoentregable_aprobado_jefe() {
        return proyectoentregable_aprobado_jefe;
    }

    public void setProyectoentregable_aprobado_jefe(String proyectoentregable_aprobado_jefe) {
        this.proyectoentregable_aprobado_jefe = proyectoentregable_aprobado_jefe;
    }
    
    public String getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(String proyecto_id) {
        this.proyecto_id = proyecto_id;
    }

    public String getEntregable_id() {
        return entregable_id;
    }

    public void setEntregable_id(String entregable_id) {
        this.entregable_id = entregable_id;
    }

    public String getProyectoentregable_fecha() {
        return proyectoentregable_fecha;
    }

    public void setProyectoentregable_fecha(String proyectoentregable_fecha) {
        this.proyectoentregable_fecha = proyectoentregable_fecha;
    }

    public String getProyectoentregable_enlace() {
        return proyectoentregable_enlace;
    }

    public void setProyectoentregable_enlace(String proyectoentregable_enlace) {
        this.proyectoentregable_enlace = proyectoentregable_enlace;
    }
        
}
