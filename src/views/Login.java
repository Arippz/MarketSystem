package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel {
    public Login(ActionListener action){
        setSize(500, 500);
        JLabel LTitle = new JLabel();
        LTitle.setText("Login");
        add(LTitle);

        JTextField Iemail = new JTextField();
        add(Iemail);
        JTextField Ipassword = new JTextField();
        add(Ipassword);

        JButton tesButton = new JButton();
        tesButton.addActionListener(action);
        add(tesButton);
    }
}
