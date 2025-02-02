/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Oprema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author korisnik
 */
public class Class1 {
    private Connection con;
    
    public Class1() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gejmingoprema","root","");
        } catch (Exception ex) {
            Logger.getLogger(Class1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DefaultTableModel PrikaziTabelu() {
        DefaultTableModel Tabela = new DefaultTableModel();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gejmingoprema", "root", "");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM oprema");

            ResultSet rs = ps.executeQuery();
            java.sql.ResultSetMetaData stData = rs.getMetaData();

            int q = stData.getColumnCount();

            String[] columnNames = new String[q];
            for (int i = 1; i <= q; i++) {
                columnNames[i - 1] = stData.getColumnName(i);
            }
            Tabela.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Vector<Object> columnData = new Vector<>();
                int i;

                for (i = 1; i <= q; i++) {
                    columnData.add(rs.getInt("ID_opreme"));
                    columnData.add(rs.getString("Kategorija"));
                    columnData.add(rs.getString("Naziv"));
                    columnData.add(rs.getString("Proizvođač"));
                    columnData.add(rs.getInt("Cena"));
                }
                Tabela.addRow(columnData);
            }
        } catch (Exception ex) {
            Logger.getLogger(Class1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Tabela;
    }

    
    public void DodajOpremu(String kategorija, String naziv, String proizvođač, int cena) {
            try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gejmingoprema", "root", "");
            PreparedStatement ps = con.prepareStatement("insert into oprema(Kategorija, Naziv, Proizvođač, Cena) values(?,?,?,?)");
            ps.setString(1, kategorija);
            ps.setString(2, naziv);
            ps.setString(3, proizvođač);
            ps.setInt(4, cena);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Oprema dodata!");
        } catch (Exception ex) {
            Logger.getLogger(Class1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void IzmeniOpremu(int ID_opreme, String kategorija, String naziv, String proizvođač, int cena) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gejmingoprema", "root", "");
                    PreparedStatement ps = con.prepareStatement("update oprema set Kategorija=?, Naziv=?, Proizvođač=?, Cena=? where ID_opreme=?");
                    ps.setString(1, kategorija);
                    ps.setString(2, naziv);
                    ps.setString(3, proizvođač);
                    ps.setInt(4, cena);
                    ps.setInt(5, ID_opreme);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Izmena izvršena!");
                } catch (Exception ex) {
                    Logger.getLogger(Class1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        public void obrisiPodatkeIzBaze(int ID_opreme) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gejmingoprema", "root", "");
                    PreparedStatement ps = con.prepareStatement("delete from oprema where ID_opreme=?");
                    ps.setInt(1, ID_opreme);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Oprema obrisana!");
                } catch (Exception ex) {
                Logger.getLogger(Class1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
