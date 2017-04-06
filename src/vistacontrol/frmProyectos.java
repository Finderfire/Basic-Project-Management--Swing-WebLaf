/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistacontrol;

import accesodatos.clsProyectoUsuarioDAO;
import transferenciacontroles.clsControlesProyecto;
import com.alee.extended.menu.DynamicMenuType;
import com.alee.extended.menu.WebDynamicMenu;
import com.alee.extended.menu.WebDynamicMenuItem;
import com.alee.utils.SwingUtils;
import conexion.clsConexionDS;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import transferenciadatos.clsProyectoUsuarioDTO;

/**
 *
 * @author Usuario
 */
public class frmProyectos extends javax.swing.JPanel {

    
    /**
     *  Declaracion de Fuentes para el titulo del proyecto
     *  y la fecha de creacion del proyecto
     */
    private static final Font FUENTE_NOMBRE_PROYECTO = new Font("Droid Sans", Font.BOLD, 14);
    private static final Font FUENTE_FECHA_CREACION = new Font("Droid Sans", Font.PLAIN, 14);
    private frmPrincipal objPrincipal;
    private ArrayList<String> arrayProyectoId = new ArrayList();
    Connection conexion;
    
    /**
     * Creates new form frmProyectos
     */
    public frmProyectos() {
        initComponents();
        
        //declaracion variables
        clsProyectoUsuarioDAO proyectoUsuarioDAO = new clsProyectoUsuarioDAO();
        conexion = clsConexionDS.AbrirConexion();
        ArrayList<clsProyectoUsuarioDTO> arrayProyectoUsuario;
        ArrayList<clsControlesProyecto> arrayControlesProyecto = new ArrayList<>();
                
        //variables de control de filas y columas
        int i = 1;
        int contadorSegundo = 0;
        int contadorTercero = 0;
        int auxCambioFila = 0;
        int auxColumna = 0;
        int auxFila = 0;
        
        //configuracion del panel contenedor (interface)
        srpProyectos.getVerticalScrollBar().setUnitIncrement(16);
        srpProyectos.setViewportBorder(null);        
        pnlProyectos.setLayout(new GridBagLayout());
        GridBagConstraints gblConstraints = new GridBagConstraints();
        
        /**
         * Se llena {@link arrayProyectoUsuario} desde el metodo
         * {@link proyectoUsuarioDAO.ListarProyectoUsuario} donde estaran
         * los proyectos segun el usuario logeado.
         */
        arrayProyectoUsuario = proyectoUsuarioDAO.SeleccionarProyectoUsuario(
                frmPrincipal.usuarioId, 
                conexion
                );
        
        /**
         * Se recorre el array {@link arrayProyectoUsuario} donde se obtienen 
         * los proyectos a traves de un for each.
         */         
        for(clsProyectoUsuarioDTO pu : arrayProyectoUsuario) 
        {
            //si el estado es activo
            if(pu.getProyecto_estado().equals("activo"))
            {
                //Creacion en tiempo de Ejecucion
                //Paneles + Layouts
                final JPanel pnlContenedorGeneral = new JPanel();
                pnlContenedorGeneral.setLayout(new java.awt.GridLayout(0, 1));                
                pnlContenedorGeneral.setMinimumSize(new Dimension(200,200));
                pnlContenedorGeneral.setBorder(BorderFactory.createLineBorder(
                        new Color(179,229,252)
                        )); 
                final JPanel pnlContenedorArriba = new JPanel();
                pnlContenedorArriba.setLayout(new java.awt.GridBagLayout());
                final JPanel pnlContenedorAbajo = new JPanel();
                pnlContenedorAbajo.setLayout(new java.awt.GridBagLayout());

                //Labels
                final JLabel lblNombreProyecto = new JLabel(pu.getProyecto_nombre());
                lblNombreProyecto.setFont(FUENTE_NOMBRE_PROYECTO);                
                pnlContenedorArriba.add(lblNombreProyecto, new GridBagConstraints());

                final JLabel lblFechaCreacion = new JLabel("<html><p>Fecha de Creación:<p>"
                                    + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                    pu.getProyecto_fecha_creacion()+"</html>");
                lblFechaCreacion.setAlignmentY(javax.swing.SwingConstants.CENTER);
                lblFechaCreacion.setFont(FUENTE_FECHA_CREACION);
                pnlContenedorAbajo.add(lblFechaCreacion, new GridBagConstraints());

                //Se agregan los paneles "Arriba y Abajo" al "pnlContenedorGeneral"
                pnlContenedorGeneral.add(pnlContenedorArriba);
                pnlContenedorGeneral.add(pnlContenedorAbajo);

                //Agregar pnlContenedorGeneral al GridBagLayout de pnlProyectos
                if(i == 1) 
                {
                    //Fill: rellena ambos espacios de la celda (both)
                    gblConstraints.fill = GridBagConstraints.BOTH;
                    //Insets: arriba - izquierda - abajo - derecha
                    gblConstraints.insets = new Insets(15, 15, 15, 15);
                    //Gridx - GridY posiciones, columas y fila que ocupara
                    //el componente se centra
                    gblConstraints.gridx = 0;
                    gblConstraints.gridy = 0;
                    //GidWidth - GridHeight (ancho - alto), cuantas celdas ocupa
                    //el componente se estira
                    gblConstraints.gridwidth = 1;
                    gblConstraints.gridheight = 1;
                    gblConstraints.weightx = 1;
                    gblConstraints.weighty = 1;
                    //Contadores
                    contadorSegundo = 2;
                    contadorTercero = 3;
                    auxCambioFila = 4;
                    auxColumna = 1;
                    auxFila = 0;
                    pnlProyectos.add(pnlContenedorGeneral,gblConstraints);
                    //System.out.println("pos "+gblConstraints.gridx+","+gblConstraints.gridy);

                }
                else if(i == contadorSegundo) 
                {
                    //Fill: rellena ambos espacios de la celda (both)
                    gblConstraints.fill = GridBagConstraints.BOTH;
                    //Insets: arriba - izquierda - abajo - derecha
                    gblConstraints.insets = new Insets(15, 15, 15, 15);
                    //Gridx - GridY posiciones, columas y fila que ocupara
                    //el componente se centra    
                    switch (auxColumna) {
                        case 0:
                            gblConstraints.gridx = auxColumna;
                            if(auxCambioFila > i) {

                                gblConstraints.gridy = auxFila;                                        
                            }
                            else {

                                gblConstraints.gridy = auxFila+1;
                            }
                            auxColumna=1;
                            break;  
                        case 1:
                            gblConstraints.gridx = auxColumna;
                            if(auxCambioFila > i) {
                                gblConstraints.gridy = auxFila;                                        
                            }
                            else {
                                gblConstraints.gridy = auxFila+1;
                            }
                            auxColumna=2;
                            break;  
                        case 2:
                            gblConstraints.gridx = auxColumna;
                            if (auxCambioFila > i) {
                                gblConstraints.gridy = auxFila;
                            } else {
                                gblConstraints.gridy = auxFila + 1;
                            }
                            auxColumna=0;
                            auxFila++;
                            auxCambioFila += 3;
                            break;     
                        default:
                            break;
                    }                            

                    //GidWidth - GridHeight (ancho - alto), cuantas celdas ocupa
                    //el componente se estira
                    gblConstraints.gridwidth = 1;
                    gblConstraints.gridheight = 1;

                    //Contadores
                    contadorSegundo += 2;

                    gblConstraints.weightx = 1;
                    gblConstraints.weighty = 1;
                    pnlProyectos.add(pnlContenedorGeneral,gblConstraints);
                    //System.out.println("i: "+i);
                    //System.out.println("pos "+gblConstraints.gridx+","+gblConstraints.gridy);

                }
                else if (i == contadorTercero) 
                {

                    //Fill: rellena ambos espacios de la celda (both)
                    gblConstraints.fill = GridBagConstraints.BOTH;
                    //Insets: arriba - izquierda - abajo - derecha
                    gblConstraints.insets = new Insets(15, 15, 15, 15);
                    //Gridx - GridY posiciones, columas y fila que ocupara
                    //el componente se centra    
                    switch (auxColumna) {
                        case 0:
                            gblConstraints.gridx = auxColumna;
                            if (auxCambioFila > i) {
                                gblConstraints.gridy = auxFila;
                            } else {
                                gblConstraints.gridy = auxFila + 1;
                            }
                            auxColumna = 1;
                            break;
                        case 1:
                            gblConstraints.gridx = auxColumna;
                            if (auxCambioFila > i) {
                                gblConstraints.gridy = auxFila;
                            } else {
                                gblConstraints.gridy = auxFila + 1;
                            }
                            auxColumna = 2;
                            break;
                        case 2:
                            gblConstraints.gridx = auxColumna;
                            if (auxCambioFila > i) {
                                gblConstraints.gridy = auxFila;
                            } else {
                                gblConstraints.gridy = auxFila + 1;
                            }
                            auxColumna = 0;
                            auxFila++;
                            auxCambioFila += 3;
                            break;
                        default:
                            break;
                    }                         

                    //GidWidth - GridHeight (ancho - alto), cuantas celdas ocupa
                    //el componente se estira
                    gblConstraints.gridwidth = 1;
                    gblConstraints.gridheight = 1;

                    //Contadores                        
                    contadorTercero += 2;                        
                    gblConstraints.weightx = 1;
                    gblConstraints.weighty = 1;                        
                    pnlProyectos.add(pnlContenedorGeneral,gblConstraints);
                    //System.out.println("i: "+i);
                    //System.out.println("pos "+gblConstraints.gridx+","+gblConstraints.gridy);
                }

                final clsControlesProyecto controlesProyecto = new clsControlesProyecto(
                                    pnlContenedorGeneral,lblNombreProyecto,lblFechaCreacion);

                arrayControlesProyecto.add(controlesProyecto);
                arrayProyectoId.add(pu.getProyecto_id());
                //ActionListener
                pnlContenedorGeneral.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if ( SwingUtils.isLeftMouseButton ( e ) ) {
                            createMenu ().showMenu ( e.getComponent (), e.getPoint () );
                            pnlIzquierda.repaint();
                            frmPrincipal.proyectoId = 
                                    arrayProyectoId.get
                                    (
                                        arrayControlesProyecto.indexOf(
                                                    controlesProyecto)
                                    );
                            System.out.println("Seleccionado: "+frmPrincipal.proyectoId);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        pnlContenedorGeneral.setBorder(BorderFactory.createLineBorder(new Color(3,169,244))); 
                        pnlIzquierda.repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        pnlContenedorGeneral.setBorder(BorderFactory.createLineBorder(new Color(179,229,252))); 
                        pnlIzquierda.repaint();
                    }
                });

                i++;
            }
        } 
        clsConexionDS.CerrarConexion(conexion);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlPrincipal = new javax.swing.JPanel();
        pnlDerecha = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblUltimaActualizacion1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblLiderProyecto1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblEquipoProyecto1 = new javax.swing.JLabel();
        btnNuevoProyecto = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlIzquierda = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        srpProyectos = new javax.swing.JScrollPane();
        pnlProyectos = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new java.awt.BorderLayout());

        pnlPrincipal.setPreferredSize(new java.awt.Dimension(1000, 600));
        pnlPrincipal.setLayout(new java.awt.GridBagLayout());

        pnlDerecha.setBackground(new java.awt.Color(255, 255, 0));
        pnlDerecha.setPreferredSize(new java.awt.Dimension(320, 753));
        pnlDerecha.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(161, 460));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel7.setText("Última Actualización:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(23, 0, 0, 0);
        jPanel6.add(jLabel7, gridBagConstraints);

        lblUltimaActualizacion1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lblUltimaActualizacion1.setText("11/11/2016");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        jPanel6.add(lblUltimaActualizacion1, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel6.setText("Líder del Proyecto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(23, 0, 0, 0);
        jPanel6.add(jLabel6, gridBagConstraints);

        lblLiderProyecto1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lblLiderProyecto1.setText("Gueiler Sosa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        jPanel6.add(lblLiderProyecto1, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel9.setText("Equipo del Proyecto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(23, 0, 0, 0);
        jPanel6.add(jLabel9, gridBagConstraints);

        lblEquipoProyecto1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lblEquipoProyecto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipoProyecto1.setText("<html>\n<div align=\"center\">\nPersona1<br>\nPersona 2<br>\nPersona 3<br>\nPersona 4<br>\n</div>\n</html>");
        lblEquipoProyecto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblEquipoProyecto1.setPreferredSize(new java.awt.Dimension(158, 68));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        jPanel6.add(lblEquipoProyecto1, gridBagConstraints);

        btnNuevoProyecto.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnNuevoProyecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/x24/library-plus.png"))); // NOI18N
        btnNuevoProyecto.setText("NUEVO PROYECTO");
        btnNuevoProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProyectoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(38, 0, 0, 0);
        jPanel6.add(btnNuevoProyecto, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(68, 0, 0, 0);
        jPanel6.add(jSeparator3, gridBagConstraints);

        pnlDerecha.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(310, 10));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(179, 229, 252));
        jPanel3.setPreferredSize(new java.awt.Dimension(310, 100));

        jLabel4.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel4.setText("Información del Proyecto");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(20, 20, 20))
        );

        jPanel4.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        pnlDerecha.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 15);
        pnlPrincipal.add(pnlDerecha, gridBagConstraints);

        pnlIzquierda.setBackground(new java.awt.Color(255, 255, 255));
        pnlIzquierda.setPreferredSize(new java.awt.Dimension(500, 308));
        pnlIzquierda.setLayout(new java.awt.BorderLayout());

        pnlTitulo.setBackground(new java.awt.Color(255, 255, 255));
        pnlTitulo.setPreferredSize(new java.awt.Dimension(711, 110));
        pnlTitulo.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(179, 229, 252));
        jPanel2.setPreferredSize(new java.awt.Dimension(671, 100));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 22)); // NOI18N
        jLabel2.setText("MIS PROYECTOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(430, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(21, 21, 21))
        );

        pnlTitulo.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTitulo.add(jPanel1, java.awt.BorderLayout.CENTER);

        pnlIzquierda.add(pnlTitulo, java.awt.BorderLayout.PAGE_START);

        srpProyectos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        srpProyectos.setToolTipText("");

        pnlProyectos.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlProyectosLayout = new javax.swing.GroupLayout(pnlProyectos);
        pnlProyectos.setLayout(pnlProyectosLayout);
        pnlProyectosLayout.setHorizontalGroup(
            pnlProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );
        pnlProyectosLayout.setVerticalGroup(
            pnlProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        srpProyectos.setViewportView(pnlProyectos);

        pnlIzquierda.add(srpProyectos, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 10);
        pnlPrincipal.add(pnlIzquierda, gridBagConstraints);

        add(pnlPrincipal, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProyectoActionPerformed
        
        objPrincipal.AbrirModalNuevoProyecto();
        
    }//GEN-LAST:event_btnNuevoProyectoActionPerformed

    /**
     * Transfiere el formulario principal como objeto.
     * Esto permite hacer uso de las funciones, métodos o propiedades
     * del formulario padre, estando activo el formulario hijo.
     * 
     * @param xFormulario nombre del formulario Padre.
     */
    public void FormularioPrincipal(frmPrincipal xFormulario) {
        this.objPrincipal = xFormulario;
    }
    
    protected WebDynamicMenu createMenu ()
    {
        
        final WebDynamicMenu menu = new WebDynamicMenu ();        
        menu.setType ( DynamicMenuType.shutter );
        menu.setHideType ( DynamicMenuType.roll );
        menu.setRadius ( 145 );
        menu.setStepProgress ( 0.07f );

        final int items = 4;
        for ( int i = 1; i <= items; i++ ) {
            switch (i) {
                case 1:
                    {
                        final ImageIcon icon = new ImageIcon(getClass().getResource("/recursos/iconos/x84/administrar-proyecto.png"));
                        final ActionListener action = (ActionEvent e) -> {
                            
                            objPrincipal.AbrirModalAdministrarProyecto();
                            
                        };      final WebDynamicMenuItem item = new WebDynamicMenuItem ( icon, action );
                        item.setMargin ( new Insets ( 8, 8, 8, 8 ) );
                        menu.addItem ( item );
                        item.setBorderColor(new Color(79,195,247));
                        break;
                    }               
                case 2:
                    {
                        final ImageIcon icon = new ImageIcon(getClass().getResource("/recursos/iconos/x84/editar-proyecto.png"));
                        final ActionListener action = (ActionEvent e) -> {
                            
                            objPrincipal.AbrirModalEditarProyecto();
                            
                        };      
                        final WebDynamicMenuItem item = new WebDynamicMenuItem ( icon, action );
                        item.setMargin ( new Insets ( 8, 8, 8, 8 ) ); 
                        item.setBorderColor(new Color(79,195,247));
                        menu.addItem ( item );
                        break;
                    }
                case 3:
                    {
                        final ImageIcon icon = new ImageIcon(getClass().getResource("/recursos/iconos/x84/archivar-proyecto.png"));
                        final ActionListener action = (ActionEvent e) -> {
                           
                            menu.hideMenu();
                            Window parentWindow = SwingUtilities.windowForComponent(this);
                            frmArchivarProyecto archivar = new frmArchivarProyecto(parentWindow);
                            archivar.setModal(true);     
                            archivar.FormularioPrincipal(objPrincipal);
                            archivar.setVisible(true);
                            archivar.toFront();
                            
                        };      final WebDynamicMenuItem item = new WebDynamicMenuItem ( icon, action );
                        item.setMargin ( new Insets ( 8, 8, 8, 8 ) );   
                        item.setBorderColor(new Color(79,195,247));
                        menu.addItem ( item );
                        break;
                    }
                    
                case 4:
                    {
                        final ImageIcon icon = new ImageIcon(getClass().getResource("/recursos/iconos/x84/configurar-entregable.png"));
                        final ActionListener action = (ActionEvent e) -> {
                           
                            menu.hideMenu();
                            objPrincipal.AbrirModalConfigurarEntregable();                           
                            
                        };      final WebDynamicMenuItem item = new WebDynamicMenuItem ( icon, action );
                        item.setMargin ( new Insets ( 8, 8, 8, 8 ) );   
                        item.setBorderColor(new Color(79,195,247));
                        menu.addItem ( item );
                        break;
                    }
                default:
                    break;    
            }
            
        }

        return menu;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevoProyecto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblEquipoProyecto1;
    private javax.swing.JLabel lblLiderProyecto1;
    private javax.swing.JLabel lblUltimaActualizacion1;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JPanel pnlIzquierda;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlProyectos;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JScrollPane srpProyectos;
    // End of variables declaration//GEN-END:variables
}
