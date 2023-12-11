/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package reproductor.propio;

import jaco.mp3.player.MP3Player;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Reproductor extends javax.swing.JInternalFrame {

    public String tipo;
    public String nombre;

    public Reproductor() {
        initComponents();

        // Establece el modelo de lista en la lista
        DefaultListModel<String> model = new DefaultListModel<>();
        lista.setModel(model);

    }

    MP3Player mp3player = new MP3Player();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        stio = new javax.swing.JLabel();
        panel = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();
        btnAgregar = new javax.swing.JButton();
        play = new javax.swing.JLabel();
        FONDO = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("Spotify");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/propio/rsz_116427.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, -1, -1));

        stio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/propio/rsz_stop.png"))); // NOI18N
        stio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stioMouseClicked(evt);
            }
        });
        jPanel2.add(stio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, 80, 70));

        lista.setBackground(new java.awt.Color(0, 0, 0));
        lista.setForeground(new java.awt.Color(255, 255, 255));
        panel.setViewportView(lista);

        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 480, 300));

        btnAgregar.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/propio/agregar.png"))); // NOI18N
        btnAgregar.setText("jButton1");
        btnAgregar.setBorder(null);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 70, 80));

        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/propio/play.png"))); // NOI18N
        play.setMinimumSize(new java.awt.Dimension(3, 16));
        play.setName(""); // NOI18N
        play.setPreferredSize(new java.awt.Dimension(30, 16));
        play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playMouseClicked(evt);
            }
        });
        jPanel2.add(play, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, 70, 70));

        FONDO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/propio/output-onlinegiftools(1).gif"))); // NOI18N
        jPanel2.add(FONDO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 500));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
private int currentIndex = -1;

    private void playMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playMouseClicked
        int selectedIndex = lista.getSelectedIndex();
        if (selectedIndex != -1) {
            if (selectedIndex < currentIndex) {
                mp3player.skipBackward();
            } else {
                mp3player.skipForward();
            }
            mp3player.play();
            currentIndex = selectedIndex;
        }
    }//GEN-LAST:event_playMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // Crea un objeto JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        if (tipo.equals("Administrador")) {
            fileChooser.setCurrentDirectory(new File("Z"));
            
        } else {
            fileChooser.setCurrentDirectory(new File("Z/" + nombre));
        }
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setFileHidingEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos MP3", "mp3");
        fileChooser.setFileFilter(filter);

        // Muestra el diálogo de selección de archivos y espera a que el usuario seleccione uno o varios archivos
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);

        // Si el usuario seleccionó un archivo, obtiene su ruta y lo carga en el reproductor
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                mp3player.addToPlayList(file);

                // Agrega el nombre del archivo a la lista
                DefaultListModel<String> model = (DefaultListModel<String>) lista.getModel();
                model.addElement(file.getName());
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void stioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stioMouseClicked
        mp3player.stop();
    }//GEN-LAST:event_stioMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        mp3player.pause();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        mp3player.stop();
    }//GEN-LAST:event_formInternalFrameClosed

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
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reproductor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FONDO;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JList<String> lista;
    private javax.swing.JScrollPane panel;
    private javax.swing.JLabel play;
    private javax.swing.JLabel stio;
    // End of variables declaration//GEN-END:variables
}
