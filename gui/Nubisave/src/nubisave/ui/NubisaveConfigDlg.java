/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nubisave.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import nubisave.*;
import nubisave.component.graph.splitteradaption.NubisaveEditor;
import org.apache.commons.lang.NumberUtils;

/**
 *
 * @author alok
 */
public class NubisaveConfigDlg extends javax.swing.JDialog {
    
    private static final String MORE_INFO_PNG = "/nubisave/images/LeftArrow2.png";
    private static final String LESS_INFO_PNG = "/nubisave/images/RightArrow2.png";

    /**
     * Creates new form NubisaveConfigDlg
     */
    public NubisaveConfigDlg() {
        initComponents();
        mntDirTxtField.setText(Nubisave.mainSplitter.getMountpoint());
        refreshSplitterParameters();
        splitterIsMountedCheckBox.setSelected(Nubisave.mainSplitter.isMounted());
        setInstantTooltipForCodecInfoInvisibleOverlayLabel();
        codecInfoInvisibleOverlayPanel.setVisible(true);
        SwingUtilities.invokeLater(new Runnable() { //invoke when GUI is constructed
            public void run() { 
                showLessInfo();
                pack();
            }
        });
    }

    /**
     * Make left side of the splitpane (the information area) invisible, and change toggle button image.
     */
    private void showLessInfo() {
        jSplitPane1.setDividerLocation(0.0); // splitpanel is now invisible!
        ImageIcon moreInfoIcon = new javax.swing.ImageIcon(getClass().getResource(LESS_INFO_PNG ));
        Image img = moreInfoIcon.getImage() ;  
        Image scaledImg = img.getScaledInstance( jToggleButton1.getWidth(), jToggleButton1.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon scaledMoreInfoIcon = new ImageIcon( scaledImg );
        jToggleButton1.setIcon(scaledMoreInfoIcon); 
        jSplitPane1.setPreferredSize(new Dimension(900, 543));
        setPreferredSize(new Dimension(900, 543));
        pack();
    }
    
    /**
     * Make left side of the splitpane (the information area) visible, and change toggle button image.
     */
    private void showMoreInfo() {
        jSplitPane1.setDividerLocation(0.17); // splitpanel is now invisible!
        ImageIcon moreInfoIcon = new javax.swing.ImageIcon(getClass().getResource(MORE_INFO_PNG));
        Image img = moreInfoIcon.getImage() ;  
        Image scaledImg = img.getScaledInstance( jToggleButton1.getWidth(), jToggleButton1.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon scaledMoreInfoIcon = new ImageIcon( scaledImg );
        jToggleButton1.setIcon(scaledMoreInfoIcon);
        jSplitPane1.setPreferredSize(new Dimension(1050, 543));
        setPreferredSize(new Dimension(1050, 543));
        pack();
    }
    
    /**
     * This should instantly show a tooltip with the latest codec information,
     * when hovering over the codecButton that is overlayed with the label
     * codecInfoInvisibleOverlayLabel, to prevent actual user interaction with
     * the button.
     */
    private void setInstantTooltipForCodecInfoInvisibleOverlayLabel() {
        codecInfoInvisibleOverlayLabel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                try {
                    KeyEvent ke = new KeyEvent(e.getComponent(), KeyEvent.KEY_PRESSED,
                            System.currentTimeMillis(), InputEvent.CTRL_MASK,
                            KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);
                    e.getComponent().dispatchEvent(ke);
                } catch (Throwable e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * Refreshes parameters from the Nubisave Splitter component for display.
     */
    private void refreshSplitterParameters() {
        availabilityLabel.setText("Availability: " + Nubisave.mainSplitter.getAvailability() * 100 + "%");
        availabilityPerYearLabel.setText("<html>Unavailability per year: <br>" + Nubisave.mainSplitter.getUnavailabilityPerYear() + "</html>");
        redundancyFactorLabel.setText("Redundancy factor: " + Nubisave.mainSplitter.getRedundancyFactor());
        codecInfoInvisibleOverlayLabel.setToolTipText(Nubisave.mainSplitter.getCodecInfo());
    }

    private void setIsSplitterMounted() {
        if (Nubisave.mainSplitter.isMounted()) {
            splitterIsMountedCheckBox.setText("Unmount Splitter");
            splitterIsMountedCheckBox.setSelected(true);
        } else {
            splitterIsMountedCheckBox.setText("Mount Splitter");
            splitterIsMountedCheckBox.setSelected(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mntDirTxtField = new javax.swing.JTextField();
        openMntDirBtn = new javax.swing.JButton();
        codecInfoButtonLayeredPane = new javax.swing.JLayeredPane();
        codecInfoInvisibleOverlayPanel = new javax.swing.JPanel();
        codecInfoInvisibleOverlayLabel = new javax.swing.JLabel();
        codecinfoButtonPanel = new javax.swing.JPanel();
        codecInfoButton = new javax.swing.JButton();
        desiredAvailabilityLabel = new javax.swing.JLabel();
        desiredAvailabilityOkButton = new javax.swing.JButton();
        desiredAvailabilityInfoLabel = new javax.swing.JLabel();
        redundancyFactorLabel = new javax.swing.JLabel();
        desiredAvailabilityTextField = new javax.swing.JTextField();
        availabilityPerYearLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        storageStrategyComboBox = new javax.swing.JComboBox();
        availabilityLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        splitterSessionComboBox = new javax.swing.JComboBox();
        loadSessionButton = new javax.swing.JButton();
        saveSessionButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        splitterIsMountedCheckBox = new javax.swing.JCheckBox();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jToggleButton1 = new javax.swing.JToggleButton();

        setMaximumSize(new java.awt.Dimension(1050, 543));
        setMinimumSize(new java.awt.Dimension(900, 543));
        setPreferredSize(new java.awt.Dimension(900, 543));

        jSplitPane1.setDividerSize(0);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(900, 815));

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 543));

        jLabel2.setText("Mount directory");

        mntDirTxtField.setText("jTextField1");

        openMntDirBtn.setText("Open");
        openMntDirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMntDirBtnActionPerformed(evt);
            }
        });

        codecInfoInvisibleOverlayPanel.setAlignmentX(0.0F);
        codecInfoInvisibleOverlayPanel.setAlignmentY(0.0F);
        codecInfoInvisibleOverlayPanel.setOpaque(false);

        codecInfoInvisibleOverlayLabel.setAlignmentY(0.0F);
        codecInfoInvisibleOverlayLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout codecInfoInvisibleOverlayPanelLayout = new javax.swing.GroupLayout(codecInfoInvisibleOverlayPanel);
        codecInfoInvisibleOverlayPanel.setLayout(codecInfoInvisibleOverlayPanelLayout);
        codecInfoInvisibleOverlayPanelLayout.setHorizontalGroup(
            codecInfoInvisibleOverlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codecInfoInvisibleOverlayPanelLayout.createSequentialGroup()
                .addComponent(codecInfoInvisibleOverlayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        codecInfoInvisibleOverlayPanelLayout.setVerticalGroup(
            codecInfoInvisibleOverlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(codecInfoInvisibleOverlayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        );

        codecinfoButtonPanel.setAlignmentX(0.0F);
        codecinfoButtonPanel.setAlignmentY(0.0F);
        codecinfoButtonPanel.setAutoscrolls(true);
        codecinfoButtonPanel.setOpaque(false);

        codecInfoButton.setText("Codec Information");
        codecInfoButton.setAlignmentY(0.0F);
        codecInfoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        codecInfoButton.setFocusPainted(false);
        codecInfoButton.setRolloverEnabled(false);

        javax.swing.GroupLayout codecinfoButtonPanelLayout = new javax.swing.GroupLayout(codecinfoButtonPanel);
        codecinfoButtonPanel.setLayout(codecinfoButtonPanelLayout);
        codecinfoButtonPanelLayout.setHorizontalGroup(
            codecinfoButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codecinfoButtonPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(codecInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        codecinfoButtonPanelLayout.setVerticalGroup(
            codecinfoButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codecinfoButtonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(codecInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout codecInfoButtonLayeredPaneLayout = new javax.swing.GroupLayout(codecInfoButtonLayeredPane);
        codecInfoButtonLayeredPane.setLayout(codecInfoButtonLayeredPaneLayout);
        codecInfoButtonLayeredPaneLayout.setHorizontalGroup(
            codecInfoButtonLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codecInfoButtonLayeredPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(codecinfoButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(codecInfoButtonLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(codecInfoButtonLayeredPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(codecInfoInvisibleOverlayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE)))
        );
        codecInfoButtonLayeredPaneLayout.setVerticalGroup(
            codecInfoButtonLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codecInfoButtonLayeredPaneLayout.createSequentialGroup()
                .addComponent(codecinfoButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(codecInfoButtonLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(codecInfoButtonLayeredPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(codecInfoInvisibleOverlayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        codecInfoButtonLayeredPane.setLayer(codecInfoInvisibleOverlayPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        codecInfoButtonLayeredPane.setLayer(codecinfoButtonPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        desiredAvailabilityLabel.setText("Desired Availability:");

        desiredAvailabilityOkButton.setText("OK");
        desiredAvailabilityOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desiredAvailabilityOkButtonActionPerformed(evt);
            }
        });

        desiredAvailabilityInfoLabel.setText("Press OK to check");

        redundancyFactorLabel.setText("Redundancy factor:");

        desiredAvailabilityTextField.setText("90%");

        availabilityPerYearLabel.setText("Unavailability per year:");

        jLabel5.setText("Storage strategy");

        storageStrategyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UseAllInParallel", "RoundRobin" }));
        storageStrategyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storageStrategyComboBoxActionPerformed(evt);
            }
        });

        availabilityLabel.setText("Availability");

        jLabel1.setText("Choose session");

        splitterSessionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", " " }));
        splitterSessionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitterSessionComboBoxActionPerformed(evt);
            }
        });

        loadSessionButton.setText("Load Session");
        loadSessionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadSessionButtonActionPerformed(evt);
            }
        });

        saveSessionButton.setText("Save Session");
        saveSessionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSessionButtonActionPerformed(evt);
            }
        });

        splitterIsMountedCheckBox.setText("Splitter mount status");
        splitterIsMountedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitterIsMountedCheckBoxActionPerformed(evt);
            }
        });

        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(splitterSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(loadSessionButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saveSessionButton))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(availabilityLabel)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(51, 51, 51)
                                        .addComponent(mntDirTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(openMntDirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(desiredAvailabilityInfoLabel)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(desiredAvailabilityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(desiredAvailabilityOkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(availabilityPerYearLabel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(storageStrategyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(codecInfoButtonLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(desiredAvailabilityLabel)
                                        .addGap(75, 75, 75)
                                        .addComponent(redundancyFactorLabel))
                                    .addComponent(splitterIsMountedCheckBox)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(splitterIsMountedCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(mntDirTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openMntDirBtn))
                .addGap(24, 24, 24)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(codecInfoButtonLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(desiredAvailabilityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(desiredAvailabilityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(desiredAvailabilityOkButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(desiredAvailabilityInfoLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(redundancyFactorLabel)
                        .addGap(34, 34, 34)
                        .addComponent(availabilityPerYearLabel)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storageStrategyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(availabilityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(splitterSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadSessionButton)
                    .addComponent(saveSessionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel1);

        jLabel3.setText("Information Area");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(646, 646, 646))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel2);

        jToggleButton1.setToolTipText("More Information");
        jToggleButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton1StateChanged(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1008, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void splitterIsMountedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitterIsMountedCheckBoxActionPerformed
        // TODO add your handling code here:
        if (!splitterIsMountedCheckBox.isSelected()) { //the selected state is toggled before entering this method
            Nubisave.mainSplitter.unmount();
        } else {
            Nubisave.mainSplitter.mount();
        }
        setIsSplitterMounted();
        refreshSplitterParameters();
    }//GEN-LAST:event_splitterIsMountedCheckBoxActionPerformed

    private void saveSessionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSessionButtonActionPerformed
        // TODO add your handling code here:
        int sessionNumber = Integer.parseInt((String) splitterSessionComboBox.getSelectedItem());
        Nubisave.mainSplitter.storeSession(sessionNumber);
    }//GEN-LAST:event_saveSessionButtonActionPerformed

    private void loadSessionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadSessionButtonActionPerformed
        // TODO add your handling code here:
        int sessionNumber = Integer.parseInt((String) splitterSessionComboBox.getSelectedItem());
        Nubisave.mainSplitter.loadSession(sessionNumber);
        tableModel.fireTableDataChanged();
        storageStrategyComboBox.setSelectedItem(Nubisave.mainSplitter.getStorageStrategy());
    }//GEN-LAST:event_loadSessionButtonActionPerformed

    private void splitterSessionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitterSessionComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_splitterSessionComboBoxActionPerformed

    private void storageStrategyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageStrategyComboBoxActionPerformed
        // TODO add your handling code here:
        JComboBox cb = (JComboBox) evt.getSource();
        String storageStrategy = (String) cb.getSelectedItem();
        Nubisave.mainSplitter.setStorageStrategy(storageStrategy);
        refreshSplitterParameters();
    }//GEN-LAST:event_storageStrategyComboBoxActionPerformed
    /**
     * Configure Nubisave's splitter component so that the overall availability
     * is greater than or equal to the desired availability expressed in the
     * desiredAvailabilityTextField.
     *
     * @param evt
     */
    private void desiredAvailabilityOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desiredAvailabilityOkButtonActionPerformed
        String desiredAv, textField;
        Double desiredAvDouble;
        textField = desiredAvailabilityTextField.getText();
        desiredAv = textField.replace("%", "");
        if (NumberUtils.isNumber(desiredAv)) {
            desiredAvDouble = Double.parseDouble(desiredAv) / 100.0;
        } else {
            desiredAvailabilityInfoLabel.setText("Your input is not a number.");
            return;
        }
        desiredAvailabilityInfoLabel.setText("Searching for optimal configuration.");
        ((AutonomousSplitter) Nubisave.mainSplitter).findConfigurationForAvailability(desiredAvDouble);
        refreshSplitterParameters();
        desiredAvailabilityInfoLabel.setText("Found optimal configuration.");
    }//GEN-LAST:event_desiredAvailabilityOkButtonActionPerformed

    private void openMntDirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMntDirBtnActionPerformed
        // TODO add your handling code here:
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(new File(Nubisave.mainSplitter.getDataDir()));
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);

                // Fallback when desktop handlers are not available
                try {
                    Runtime.getRuntime().exec(new String[]{"xdg-open", Nubisave.mainSplitter.getDataDir()});
                } catch (IOException ex2) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        }
    }//GEN-LAST:event_openMntDirBtnActionPerformed

    private void jToggleButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButton1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1StateChanged

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if(jToggleButton1.isSelected()){
            showMoreInfo();
        } else {
            showLessInfo();
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    public NubiTableModel tableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availabilityLabel;
    private javax.swing.JLabel availabilityPerYearLabel;
    private javax.swing.JButton codecInfoButton;
    private javax.swing.JLayeredPane codecInfoButtonLayeredPane;
    private javax.swing.JLabel codecInfoInvisibleOverlayLabel;
    private javax.swing.JPanel codecInfoInvisibleOverlayPanel;
    private javax.swing.JPanel codecinfoButtonPanel;
    private javax.swing.JLabel desiredAvailabilityInfoLabel;
    private javax.swing.JLabel desiredAvailabilityLabel;
    private javax.swing.JButton desiredAvailabilityOkButton;
    private javax.swing.JTextField desiredAvailabilityTextField;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton loadSessionButton;
    private javax.swing.JTextField mntDirTxtField;
    private javax.swing.JButton openMntDirBtn;
    private javax.swing.JLabel redundancyFactorLabel;
    private javax.swing.JButton saveSessionButton;
    private javax.swing.JCheckBox splitterIsMountedCheckBox;
    private javax.swing.JComboBox splitterSessionComboBox;
    private javax.swing.JComboBox storageStrategyComboBox;
    // End of variables declaration//GEN-END:variables
}
