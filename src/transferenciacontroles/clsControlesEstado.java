/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transferenciacontroles;

import com.alee.laf.progressbar.WebProgressBar;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author Kevin
 */
public class clsControlesEstado {
        
    private JLabel lblAgrego;
    private JCheckBox cbxAprobadoResponsable;
    private JCheckBox cbxAprobadoJefe;
    private WebProgressBar pbrEstadoEntregable;

    public clsControlesEstado(JLabel lblAgrego, JCheckBox cbxAprobadoResponsable, JCheckBox cbxAprobadoJefe, WebProgressBar pbrEstadoEntregable) {
        this.lblAgrego = lblAgrego;
        this.cbxAprobadoResponsable = cbxAprobadoResponsable;
        this.cbxAprobadoJefe = cbxAprobadoJefe;
        this.pbrEstadoEntregable = pbrEstadoEntregable;
    }
        
}
