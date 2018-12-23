package Koneksi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import FormTugasAkhir.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author USER
 */
public class koneksi {

    public Connection koneksi;
    public Statement stmt;

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil konek");
        } catch (ClassNotFoundException ex) {
            System.out.println("Gagal konek Database" + ex);
        }
        String url = "jdbc:mysql://localhost/klinikrose";
        try {
            koneksi = (Connection) DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            System.out.println("Gagal konek database" + ex);
        }
        return koneksi;
    }
}
