/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormTugasAkhir;


import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author IKHSAN
 */
public class Petugas extends javax.swing.JFrame {

    public Petugas() {
        initComponents();
//        setUnitKerja();
        datatable();
        autokode();
        JenKel.add(radioL);
        JenKel.add(radioP);
        this.radioL.setActionCommand("Laki - Laki");
        this.radioP.setActionCommand("Perempuan");
    }
    
    public static Tree t;
    String kel;
    String Tanggal;
    static ArrayList<String> arlist22 = new ArrayList<>();
    Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private static final String[] BUTTON_TEXT = {"Laki - Laki", "Perempuan"};
    private ButtonGroup JenKel = new ButtonGroup();
    private String tgl;
    JasperDesign jasperDesign;
    JasperReport JasRep;
    JasperPrint JasPri;

    protected void aktif() {
        txtNama.setEnabled(true);
        radioL.setEnabled(true);
        radioP.setEnabled(true);
        JD_Tgl.setCalendar(null);
    }

    protected void kosong() {
        autokode();
        txtNama.setText("");
        CbPetugas.setSelectedIndex(0);
        JD_Tgl.setCalendar(null);
        radioL.setSelected(false);
        radioP.setSelected(false);
    }

    private void autokode() {
        try {
            String sql = "SELECT MAX(RIGHT(ID_Pet,3)) AS NO FROM Petugas";
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rsid = stat.executeQuery(sql);
            while (rsid.next()) {
                if (rsid.first() == false) {
                    Txt_Id.setText("PET-001");
                } else {
                    rsid.last();
                    int auto_id = rsid.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int pet = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 3 - pet; j++) {
                        no = "0" + no;
                    }
                    Txt_Id.setText("PET-" + no);
                    Txt_Id.setEnabled(false);
                }
            }
            rsid.close();
            stat.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    protected void datatable() {
        Object[] Baris = {"ID", "Nama", "Jenis Kelamin", "Tanggal Lahir", "Jabatan"};
        tabmode = new DefaultTableModel(null, Baris);
        TblPetugas.setModel(tabmode);
        String sql = "select * from petugas";

        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            while (hasil.next()) {
                String id_pet = hasil.getString("ID_Pet");
                String Nama = hasil.getString("Nama");
                String Jenis_Kelamin = hasil.getString("Jenis_Kelamin");
                String Tanggal_Lahir = hasil.getString("Tanggal_Lahir");
                String Jabatan = hasil.getString("Jabatan");

                String[] data = {id_pet, Nama, Jenis_Kelamin, Tanggal_Lahir, Jabatan};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    public static Date getTanggalFromTable(JTable table, int kolom) {
        JTable tabel = table;
        String str_tgl = String.valueOf(tabel.getValueAt(tabel.getSelectedRow(), kolom));
        Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tgl);
        } catch (ParseException ex) {
            Logger.getLogger(Petugas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jk = new javax.swing.ButtonGroup();
        btnUpdate1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPetugas = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Txt_Id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        radioL = new javax.swing.JRadioButton();
        radioP = new javax.swing.JRadioButton();
        btnReset = new javax.swing.JButton();
        Balek = new javax.swing.JToggleButton();
        CbPetugas = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        JD_Tgl = new com.toedter.calendar.JDateChooser();
        Print = new javax.swing.JButton();

        btnUpdate1.setText("Update");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PETUGAS KLINIK");

        TblPetugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Petugas", "Nama", "Jenis Kelamin", "Tanggal Lahir", "Jabatan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPetugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblPetugas);
        if (TblPetugas.getColumnModel().getColumnCount() > 0) {
            TblPetugas.getColumnModel().getColumn(0).setResizable(false);
            TblPetugas.getColumnModel().getColumn(1).setResizable(false);
            TblPetugas.getColumnModel().getColumn(2).setResizable(false);
            TblPetugas.getColumnModel().getColumn(3).setResizable(false);
            TblPetugas.getColumnModel().getColumn(4).setResizable(false);
        }

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setText("Untuk update/delete data, pilih data yang akan diubah/dihapus lalu klik Update/Delete");

        jLabel5.setText("Jabatan");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ID Petugas");

        jLabel6.setText("Jenis Kelamin");

        jLabel7.setText("Tanggal Lahir");

        jk.add(radioL);
        radioL.setText("Laki-Laki");
        radioL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioLActionPerformed(evt);
            }
        });

        jk.add(radioP);
        radioP.setText("Perempuan");
        radioP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        Balek.setText("Kembali");
        Balek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalekActionPerformed(evt);
            }
        });

        CbPetugas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ketua", "Sekretaris", "Bendahara", "Apoteker", "Dokter" }));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Nama");

        JD_Tgl.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JD_TglPropertyChange(evt);
            }
        });

        Print.setText("Print");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioL)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioP)
                                        .addGap(62, 62, 62))
                                    .addComponent(Txt_Id)
                                    .addComponent(txtNama)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(CbPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(JD_Tgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(10, 10, 10)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Balek)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Print)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(radioL)
                            .addComponent(radioP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(JD_Tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(CbPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnReset)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(Balek)
                    .addComponent(Print))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        String sql = "insert into petugas values (?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            t = new Tree(new TreeNode(""));
            t.root.add_child(new TreeNode("Ketua"), 0);
            t.root.add_child(new TreeNode("Sekretaris"), 0);
            t.root.add_child(new TreeNode("Bendahara"), 0);
            t.root.add_child(new TreeNode("Apoteker"), 0);
            t.root.add_child(new TreeNode("Dokter"), 0);
            int panjang = t.root.children.size();
            String[] titik = new String[panjang];
            double[] KM = new double[panjang];
            for (int i = 0; i < t.root.children.size(); i++) {
                titik[i] = t.root.children.get(i).data;
                KM[i] = t.root.children.get(i).distance;
            }
            switch (CbPetugas.getSelectedIndex()) {
                case 0:
                    stat.setString(5, t.root.children.get(0).data);
                    break;
                case 1:
                    stat.setString(5, t.root.children.get(1).data);
                    break;
                case 2:
                    stat.setString(5, t.root.children.get(2).data);
                    break;
                case 3:
                    stat.setString(5, t.root.children.get(3).data);
                    break;
                case 4:
                    stat.setString(5, t.root.children.get(4).data);
                    break;
            }

            arlist22.clear();
            arlist22.add(Txt_Id.getText());
            arlist22.add(txtNama.getText());
            arlist22.add(kel);
            arlist22.add(tgl);
            for (int i = 0; i < arlist22.size(); i++) {
                stat.setString(i + 1, arlist22.get(i));
            }
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN");
            kosong();
            autokode();
            Txt_Id.requestFocus();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATA GAGAL DISIMPAN" + e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String sql = "update petugas set Nama=?, Jenis_Kelamin=?, Tanggal_Lahir=?, Jabatan=? where ID_Pet='" + Txt_Id.getText() + "'";
            arlist22.clear();
            PreparedStatement stat = conn.prepareStatement(sql);
            t = new Tree(new TreeNode(""));
            t.root.add_child(new TreeNode("Ketua"), 0);
            t.root.add_child(new TreeNode("Sekretaris"), 0);
            t.root.add_child(new TreeNode("Bendahara"), 0);
            t.root.add_child(new TreeNode("Apoteker"), 0);
            t.root.add_child(new TreeNode("Dokter"), 0);
            int panjang = t.root.children.size();
            String[] titik = new String[panjang];
            double[] KM = new double[panjang];
            for (int i = 0; i < t.root.children.size(); i++) {
                titik[i] = t.root.children.get(i).data;
                KM[i] = t.root.children.get(i).distance;
            }
            switch (CbPetugas.getSelectedIndex()) {
                case 0:
                    stat.setString(4, t.root.children.get(0).data);
                    break;
                case 1:
                    stat.setString(4, t.root.children.get(1).data);
                    break;
                case 2:
                    stat.setString(4, t.root.children.get(2).data);
                    break;
                case 3:
                    stat.setString(4, t.root.children.get(3).data);
                    break;
                case 4:
                    stat.setString(4, t.root.children.get(4).data);
                    break;
            }
            arlist22.add(txtNama.getText());
            arlist22.add(kel);
            arlist22.add(tgl);
            for (int i = 0; i < arlist22.size(); i++) {
                stat.setString(i + 1, arlist22.get(i));
            }
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIEDIT");
            kosong();
            Txt_Id.requestFocus();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATA GAGAL DIEDIT " + e);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "YAKIN MAU HAPUS?", "PESAN KONFIRMASI", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from petugas where ID_Pet = '" + Txt_Id.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS");
                kosong();
                Txt_Id.requestFocus();
                datatable();
                autokode();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "DATA GAGAL DIHAPUS " + e);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void BalekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalekActionPerformed
        this.setVisible(false);
        new MenuPil().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BalekActionPerformed

    private void JD_TglPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JD_TglPropertyChange
        if (JD_Tgl.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl = format.format(JD_Tgl.getDate());
        }      // TODO add your handling code here:
    }//GEN-LAST:event_JD_TglPropertyChange

    private void radioLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioLActionPerformed
        kel = radioL.getText();             // TODO add your handling code here:
    }//GEN-LAST:event_radioLActionPerformed

    private void radioPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPActionPerformed
        kel = radioP.getText();         // TODO add your handling code here:
    }//GEN-LAST:event_radioPActionPerformed

    private void TblPetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPetugasMouseClicked

        int bar = TblPetugas.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();

        String e = tabmode.getValueAt(bar, 4).toString();
        Txt_Id.setText(a);
        txtNama.setText(b);
        CbPetugas.setSelectedItem(e);
        JD_Tgl.setDate(getTanggalFromTable(TblPetugas, 3));


        String jenisKelamin = TblPetugas.getValueAt(bar, 2).toString();

        if (jenisKelamin.equals("Laki-Laki")) {
            radioL.setSelected(true);
        } else {
            radioP.setSelected(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_TblPetugasMouseClicked

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed

        String reportSource = null;
        String reportDest = null;
        try {
            reportSource = System.getProperty("user.dir") + "/Laporan/Petugas.jrxml";
            reportDest = System.getProperty("user.dir") + "/Laporan/Petugas.jasper";

            JasRep = JasperCompileManager.compileReport(reportSource);
            JasPri = JasperFillManager.fillReport(JasRep, null, conn);
            JasperExportManager.exportReportToHtmlFile(JasPri, reportDest);
            JasperViewer.viewReport(JasPri, false);

        } catch (Exception e) {
            System.out.println(e);;
        }

    }//GEN-LAST:event_PrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Petugas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Petugas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Petugas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Petugas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Balek;
    private javax.swing.JComboBox<String> CbPetugas;
    private com.toedter.calendar.JDateChooser JD_Tgl;
    private javax.swing.JButton Print;
    private javax.swing.JTable TblPetugas;
    private javax.swing.JTextField Txt_Id;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup jk;
    private javax.swing.JRadioButton radioL;
    private javax.swing.JRadioButton radioP;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
