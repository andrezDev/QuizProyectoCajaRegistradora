import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmCajaRegistradora extends JFrame {

    JComboBox cmbDenominaciones;
    JTable tablaDevuelta; 
    JButton btnExistencia;
    JLabel labelDato;
    JTextField textDato;
    JTextField textDevolver;
    String [] encabezados = new String [] {"Cantidad", "Presentación", "Denominación"};
    Integer [] opcDenominaciones = new Integer [] {100000,50000,20000,10000,5000,2000,1000,500,200, 100,50};

    @SuppressWarnings("rawtypes")
    public FrmCajaRegistradora () {

        setSize(400,350); 
        setTitle("Caja Registradora");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        labelDato = new JLabel("Denominación");
        labelDato.setBounds(20, 20, 100, 25);
        getContentPane().add(labelDato);

        cmbDenominaciones = new JComboBox();
        
        DefaultComboBoxModel modelDenominaciones = new DefaultComboBoxModel(opcDenominaciones);
        cmbDenominaciones.setModel(modelDenominaciones);
        cmbDenominaciones.setBounds(120, 20, 100, 25);
        getContentPane().add(cmbDenominaciones);

        textDato = new JTextField();
        textDato.setBounds (20,63,50,20);
        getContentPane().add(textDato);

        btnExistencia = new JButton("Actualizar existencia");
        btnExistencia.setBounds(80,60,160,25); 
        getContentPane().add(btnExistencia);

        JLabel labelDevolver = new JLabel("Valor a Devolver");
        labelDevolver.setBounds(20, 108, 100, 25);
        getContentPane().add(labelDevolver);

        textDevolver = new JTextField();
        textDevolver.setBounds (130,112,100,20);
        getContentPane().add(textDevolver);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.setBounds(250,107,100,28); 
        getContentPane().add(btnDevolver );

        tablaDevuelta = new JTable();
        JScrollPane espaciotablaDevuelta = new JScrollPane(tablaDevuelta);
        espaciotablaDevuelta.setBounds(17,150,350,150); 
        getContentPane().add(espaciotablaDevuelta);

        DefaultTableModel datosDevuelta = new DefaultTableModel(null, encabezados);
        tablaDevuelta.setModel(datosDevuelta);

        btnExistencia.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                actualizarExistencia();
            }
        });

        btnDevolver.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                mostrarDevuelta();
            }
        }
        );
    }
    
    int [][] mtzExistencia = new int [opcDenominaciones.length][2];
    private void actualizarExistencia () {
        
        for (int i = 0; i <= opcDenominaciones.length; i ++) {
            //cantidadYDenominacion [i] ++;
            //copia el contenido de opciones denominaciones a la primera columna de la matriz
            mtzExistencia[i][0] =  opcDenominaciones[i];

            //validar si el valor seleccionado en el cmbDenominaciones es igual al valor de la denominacion en la matriz (columna 0)
            //si es el mismo valor se actualiza la existencia (columna 1)
            String denominacionEscogida =  String.valueOf(cmbDenominaciones.getSelectedItem()); 
            try {
                if (mtzExistencia[i][0] == Integer.parseInt(denominacionEscogida)){
                    mtzExistencia[i][1] = Integer.parseInt(textDato.getText());
                }
            }   
            catch (Exception ex) {
                textDato.setText("");
                JOptionPane.showMessageDialog(null, "Debe especificar un valor numérico");
            }
         }
    }
    // Matriz de devuelta 
    private void mostrarDevuelta() {

        String [][] mtzDevolver = new String [opcDenominaciones.length][3];

        int numEntrada = Integer.parseInt(textDevolver.getText());
        System.out.println("matriz existencia i: " + mtzExistencia.length);
       
        int j = 0;
        try {
            if (numEntrada > 0) {
                while (numEntrada > 0) {
                    System.out.println("numentrada: " + numEntrada);
                    
                    // Recorrer matriz existencia 
                    for (int i = 0; i < mtzExistencia.length;  i++) {                
                        System.out.println("Entró al for i" + i);
                        if (numEntrada >= mtzExistencia [i][0]) {
                            System.out.println("entra la if numEntrada mayor a mtzExistencia[i][0]:" + mtzExistencia [i][0]);
                            
                            /* 
                            if (mtzDevolver [j][2] != null)
                            {
                                System.out.println("mtzDevolver.length:  " + mtzDevolver.length);
                                System.out.println("Integer.parseInt(mtzDevolver [j][2]:  " + mtzDevolver [j][2]);
                                System.out.println("mtzExistencia [i][0]:  " + mtzExistencia [i][0]);
                                //validar si tenemos la misma denominacion registrada para actualizarla
                                if (Integer.parseInt(mtzDevolver [j][2]) == mtzExistencia [i][0])
                                {
                                    //Se actualiza la columna cantidad 
                                    mtzDevolver [j][0] += String.valueOf(1);
                                }
                            }
                            else
                            {
                                
                                
                            }*/
                            if (i > 0)
                            {
                                j +=1;
                            }
                            System.out.println("j:  " + j);

                            // Restar uno a la columna existencia 
                            mtzExistencia [i][1] -= 1;                               
                            
                            // Validar si es billete o moneda 
                            if (mtzExistencia [i][0] > 1000) {
                                mtzDevolver [j][1] = "Billete";  // Columna presentación
                            }
                            else {
                                mtzDevolver [j][1] = "Moneda";
                            }
                             
                            mtzDevolver [j][0] = String.valueOf(1);     // columna cantidad
                            System.out.println("mtzDevolver [i][0] :" + mtzDevolver [j][0]); 
                            mtzDevolver [j][2] = String.valueOf(mtzExistencia [i][0]);  // Columna denominación
                            System.out.println("mtzDevolver [i][2]:" + mtzDevolver [j][2]);
                            numEntrada -= mtzExistencia [i][0];  // Se resta la denominación al número de entrada para actualizar la diferencia
                            System.out.println("numEntrada:" + numEntrada);
                            
                            break; 

                        }
                    }
                }
            }            
            else {
                JOptionPane.showMessageDialog(null, "El número debe ser positivo");
                textDevolver.setText("");
            }
        }
        catch (Exception ex) {
            textDevolver.setText("");
            JOptionPane.showInternalMessageDialog(null, "Por favor digite un número entero");
        }
        DefaultTableModel datosDevuelta = new DefaultTableModel(mtzDevolver, encabezados);
        tablaDevuelta.setModel(datosDevuelta);
    }
}


/* 
private void quitarDato() {
    // obtener la posicion escogida
    int posicion = lstMuestra.getSelectedIndex();
    if (posicion >= 0) {
        // retirar la posicion del vector
        for (int i = posicion; i < totalDatos; i++) {
            muestra[i] = muestra[i + 1];
        }
        totalDatos--;
        mostrarMuestra();
    } else {
        JOptionPane.showMessageDialog(null, "Debe seleccionar una posición");
    }
}
*/











    /* 
        int [][] cantidadYDenominacion = new int [opcDenominaciones.length][3];
       
        for (int i = 0; i <= opcDenominaciones.length; i ++) {
            //cantidadYDenominacion [i] ++;
            //copia el contenido de opciones denominaciones a la tercera columna de la matriz
            cantidadYDenominacion[i][2] =  opcDenominaciones[i];           

            
            if ( cantidadYDenominacion[i][2] == cmbDenominaciones.getSelectedItem()){
                cantidadYDenominacion[i][0] = textDato.getText();
            }

            //Llena la columna 2 con el valor de billete o moneda según la denominacion
            if (Integer.parseInt(cantidadYDenominacion[i][0]) > 1000){
                cantidadYDenominacion[i][1] = "Billete";
                 }
                 else {
                    cantidadYDenominacion[i][1] = "Moneda";
                    
                 }
            String [][]stringTablaDenominacion = new String[20][3];
    
            /*switch (cantidadYDenominacion[i][0]) {
                case "100000":
                    cantidadYDenominacion[i][2] = "Billete";
                    break;
                case "50000":
                    cantidadYDenominacion[i][2] = "Billete";
                    break;
                case "20000":
                    cantidadYDenominacion[i][2] = "Billete";
                    break;
                case "10000":
                    cantidadYDenominacion[i][2] = "Billete";
                    break;
                case "5000":
                    cantidadYDenominacion[i][2] = "Billete";
                    break;
                case "2000":
                    cantidadYDenominacion[i][2] = "Billete";
                    break;
                case "1000":
                    cantidadYDenominacion[i][2] = "Moneda";
                    break;
                case "500":
                    cantidadYDenominacion[i][2] = "Moneda";
                    break;
                case "200":
                    cantidadYDenominacion[i][2] = "Moneda";
                    break;
                case "100":
                    cantidadYDenominacion[i][2] = "Moneda";
                    break;
                case "50":
                    cantidadYDenominacion[i][2] = "Moneda";
                    break;
                default:
                    break;
            }
            System.out.println(cantidadYDenominacion [i][0]); 
            System.out.println(cantidadYDenominacion [i][1]);
            System.out.println(cantidadYDenominacion [i][2]);
            //System.out.println(textDato.getText());
            //System.out.println(cmbDenominaciones.getSelectedItem());
        }  */          
    
        
        //
        


       

    


    
