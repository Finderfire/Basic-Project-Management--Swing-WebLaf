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
public class clsMetodologiaDTO {
    
    private String metodologia_id;
    private String metodologia_nombre;
    private String metodologia_descripcion;

    public clsMetodologiaDTO(String metodologia_id, String metodologia_nombre, String metodologia_descripcion) {
        this.metodologia_id = metodologia_id;
        this.metodologia_nombre = metodologia_nombre;
        this.metodologia_descripcion = metodologia_descripcion;
    }

    public String getMetodologia_id() {
        return metodologia_id;
    }

    public void setMetodologia_id(String metodologia_id) {
        this.metodologia_id = metodologia_id;
    }

    public String getMetodologia_nombre() {
        return metodologia_nombre;
    }

    public void setMetodologia_nombre(String metodologia_nombre) {
        this.metodologia_nombre = metodologia_nombre;
    }

    public String getMetodologia_descripcion() {
        return metodologia_descripcion;
    }

    public void setMetodologia_descripcion(String metodologia_descripcion) {
        this.metodologia_descripcion = metodologia_descripcion;
    }
    
}
