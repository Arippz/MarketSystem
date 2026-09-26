package views;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;



public class Products extends JPanel {
    private JTable table;
    public Products(ActionListener e){
        JLabel title = new JLabel("Products");
        add(title);


        table = new JTable();

    }
}
