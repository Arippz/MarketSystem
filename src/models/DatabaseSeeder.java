package models;
import java.sql.*;
import java.security.*;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;



import config.DBConnection;
public class DatabaseSeeder {
    public static void seed(){
        DBConnection db = new DBConnection();

        String checkSQL = "SELECT COUNT(*) FROM users";
        String insertSQL = "INSERT INTO users (name, email, password, roles) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
            PreparedStatement chckStmt = conn.prepareStatement(checkSQL);
            ResultSet rs = chckStmt.executeQuery())
            {
                if(rs.next() && rs.getInt(1) == 0){
                    System.out.println("[SEEDER] Database is empty, start seeding....");

                    try(PreparedStatement insertStmt = conn.prepareStatement(insertSQL)){
                        insertStmt.setString(1, "admin");
                        insertStmt.setString(2, "admin@gmail.com");
                        String rawPassword = "123";
                            
                        String securePassword = hashingPassword(rawPassword);


                        insertStmt.setString(3, securePassword);
                        insertStmt.setString(4, "admin");
                        insertStmt.executeUpdate();
                        System.out.println("[SEEDER] Seeding done!");

                    }
                }else{
                    System.out.println("[SEEDER] Database has record, skip seeding.");
                }
        }catch(SQLException | NoSuchAlgorithmException | InvalidKeySpecException e){
             System.err.println("[SEEDER] Failed to seeding new record: " + e.getMessage());
        }


    }


    public static String hashingPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = generateSalt();

        byte[] hash = hashPassword(password.toCharArray(), salt);

        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }


    public static byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] hashPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
            PBEKeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return f.generateSecret(spec).getEncoded();
        }

    
    public static boolean verifypassword(String password, String storedHash) throws NoSuchAlgorithmException, InvalidKeySpecException{
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);

        byte[] testHash = hashPassword(password.toCharArray(), salt);

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++){
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;

    }

}
