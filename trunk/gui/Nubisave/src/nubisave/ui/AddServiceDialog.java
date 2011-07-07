/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddServiceDialog.java
 *
 * Created on Jul 1, 2011, 1:44:20 AM
 */
package nubisave.ui;

import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import nubisave.*;
import nubisave.request.Searcher;

/**
 *
 * @author demo
 */
public class AddServiceDialog extends javax.swing.JDialog {

    private Searcher searcher;
    private List<MatchmakerService> services;

    /** Creates new form AddServiceDialog */
    public AddServiceDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        searcher = new Searcher();
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        searchResultTable = new javax.swing.JTable();
        addBtn = new javax.swing.JToggleButton();
        cancelBtn = new javax.swing.JToggleButton();
        pricePerDataCheckBox = new javax.swing.JCheckBox();
        pricePerMonthCheckBox = new javax.swing.JCheckBox();
        availabilityCheckBox = new javax.swing.JCheckBox();
        bandwidthCheckBox = new javax.swing.JCheckBox();
        maxDownTimeCheckBox = new javax.swing.JCheckBox();
        responseTimeCheckBox = new javax.swing.JCheckBox();
        pricePerDataTextField = new javax.swing.JFormattedTextField();
        pricePerMonthTextField = new javax.swing.JFormattedTextField();
        availabilityTextField = new javax.swing.JFormattedTextField();
        bandwidthTextField = new javax.swing.JFormattedTextField();
        maxDownTimeTextField = new javax.swing.JFormattedTextField();
        responseTimeTextField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bandwidthUnitCB = new javax.swing.JComboBox();
        maxDownTimeUnitCB = new javax.swing.JComboBox();
        responseTimeUnitCB = new javax.swing.JComboBox();
        bandwidthWeightCB = new javax.swing.JComboBox();
        maxDownTimeWeightCB = new javax.swing.JComboBox();
        responseTimeWeightCB = new javax.swing.JComboBox();
        availabilityWeightCB = new javax.swing.JComboBox();
        pricePerMonthWeightCB = new javax.swing.JComboBox();
        pricePerDataWeightCB = new javax.swing.JComboBox();
        searchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        searchResultTable.setAutoCreateRowSorter(true);
        searchResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "P/D", "P/M", "A", "BW", "DT", "RT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        searchResultTable.setRowHeight(25);
        searchResultTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(searchResultTable);

        addBtn.setText("Add selected Services");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        pricePerDataCheckBox.setText("Price per Data");

        pricePerMonthCheckBox.setText("Price per Month");
        pricePerMonthCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricePerMonthCheckBoxActionPerformed(evt);
            }
        });

        availabilityCheckBox.setText("Availability");

        bandwidthCheckBox.setText("Bandwidth");

        maxDownTimeCheckBox.setText("max. Down Time");

        responseTimeCheckBox.setText("Response Time");

        pricePerDataTextField.setText("0.00");

        pricePerMonthTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        pricePerMonthTextField.setText("0.00");

        availabilityTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        availabilityTextField.setText("0.00");

        bandwidthTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        bandwidthTextField.setText("0.00");

        maxDownTimeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        maxDownTimeTextField.setText("0.00");

        responseTimeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        responseTimeTextField.setText("0.00");

        jLabel1.setText("Euro");

        jLabel2.setText("Euro");

        jLabel3.setText("%");

        bandwidthUnitCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mbps", "Kbps" }));

        maxDownTimeUnitCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Minute", "Second", "MilliSecond" }));

        responseTimeUnitCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Minute", "Second", "MilliSecond" }));

        bandwidthWeightCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "Medium", "High" }));
        bandwidthWeightCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bandwidthWeightCBActionPerformed(evt);
            }
        });

        maxDownTimeWeightCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "Medium", "High" }));
        maxDownTimeWeightCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxDownTimeWeightCBActionPerformed(evt);
            }
        });

        responseTimeWeightCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "Medium", "High" }));
        responseTimeWeightCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                responseTimeWeightCBActionPerformed(evt);
            }
        });

        availabilityWeightCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "Medium", "High" }));
        availabilityWeightCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availabilityWeightCBActionPerformed(evt);
            }
        });

        pricePerMonthWeightCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "Medium", "High" }));
        pricePerMonthWeightCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricePerMonthWeightCBActionPerformed(evt);
            }
        });

        pricePerDataWeightCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "Medium", "High" }));
        pricePerDataWeightCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricePerDataWeightCBActionPerformed(evt);
            }
        });

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(maxDownTimeCheckBox)
                                            .addComponent(bandwidthCheckBox)
                                            .addComponent(availabilityCheckBox))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(availabilityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                            .addComponent(responseTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                            .addComponent(maxDownTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                            .addComponent(bandwidthTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                            .addComponent(pricePerMonthTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                            .addComponent(pricePerDataTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                                    .addComponent(responseTimeCheckBox))
                                .addGap(6, 6, 6))
                            .addComponent(pricePerDataCheckBox)
                            .addComponent(pricePerMonthCheckBox))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(responseTimeUnitCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bandwidthUnitCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(maxDownTimeUnitCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel1))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pricePerDataWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pricePerMonthWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(availabilityWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(maxDownTimeWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bandwidthWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(responseTimeWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBtn))
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pricePerDataCheckBox)
                            .addComponent(pricePerDataWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(pricePerDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pricePerMonthCheckBox)
                                .addComponent(pricePerMonthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)))
                    .addComponent(pricePerMonthWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(availabilityCheckBox)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(availabilityWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(availabilityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bandwidthCheckBox)
                    .addComponent(bandwidthUnitCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bandwidthWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bandwidthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxDownTimeCheckBox)
                    .addComponent(maxDownTimeWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxDownTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxDownTimeUnitCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responseTimeCheckBox)
                    .addComponent(responseTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responseTimeWeightCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responseTimeUnitCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(cancelBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showServices() {
        DefaultTableModel tm = (DefaultTableModel) searchResultTable.getModel();
        for (int i = tm.getRowCount() - 1; i >= 0; i--) {
            tm.removeRow(i);
        }
        tm.fireTableDataChanged();

        if (services == null) {
            return;
        }
        
        for (MatchmakerService s : services) {
            Object[] row = {
                s.getName(),
                s.isSatPricePerData(),
                s.isSatPricePerMonth(),
                s.isSatAvailability(),
                s.isSatNetworkBandwith(),
                s.isSatMaxDownTime(),
                s.isSatResponseTime()};
            tm.addRow(row);
        }
        tm.fireTableDataChanged();
    }

    public void setServices(List<MatchmakerService> services) {
        this.services = services;
        showServices();
    }

    public JCheckBox getAvailabilityCheckBox() {
        return availabilityCheckBox;
    }

    public JFormattedTextField getAvailabilityTextField() {
        return availabilityTextField;
    }

    public JComboBox getAvailabilityWeightCB() {
        return availabilityWeightCB;
    }

    public JCheckBox getBandwidthCheckBox() {
        return bandwidthCheckBox;
    }

    public JFormattedTextField getBandwidthTextField() {
        return bandwidthTextField;
    }

    public JComboBox getBandwidthUnitCB() {
        return bandwidthUnitCB;
    }

    public JComboBox getBandwidthWeightCB() {
        return bandwidthWeightCB;
    }

    public JCheckBox getMaxDownTimeCheckBox() {
        return maxDownTimeCheckBox;
    }

    public JFormattedTextField getMaxDownTimeTextField() {
        return maxDownTimeTextField;
    }

    public JComboBox getMaxDownTimeUnitCB() {
        return maxDownTimeUnitCB;
    }

    public JComboBox getMaxDownTimeWeightCB() {
        return maxDownTimeWeightCB;
    }

    public JCheckBox getPricePerDataCheckBox() {
        return pricePerDataCheckBox;
    }

    public JFormattedTextField getPricePerDataTextField() {
        return pricePerDataTextField;
    }

    public JComboBox getPricePerDataWeightCB() {
        return pricePerDataWeightCB;
    }

    public JCheckBox getPricePerMonthCheckBox() {
        return pricePerMonthCheckBox;
    }

    public JFormattedTextField getPricePerMonthTextField() {
        return pricePerMonthTextField;
    }

    public JComboBox getPricePerMonthWeightCB() {
        return pricePerMonthWeightCB;
    }

    public JCheckBox getResponseTimeCheckBox() {
        return responseTimeCheckBox;
    }

    public JFormattedTextField getResponseTimeTextField() {
        return responseTimeTextField;
    }

    public JComboBox getResponseTimeUnitCB() {
        return responseTimeUnitCB;
    }

    public JComboBox getResponseTimeWeightCB() {
        return responseTimeWeightCB;
    }

    
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        for (int i : searchResultTable.getSelectedRows()) {
            MatchmakerService newService = new MatchmakerService((String) searchResultTable.getModel().getValueAt(i, 0));
            Nubisave.services.getMmServices().add(newService);
        }
        ((MainWindow) getParent()).tableModel.fireTableDataChanged();
        dispose();
    }//GEN-LAST:event_addBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void bandwidthWeightCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bandwidthWeightCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bandwidthWeightCBActionPerformed

    private void maxDownTimeWeightCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxDownTimeWeightCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxDownTimeWeightCBActionPerformed

    private void responseTimeWeightCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_responseTimeWeightCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_responseTimeWeightCBActionPerformed

    private void availabilityWeightCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_availabilityWeightCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_availabilityWeightCBActionPerformed

    private void pricePerMonthWeightCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricePerMonthWeightCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pricePerMonthWeightCBActionPerformed

    private void pricePerDataWeightCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricePerDataWeightCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pricePerDataWeightCBActionPerformed

    private void pricePerMonthCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricePerMonthCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pricePerMonthCheckBoxActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        searcher.find(this);
    }//GEN-LAST:event_searchBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addBtn;
    private javax.swing.JCheckBox availabilityCheckBox;
    private javax.swing.JFormattedTextField availabilityTextField;
    private javax.swing.JComboBox availabilityWeightCB;
    private javax.swing.JCheckBox bandwidthCheckBox;
    private javax.swing.JFormattedTextField bandwidthTextField;
    private javax.swing.JComboBox bandwidthUnitCB;
    private javax.swing.JComboBox bandwidthWeightCB;
    private javax.swing.JToggleButton cancelBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox maxDownTimeCheckBox;
    private javax.swing.JFormattedTextField maxDownTimeTextField;
    private javax.swing.JComboBox maxDownTimeUnitCB;
    private javax.swing.JComboBox maxDownTimeWeightCB;
    private javax.swing.JCheckBox pricePerDataCheckBox;
    private javax.swing.JFormattedTextField pricePerDataTextField;
    private javax.swing.JComboBox pricePerDataWeightCB;
    private javax.swing.JCheckBox pricePerMonthCheckBox;
    private javax.swing.JFormattedTextField pricePerMonthTextField;
    private javax.swing.JComboBox pricePerMonthWeightCB;
    private javax.swing.JCheckBox responseTimeCheckBox;
    private javax.swing.JFormattedTextField responseTimeTextField;
    private javax.swing.JComboBox responseTimeUnitCB;
    private javax.swing.JComboBox responseTimeWeightCB;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable searchResultTable;
    // End of variables declaration//GEN-END:variables
}
