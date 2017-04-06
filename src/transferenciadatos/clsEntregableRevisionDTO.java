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
public class clsEntregableRevisionDTO {
    
    private String revision_id;
    private String revision_nombre;
    private String revision_fecha;
    private String revision_descripcion;
    private String revision_enlace;
    private String entregable_id;
    private String entregable_nombre;
    private String revision_id_hijo;
    private String revision_nombre_hijo;

    public clsEntregableRevisionDTO(String revision_id, String revision_nombre, String revision_fecha, String revision_descripcion, String revision_enlace, String entregable_id, String entregable_nombre, String revision_id_hijo, String revision_nombre_hijo) {
        this.revision_id = revision_id;
        this.revision_nombre = revision_nombre;
        this.revision_fecha = revision_fecha;
        this.revision_descripcion = revision_descripcion;
        this.revision_enlace = revision_enlace;
        this.entregable_id = entregable_id;
        this.entregable_nombre = entregable_nombre;
        this.revision_id_hijo = revision_id_hijo;
        this.revision_nombre_hijo = revision_nombre_hijo;
    }

    public String getRevision_nombre_hijo() {
        return revision_nombre_hijo;
    }

    public void setRevision_nombre_hijo(String revision_nombre_hijo) {
        this.revision_nombre_hijo = revision_nombre_hijo;
    }

    public String getRevision_id() {
        return revision_id;
    }

    public void setRevision_id(String revision_id) {
        this.revision_id = revision_id;
    }

    public String getRevision_nombre() {
        return revision_nombre;
    }

    public void setRevision_nombre(String revision_nombre) {
        this.revision_nombre = revision_nombre;
    }

    public String getRevision_fecha() {
        return revision_fecha;
    }

    public void setRevision_fecha(String revision_fecha) {
        this.revision_fecha = revision_fecha;
    }

    public String getRevision_descripcion() {
        return revision_descripcion;
    }

    public void setRevision_descripcion(String revision_descripcion) {
        this.revision_descripcion = revision_descripcion;
    }

    public String getRevision_enlace() {
        return revision_enlace;
    }

    public void setRevision_enlace(String revision_enlace) {
        this.revision_enlace = revision_enlace;
    }

    public String getEntregable_id() {
        return entregable_id;
    }

    public void setEntregable_id(String entregable_id) {
        this.entregable_id = entregable_id;
    }

    public String getEntregable_nombre() {
        return entregable_nombre;
    }

    public void setEntregable_nombre(String entregable_nombre) {
        this.entregable_nombre = entregable_nombre;
    }

    public String getRevision_id_hijo() {
        return revision_id_hijo;
    }

    public void setRevision_id_hijo(String revision_id_hijo) {
        this.revision_id_hijo = revision_id_hijo;
    }
    
    
    
}
