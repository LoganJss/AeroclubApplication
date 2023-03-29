package Aeroclub.app.data;

import Aeroclub.app.SHA512;
import java.sql.*;

public class dataMember {
    public int num_membre;
    public String login;
    public String password;
    
    public void getMember(int num_member) {
        
        Connection connection = null;
        
        try {
            
            database db = new database();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.database, db.username, db.password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM membres WHERE num_membre = ?;");
            ps.setInt(1, num_member);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            num_membre = rs.getInt("num_membre");
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
    
    public void addMember(int num_membre, String login, String password) {
        
        Connection connection = null;
        
        try {
            
            database db = new database();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.database, db.username, db.password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO membres (num_membre, login, password) VALUES (?, ?, ?);");
            ps.setInt(1, num_membre);
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
    
    public void modifyMember(int num_membre, String login, String password) {
        
        Connection connection = null;
        
        try {
            
            database db = new database();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.database, db.username, db.password);
            PreparedStatement ps = connection.prepareStatement("UPDATE membres SET login = ?, password = ? WHERE num_membre = ?;");
            ps.setString(1, login);
            password = (new SHA512()).hash(password);
            ps.setString(2, password);
            ps.setInt(3, num_membre);
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
    
    public void deleteMember(int num_membre) {
        Connection connection = null;
        
        try {
            
            database db = new database();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.database, db.username, db.password);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM membres WHERE num_membre = ?;");
            ps.setInt(1, num_membre);
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
