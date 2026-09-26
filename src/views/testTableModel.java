package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;


public class testTableModel extends JFrame {
    private JTable tableUser;
    public testTableModel(){
        setTitle("tesModel");
        setLayout(new BorderLayout());
        
        setSize(600, 600);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // TableModel tableModel = new TableModel() {
        //     @Override
        //     public void
        // };

        
        JPanel container = new JPanel(new BorderLayout());

        
        Object[] columnNames = {"ID", "NAME", "EMAIL", "PASSWORD", "ROLES"};
        
        Object[][] data = {{"1", "arifin", "arifin@gmail.com", "12345", "admin"}, {"2", "agus", "agus@gmail.com", "12345", "user"}};
        tableUser = new JTable(data, columnNames);
        
        tableUser.setCellSelectionEnabled(false);
        
        
        tableUser.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tableUser);
        container.add(tableUser.getTableHeader(), BorderLayout.SOUTH);
        container.add(tableUser, BorderLayout.CENTER);

        add(container);


        setVisible(true);
        
    }    
}
