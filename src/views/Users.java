package views;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;



import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import javax.swing.table.TableModel;

import views.GUI.tableModel;
import views.GUI.ActionButtonRenderer;

import javax.swing.BorderFactory;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



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

        tableUser.setBackground(new Color(0, 0, 0, 0));
        tableUser.setEnabled(true);
        tableUser.getColumnModel().getColumn(5).setMinWidth(122);


        // Table Header thing
        JTableHeader tbheader = tableUser.getTableHeader();
        final DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer(){
            @Override 
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                int lineHeight = getHeight();
                int width = getWidth();
                int x = getLocation().x;

                // int y = (int) (getLocation().y / 0.5);
                int y1 = getLocation().y + lineHeight;
                int y2 = getLocation().y + y1;
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.blue);
                System.out.println(y1);
                System.out.println(y2);
                g2d.drawLine(x, y1, width, 16);
            }
        };
        headerRenderer.setBorder(null);

        tbheader.setDefaultRenderer(headerRenderer);
        tbheader.setReorderingAllowed(false);
        tbheader.setResizingAllowed(false);
        tbheader.setForeground(new Color(243, 244, 244));
        tbheader.setBackground(new Color(0, 0, 0, 0));


        tableUser.setFocusable(false);
        


        tableUser.setFillsViewportHeight(true);
        tableUser.setIntercellSpacing(new Dimension(0, 0));

        tableUser.setShowHorizontalLines(false);
        tableUser.setShowVerticalLines(false);

        






        tableUser.setForeground(new Color(243, 244, 244));

        JScrollPane scrollPane = new JScrollPane(tableUser);
        scrollPane.setOpaque(false);



        // Border thing
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, new GridBagConstraints());


    }
}
