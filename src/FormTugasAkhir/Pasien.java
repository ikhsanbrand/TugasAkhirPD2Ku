/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormTugasAkhir;

import Koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
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
public class Pasien extends javax.swing.JFrame {

    String kel;
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private static final String[] BUTTON_TEXT = {"Laki Laki", "Perempuan"};
    private ButtonGroup JenKel = new ButtonGroup();
    static ArrayList<String> arlist = new ArrayList<>();
    static String output;
    JasperDesign jasperDesign;
    JasperReport JasRep;
    JasperPrint JasPri;

    public Pasien() {
        initComponents();
        Tanggal();
        datatable();
        autokode();
        JenKel.add(radioL);
        JenKel.add(radioP);
        this.radioL.setActionCommand("Laki - Laki");
        this.radioP.setActionCommand("Perempuan");
    }

    public void Tanggal() {

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        output = ft.format(c.getTime());
        invoicedate.setText(output);
        invoicedate.setEnabled(false);

    }

    private void autokode() {
        try {
            String sql = "SELECT MAX(RIGHT(ID_Reg,3)) AS NO FROM pasien";
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rsid = stat.executeQuery(sql);
            while (rsid.next()) {
                if (rsid.first() == false) {
                    txtid.setText("REG-001");
                } else {
                    rsid.last();
                    int auto_id = rsid.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int reg = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 3 - reg; j++) {
                        no = "0" + no;
                    }
                    txtid.setText("REG-" + no);
                    txtid.setEnabled(false);
                }
            }
            rsid.close();
            stat.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    protected void aktif() {
        txtNama.setEnabled(true);
        txtalamat.setEnabled(true);
        radioL.setEnabled(true);
        radioP.setEnabled(true);
        txtumur.setEnabled(true);
        invoicedate.setEnabled(true);
        txtkeluhan.setEnabled(true);
        txttindakan.setEnabled(true);
    }

    protected void kosong() {
        autokode();
        txtNama.setText("");
        txtalamat.setText("");
        txtumur.setText("");
        txtkeluhan.setText("");
        txttindakan.setText("");
        invoicedate.setText(output);
        jk.clearSelection();
    }

    protected void datatable() {
        Object[] Baris = {"ID", "Nama", "Alamat", "Jenis Kelamin", "Umur", "Tanggal Periksa", "Keluhan", "Tindakan"};
        tabmode = new DefaultTableModel(null, Baris);
        TblPasien.setModel(tabmode);
        String sql = "select * from pasien";

        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            while (hasil.next()) {
                String id_reg = hasil.getString("ID_Reg");
                String Nama = hasil.getString("Nama");
                String Alamat = hasil.getString("Alamat");
                String Jenis_Kelamin = hasil.getString("Jenis_Kelamin");
                String Umur = hasil.getString("Umur");
                String Tanggal_Periksa = hasil.getString("Tanggal_Periksa");
                String Keluhan = hasil.getString("Keluhan");
                String Tindakan = hasil.getString("Tindakan");

                String[] data = {id_reg, Nama, Alamat, Jenis_Kelamin, Umur, Tanggal_Periksa, Keluhan, Tindakan};
                tabmode.addRow(data);
            }
        } catch (Exception e) {

        }
    }


    /*
    * Function ini untuk mengeset combo-box tanggal secara dinamis sehingga 
    dapat berubah-ubah isinya berdasarkan bulan dan tahun yang dipilih
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jk = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPasien = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        radioL = new javax.swing.JRadioButton();
        radioP = new javax.swing.JRadioButton();
        Balek = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtkeluhan = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txttindakan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        BtnSimpan = new javax.swing.JButton();
        BtnReset = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtumur = new javax.swing.JTextField();
        invoicedate = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PASIEN KLINIK");

        TblPasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Alamat", "Jenis Kelamin", "Umur", "Tanggal Periksa", "Keluhan", "Tindakan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPasienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblPasien);

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setText("Untuk update/delete data, pilih data yang akan diubah/dihapus lalu klik Update/Delete");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nama");

        jLabel6.setText("Jenis Kelamin");

        jLabel7.setText("Tanggal Periksa");

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

        Balek.setText("Kembali");
        Balek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalekActionPerformed(evt);
            }
        });

        jLabel2.setText("ID Registrasi");

        jLabel8.setText("Keluhan");

        txtkeluhan.setColumns(20);
        txtkeluhan.setRows(5);
        jScrollPane2.setViewportView(txtkeluhan);

        jLabel9.setText("Tindakan");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Alamat");

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        jScrollPane3.setViewportView(txtalamat);

        BtnSimpan.setText("Simpan");
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });

        BtnReset.setText("Reset");
        BtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetActionPerformed(evt);
            }
        });

        jLabel10.setText("Umur");

        invoicedate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoicedateActionPerformed(evt);
            }
        });

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioL)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioP)
                                        .addGap(195, 195, 195))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(67, 67, 67)
                                        .addComponent(txtumur))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(invoicedate)))
                                .addGap(96, 96, 96)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addComponent(txttindakan))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnReset)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Balek)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnSimpan)
                            .addComponent(BtnReset)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(radioL))
                            .addComponent(radioP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtumur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(invoicedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(Balek)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String sql = "update pasien set Nama=?, Alamat=?, Jenis_kelamin=?, Umur=?, Tanggal_Periksa=?, Keluhan=?, Tindakan=? where ID_Reg='" + txtid.getText() + "'";
            arlist.clear();
            PreparedStatement stat = conn.prepareStatement(sql);
            arlist.add(txtNama.getText());
            arlist.add(txtalamat.getText());
            arlist.add(kel);
            arlist.add(txtumur.getText());
            arlist.add(invoicedate.getText());
            arlist.add(txtkeluhan.getText());
            arlist.add(txttindakan.getText());
            for (int i = 0; i < arlist.size(); i++) {
                stat.setString(i + 1, arlist.get(i));
            }
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIEDIT");
            kosong();
            txtid.requestFocus();
            datatable();
            autokode();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATA GAGAL DIEDIT " + e);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "YAKIN MAU HAPUS?", "PESAN KONFIRMASI", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from pasien where ID_Reg = '" + txtid.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS");
                kosong();
                txtid.requestFocus();
                datatable();
                autokode();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "DATA GAGAL DIHAPUS " + e);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void BalekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalekActionPerformed
        this.setVisible(false);
        new MenuPil().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BalekActionPerformed

    private void invoicedateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoicedateActionPerformed

    }//GEN-LAST:event_invoicedateActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String sql = "insert into pasien values (?,?,?,?,?,?,?,?)";
        try {
            arlist.clear();
            PreparedStatement stat = conn.prepareStatement(sql);
            arlist.add(txtid.getText());
            arlist.add(txtNama.getText());
            arlist.add(txtalamat.getText());
            arlist.add(kel);
            arlist.add(txtumur.getText());
            arlist.add(invoicedate.getText());
            arlist.add(txtkeluhan.getText());
            arlist.add(txttindakan.getText());
            for (int i = 0; i < arlist.size(); i++) {
                stat.setString(i + 1, arlist.get(i));
            }
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN");
            kosong();
            txtid.requestFocus();
            kosong();
            datatable();
            autokode();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATA GAGAL DISIMPAN" + e);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void radioLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioLActionPerformed
        kel = radioL.getText();        // TODO add your handling code here:
    }//GEN-LAST:event_radioLActionPerformed

    private void radioPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPActionPerformed
        kel = radioP.getText();        // TODO add your handling code here:
    }//GEN-LAST:event_radioPActionPerformed

    private void TblPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPasienMouseClicked
        kosong();
        int bar = TblPasien.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();

        txtid.setText(a);
        txtNama.setText(b);
        txtalamat.setText(c);
        txtumur.setText(e);
        invoicedate.setText(f);
        txtkeluhan.setText(g);
        txttindakan.setText(h);

        String jenisKelamin = TblPasien.getValueAt(bar, 3).toString();

        if (jenisKelamin.equals("Laki-Laki")) {
            radioL.setSelected(true);
        } else {
            radioP.setSelected(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_TblPasienMouseClicked

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
        kosong();       // TODO add your handling code here:
    }//GEN-LAST:event_BtnResetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String reportSource = null;
        String reportDest = null;
        try {
            reportSource = System.getProperty("user.dir") + "/Laporan/Pasien.jrxml";
            reportDest = System.getProperty("user.dir") + "/Laporan/Pasien.jasper";

            JasRep = JasperCompileManager.compileReport(reportSource);
            JasPri = JasperFillManager.fillReport(JasRep, null, conn);
            JasperExportManager.exportReportToHtmlFile(JasPri, reportDest);
            JasperViewer.viewReport(JasPri, false);

        } catch (Exception e) {
            System.out.println(e);;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Pasien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pasien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pasien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pasien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton BtnReset;
    private javax.swing.JButton BtnSimpan;
    private javax.swing.JTable TblPasien;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField invoicedate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.ButtonGroup jk;
    private javax.swing.JRadioButton radioL;
    private javax.swing.JRadioButton radioP;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextArea txtkeluhan;
    private javax.swing.JTextField txttindakan;
    private javax.swing.JTextField txtumur;
    // End of variables declaration//GEN-END:variables
}
