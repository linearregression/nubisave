/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nubisave.ui.backendconfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nubisave.StorageService;
import nubisave.ui.ServiceParameterDialog;
import org.ini4j.Ini;

/**
 * Subclass of ServiceParameterDialog, which is used as a configuration dialogue for the directory.ini. 
 * Subclasses in this package will be instantiated by reflection instead of the generic ServiceParameterDialog,
 * so that custom configuration dialogues can be added in a modular fashion.
 */
public class Directory extends JDialog {
    private StorageService service;
    private Ini config;

    /**
     * Creates new form Directory
     */
    public Directory(java.awt.Frame parent, boolean modal,StorageService service) {
        super(parent, modal);
        this.service = service;
        this.config = service.getConfig();
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        directoryChooserBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        storageDirectoryTF = new javax.swing.JTextField();
        cancelBtn = new javax.swing.JButton();
        applyBtn = new javax.swing.JButton();

        directoryChooserBtn.setText("Browse");
        directoryChooserBtn.setName("directoryChooserBtn"); // NOI18N
        directoryChooserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directoryChooserBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Local Storage Directory:");

        storageDirectoryTF.setEditable(false);
        storageDirectoryTF.setText("local directory");
        storageDirectoryTF.setDoubleBuffered(true);
        storageDirectoryTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storageDirectoryTFActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        applyBtn.setText("Apply");
        applyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(applyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storageDirectoryTF, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(directoryChooserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directoryChooserBtn)
                    .addComponent(storageDirectoryTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(applyBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void directoryChooserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directoryChooserBtnActionPerformed
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryChooser.showOpenDialog(null);
        File directory = directoryChooser.getSelectedFile();
        if( directory != null) {
            storageDirectoryTF.setText(directory.getAbsolutePath());
        }
    }//GEN-LAST:event_directoryChooserBtnActionPerformed

    private void applyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyBtnActionPerformed
        config.get("parameter").put("directory", storageDirectoryTF.getText());
        nubisave.Nubisave.services.update(service);
        dispose();
    }//GEN-LAST:event_applyBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void storageDirectoryTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageDirectoryTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_storageDirectoryTFActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton directoryChooserBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField storageDirectoryTF;
    // End of variables declaration//GEN-END:variables
}
