/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistacontrol;

import accesodatos.clsEntregableRevisionDAO;
import accesodatos.clsEntregableVersionDAO;
import accesodatos.clsMetodologiaDAO;
import accesodatos.clsProyectoEntregableDAO;
import accesodatos.clsProyectoEtapaDAO;
import accesodatos.clsProyectoUsuarioEntregableDAO;
import com.alee.extended.layout.TableLayout;
import com.alee.laf.progressbar.WebProgressBar;
import conexion.clsConexionDS;
import global.clsTablasRender;
import global.clsTablasRender.miRender;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import transferenciacontroles.clsControlesEstado;
import transferenciadatos.clsEntregableRevisionDTO;
import transferenciadatos.clsEntregableVersionDTO;
import transferenciadatos.clsProyectoEntregableDTO;


/**
 * Esta clase administrara el proyecto a traves del estado, revisiones y versiones.
 * <p>
 * Solo los encargados de entregable podran configurar enlace de entregable.
 * @author Kevin
 */
public class frmAdministrarProyecto extends javax.swing.JPanel {
    
    private static final Font FUENTE_DROID = new Font("Droid Sans", Font.PLAIN, 14);
    private static final Font FUENTE_DROID_BOLD = new Font("Droid Sans", Font.BOLD, 14);
    public static String entregableSeleccionadoId;
    Connection conexion;
    String metodologiaId;
    String entregableId;
    String proyectoUsuario_Id;
    String proyectoEntregable_Id;
    String proyectoEntregableId = "";
    
    DefaultMutableTreeNode dmtnMetodologia;
    DefaultMutableTreeNode dmtnEtapa;
    DefaultMutableTreeNode dmtnEntregable;
    
    ArrayList<String> arrayProyectoEntregableId = new ArrayList();
    ArrayList<String> arrayEntregableIdAux = new ArrayList();
    ArrayList<String> arrayEtapaId = new ArrayList();
    ArrayList<String> arrayEntregableIdArbol = new ArrayList();
    ArrayList<String> arrayEntregableIdEstado = new ArrayList();
    ArrayList<String> arrayAgregoDocumento = new ArrayList();
    ArrayList<clsControlesEstado> arrayControlesEstado = new ArrayList<>();
    /**
     * Creates new form frmNuevoProyecto
     */
    public frmAdministrarProyecto() {
        
        initComponents();        
        srpPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        
        //declaracion
        clsProyectoEntregableDAO  proyectoEntregableDAO;
        
        ResultSet resultado = null;
        int limite = 0;
        Color rojo = new Color(229,115,115);
        Color naranja = new Color(255,183,77);
        Color amarillo = new Color(255,241,118);
        Color verde = new Color(129,199,132);
        
        ///////// ESTADO /////////      
        proyectoEntregableDAO = new clsProyectoEntregableDAO();
        conexion = clsConexionDS.AbrirConexion();        
        try 
        {
            resultado = proyectoEntregableDAO.SeleccionarProyectoEntregable(frmPrincipal.proyectoId, conexion);
            while(resultado.next()) 
            {
                limite++;
                arrayEntregableIdEstado.add(resultado.getString("entregable_id"));
            }            
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(frmAdministrarProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }  
        clsConexionDS.CerrarConexion(conexion);
        //matriz del table layout constraints
        double size[][] = new double[2][10];        
        size[0][0]=40; //espacio blanco
        size[0][1]=100; //Entregable
        size[0][2]=40; //espacio blanco
        size[0][3]=150;//Se agrego documento
        size[0][4]=40; //espacio blanco
        size[0][5]=150; //Aprobado responsable
        size[0][6]=40; //espacio blanco
        size[0][7]=150; //Aprobado jefe
        size[0][8]=40; //espacio en blanco
        size[0][9]=200; //estado del entregable          
        //se define altura de controles                 
        for(int j=0;j<=limite+1;j++)
        {
            switch (j) 
            {
                case 0:
                    //ancho primera fila es 10 (espacio blanco)
                    size[1][j]= 30;    
                    break;
                case 1:
                    //ancho de la segunda fila
                    size[1][j]= 70;
                    break;
                
            //las demas quedan con 20
                default:
                    size[1][j]= 40;
                    break;
            }
        }
        pnlContenedor.setLayout( new TableLayout(size));        
        //Se definen y agregan los titulos
        JLabel lblEntregableTitulo = new JLabel("Entregable");        
        JLabel lblAgregoTitulo = new JLabel("<html><div align=\"center\">¿Se agregó <br> un documento?<div></html>");
        JLabel lblAprobadoResponsableTitulo = new JLabel("<html><div align=\"center\">Aprobado por<br>el responsable<div></html>");
        JLabel lblAprobadoJefeTitulo = new JLabel("<html><div align=\"center\">Aprobado por el<br>jefe de proyecto<div></html>");
        JLabel lblEstadoEntregableTitulo = new JLabel("Estado del entregable");
        lblEntregableTitulo.setFont(FUENTE_DROID_BOLD);
        lblAgregoTitulo.setFont(FUENTE_DROID_BOLD);
        lblAprobadoResponsableTitulo.setFont(FUENTE_DROID_BOLD);
        lblAprobadoJefeTitulo.setFont(FUENTE_DROID_BOLD);
        lblEstadoEntregableTitulo.setFont(FUENTE_DROID_BOLD);        
        pnlContenedor.add(lblEntregableTitulo,"1,"+1+",c,c");
        pnlContenedor.add(lblAgregoTitulo,"3,"+1+",c,t");
        pnlContenedor.add(lblAprobadoResponsableTitulo,"5,"+1+",c,t");
        pnlContenedor.add(lblAprobadoJefeTitulo,"7,"+1+",c,t");
        pnlContenedor.add(lblEstadoEntregableTitulo,"9,"+1+",c,c");        
        //se define y agrega el contenido
        try 
        {
            int i=2;
            conexion = clsConexionDS.AbrirConexion();
            resultado = proyectoEntregableDAO.SeleccionarProyectoEntregable(frmPrincipal.proyectoId, conexion);
            JLabel lblAgrego;
            while(resultado.next())
            {                
                JLabel lblEntregable = new JLabel(resultado.getString("entregable_nombre"));
                //comprueba si se a agregado un documento (enlace)
                if(resultado.getString("proyectoentregable_enlace").equals("https://docs.google.com/?hl=es"))
                {
                    lblAgrego = new JLabel("NO");
                    arrayAgregoDocumento.add("NO");
                }
                else
                {
                    lblAgrego = new JLabel("SI");
                    arrayAgregoDocumento.add("SI");
                }                
                //comprueba si el responsable a aprobado el documento
                JCheckBox cbxAprobadoResponsable = new JCheckBox("");
                if(resultado.getString("proyectoentregable_aprobado_responsable").equals("1"))
                {
                    cbxAprobadoResponsable.setSelected(true);
                }
                else
                {
                    cbxAprobadoResponsable.setSelected(false);
                }
                //comprueba si el jefe de proyecto a aprobado el documento
                JCheckBox cbxAprobadoJefe = new JCheckBox("");
                if(resultado.getString("proyectoentregable_aprobado_jefe").equals("1"))
                {
                    cbxAprobadoJefe.setSelected(true);
                }
                else
                {
                    cbxAprobadoJefe.setSelected(false);
                }  
                //comprueba el estado del entregable por operaciones
                 WebProgressBar pbrEstadoEntregable = new WebProgressBar();
                if(lblAgrego.getText().equals("SI") && cbxAprobadoResponsable.isSelected() && cbxAprobadoJefe.isSelected())
                {
                    pbrEstadoEntregable.setFont(FUENTE_DROID);
                    pbrEstadoEntregable.setValue(100);
                    pbrEstadoEntregable.setBgTop(verde);
                    pbrEstadoEntregable.setBgBottom(verde);
                    pbrEstadoEntregable.setProgressTopColor(verde);
                    pbrEstadoEntregable.setProgressBottomColor(verde);
                    pbrEstadoEntregable.setStringPainted(true); 
                }
                else if(lblAgrego.getText().equals("SI") && cbxAprobadoResponsable.isSelected() && cbxAprobadoJefe.isSelected() == false)
                {
                    pbrEstadoEntregable.setFont(FUENTE_DROID);
                    pbrEstadoEntregable.setValue(66);
                    pbrEstadoEntregable.setBgTop(amarillo);
                    pbrEstadoEntregable.setBgBottom(amarillo);
                    pbrEstadoEntregable.setProgressTopColor(amarillo);
                    pbrEstadoEntregable.setProgressBottomColor(amarillo);
                    pbrEstadoEntregable.setStringPainted(true); 
                }
                else if(lblAgrego.getText().equals("SI") && cbxAprobadoResponsable.isSelected() == false && cbxAprobadoJefe.isSelected() == false)
                {
                    pbrEstadoEntregable.setFont(FUENTE_DROID);
                    pbrEstadoEntregable.setValue(33);
                    pbrEstadoEntregable.setBgTop(naranja);
                    pbrEstadoEntregable.setBgBottom(naranja);
                    pbrEstadoEntregable.setProgressTopColor(naranja);
                    pbrEstadoEntregable.setProgressBottomColor(naranja);
                    pbrEstadoEntregable.setStringPainted(true); 
                }
                else if(lblAgrego.getText().equals("NO") && cbxAprobadoResponsable.isSelected() == false && cbxAprobadoJefe.isSelected() == false)
                {
                    pbrEstadoEntregable.setFont(FUENTE_DROID);
                    pbrEstadoEntregable.setValue(0);
                    pbrEstadoEntregable.setBgTop(rojo);
                    pbrEstadoEntregable.setBgBottom(rojo);
                    pbrEstadoEntregable.setProgressTopColor(rojo);
                    pbrEstadoEntregable.setProgressBottomColor(rojo);
                    pbrEstadoEntregable.setStringPainted(true); 
                }
                
               
                               
                lblEntregable.setFont(FUENTE_DROID);
                lblAgrego.setFont(FUENTE_DROID);
                cbxAprobadoResponsable.setFont(FUENTE_DROID);                 
                cbxAprobadoJefe.setFont(FUENTE_DROID);              
                pbrEstadoEntregable.setFont(FUENTE_DROID);
                pnlContenedor.add(lblEntregable,"1,"+i);
                pnlContenedor.add(lblAgrego,"3,"+i+",c,c");
                pnlContenedor.add(cbxAprobadoResponsable,"5,"+i+",c,c");
                pnlContenedor.add(cbxAprobadoJefe,"7,"+i+",c,c");
                pnlContenedor.add(pbrEstadoEntregable,"9,"+i+",c,c");                
                i++;
                
                //Se agregan los controles para controlar índices
                final clsControlesEstado controlesEstado = new clsControlesEstado (
                        
                        lblAgrego,
                        cbxAprobadoResponsable,
                        cbxAprobadoJefe,
                        pbrEstadoEntregable
                );
                arrayControlesEstado.add(controlesEstado);
                
                //se agregan eventos a los controles
                cbxAprobadoResponsable.addItemListener(new ItemListener() {
                    boolean aceptoAprobadoResponsable = false;
                    boolean actualizarRegistro = true;
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        
                        int indice = arrayControlesEstado.indexOf(controlesEstado);
                        entregableSeleccionadoId = arrayEntregableIdEstado.get(indice);
                        if(aceptoAprobadoResponsable == false)
                        {
                            if(e.getStateChange() == ItemEvent.SELECTED)
                            {
                                if(arrayAgregoDocumento.get(indice).equals("SI"))
                                {
                                    int option = JOptionPane.showConfirmDialog(null, 
                                            "Se generará una revisión del documento."
                                            + " ¿Desea continuar?","Generar revisión",
                                            JOptionPane.YES_NO_OPTION);

                                    if(option == JOptionPane.YES_NO_OPTION)
                                    {   
                                        //se cambia la variable de control a true
                                        aceptoAprobadoResponsable = true;
                                        //se da check de nuevo porque el ConfirmDialog
                                        //interrumpe el proceso
                                        cbxAprobadoResponsable.setSelected(true);
                                        //se llama al Dialog de revision
                                        AbrirDialogRevision(); 
                                        //se actualiza el registro de la bd a 1
                                        clsProyectoEntregableDAO proyectoEntregableDAO;
                                        clsProyectoEntregableDTO proyectoEntregableDTO;   
                                        conexion = clsConexionDS.AbrirConexion();
                                        proyectoEntregableDAO = new clsProyectoEntregableDAO();
                                        proyectoEntregableDTO = new clsProyectoEntregableDTO (
                                                                entregableSeleccionadoId,
                                                                "1",
                                                                ""
                                                            );
                                        proyectoEntregableDAO.ActualizarProyectoEntregableAprobadoResponsable(
                                                    proyectoEntregableDTO, conexion
                                                ); 
                                        clsConexionDS.CerrarConexion(conexion);
                                    }
                                    else
                                    {
                                        aceptoAprobadoResponsable = false;
                                        cbxAprobadoResponsable.setSelected(false);
                                        actualizarRegistro = false;
                                    }
                                }
                                else
                                {
                                    cbxAprobadoResponsable.setSelected(false);
                                }
                                
                            }
                            else if(e.getStateChange() == ItemEvent.DESELECTED)
                            {
                                if(actualizarRegistro)
                                {
                                    //se actualiza el registro de la bd a 0
                                    clsProyectoEntregableDAO proyectoEntregableDAO;
                                    clsProyectoEntregableDTO proyectoEntregableDTO; 
                                    conexion = clsConexionDS.AbrirConexion();
                                    proyectoEntregableDAO = new clsProyectoEntregableDAO();
                                    proyectoEntregableDTO = new clsProyectoEntregableDTO (
                                                            entregableSeleccionadoId,
                                                            "0",
                                                            ""
                                                        );
                                    proyectoEntregableDAO.ActualizarProyectoEntregableAprobadoResponsable(
                                                proyectoEntregableDTO, conexion
                                            );  
                                    clsConexionDS.CerrarConexion(conexion);
                                }                                
                            }
                        }
                    }
                    
                });
                
                
                cbxAprobadoJefe.addItemListener(new ItemListener() {
                    boolean aceptoAprobadoJefe = false;
                    boolean actualizarRegistro = true;
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        int indice = arrayControlesEstado.indexOf(controlesEstado);
                        entregableSeleccionadoId = arrayEntregableIdEstado.get(indice);
                        //condicional
                        if(aceptoAprobadoJefe == false)
                        {            
                            //solo si el check del encargado esta seleccionado
                            if(cbxAprobadoResponsable.isSelected())
                            {
                                if(e.getStateChange() == ItemEvent.SELECTED)
                                {                                    
                                    int option = JOptionPane.showConfirmDialog(null, 
                                                "Se generará una versión del documento."
                                                + " ¿Desea continuar?","Generar versión",
                                                JOptionPane.YES_NO_OPTION);

                                    if(option == JOptionPane.YES_NO_OPTION)
                                    {   
                                        //se cambia la variable de control a true
                                        aceptoAprobadoJefe = true;
                                        //se da check de nuevo porque el ConfirmDialog
                                        //interrumpe el proceso
                                        cbxAprobadoJefe.setSelected(true);
                                        //se abre el Dialog para version
                                        AbrirDialogVersion(); 
                                        //se cambia el estado a 1
                                        clsProyectoEntregableDAO proyectoEntregableDAO;
                                        clsProyectoEntregableDTO proyectoEntregableDTO; 
                                        conexion = clsConexionDS.AbrirConexion();
                                        proyectoEntregableDAO = new clsProyectoEntregableDAO();
                                        proyectoEntregableDTO = new clsProyectoEntregableDTO (
                                                            entregableSeleccionadoId,
                                                            "",
                                                            "1"
                                                        );
                                        proyectoEntregableDAO.ActualizarProyectoEntregableAprobadoJefe(
                                                proyectoEntregableDTO, conexion
                                            );  
                                        clsConexionDS.CerrarConexion(conexion);
                                    }
                                    else
                                    {
                                        //si no acepta conserva el unchecked
                                        aceptoAprobadoJefe = false;
                                        cbxAprobadoJefe.setSelected(false);
                                        actualizarRegistro = false;
                                    }
                                }
                                else if(e.getStateChange() == ItemEvent.DESELECTED)
                                {
                                    if(actualizarRegistro)
                                    {
                                        //se actualiza el registro de la bd a 0
                                        clsProyectoEntregableDAO proyectoEntregableDAO;
                                        clsProyectoEntregableDTO proyectoEntregableDTO;
                                        conexion = clsConexionDS.AbrirConexion();
                                        proyectoEntregableDAO = new clsProyectoEntregableDAO();
                                        proyectoEntregableDTO = new clsProyectoEntregableDTO (
                                                                entregableSeleccionadoId,
                                                                "",
                                                                "0"
                                                            );
                                        proyectoEntregableDAO.ActualizarProyectoEntregableAprobadoJefe(
                                                    proyectoEntregableDTO, conexion
                                            ); 
                                        clsConexionDS.CerrarConexion(conexion);
                                    }                                     
                                }
                            }
                            else
                            {
                                aceptoAprobadoJefe = false;
                                cbxAprobadoJefe.setSelected(false);
                            }
                        }
                    }
                    
                });
                
            }
            
        } catch (Exception ex) 
        {
            Logger.getLogger(frmAdministrarProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ///// CARGAR ARBOL /////        
        ObtenerMetodologiaProyecto();
        ObtenerProyectoEntregable();
        CargarArbolMetodologia();
        ///// CARGAR COMBO Y ENTREGABLE /////        
        CargarComboEtapa();
        clsConexionDS.CerrarConexion(conexion);
        CargarListaEntregable();        
        
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

        pnlTitulo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlPrincipalPadre = new javax.swing.JPanel();
        srpPrincipal = new javax.swing.JScrollPane();
        pnlPrincipal = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pnlEstadoTitulo = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlEstadoCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlContenedor = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlRevisionVersion = new javax.swing.JPanel();
        pnlEquipoProyectoTitlo = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnlEquipoProyectoCuerpo = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        treEntregable = new com.alee.laf.tree.WebTree();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRevision = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVersion = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btnEnlaceRevision = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnEnlaceVersion = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtEnlaceVersion = new javax.swing.JTextField();
        txtEnlaceRevision = new javax.swing.JTextField();
        pnlEntregable = new javax.swing.JPanel();
        pnlEquipoProyectoTitlo1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlEquipoProyectoCuerpo1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmbEtapa = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstEntregable = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        txtEnlaceEntregable = new javax.swing.JTextField();
        btnAbrirEnlaceEntregable = new javax.swing.JButton();
        btnEditarEnlaceEntregable = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblParticipante = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtParticipanteRol = new javax.swing.JTextArea();
        btnGuardarRol = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        pnlTitulo.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(179, 229, 252));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 22)); // NOI18N
        jLabel2.setText("ADMINISTRAR PROYECTO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(713, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(21, 21, 21))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        pnlTitulo.add(jPanel1, gridBagConstraints);

        add(pnlTitulo, java.awt.BorderLayout.PAGE_START);

        pnlPrincipalPadre.setLayout(new java.awt.GridBagLayout());

        srpPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        srpPrincipal.setBorder(null);

        pnlPrincipal.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        pnlEstadoTitulo.setBackground(new java.awt.Color(255, 255, 0));
        pnlEstadoTitulo.setPreferredSize(new java.awt.Dimension(970, 110));
        pnlEstadoTitulo.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());
        jPanel7.add(jSeparator1, java.awt.BorderLayout.CENTER);

        pnlEstadoTitulo.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel4.setText("Estado del proyecto");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addContainerGap(997, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(20, 20, 20))
        );

        pnlEstadoTitulo.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(pnlEstadoTitulo, java.awt.BorderLayout.PAGE_START);

        pnlEstadoCuerpo.setBackground(new java.awt.Color(255, 255, 255));
        pnlEstadoCuerpo.setMinimumSize(new java.awt.Dimension(516, 340));
        pnlEstadoCuerpo.setPreferredSize(new java.awt.Dimension(500, 450));
        pnlEstadoCuerpo.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1030, 400));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1030, 400));
        jScrollPane1.setRequestFocusEnabled(false);

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedor.setPreferredSize(new java.awt.Dimension(1030, 900));

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1030, Short.MAX_VALUE)
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlContenedor);

        pnlEstadoCuerpo.add(jScrollPane1, new java.awt.GridBagConstraints());

        jPanel3.add(pnlEstadoCuerpo, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        pnlPrincipal.add(jPanel3, gridBagConstraints);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        pnlRevisionVersion.setBackground(new java.awt.Color(255, 255, 255));
        pnlRevisionVersion.setLayout(new java.awt.BorderLayout());

        pnlEquipoProyectoTitlo.setBackground(new java.awt.Color(255, 255, 255));
        pnlEquipoProyectoTitlo.setPreferredSize(new java.awt.Dimension(970, 110));
        pnlEquipoProyectoTitlo.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel9.add(jSeparator2, java.awt.BorderLayout.CENTER);

        pnlEquipoProyectoTitlo.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel7.setText("Revisiones y versiones");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addContainerGap(973, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(20, 20, 20))
        );

        pnlEquipoProyectoTitlo.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        pnlRevisionVersion.add(pnlEquipoProyectoTitlo, java.awt.BorderLayout.PAGE_START);

        pnlEquipoProyectoCuerpo.setBackground(new java.awt.Color(255, 255, 255));
        pnlEquipoProyectoCuerpo.setLayout(new java.awt.GridBagLayout());

        jScrollPane9.setPreferredSize(new java.awt.Dimension(86, 230));

        treEntregable.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treEntregableValueChanged(evt);
            }
        });
        jScrollPane9.setViewportView(treEntregable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 0, 0);
        pnlEquipoProyectoCuerpo.add(jScrollPane9, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel1.setText("Entregables.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(24, 35, 0, 0);
        pnlEquipoProyectoCuerpo.add(jLabel1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel3.setText("Revisiones.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(24, 35, 0, 0);
        pnlEquipoProyectoCuerpo.add(jLabel3, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel5.setText("Versiones");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(24, 35, 0, 0);
        pnlEquipoProyectoCuerpo.add(jLabel5, gridBagConstraints);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(520, 200));

        tblRevision.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        tblRevision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Revision", "Fecha", "Motivo", "Enlace"
            }
        ));
        tblRevision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRevisionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRevision);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 0, 0);
        pnlEquipoProyectoCuerpo.add(jScrollPane2, gridBagConstraints);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 200));

        tblVersion.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        tblVersion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Version", "Fecha", "Enlace", "Motivo"
            }
        ));
        tblVersion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVersionMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVersion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 35, 0);
        pnlEquipoProyectoCuerpo.add(jScrollPane3, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel6.setText("Enlace a la revisión.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(24, 24, 0, 0);
        pnlEquipoProyectoCuerpo.add(jLabel6, gridBagConstraints);

        btnEnlaceRevision.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnEnlaceRevision.setText("Abrir Enlace");
        btnEnlaceRevision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnlaceRevisionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 100);
        pnlEquipoProyectoCuerpo.add(btnEnlaceRevision, gridBagConstraints);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("...................................................");
        jLabel10.setMinimumSize(new java.awt.Dimension(50, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlEquipoProyectoCuerpo.add(jLabel10, gridBagConstraints);

        btnEnlaceVersion.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnEnlaceVersion.setText("Abrir Enlace");
        btnEnlaceVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnlaceVersionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 100);
        pnlEquipoProyectoCuerpo.add(btnEnlaceVersion, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel12.setText("Enlace a la versión.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(24, 24, 0, 0);
        pnlEquipoProyectoCuerpo.add(jLabel12, gridBagConstraints);

        txtEnlaceVersion.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtEnlaceVersion.setForeground(new java.awt.Color(0, 0, 255));
        txtEnlaceVersion.setText("Seleccione una revisión...");
        txtEnlaceVersion.setDisabledTextColor(new java.awt.Color(0, 0, 255));
        txtEnlaceVersion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 100);
        pnlEquipoProyectoCuerpo.add(txtEnlaceVersion, gridBagConstraints);

        txtEnlaceRevision.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtEnlaceRevision.setForeground(new java.awt.Color(0, 0, 255));
        txtEnlaceRevision.setText("Seleccione una revisión...");
        txtEnlaceRevision.setDisabledTextColor(new java.awt.Color(0, 0, 255));
        txtEnlaceRevision.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 100);
        pnlEquipoProyectoCuerpo.add(txtEnlaceRevision, gridBagConstraints);

        pnlRevisionVersion.add(pnlEquipoProyectoCuerpo, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Revisiones y versiones", pnlRevisionVersion);

        pnlEntregable.setBackground(new java.awt.Color(255, 255, 255));
        pnlEntregable.setLayout(new java.awt.BorderLayout());

        pnlEquipoProyectoTitlo1.setBackground(new java.awt.Color(255, 255, 255));
        pnlEquipoProyectoTitlo1.setPreferredSize(new java.awt.Dimension(970, 110));
        pnlEquipoProyectoTitlo1.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.BorderLayout());
        jPanel13.add(jSeparator4, java.awt.BorderLayout.CENTER);

        pnlEquipoProyectoTitlo1.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel9.setText("Mis entregables");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9)
                .addContainerGap(1027, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(20, 20, 20))
        );

        pnlEquipoProyectoTitlo1.add(jPanel14, java.awt.BorderLayout.PAGE_START);

        pnlEntregable.add(pnlEquipoProyectoTitlo1, java.awt.BorderLayout.PAGE_START);

        pnlEquipoProyectoCuerpo1.setBackground(new java.awt.Color(255, 255, 255));
        pnlEquipoProyectoCuerpo1.setLayout(new java.awt.GridBagLayout());

        jLabel14.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel14.setText("Rol del participante.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(24, 35, 0, 0);
        pnlEquipoProyectoCuerpo1.add(jLabel14, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel16.setText("Etapa.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(24, 35, 0, 0);
        pnlEquipoProyectoCuerpo1.add(jLabel16, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel17.setText("Entregable.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(24, 35, 0, 0);
        pnlEquipoProyectoCuerpo1.add(jLabel17, gridBagConstraints);

        cmbEtapa.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        cmbEtapa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INICIO", "ELABORACION", "CONSTRUCCION" }));
        cmbEtapa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEtapaItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 0, 0);
        pnlEquipoProyectoCuerpo1.add(cmbEtapa, gridBagConstraints);

        jScrollPane4.setPreferredSize(new java.awt.Dimension(230, 130));

        lstEntregable.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstEntregable.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstEntregableValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(lstEntregable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 0, 0);
        pnlEquipoProyectoCuerpo1.add(jScrollPane4, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel8.setText("Enlace al entregable.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(24, 24, 0, 250);
        pnlEquipoProyectoCuerpo1.add(jLabel8, gridBagConstraints);

        txtEnlaceEntregable.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtEnlaceEntregable.setForeground(new java.awt.Color(0, 0, 255));
        txtEnlaceEntregable.setText("Seleccione un entregable...");
        txtEnlaceEntregable.setDisabledTextColor(new java.awt.Color(0, 0, 255));
        txtEnlaceEntregable.setEnabled(false);
        txtEnlaceEntregable.setMinimumSize(new java.awt.Dimension(185, 28));
        txtEnlaceEntregable.setPreferredSize(new java.awt.Dimension(190, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 250);
        pnlEquipoProyectoCuerpo1.add(txtEnlaceEntregable, gridBagConstraints);

        btnAbrirEnlaceEntregable.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnAbrirEnlaceEntregable.setText("Abrir Enlace");
        btnAbrirEnlaceEntregable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirEnlaceEntregableActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 250);
        pnlEquipoProyectoCuerpo1.add(btnAbrirEnlaceEntregable, gridBagConstraints);

        btnEditarEnlaceEntregable.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnEditarEnlaceEntregable.setText("Editar Enlace");
        btnEditarEnlaceEntregable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEnlaceEntregableActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 250);
        pnlEquipoProyectoCuerpo1.add(btnEditarEnlaceEntregable, gridBagConstraints);

        jScrollPane5.setPreferredSize(new java.awt.Dimension(440, 202));

        tblParticipante.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        tblParticipante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Correo Electrónico", "Cargo", "Rol"
            }
        ));
        tblParticipante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblParticipanteMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblParticipante);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 0, 0);
        pnlEquipoProyectoCuerpo1.add(jScrollPane5, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel15.setText("Participantes del entregable.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(24, 35, 0, 0);
        pnlEquipoProyectoCuerpo1.add(jLabel15, gridBagConstraints);

        jScrollPane6.setPreferredSize(new java.awt.Dimension(266, 151));

        txtParticipanteRol.setColumns(20);
        txtParticipanteRol.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtParticipanteRol.setRows(5);
        txtParticipanteRol.setEnabled(false);
        jScrollPane6.setViewportView(txtParticipanteRol);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 0, 0);
        pnlEquipoProyectoCuerpo1.add(jScrollPane6, gridBagConstraints);

        btnGuardarRol.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnGuardarRol.setText("<html><div align=\"center\">Guardar Rol del <br> Participante<div></html>");
        btnGuardarRol.setEnabled(false);
        btnGuardarRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRolActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 250);
        pnlEquipoProyectoCuerpo1.add(btnGuardarRol, gridBagConstraints);

        pnlEntregable.add(pnlEquipoProyectoCuerpo1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Entregables", pnlEntregable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        pnlPrincipal.add(jTabbedPane1, gridBagConstraints);

        srpPrincipal.setViewportView(pnlPrincipal);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        pnlPrincipalPadre.add(srpPrincipal, gridBagConstraints);

        add(pnlPrincipalPadre, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void treEntregableValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treEntregableValueChanged

        Object nodoSeleccionado;
        DefaultMutableTreeNode node;
        node = (DefaultMutableTreeNode) treEntregable.getLastSelectedPathComponent();

        if(node != null)
        {
            nodoSeleccionado = node.getUserObject();
            String[] auxPartes = nodoSeleccionado.toString().split("-");
            entregableId = auxPartes[0];
            conexion = clsConexionDS.AbrirConexion();
            CargarTablaRevision();
            CargarTablaVersion();
            clsConexionDS.CerrarConexion(conexion);
        }

    }//GEN-LAST:event_treEntregableValueChanged

    private void cmbEtapaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEtapaItemStateChanged
        
        if(cmbEtapa.getSelectedIndex() != -1)
        {
            CargarListaEntregable();
        }
        
    }//GEN-LAST:event_cmbEtapaItemStateChanged

    private void lstEntregableValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstEntregableValueChanged
        
        if(lstEntregable.getSelectedIndex() != -1)
        {
            
            conexion = clsConexionDS.AbrirConexion();
            CargarEnlaceEntregable();
            
            clsProyectoEntregableDAO proyectoEntregableDAO;
            
            proyectoEntregableDAO = new clsProyectoEntregableDAO();
            try 
            {
                proyectoEntregableId = proyectoEntregableDAO.SeleccionarProyectoEntregableId (
                        arrayEntregableIdArbol.get(lstEntregable.getSelectedIndex()),
                        frmPrincipal.proyectoId,
                        conexion);
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(frmAdministrarProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            CargarTablaParticipantes(proyectoEntregableId);
            clsConexionDS.CerrarConexion(conexion);
        }        
        
    }//GEN-LAST:event_lstEntregableValueChanged

    private void tblRevisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRevisionMouseClicked
        
        int reg;
        DefaultTableModel dtmRevision = new DefaultTableModel();
        reg = tblRevision.getSelectedRow();
        
        if(reg == -1)
        {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un registro");
        }
        else
        {
            dtmRevision = (DefaultTableModel)tblRevision.getModel();
            txtEnlaceRevision.setText((String) dtmRevision.getValueAt(reg, 6));
            
        }
        
    }//GEN-LAST:event_tblRevisionMouseClicked

    private void tblVersionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVersionMouseClicked
        
        int reg;
        DefaultTableModel dtmVersion = new DefaultTableModel();
        reg = tblVersion.getSelectedRow();
        
        if(reg == -1)
        {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un registro");
        }
        else
        {
            dtmVersion = (DefaultTableModel)tblVersion.getModel();
            txtEnlaceVersion.setText((String) dtmVersion.getValueAt(reg, 6));
            
        }
        
    }//GEN-LAST:event_tblVersionMouseClicked

    private void btnEnlaceRevisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnlaceRevisionActionPerformed
        
        AbrirEnlaceGoogleDocs(txtEnlaceRevision.getText());
        
    }//GEN-LAST:event_btnEnlaceRevisionActionPerformed

    private void btnEnlaceVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnlaceVersionActionPerformed
        
        AbrirEnlaceGoogleDocs(txtEnlaceVersion.getText());
        
    }//GEN-LAST:event_btnEnlaceVersionActionPerformed

    private void tblParticipanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParticipanteMouseClicked
      
        int reg;
        
        DefaultTableModel dtm = new DefaultTableModel();
        reg = tblParticipante.getSelectedRow();
        if(reg == -1)
        {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un registro");
        }
        else
        {
            dtm = (DefaultTableModel)tblParticipante.getModel();
            proyectoUsuario_Id = ((String) dtm.getValueAt(reg, 0)); 
            proyectoEntregable_Id = ((String) dtm.getValueAt(reg, 1)); 
            txtParticipanteRol.setText((String) dtm.getValueAt(reg, 5));
            btnGuardarRol.setEnabled(true);
            txtParticipanteRol.setEnabled(true);
        }
        
    }//GEN-LAST:event_tblParticipanteMouseClicked

    private void btnGuardarRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRolActionPerformed
        
        clsProyectoUsuarioEntregableDAO proyectoUsuarioEntregableDAO;
        proyectoUsuarioEntregableDAO = new clsProyectoUsuarioEntregableDAO();
        
        conexion = clsConexionDS.AbrirConexion();
        proyectoUsuarioEntregableDAO.ActualizarProyectoUsuarioEntregableDescripcionRol (
                        txtParticipanteRol.getText(), 
                        proyectoUsuario_Id, 
                        proyectoEntregable_Id, 
                        conexion);
        CargarTablaParticipantes(proyectoEntregableId);
        txtParticipanteRol.setText("");
        btnGuardarRol.setEnabled(false);
        txtParticipanteRol.setEnabled(false);
        clsConexionDS.CerrarConexion(conexion);
        
    }//GEN-LAST:event_btnGuardarRolActionPerformed

    private void btnAbrirEnlaceEntregableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirEnlaceEntregableActionPerformed
       
        AbrirEnlaceGoogleDocs(txtEnlaceEntregable.getText());
        
    }//GEN-LAST:event_btnAbrirEnlaceEntregableActionPerformed

    private void btnEditarEnlaceEntregableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEnlaceEntregableActionPerformed
       
        
        
    }//GEN-LAST:event_btnEditarEnlaceEntregableActionPerformed
    
    private void AbrirEnlaceGoogleDocs(String url) {
        
        try 
        {
            URL urlGoogle = new URL(url);
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) 
            {
                try 
                {
                    desktop.browse(urlGoogle.toURI());
                }
                catch (IOException | URISyntaxException e) 
                {
                    Logger.getLogger(frmConfigurarEntregable.class.getName()).log(Level.SEVERE, null, e);
                }
            }            
        }
        catch (MalformedURLException ex) 
        {
            JOptionPane.showMessageDialog(this, "El enlace debe contener un determinado protocolo", 
                    "No se puedo abrir el enlace", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(frmConfigurarEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void CargarEnlaceEntregable()
    {
        clsProyectoEntregableDAO proyectoEntregableDAO;
        ResultSet rsResultado;
        
        
        proyectoEntregableDAO = new clsProyectoEntregableDAO();
        try 
        {            
            rsResultado = proyectoEntregableDAO.SeleccionarDatosProyectoEntregable
                            (arrayEntregableIdArbol.get(lstEntregable.getSelectedIndex()),
                                frmPrincipal.proyectoId,
                                conexion);
            while(rsResultado.next())
            {
                txtEnlaceEntregable.setText(rsResultado.getString("proyectoentregable_enlace"));
            }
        } catch (Exception ex) {
            Logger.getLogger(frmAdministrarProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void CargarTablaParticipantes(String xProyectoEntregableId)
    {
        
        
        clsProyectoUsuarioEntregableDAO proyectoUsuarioEntregableDAO;
        String[] titulos = {"id","id usuario", "Nombre", "Correo Electrónico", 
                            "Cargo", "Rol"};
        String[] fila; 
        DefaultTableModel dtmParticipante;
        ResultSet rsResultado;
        
        fila = new String[7];
        dtmParticipante = new DefaultTableModel(null,titulos) {        
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}               
        }; 
        proyectoUsuarioEntregableDAO = new clsProyectoUsuarioEntregableDAO();
        
        try 
        {
            rsResultado = proyectoUsuarioEntregableDAO.SeleccionarProyectoUsuarioEntregable (
                                        xProyectoEntregableId, 
                                        conexion
                                    );
            
            while(rsResultado.next())
            {
                fila[0] = rsResultado.getString("proyectousuario_id");
                fila[1] = rsResultado.getString("proyectoentregable_id");
                fila[2] = rsResultado.getString("usuario_apellido")+", "+
                          rsResultado.getString("usuario_nombre");
                fila[3] = rsResultado.getString("usuario_correo_electronico");
                if(rsResultado.getString("proyectousuario_proyectoentregable_responsable").equals("1"))
                {
                    fila[4] = "Responsable";
                }
                else
                {
                    fila[4] = "Participante";
                }
                fila[5] = rsResultado.getString("proyectousuario_proyectoentregable_descripcion");
                
                dtmParticipante.addRow(fila);
            }
            
            tblParticipante.setModel(dtmParticipante);
            tblParticipante.removeColumn(tblParticipante.getColumnModel().getColumn(0));
            tblParticipante.removeColumn(tblParticipante.getColumnModel().getColumn(0));
            TableCellRenderer tcr =  tblParticipante.getTableHeader().getDefaultRenderer();
            clsTablasRender.miRender mr = new clsTablasRender.miRender(tcr);
            mr.getTableCellRendererComponent(tblParticipante, fila, true, true, WIDTH, 2);
            
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(frmAdministrarProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void CargarListaEntregable()
    {
        
        DefaultListModel dlmEntregable;
        clsProyectoEntregableDAO proyectoEntregableDAO;
        ResultSet rsResultado;
        
        dlmEntregable = new DefaultListModel();
        proyectoEntregableDAO = new clsProyectoEntregableDAO();
        dlmEntregable.removeAllElements();
        arrayEntregableIdArbol.clear();
        conexion = clsConexionDS.AbrirConexion();
        try 
        {
            rsResultado = proyectoEntregableDAO.SeleccionarProyectoEntregableEtapa
                        (                            
                            arrayEtapaId.get(cmbEtapa.getSelectedIndex()),
                            frmPrincipal.proyectoId,
                            conexion
                        );
            while(rsResultado.next())
            {
                dlmEntregable.addElement(rsResultado.getString("entregable_nombre"));
                arrayEntregableIdArbol.add(rsResultado.getString("entregable_id"));
            }
            lstEntregable.setModel(dlmEntregable);            
        }
        catch (Exception ex) 
        {
            Logger.getLogger(frmAdministrarProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        clsConexionDS.CerrarConexion(conexion);
    }
    
    private void ObtenerMetodologiaProyecto() {
        
        
        ResultSet rsResultado;
        clsMetodologiaDAO metodologiaDAO = new clsMetodologiaDAO();
        
        conexion = clsConexionDS.AbrirConexion();     
        try 
        {                        
            rsResultado = metodologiaDAO.SeleccionarMetodologiaProyecto(frmPrincipal.proyectoId, conexion);
            while(rsResultado.next())
            {
                metodologiaId = rsResultado.getString("metodologia_id");
                dmtnMetodologia = new DefaultMutableTreeNode(rsResultado.getString("metodologia_nombre"));
            }                   
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(frmConfigurarEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
    
    private void ObtenerProyectoEntregable() {
        
        ResultSet rsResultado;        
        clsProyectoEntregableDAO proyectoEntregableDAO = new clsProyectoEntregableDAO();
        
        arrayEntregableIdAux.clear();
        arrayProyectoEntregableId.clear();
        conexion = clsConexionDS.AbrirConexion();
        try
        {
            rsResultado = proyectoEntregableDAO.SeleccionarProyectoEntregable(frmPrincipal.proyectoId, conexion);
            while(rsResultado.next())
            {
                arrayProyectoEntregableId.add(rsResultado.getString("proyectoentregable_id"));
                arrayEntregableIdAux.add(rsResultado.getString("entregable_id"));                
            }                    
        }
        catch (Exception ex) 
        {
            Logger.getLogger(frmConfigurarEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    private void CargarArbolMetodologia() {
        
        clsMetodologiaDAO metodologiaDAO = new clsMetodologiaDAO();
        
        Object[][] datosEtapa;
        Object[][] datosEntregable;              
        
        conexion = clsConexionDS.AbrirConexion();        
        datosEtapa =  metodologiaDAO.SeleccionarEtapaEntregableMetodologia (
                    "etapa", 
                    " etapa_id, etapa_nombre", 
                    " metodologia_id='" + metodologiaId + "' ", 
                    conexion
                    );
        
        if( datosEtapa.length > 0)
        {
            for (int i=0; i < datosEtapa.length; i++) 
            {
                //se crea nodo Etapa
                dmtnEtapa = new DefaultMutableTreeNode(datosEtapa[i][1]);
                dmtnMetodologia.add(dmtnEtapa);
                //se obtiene los Entregable
                datosEntregable = metodologiaDAO.SeleccionarEntregableEtapaMetodologia (
                                "proyectoentregable as pe", 
                                "pe.proyectoentregable_id,pe.entregable_id,e.entregable_nombre", 
                                "e.etapa_id='" + datosEtapa[i][0].toString() + "' and "
                                + "pe.proyecto_id='"+ frmPrincipal.proyectoId+ "'", 
                                conexion
                                );
                
                if( datosEntregable.length > 0)
                {
                    for (int j=0; j< datosEntregable.length; j++) 
                    {
                        //intento de colocar en blanco
                        JLabel l = new JLabel((String) datosEntregable[j][1]);     
                        l.setForeground(Color.white);
                        //fin del intento
                        //Filtra los entregables que solo se selecciono
                        for(String ei : arrayProyectoEntregableId)
                        {
                            if(datosEntregable[j][0].equals(ei))
                            {
                            dmtnEntregable = new DefaultMutableTreeNode(l.getText() +
                                " - " + (String) datosEntregable[j][2] );
                            dmtnEtapa.add(dmtnEntregable);
                            }
                        }                        
                    }
                }
            }
        }
        
        DefaultTreeModel modelo = new DefaultTreeModel(dmtnMetodologia);
        treEntregable.setModel(modelo);
        
    }
    
    private void CargarComboEtapa()
    {
        
        DefaultComboBoxModel dcbmEtapa;
        clsProyectoEtapaDAO proyectoEtapaDAO;
        ResultSet resultado;
        
        dcbmEtapa = new DefaultComboBoxModel();
        proyectoEtapaDAO = new clsProyectoEtapaDAO();
        cmbEtapa.removeAllItems();
        dcbmEtapa.removeAllElements();
        try 
        {
            resultado = proyectoEtapaDAO.SeleccionarProyectoEtapa(frmPrincipal.proyectoId, conexion);
            while(resultado.next())
            {
                dcbmEtapa.addElement(resultado.getString("etapa_nombre"));
                arrayEtapaId.add(resultado.getString("etapa_id"));
            }
        }
        catch (Exception ex) 
        {
            Logger.getLogger(frmAdministrarProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbEtapa.setModel(dcbmEtapa);        
        
    }

    private void AbrirDialogRevision() {
        
        Window parentWindow = SwingUtilities.windowForComponent(this);
        frmGenerarRevision generarRevision = new frmGenerarRevision(parentWindow);
        generarRevision.setModal(true);
        generarRevision.setVisible(true);
        generarRevision.toFront();
    }
    
    private void AbrirDialogVersion() {
        
        Window parentWindow = SwingUtilities.windowForComponent(this);
        frmGenerarVersion generarVersion = new frmGenerarVersion(parentWindow);
        generarVersion.setModal(true);
        generarVersion.setVisible(true);
        generarVersion.toFront();
    }
           
    private void CargarTablaRevision() {
        
        String[] titulos = {"revision id", "entregable id", "revision id hijo",
                            "Revision", "Fecha", "Motivo", "Enlace", "Predecesor"};
        String[] fila;
        clsEntregableRevisionDAO entregableRevisionDAO;
        ArrayList<clsEntregableRevisionDTO> arrayEntregableRevision;
        DefaultTableModel dtmRevision;        
        
        fila = new String [8];        
        dtmRevision = new DefaultTableModel(null,titulos) {        
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}               
        };        
        entregableRevisionDAO = new clsEntregableRevisionDAO();
        arrayEntregableRevision = entregableRevisionDAO.SeleccionarEntregableRevision (
                            entregableId,
                            conexion
                        );        
        for(clsEntregableRevisionDTO er : arrayEntregableRevision)
        {
            fila[0] = er.getRevision_id();
            fila[1] = er.getEntregable_id();
            fila[2] = er.getRevision_id_hijo();
            fila[3] = er.getRevision_nombre();
            fila[4] = er.getRevision_fecha();
            fila[5] = er.getRevision_descripcion();
            fila[6] = er.getRevision_enlace();
            fila[7] = er.getRevision_nombre_hijo();
            dtmRevision.addRow(fila);  
        }        
        tblRevision.setModel(dtmRevision);
        tblRevision.removeColumn(tblRevision.getColumnModel().getColumn(0));
        tblRevision.removeColumn(tblRevision.getColumnModel().getColumn(0));
        tblRevision.removeColumn(tblRevision.getColumnModel().getColumn(0));
        TableCellRenderer tcr =  tblRevision.getTableHeader().getDefaultRenderer();
        clsTablasRender.miRender mr = new clsTablasRender.miRender(tcr);
        mr.getTableCellRendererComponent(tblRevision, fila, true, true, WIDTH, 0);
        mr.getTableCellRendererComponent(tblRevision, fila, true, true, WIDTH, 1);
        
    }
    
    private void CargarTablaVersion() {
        
        String[] titulos = {"version id", "revision id", "version id hijo",
                            "Version", "Fecha", "Motivo", "Enlace", "Originado por","Predecesor"};
        String[] fila;
        clsEntregableVersionDAO entregableVersionDAO;
        ArrayList<clsEntregableVersionDTO> arrayEntregableVersion;
        DefaultTableModel dtmVersion;
        DefaultComboBoxModel dcbmPredecesor;
        
        fila = new String [9];        
        dtmVersion = new DefaultTableModel(null,titulos) {        
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}               
        };
        dcbmPredecesor = new DefaultComboBoxModel();
        entregableVersionDAO = new clsEntregableVersionDAO();
        arrayEntregableVersion = entregableVersionDAO.SeleccionarEntregableVersion (
                            entregableId,
                            conexion
                        );
        dcbmPredecesor.removeAllElements();
        for(clsEntregableVersionDTO ev : arrayEntregableVersion)
        {
            fila[0] = ev.getVersion_id();
            fila[1] = ev.getRevision_id();
            fila[2] = ev.getVersion_id_hijo();
            fila[3] = ev.getVersion_nombre();
            fila[4] = ev.getVersion_fecha();
            fila[5] = ev.getVersion_descripcion();
            fila[6] = ev.getVersion_enlace();
            fila[7] = ev.getRevision_nombre();
            fila[8] = ev.getVersion_nombre_hijo();
            dtmVersion.addRow(fila);
            dcbmPredecesor.addElement(fila[3]);            
        }
        
        tblVersion.setModel(dtmVersion);
        tblVersion.removeColumn(tblVersion.getColumnModel().getColumn(0));
        tblVersion.removeColumn(tblVersion.getColumnModel().getColumn(0));
        tblVersion.removeColumn(tblVersion.getColumnModel().getColumn(0));
        TableCellRenderer tcr =  tblVersion.getTableHeader().getDefaultRenderer();
        miRender mr = new miRender(tcr);
        mr.getTableCellRendererComponent(tblVersion, fila, true, true, WIDTH, 0);
        mr.getTableCellRendererComponent(tblVersion, fila, true, true, WIDTH, 1);        
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirEnlaceEntregable;
    private javax.swing.JButton btnEditarEnlaceEntregable;
    private javax.swing.JButton btnEnlaceRevision;
    private javax.swing.JButton btnEnlaceVersion;
    private javax.swing.JButton btnGuardarRol;
    private javax.swing.JComboBox<String> cmbEtapa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> lstEntregable;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlEntregable;
    private javax.swing.JPanel pnlEquipoProyectoCuerpo;
    private javax.swing.JPanel pnlEquipoProyectoCuerpo1;
    private javax.swing.JPanel pnlEquipoProyectoTitlo;
    private javax.swing.JPanel pnlEquipoProyectoTitlo1;
    private javax.swing.JPanel pnlEstadoCuerpo;
    private javax.swing.JPanel pnlEstadoTitulo;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlPrincipalPadre;
    private javax.swing.JPanel pnlRevisionVersion;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JScrollPane srpPrincipal;
    private javax.swing.JTable tblParticipante;
    private javax.swing.JTable tblRevision;
    private javax.swing.JTable tblVersion;
    private com.alee.laf.tree.WebTree treEntregable;
    private javax.swing.JTextField txtEnlaceEntregable;
    private javax.swing.JTextField txtEnlaceRevision;
    private javax.swing.JTextField txtEnlaceVersion;
    private javax.swing.JTextArea txtParticipanteRol;
    // End of variables declaration//GEN-END:variables
}
