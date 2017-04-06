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
public class clsEvaluacionDTO {
    
    private String evaluacion_id;
    private String evaluacion_tipo;
    private String evaluacion_descripcion_encargado;
    private String evaluacion_descripcion_jefe;
    private String evaluacion_estado;
    private String solicitud_id;

    public clsEvaluacionDTO(String evaluacion_id, String evaluacion_tipo, String evaluacion_descripcion_encargado, String evaluacion_descripcion_jefe, String evaluacion_estado, String solicitud_id) {
        this.evaluacion_id = evaluacion_id;
        this.evaluacion_tipo = evaluacion_tipo;
        this.evaluacion_descripcion_encargado = evaluacion_descripcion_encargado;
        this.evaluacion_descripcion_jefe = evaluacion_descripcion_jefe;
        this.evaluacion_estado = evaluacion_estado;
        this.solicitud_id = solicitud_id;
    }

    public String getEvaluacion_id() {
        return evaluacion_id;
    }

    public void setEvaluacion_id(String evaluacion_id) {
        this.evaluacion_id = evaluacion_id;
    }

    public String getEvaluacion_tipo() {
        return evaluacion_tipo;
    }

    public void setEvaluacion_tipo(String evaluacion_tipo) {
        this.evaluacion_tipo = evaluacion_tipo;
    }

    public String getEvaluacion_descripcion_encargado() {
        return evaluacion_descripcion_encargado;
    }

    public void setEvaluacion_descripcion_encargado(String evaluacion_descripcion_encargado) {
        this.evaluacion_descripcion_encargado = evaluacion_descripcion_encargado;
    }

    public String getEvaluacion_descripcion_jefe() {
        return evaluacion_descripcion_jefe;
    }

    public void setEvaluacion_descripcion_jefe(String evaluacion_descripcion_jefe) {
        this.evaluacion_descripcion_jefe = evaluacion_descripcion_jefe;
    }

    public String getEvaluacion_estado() {
        return evaluacion_estado;
    }

    public void setEvaluacion_estado(String evaluacion_estado) {
        this.evaluacion_estado = evaluacion_estado;
    }

    public String getSolicitud_id() {
        return solicitud_id;
    }

    public void setSolicitud_id(String solicitud_id) {
        this.solicitud_id = solicitud_id;
    }

    
}
