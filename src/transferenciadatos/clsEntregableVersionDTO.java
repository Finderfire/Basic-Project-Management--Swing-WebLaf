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
public class clsEntregableVersionDTO {
    private String version_id;
    private String version_nombre;
    private String version_fecha;
    private String version_enlace;
    private String version_descripcion;
    private String revision_id;
    private String revision_nombre;
    private String version_id_hijo;
    private String version_nombre_hijo;

    public clsEntregableVersionDTO(String version_id, String version_nombre, String version_fecha, String version_enlace, String version_descripcion, String revision_id, String revision_nombre, String version_id_hijo, String version_nombre_hijo) {
        this.version_id = version_id;
        this.version_nombre = version_nombre;
        this.version_fecha = version_fecha;
        this.version_enlace = version_enlace;
        this.version_descripcion = version_descripcion;
        this.revision_id = revision_id;
        this.revision_nombre = revision_nombre;
        this.version_id_hijo = version_id_hijo;
        this.version_nombre_hijo = version_nombre_hijo;
    }

    public String getVersion_id() {
        return version_id;
    }

    public void setVersion_id(String version_id) {
        this.version_id = version_id;
    }

    public String getVersion_nombre() {
        return version_nombre;
    }

    public void setVersion_nombre(String version_nombre) {
        this.version_nombre = version_nombre;
    }

    public String getVersion_fecha() {
        return version_fecha;
    }

    public void setVersion_fecha(String version_fecha) {
        this.version_fecha = version_fecha;
    }

    public String getVersion_enlace() {
        return version_enlace;
    }

    public void setVersion_enlace(String version_enlace) {
        this.version_enlace = version_enlace;
    }

    public String getVersion_descripcion() {
        return version_descripcion;
    }

    public void setVersion_descripcion(String version_descripcion) {
        this.version_descripcion = version_descripcion;
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

    public String getVersion_id_hijo() {
        return version_id_hijo;
    }

    public void setVersion_id_hijo(String version_id_hijo) {
        this.version_id_hijo = version_id_hijo;
    }

    public String getVersion_nombre_hijo() {
        return version_nombre_hijo;
    }

    public void setVersion_nombre_hijo(String version_nombre_hijo) {
        this.version_nombre_hijo = version_nombre_hijo;
    }
    
    
}
