package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import views.GUI.tableModel;
import views.GUI.tableRenderer;

public class Users extends JPanel {
    private JTable tableUser;
    private GUI guiStuff = new GUI();
    private Dimension cellRenderSize;
    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, new Color(6, 30, 41), getWidth() / 2, 0, new Color(29, 84, 109));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public Users(ActionListener action) {
        


        setLayout(new GridBagLayout());


       
        TableModel tbmodel = guiStuff.new tableModel();
        tableRenderer renderer = guiStuff.new tableRenderer();



        tableUser = new JTable(tbmodel){
            public TableCellRenderer getCellRenderer(int row, int col){
                for(int a = 0; a <= 2; a++){
                    for(int b = 0; b <= tableUser.getRowCount(); b++){
                        if ((row == b) && (col == 5)){
                            return renderer;
                        }
                    }
                }
                return super.getCellRenderer(row, col);
            }

            
        };
        
        
        tableUser.setEnabled(true);
        tableUser.getColumnModel().getColumn(5).setMinWidth(122);
        // tableUser.setSiz
        JTable.DropLocation dropLocation = tableUser.getDropLocation();
        System.out.print(dropLocation);
           




        // JLabel tesLabel = new JLabel("testing");

        tableUser.setCellSelectionEnabled(false);
        tableUser.setFillsViewportHeight(true);



        tableUser.setShowHorizontalLines(false);
        tableUser.setShowVerticalLines(false);
        
        tableUser.setBackground(Color.YELLOW);
        
        tableUser.getTableHeader().setBackground(Color.CYAN);
        JScrollPane scrollPane = new JScrollPane(tableUser);

        
        add(scrollPane, new GridBagConstraints());
    }
}

