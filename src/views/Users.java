package views;

import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Component;


import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import views.GUI.tableModel;
import views.GUI.ActionButtonRenderer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;


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


       
        TableModel tbmodel = new tableModel();
        ActionButtonRenderer buttonRenderer = new ActionButtonRenderer();
        
        tableUser = new JTable(tbmodel) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Insets insets = getInsets();
                
                int rightInset = insets.right;


                int lineHeight = (int) (getHeight() * 0.5);
                int y = (getHeight() - lineHeight) / 2;

                Graphics2D g2d = (Graphics2D) g.create();
                try {
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(2));
                    for (int column = 1; column < getColumnCount(); column++) {
                        int x = getCellRect(0, column, true).x;
                        g2d.drawLine(x, y, x, y + lineHeight);
                    }
                } finally {
                    g2d.dispose();
                }   
            }

        };
        tableUser.getColumnModel().getColumn(5).setCellRenderer(buttonRenderer);
        //     public TableCellRenderer getCellRenderer(int row, int col){
        //         for(int a = 0; a <= 2; a++){
        //             for(int b = 0; b <= tableUser.getRowCount(); b++){
        //                 if ((row == b) && (col == 5)){
        //                     return renderer;
        //                 }
        //             }
        //         }
        //         return super.getCellRenderer(row, col);
        //     }

            
        // };
        tableUser.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        tableUser.setBackground(new Color(0, 0, 0, 0));
        tableUser.setEnabled(true);
        tableUser.getColumnModel().getColumn(5).setMinWidth(122);
        JTableHeader tbheader = tableUser.getTableHeader();
        tbheader.setReorderingAllowed(false);
        // tableUser.setSiz

           




        // JLabel tesLabel = new JLabel("testing");

        tableUser.setCellSelectionEnabled(false);
        tableUser.setFillsViewportHeight(true);
        tableUser.setIntercellSpacing(new Dimension(0, 0));

        tableUser.setShowHorizontalLines(false);
        tableUser.setShowVerticalLines(false);
        tableUser.getTableHeader().setBackground(Color.CYAN);
        tableUser.setForeground(new Color(243, 244, 244));
        JScrollPane scrollPane = new JScrollPane(tableUser);
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, new GridBagConstraints());
    }
}
