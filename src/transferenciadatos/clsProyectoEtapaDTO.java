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
public class clsProyectoEtapaDTO {
    
    private String etapa_id;
    private String etapa_nombre;
    private String etapa_descripcion;
    private String metodologia_id;

    public clsProyectoEtapaDTO(String etapa_id, String etapa_nombre, String etapa_descripcion, String metodologia_id) {
        this.etapa_id = etapa_id;
        this.etapa_nombre = etapa_nombre;
        this.etapa_descripcion = etapa_descripcion;
        this.metodologia_id = metodologia_id;
    }

    public String getEtapa_id() {
        return etapa_id;
    }

    public void setEtapa_id(String etapa_id) {
        this.etapa_id = etapa_id;
    }

    public String getEtapa_nombre() {
        return etapa_nombre;
    }

    public void setEtapa_nombre(String etapa_nombre) {
        this.etapa_nombre = etapa_nombre;
    }

    public String getEtapa_descripcion() {
        return etapa_descripcion;
    }

    public void setEtapa_descripcion(String etapa_descripcion) {
        this.etapa_descripcion = etapa_descripcion;
    }

    public String getMetodologia_id() {
        return metodologia_id;
    }

    public void setMetodologia_id(String metodologia_id) {
        this.metodologia_id = metodologia_id;
    }
        
}
