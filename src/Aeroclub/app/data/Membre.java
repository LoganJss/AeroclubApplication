package Aeroclub.app.data;

import Aeroclub.app.SHA512;
import java.sql.*;

public class Membre {
    public int num_membre;
    public String nom;
    public String prenom;
    public String adresse;
    public String code_postal;
    public String ville;
    public int num_civil;
    public String tel;
    public String portable;
    public String email;
    public String commentaire;
    public Date date_vm;
    public Date validite_vm;
    public Date date_seq;
    public Date validite_seq;
    public String num_badge;
    public int num_qualif;
    public String profession;
    public Date date_naissance;
    public String lieu_naissance;
    public String carte_federale;
    public Date date_attestation;
    public Date date_theorique_bb;
    public Date date_theorique_ppla;
    public Date date_bb;
    public Date date_ppla;
    public String numero_bb;
    public String numera_ppla;
    public Date date_entree;
    public int membre_actif;
    public int membre_inscrit;
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
            nom = rs.getString("nom");
            prenom = rs.getString("prenom");
            adresse = rs.getString("adresse");
            code_postal = rs.getString("code_postal");
            ville = rs.getString("ville");
            num_civil = rs.getInt("num_civil");
            tel = rs.getString("tel");
            portable = rs.getString("portable");
            email = rs.getString("email");
            commentaire = rs.getString("commentaire");
            date_vm = rs.getDate("date_vm");
            validite_vm = rs.getDate("validite_vm");
            date_seq = rs.getDate("date_seq");
            validite_seq = rs.getDate("validite_seq");
            num_badge = rs.getString("num_badge");
            num_qualif = rs.getInt("num_qualif");
            profession = rs.getString("profession");
            date_naissance = rs.getDate("date_naissance");
            lieu_naissance = rs.getString("lieu_naissance");
            carte_federale = rs.getString("carte_federale");
            date_attestation = rs.getDate("date_attestation");
            date_theorique_bb = rs.getDate("date_theorique_bb");
            date_theorique_ppla = rs.getDate("date_theorique_ppla");
            date_bb = rs.getDate("date_bb");
            date_ppla = rs.getDate("date_ppla");
            numero_bb = rs.getString("numero_bb");
            numera_ppla = rs.getString("numera_ppla");
            date_entree = rs.getDate("date_entree");
            membre_actif = rs.getInt("membre_actif");
            membre_inscrit = rs.getInt("membre_inscrit");
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
    
    public void addMember() {
        
        Connection connection = null;
        
        try {
            
            database db = new database();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.database, db.username, db.password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO public.membres(nom, prenom, adresse, code_postal, ville, num_civil, tel, portable, email, commentaire, date_vm, validite_vm, date_seq, validite_seq, num_badge, num_qualif, profession, date_naissance, lieu_naissance, carte_federale, date_attestation, date_theorique_bb, date_theorique_ppla, date_bb, date_ppla, numero_bb, numera_ppla, date_entree, membre_actif, membre_inscrit, login, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, this.nom);
            ps.setString(2, this.prenom);
            ps.setString(3, this.adresse);
            ps.setString(4, this.code_postal);
            ps.setString(5, this.ville);
            ps.setInt(6, this.num_civil);
            ps.setString(7, this.tel);
            ps.setString(8, this.portable);
            ps.setString(9, this.email);
            ps.setString(10, this.commentaire);
            ps.setDate(11, this.date_vm);
            ps.setDate(12, this.validite_vm);
            ps.setDate(13, this.date_seq);
            ps.setDate(14, this.validite_seq);
            ps.setString(15, this.num_badge);
            ps.setInt(16, this.num_qualif);
            ps.setString(17, this.profession);
            ps.setDate(18, this.date_naissance);
            ps.setString(19, this.lieu_naissance);
            ps.setString(20, this.carte_federale);
            ps.setDate(21, this.date_attestation);
            ps.setDate(22, this.date_theorique_bb);
            ps.setDate(23, this.date_theorique_ppla);
            ps.setDate(24, this.date_bb);
            ps.setDate(25, this.date_ppla);
            ps.setString(26, this.numero_bb);
            ps.setString(27, this.numera_ppla);
            ps.setDate(28, this.date_entree);
            ps.setInt(29, this.membre_actif);
            ps.setInt(30, this.membre_inscrit);
            ps.setString(31, this.login);
            ps.setString(32, (new SHA512()).hash(password));
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
