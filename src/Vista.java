import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame implements ActionListener {

    String[] columnas = {"Nutriente", "Cant. Req.", "Maiz A", "Avena", "Maiz B", "Salvado", "Linaza"};
    DefaultTableModel dtm = new DefaultTableModel(columnas, 0);
    JButton solveButton;
    JTable tabla;

    String[] nut = {"DN", "DP", "Ca", "Ph"};
    //Dieta dieta = new Dieta(this);

    public Vista() {

        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        tabla = new JTable();
        tabla.setModel(dtm);
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));

        JScrollPane scroll = new JScrollPane(tabla);
        add(solveButton);
        add(scroll);
        llenarNutrientes();
        inicializar();
        setSize(550, 500);
        setVisible(true);

    }

    public void llenarNutrientes() {

        for (int i = 0; i < nut.length; i++) {

            dtm.setRowCount(nut.length);
            dtm.setValueAt(nut[i], i, 0);
        }
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    //break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        Vista v = new Vista();
    }

    public void inicializar() {

        for (int i = 1; i < 7; i++) {

            for(int j=0; j<4; j++ ) {
                dtm.setValueAt("3", j, i);
            
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == solveButton) {

            //dieta.solve();
        }

    }

}
