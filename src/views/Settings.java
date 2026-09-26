package views;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Settings extends JPanel {
    public Settings(ActionListener e){
        JLabel title = new JLabel("Settings");
        add(title);
    }
}
