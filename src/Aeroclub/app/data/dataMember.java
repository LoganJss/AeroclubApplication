package Aeroclub.app.data;

import Aeroclub.app.SHA512;
import Aeroclub.app.database;
import java.sql.*;

/**
 *
 * @author zykow
 */
public class dataMember {
    public String num_membre;
    public String login;
    private String password;
    
    public void getMember(String num_member) {
        
        Connection connection = null;
        
        try {
            
            database db = new database();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.database, db.username, db.password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM membres WHERE num_membre = ?;");
            ps.setString(1, num_member);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            num_membre = rs.getString("num_membre");
            login = rs.getString("login");
            password = rs.getString("password");
            
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void addMember(String num_membre, String login, String password) {
        
        Connection connection = null;
        
        try {
            
            database db = new database();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.database, db.username, db.password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO membres (num_membre, login, password) VALUES (?, ?, ?);");
            ps.setString(1, num_membre);
            ps.setString(2, login);
            password = (new SHA512()).hash(password);
            ps.setString(3, password);
            ps.executeUpdate();
            
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void deleteMember(String num_membre) {
        Connection connection = null;
        
        try {
            
            database db = new database();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.database, db.username, db.password);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM membres WHERE num_membre = ?;");
            ps.setString(1, num_membre);
            ps.executeUpdate();
            
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
