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
public class clsCambioDTO {
    
    private String solicitud_id;
    private String solicitud_fecha;
    private String solicitud_cliente;
    private String solicitud_objetivo;
    private String solicitud_descripcion;
    private String proyecto_id;

    public clsCambioDTO(String solicitud_id, String solicitud_fecha, String solicitud_cliente, String solicitud_objetivo, String solicitud_descripcion, String proyecto_id) {
        this.solicitud_id = solicitud_id;
        this.solicitud_fecha = solicitud_fecha;
        this.solicitud_cliente = solicitud_cliente;
        this.solicitud_objetivo = solicitud_objetivo;
        this.solicitud_descripcion = solicitud_descripcion;
        this.proyecto_id = proyecto_id;
    }

    public String getSolicitud_id() {
        return solicitud_id;
    }

    public void setSolicitud_id(String solicitud_id) {
        this.solicitud_id = solicitud_id;
    }

    public String getSolicitud_fecha() {
        return solicitud_fecha;
    }

    public void setSolicitud_fecha(String solicitud_fecha) {
        this.solicitud_fecha = solicitud_fecha;
    }

    public String getSolicitud_cliente() {
        return solicitud_cliente;
    }

    public void setSolicitud_cliente(String solicitud_cliente) {
        this.solicitud_cliente = solicitud_cliente;
    }

    public String getSolicitud_objetivo() {
        return solicitud_objetivo;
    }

    public void setSolicitud_objetivo(String solicitud_objetivo) {
        this.solicitud_objetivo = solicitud_objetivo;
    }

    public String getSolicitud_descripcion() {
        return solicitud_descripcion;
    }

    public void setSolicitud_descripcion(String solicitud_descripcion) {
        this.solicitud_descripcion = solicitud_descripcion;
    }

    public String getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(String proyecto_id) {
        this.proyecto_id = proyecto_id;
    }
    
    
    
}
