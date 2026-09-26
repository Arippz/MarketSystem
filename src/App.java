import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import config.DBConnection;
import controllers.UserController;
import models.DatabaseSeeder;
import DAO.UserDAO;
import views.GUI;
import views.testTableModel;



 
public class App {
    private static GUI startAPP;
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
        // DatabaseSeeder.seed();
        
        // DBConnection db = new DBConnection();
        
        // UserDAO userModel = new UserDAO();
    

        // UserDAO.main(args);
        
        SwingUtilities.invokeLater(() -> {
            startAPP = new GUI();
            startAPP.gui();
            
            // testTableModel startAPP = new testTableModel();
            // // startAPP();
        });

        // UserController controller = new UserController(userModel, homeView);
    }
}
