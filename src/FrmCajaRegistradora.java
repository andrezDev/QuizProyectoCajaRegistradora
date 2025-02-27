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
    JLabel labelExistencia;
    JTextField textDato;
    JTextField textDevolver;
    String [] encabezados = new String [] {"Cantidad", "Presentación", "Denominación"};
    Integer [] opcDenominaciones = new Integer [] {100000,50000,20000,10000,5000,2000,1000,500,200, 100,50};

    @SuppressWarnings("rawtypes")
    public FrmCajaRegistradora () {

        setSize(400,360); 
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
        labelDevolver.setBounds(20, 100, 100, 25);
        getContentPane().add(labelDevolver);

        textDevolver = new JTextField();
        textDevolver.setBounds (130,104,100,20);
        getContentPane().add(textDevolver);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.setBounds(250,100,100,28); 
        getContentPane().add(btnDevolver );

        labelExistencia = new JLabel(" ");
        labelExistencia.setBounds(20, 130, 800, 25);
        getContentPane().add(labelExistencia);

        tablaDevuelta = new JTable();
        JScrollPane espaciotablaDevuelta = new JScrollPane(tablaDevuelta);
        espaciotablaDevuelta.setBounds(17,155,350,150); 
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
            mtzExistencia[i][0] =  opcDenominaciones[i];
            String denominacionEscogida =  String.valueOf(cmbDenominaciones.getSelectedItem()); 
                try {
                    
                    if (mtzExistencia[i][0] == Integer.parseInt(denominacionEscogida)){
                        mtzExistencia[i][1] = Integer.parseInt(textDato.getText());
                    }                                         
                } 
                catch (Exception ex) {
                    textDato.setText("");
                    JOptionPane.showMessageDialog(null, "Por favor ingresar un dato entero positivo");
                } 
        }      
          
    }
       
    private void mostrarDevuelta() {

        try {
            String [][] mtzDevolver = new String [opcDenominaciones.length][3];
            int numEntrada = Integer.parseInt(textDevolver.getText());
            int sumaCantidad = 0;
            String presentacion = "";
            int existencia = 0;
            int j = 0;
            if (numEntrada > 0){
                while (numEntrada > 0) {                         
                    for (int i = 0; i < mtzExistencia.length;  i++) {                
                        if (numEntrada >= mtzExistencia [i][0]) {
                        existencia = mtzExistencia [i][1];
                            if (mtzDevolver [j][2] != null) {                                                            
                                if (Integer.parseInt(mtzDevolver [j][2]) == mtzExistencia [i][0]) {                                                           
                                    sumaCantidad++;                                    
                                }
                                else {
                                    {
                                        j ++;
                                    }
                                    sumaCantidad=1;
                                }

                            }
                            else {
                                if (i > 0)
                                {
                                    j ++;
                                }
                                sumaCantidad++;                        
                            }
                                                
                            if (mtzExistencia [i][0] > 1000) {
                                presentacion = "Billete";  
                            }
                            else {
                                presentacion = "Moneda";
                            } 
                        
                            if (existencia > 0)
                            {
                                mtzExistencia [i][1] -= 1;
                                numEntrada -= mtzExistencia [i][0]; 
                                
                            }
                            else { 
                                labelExistencia.setText("No existen más Billetes y/o monedas.");
                                numEntrada = 0;
                                break;
                            } 

                            mtzDevolver [j][0] =  String.valueOf(sumaCantidad);
                            mtzDevolver [j][1] = presentacion; 
                            mtzDevolver [j][2] = String.valueOf(mtzExistencia [i][0]);  
                            break; 
                        
                        }
                    }           
                } 

                DefaultTableModel datosDevuelta = new DefaultTableModel(mtzDevolver, encabezados);
                tablaDevuelta.setModel(datosDevuelta);  
            }
            else {
                textDato.setText("");
                JOptionPane.showMessageDialog(null, "Por favor ingresar un número entero positivo");
            }
        }
        catch (Exception ex) {
            textDato.setText("");
            JOptionPane.showMessageDialog(null, "Por favor ingresar un número entero positivo");
        }
    }
} 


