/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormTugasAkhir;

//import static FormTugasAkhir.Tri.t;
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

/**
 *
 * @author IKHSAN
 */
public class Transaksi extends javax.swing.JFrame {

    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    static ArrayList<String> arlist = new ArrayList<>();
    static String output;
    public static Tree t;

    public Transaksi() {
        initComponents();
        Tanggal();
//        setUnitKerja();
        datatable();
        autokode();
    }

    protected void aktif() {
//        txt_id.setEnabled(true);
        txt_nama.setEnabled(true);
        invoicedate.setEnabled(true);
        txt_ket.setEnabled(true);
        Nominal.setEnabled(true);
    }

    protected void kosong() {
        autokode();
        txt_nama.setText("");
        txt_ket.setText("");
        Nominal.setText("");
        invoicedate.setText(output);
        cbTransaksi.setSelectedIndex(0);
    }

    private void autokode() {
        try {
            String sql = "SELECT MAX(RIGHT(ID_Pem,3)) AS NO FROM transaksi";
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rsid = stat.executeQuery(sql);
            while (rsid.next()) {
                if (rsid.first() == false) {
                    txt_id.setText("TRK-001");
                } else {
                    rsid.last();
                    int auto_id = rsid.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int trk = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 3 - trk; j++) {
                        no = "0" + no;
                    }
                    txt_id.setText("TRK-" + no);
                    txt_id.setEnabled(false);
                }
            }
            rsid.close();
            stat.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    protected void Tanggal() {

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        output = ft.format(c.getTime());
        invoicedate.setText(output);
        invoicedate.setEnabled(false);

    }
//    public static final String[] UNIT_KERJA = {
//        "APOTEK", "KONSULTASI", "BEROBAT"
//    };
//
//    public void setUnitKerja() {
//        if (cbTransaksi.getItemCount() > 1) {
//            for (int i = 1; i < cbTransaksi.getItemCount(); i++) {
//                cbTransaksi.removeItemAt(i);
//            }
//        }
//        for (int i = 0; i < UNIT_KERJA.length; i++) {
//            cbTransaksi.addItem(UNIT_KERJA[i]);
//        }
//    }

    protected void datatable() {
        Object[] Baris = {"ID_Pembayaran", "Nama", "Tanggal Bayar", "Keterangan", "Nominal", "Transaksi"};
        tabmode = new DefaultTableModel(null, Baris);
        TblTransaksi.setModel(tabmode);
        String sql = "select * from transaksi";

        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            while (hasil.next()) {
                String id_pem = hasil.getString("ID_Pem");
                String Nama = hasil.getString("Nama");
                String byr = hasil.getString("Tanggal_Pembayaran");
                String ket = hasil.getString("Keterangan");
                String nml = hasil.getString("Nominal");
                String trx = hasil.getString("Transaksi");
                String hsl = "TRX" + id_pem;
                String[] data = {id_pem, Nama, byr, ket, nml, trx};
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
        btnUpdate1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblTransaksi = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Balek = new javax.swing.JToggleButton();
        BtnSimpan = new javax.swing.JButton();
        BtnReset = new javax.swing.JButton();
        invoicedate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Nominal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_ket = new javax.swing.JTextArea();
        cbTransaksi = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        btnUpdate1.setText("Update");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRANSAKSI KLINIK");

        TblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Tanggal Pembayaran", "Keterangan", "Nominal", "Tansaksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblTransaksi);

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

        jLabel7.setText("Tanggal Pembayaran");

        Balek.setText("Kembali");
        Balek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalekActionPerformed(evt);
            }
        });

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

        invoicedate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoicedateActionPerformed(evt);
            }
        });

        jLabel11.setText("ID Pembelian");

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        jLabel5.setText("Keterangan");

        jLabel6.setText("Nominal");

        txt_ket.setColumns(20);
        txt_ket.setRows(5);
        jScrollPane2.setViewportView(txt_ket);

        cbTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apotek", "Konsutltasi", "Berobat" }));

        jLabel2.setText("Transaksi");

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
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnReset)
                                .addGap(46, 46, 46)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Balek)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                            .addComponent(txt_id)
                                            .addComponent(invoicedate))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Nominal)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
                                    .addComponent(cbTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(invoicedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnReset)
                        .addComponent(BtnSimpan))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(Balek))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String sql = "update transaksi set Nama=?, Tanggal_Pembayaran=?, Keterangan=?, Nominal=?, Transaksi =? where ID_Pem = '" + txt_id.getText() + "'";
            arlist.clear();
            PreparedStatement stat = conn.prepareStatement(sql);

            t = new Tree(new TreeNode(""));
            t.root.add_child(new TreeNode("Apotek"), 0);
            t.root.add_child(new TreeNode("Konsutltasi"), 0);
            t.root.add_child(new TreeNode("Berobat"), 0);
            int panjang = t.root.children.size();
            String[] titik = new String[panjang];
            double[] KM = new double[panjang];
            for (int i = 0; i < t.root.children.size(); i++) {
                titik[i] = t.root.children.get(i).data;
                KM[i] = t.root.children.get(i).distance;
            }
            switch (cbTransaksi.getSelectedIndex()) {
                case 0:
                    stat.setString(5, t.root.children.get(0).data);
                    break;
                case 1:
                    stat.setString(5, t.root.children.get(1).data);
                    break;
                case 2:
                    stat.setString(5, t.root.children.get(2).data);
                    break;
            }

            arlist.add(txt_nama.getText());
            arlist.add(invoicedate.getText());
            arlist.add(txt_ket.getText());
            arlist.add(Nominal.getText());
            for (int i = 0; i < arlist.size(); i++) {

                stat.setString(i + 1, arlist.get(i));
            }
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIEDIT");
            kosong();
            txt_id.requestFocus();
            datatable();
            autokode();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATA GAGAL DIEDIT " + e);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "YAKIN MAU HAPUS?", "PESAN KONFIRMASI", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from transaksi where ID_Pem = '" + txt_id.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS");
                kosong();
                txt_id.requestFocus();
                datatable();
                autokode();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "DATA GAGAL DIHAPUS " + e);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void BalekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalekActionPerformed
        this.setVisible(false);
        new MenuPil().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BalekActionPerformed

    private void invoicedateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoicedateActionPerformed

    }//GEN-LAST:event_invoicedateActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String sql = "insert into transaksi values (?,?,?,?,?,?)";
        try {
            arlist.clear();
            PreparedStatement stat = conn.prepareStatement(sql);

            t = new Tree(new TreeNode(""));
            t.root.add_child(new TreeNode("Apotek"), 0);
            t.root.add_child(new TreeNode("Konsutltasi"), 0);
            t.root.add_child(new TreeNode("Berobat"), 0);
            int panjang = t.root.children.size();
            String[] titik = new String[panjang];
            double[] KM = new double[panjang];
            for (int i = 0; i < t.root.children.size(); i++) {
                titik[i] = t.root.children.get(i).data;
                KM[i] = t.root.children.get(i).distance;
            }
            switch (cbTransaksi.getSelectedIndex()) {
                case 0:
                    stat.setString(6, t.root.children.get(0).data);
                    break;
                case 1:
                    stat.setString(6, t.root.children.get(1).data);
                    break;
                case 2:
                    stat.setString(6, t.root.children.get(2).data);
                    break;
            }

            arlist.add(txt_id.getText());
            arlist.add(txt_nama.getText());
            arlist.add(invoicedate.getText());
            arlist.add(txt_ket.getText());
            arlist.add(Nominal.getText());
            for (int i = 0; i < arlist.size(); i++) {

                stat.setString(i + 1, arlist.get(i));

//                stat.setString(i + 1, arlist.get(i));
            }
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN");
            kosong();
            txt_id.requestFocus();
            kosong();
            datatable();
            autokode();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATA GAGAL DISIMPAN" + e);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void TblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblTransaksiMouseClicked
        int bar = TblTransaksi.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();

        txt_id.setText(a);
        txt_nama.setText(b);
        invoicedate.setText(c);
        txt_ket.setText(d);
        Nominal.setText(e);
        cbTransaksi.setSelectedItem(f);
        // TODO add your handling code here:
    }//GEN-LAST:event_TblTransaksiMouseClicked

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
        kosong();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnResetActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class
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
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Balek;
    private javax.swing.JButton BtnReset;
    private javax.swing.JButton BtnSimpan;
    private javax.swing.JTextField Nominal;
    private javax.swing.JTable TblTransaksi;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JComboBox<String> cbTransaksi;
    private javax.swing.JTextField invoicedate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.ButtonGroup jk;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextArea txt_ket;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
