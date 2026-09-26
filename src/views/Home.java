package views;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;



public class Home extends JPanel {
    public Home(ActionListener action){
        setSize(500, 500);
        JButton button = new JButton("hai");
        button.addActionListener(action);
        button.setBounds(0, 0, 0, 0);
        add(button);
        

        JPanel menuLayout = new JPanel();

        JButton lala = new JButton("tes");
        menuLayout.add(lala);
    }

    // public JPanel passContentPanel(){
        
    // }

}

