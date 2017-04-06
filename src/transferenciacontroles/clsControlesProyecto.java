/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transferenciacontroles;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class clsControlesProyecto {
    
    private JPanel pnlContenedor;
    private JLabel lblTitulo;
    private JLabel lblFechaCreacion;

    public clsControlesProyecto(JPanel pnlContenedor, JLabel lblTitulo, JLabel lblFechaCreacion) 
    {
        this.pnlContenedor = pnlContenedor;
        this.lblTitulo = lblTitulo;
        this.lblFechaCreacion = lblFechaCreacion;
        
    }
    
}
