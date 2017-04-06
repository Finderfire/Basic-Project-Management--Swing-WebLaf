/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Kevin
 */
public class clsTablasRender {
    
    //CENTRAR TABLAS
    public static class miRender implements javax.swing.table.TableCellRenderer {
        TableCellRenderer tcr;

        public miRender(TableCellRenderer tcr)
        {
            this.tcr = tcr;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {
            //centrar header de columna
            javax.swing.JComponent wth = (javax.swing.JComponent) tcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            javax.swing.JLabel label = (javax.swing.JLabel)wth;
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            //centrar columna completa
            DefaultTableCellRenderer m = new DefaultTableCellRenderer();
            m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            table.getColumnModel().getColumn(column).setCellRenderer(m);             
            
            return wth;
        }      
    }
    
}
