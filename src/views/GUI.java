package views;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import models.User;
import views.GUI.ActionButton;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private JFrame mainFrame;
    private JPanel mainContainer;
    private JPanel backingPane;
    private JPanel headerContainer;
    private JPanel pageContainer;
    private JPanel menuLayout;
    private CardLayout pageCardLayout;
    private JPanel leftContent;
    private JButton[] headerButtons;
    private ArrayList<ImageIcon> headerIcon;
    private static final Map<java.net.URL, ImageIcon> cache = new HashMap<>();
    private Dimension buttonSizeHeader;

    public void gui() {

        mainFrame = new JFrame("MyAPP");
        backingPane = new JPanel(new GridBagLayout());

        mainFrame.setSize(600, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        pageCardLayout = new CardLayout();
        pageContainer = new JPanel(pageCardLayout);

        Login loginPanel = new Login(e -> menuActionPerformed(e));
        Home homePanel = new Home(e -> menuActionPerformed(e));
        Products productsPanel = new Products(e -> menuActionPerformed(e));
        Users usersPanel = new Users(e -> menuActionPerformed(e));
        Settings settingsPanel = new Settings(e -> menuActionPerformed(e));

        pageContainer.add(loginPanel, "LOGIN");
        pageContainer.add(homePanel, "DASHBOARD");
        pageContainer.add(productsPanel, "PRODUCTS");
        pageContainer.add(usersPanel, "USERS");
        pageContainer.add(settingsPanel, "SETTINGS");

        loadHeader();

        backingPane.setOpaque(false);

        mainFrame.setContentPane(mainContainer);
        mainFrame.getContentPane().add(headerContainer, BorderLayout.NORTH);
        mainFrame.getContentPane().add(pageContainer);
        mainFrame.getContentPane().setVisible(true);
        mainFrame.setGlassPane(backingPane);
        mainFrame.getGlassPane().setVisible(false);
        mainFrame.setVisible(true);
    }

    public void showLogin() {
        pageCardLayout.show(pageContainer, "LOGIN");
    }

    public void menuActionPerformed(ActionEvent e) {
        if (this.menuLayout.getComponent(0).isFocusOwner()) {
            mainFrame.getGlassPane().setVisible(false);
            headerButtons[0].setVisible(true);
        } else if (this.menuLayout.getComponent(1).isFocusOwner()) {
            System.out.println("DASHBOARD");
            pageCardLayout.show(pageContainer, "DASHBOARD");
        } else if (this.menuLayout.getComponent(2).isFocusOwner()) {
            System.out.println("PRODUCTS");
            pageCardLayout.show(pageContainer, "PRODUCTS");
        } else if (this.menuLayout.getComponent(3).isFocusOwner()) {
            System.out.println("USERS");
            pageCardLayout.show(pageContainer, "USERS");
        } else if (this.menuLayout.getComponent(4).isFocusOwner()) {
            System.out.println("SETTINGS");
            pageCardLayout.show(pageContainer, "SETTINGS");
        }
    }

    public void loadHeader() {
        headerContainer = new JPanel(new BorderLayout());
        JPanel rightContent = new JPanel();

        leftContent = new JPanel();
        headerContainer.setBorder(BorderFactory.createTitledBorder("HEADER"));

        try {
            java.net.URL imgUrl1 = getClass().getResource("ICN_HAMBURGER.png");
            java.net.URL imgUrl2 = getClass().getResource("ICN_NOTIFY.png");
            java.net.URL imgUrl3 = getClass().getResource("ICN_USER.png");
            headerIcon = new ArrayList<>();
            headerIcon.add(getIconSize(imgUrl1));
            headerIcon.add(getIconSize(imgUrl2));
            headerIcon.add(getIconSize(imgUrl3));
        } catch (Exception e) {
            System.out.println("Image cant be found!");
        }

        loadHeaderButtons();

        // Menu Icon
        headerButtons[0].addActionListener(e -> BMenus());
        leftContent.add(headerButtons[0], "CLOSEMENU");

        // Notification Icon
        rightContent.add(headerButtons[1]);
        // User Icon
        rightContent.add(headerButtons[2]);

        headerContainer.add(rightContent, BorderLayout.EAST);
        headerContainer.add(leftContent, BorderLayout.WEST);
    }

    protected void loadHeaderButtons() {
        buttonSizeHeader = new Dimension(28, 28);
        JButton BMenus = new JButton(headerIcon.get(0));
        BMenus.setName("menu");
        BMenus.setPreferredSize(buttonSizeHeader);
        BMenus.setContentAreaFilled(false);

        JButton BNotify = new JButton(headerIcon.get(1));
        BNotify.setPreferredSize(buttonSizeHeader);
        BNotify.setContentAreaFilled(false);
        JButton BProfile = new JButton(headerIcon.get(2));
        BProfile.setPreferredSize(buttonSizeHeader);
        BProfile.setContentAreaFilled(false);
        headerButtons = new JButton[] { BMenus, BNotify, BProfile };
    }

    public void BMenus() {
        if (menuLayout == null) {
            menuLayout = new JPanel();
            headerButtons[0].setVisible(false);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;
            backingPane.add(menuLayout, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 10.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            backingPane.add(Box.createHorizontalGlue(), gbc);
            menuLayout.setLayout(new GridBagLayout());
            menuLayout.setBorder(BorderFactory.createTitledBorder("lah"));

            createMenuButtons(gbc);
            if (menuLayout.getComponentCount() != 0) {
                Component[] buttons = menuLayout.getComponents();
                for (Component c : buttons) {
                    if (c instanceof JButton) {
                        JButton button = (JButton) c;
                        button.addActionListener(e -> menuActionPerformed(e));
                    }
                }
                mainFrame.getGlassPane().setVisible(true);
            }
        } else {
            mainFrame.getGlassPane().setVisible(true);
        }
    }

    public void createMenuButtons(GridBagConstraints gbc) {
        ArrayList<JButton> menuButtons = new ArrayList<>();
        if (menuButtons.isEmpty()) {
            String[] buttonName = { "X", "DASHBOARD", "PRODUCTS", "USERS", "SETTINGS" };
            Integer i = 1;
            for (String getString : buttonName) {
                JButton menuButton = new JButton(getString);
                menuButton.setContentAreaFilled(true);
                menuButton.setBorderPainted(true);
                menuButton.setBackground(new Color(255, 0, 0));
                gbc = new GridBagConstraints();
                if (getString == "X") {
                    gbc.gridx = 1;
                    gbc.anchor = GridBagConstraints.FIRST_LINE_END;
                    gbc.weightx = 0.0;
                    gbc.weighty = 0.3;
                    menuLayout.add(menuButton, gbc);
                } else {
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridx = 0;
                    gbc.gridy = i;
                    gbc.gridwidth = 2;
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    menuButtons.add(menuButton);
                    menuLayout.add(menuButton, gbc);
                }
                i++;
            }
            ;
        }
    }

    public static ImageIcon getIconSize(java.net.URL path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        ImageIcon originIcon = new ImageIcon(path);
        Image getIcon = originIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(getIcon);
        cache.put(path, scaledIcon);
        return scaledIcon;

    }

    static class ActionButton extends JButton {

        private final int buttonId;

        public ActionButton(String text, int id) {
            super(text);
            new JButton(text);
            this.buttonId = id;
        }

        public int getId() {
            return buttonId;
        }

    }

    static class ActionButtonRenderer extends JPanel implements TableCellRenderer {
        ActionButton editButton = new ActionButton("EDIT", 1);
        JLabel teslabel = new JLabel("tes");
        ActionButton deleteButton = new ActionButton("MORE", 2);
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            table.getDefaultRenderer(JComponent.class).getClass();
            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            this.add(editButton);   
            this.add(deleteButton);
            return this;
        }

    }

    static class VerticalLinesBorder extends AbstractBorder{
        private final Color lineColor;
        private final int thickness;
        private final int verticalSize;
        private final int horizontalSize;
        public VerticalLinesBorder(Color lineColor, int thickness, int verticalSize, int horizontalSize){
            this.lineColor = lineColor;
            this.thickness = thickness;
            this.verticalSize = verticalSize;
            this.horizontalSize = horizontalSize;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(lineColor);
            g2d.setStroke(new BasicStroke(thickness));

            int y1 = y + verticalSize;
            int y2 = y + height - verticalSize;

            g2d.drawLine(x, y1, x, y2);

        
        }
        @Override 
        public Insets getBorderInsets(Component c){
            return new Insets(0, thickness + 8, 0, 0);
        }
    }


    static class VerticalLinesRenderer extends DefaultTableCellRenderer{
        private final VerticalLinesBorder customBorder = new VerticalLinesBorder(Color.BLUE, 2, 8, 1);
        public VerticalLinesRenderer (){
            setOpaque(true);
        }

        @Override 
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(customBorder);
            setHorizontalAlignment(SwingConstants.LEFT);
            // if(isSelected){
            //     setBackground(table.getSelectionBackground());
            //     setForeground(table.getSelectionForeground());
            // }else {
            //     setBackground(table.getBackground());
            //     setForeground(table.getForeground());
            // }
            
            return this;
        }
    }

    static class tableModel extends AbstractTableModel {

        Boolean isChecked = true;
        String[] columnNames = { "ID", "NAME", "EMAIL", "PASSWORD", "ROLES", "ACTION" };

        Object[][] data = { { "1", "arifin", "arifin@gmail.com", "12345", "admin", "a" },
                            { "2", "agus", "agus@gmail.com", "12345", "user", "a" },
                            { "2", "agus", "agus@gmail.com", "12345", "user", "a" },
                            { "2", "agus", "agus@gmail.com", "12345", "user", "a" }, 
                            { "2", "agus", "agus@gmail.com", "12345", "user", "a" }};

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

    }



}
