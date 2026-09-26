package DAO;
import models.User;
public class UserDAO {
    public static void main(String[] args) {
        User row = new User();
        row.setId(2);
        row.setName("admin");

        int id = row.getId();
        String name = row.getName();
        System.out.println("ID: " + id + ", Name: " + name);
    }
}
