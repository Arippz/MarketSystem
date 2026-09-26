package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class testgui extends JFrame {
    private JLayeredPane layeredPane;
    private JPanel backingPane;
    private JPanel parentPanel;
    private JPanel panel1;
    private JPanel barPanel;

    private JFrame mainFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            testgui testgui = new testgui();
            testgui.load();
        });
    }

    public void load() {
        mainFrame = new JFrame("tesapp");
        mainFrame.setSize(600, 600);
        parentPanel = new JPanel(new BorderLayout());

        backingPane = new JPanel(new GridBagLayout());

        panel1 = new JPanel();

        layeredPane = new JLayeredPane();

        JButton btn1 = new JButton("teslabel");
        JButton btn2 = new JButton("teslabel1");
        JButton btn3 = new JButton("X");
        btn3.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getGlassPane().setVisible(false);
            }
        });

        JPanel barPanel = new JPanel();
        barPanel.setOpaque(false);
        barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.PAGE_AXIS));
        // barPanel.setBorder(BorderFactory.createTitledBorder("lalala"));
        barPanel.add(btn1);
        barPanel.add(btn2);

        JPanel menuLayout = new JPanel(new GridBagLayout());
        
        menuLayout.setBackground(Color.GREEN);
        // menuLayout.setBorder(BorderFactory.createTitledBorder("lalala"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        backingPane.add(menuLayout, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        backingPane.add(Box.createHorizontalGlue(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        menuLayout.add(btn3, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        menuLayout.add(barPanel, gbc);

        JButton button = new JButton("hai");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getGlassPane().setVisible(true);
            }
        });

        // JPanel tesPanel = new JPanel();
        // tesPanel.setVisible(true);
        // tesPanel.setBorder(BorderFactory.createTitledBorder("tesPanel"));

        panel1.add(button);

        parentPanel.add(panel1, BorderLayout.SOUTH);
        mainFrame.setGlassPane(backingPane);
        mainFrame.getGlassPane().setVisible(false);
        backingPane.setOpaque(false);
        mainFrame.setContentPane(parentPanel);
        // layeredPane.add(parentPanel);
        // layeredPane.setLayer(panel1, JLayeredPane.DEFAULT_LAYER);
        // layeredPane.setLayer(panel2, JLayeredPane.PALETTE_LAYER);
        mainFrame.setVisible(true);
    }

}
