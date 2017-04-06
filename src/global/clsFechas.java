/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que maneja metodos globales referentes a fechas.
 * Dichos metodos podran ser usados en cualquier parte dle sistema.
 * @author Kevin Montes De Oca Vizcarra
 */
public class clsFechas {
    
    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    
    /**
     * Retorna la fecha actual del sistema.
     * @return  fecha String de formato dd/MM/yyyy
     */
    public static String ObtenerFechaActual()
    {
        Date hoy = new Date();
        String fecha = formato.format(hoy);        
        return fecha;
    }
    
    
    
}
