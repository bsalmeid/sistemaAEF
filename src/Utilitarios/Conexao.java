package Utilitarios;

import Icones.FormatarICO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexao {

    public static String URL;
    public static String DRIVER;
    public static String Usuario;
    public static String SENHA;
    public static Connection con;

    public Conexao() {
        try {
            Class.forName(DRIVER);
            if (con == null) {
                con = DriverManager.getConnection(URL, Usuario, SENHA);
                con.setAutoCommit(true);
            } else if (con.isClosed()) {
                con = DriverManager.getConnection(URL, Usuario, SENHA);
                con.setAutoCommit(true);
            } else if (!con.isValid(0)) {
                con.close();
                con = DriverManager.getConnection(URL, Usuario, SENHA);
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Erro: " + e, "Erro", 0, FormatarICO.ICObtnSair());
            try {
                con.close();
                con = DriverManager.getConnection(URL, Usuario, SENHA);
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Boolean setDadosConexao(String tipoConexao) {
        switch (tipoConexao) {
            case "Local":
                URL = "jdbc:mysql://localhost:3306/aefvilhena2";
                DRIVER = "com.mysql.jdbc.Driver";
                Usuario = "root";
                SENHA = "";
                HibernateUtil.getSessionFactory().openSession();
                return true;
//            case "TCP/IP - Local":
//                URL = "jdbc:mysql://192.168.0.250:3306/aefvilhena2?zeroDateTimeBehavior=convertToNull";
//                DRIVER = "com.mysql.jdbc.Driver";
//                Usuario = "bruno";
//                SENHA = "niv89dy!B"; // Não Usar por enquanto
//                return true; 
//            case "TCP/IP - HostNet":
//                URL = "jdbc:mysql://xmysql2.aefvilhena.com:3306/aefvilhena2?autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull";
//                DRIVER = "com.mysql.jdbc.Driver";
//                Usuario = "aefvilhena2";
//                SENHA = "niv89dy!B"; // Não Usar por enquanto
//                return true;
//            case "TCP/IP - Vilhena":
//                URL = "jdbc:mysql://177.129.90.94:6033/aefvilhena2?zeroDateTimeBehavior=convertToNull";
//                DRIVER = "com.mysql.jdbc.Driver";
//                Usuario = "bruno";
//                SENHA = "niv89dy!B"; // Não Usar por enquanto
//                return true; 
            default:
                JOptionPane.showMessageDialog(null, "Conexão Não Localizada!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
        }
    }

    public static Connection getConnection() {
        try {
            if (con == null) {
                new Conexao();
            }
        } catch (Exception e) {
            return null;
        }
        return con;
    }

    public static void fecharConexao() {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0);
        }
    }

    public static Conexao ReiniciarCon() {
        return new Conexao();
    }

    public static void main(String args[]) {
        Conexao.getConnection();
    }

    
}
