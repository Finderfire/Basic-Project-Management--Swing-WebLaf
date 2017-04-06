/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import com.alee.laf.WebLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de iniciar el sistema.
 * <p>
 * Se instala el LookAndFeel usado por la aplicacion
 * @author      Kevin Montes De Oca V.
 * @version     %I%, %G%
 */
public class clsPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try 
        {
            WebLookAndFeel.install();
            AbrirVentanaInicioSesion();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(clsPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Abre el formulario de Inicio de Sesion {@link frmInicioSesion}
     * <p>
     * Se visualiza el formulario y se centra.        
     */
    private static void AbrirVentanaInicioSesion() {
    
        vistacontrol.frmInicioSesion obj= new vistacontrol.frmInicioSesion();  
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);        
        
    }    
    
}
