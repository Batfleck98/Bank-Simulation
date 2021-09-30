
package InventorySystem;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class SimulationTable extends javax.swing.JFrame {

    /**
     * Creates new form SimulationTable
     */
      ArrayList<Day> day;
    
    
   String[][] TwoDimensionalDayArray;
    public SimulationTable(ArrayList<Day> da) {
        initComponents();
        
          this.day = da;
        TwoDimensionalDayArray = new String[da.size()][7];
        for (int i = 0; i < da.size(); i++) {

            TwoDimensionalDayArray[i][0] = String.valueOf(i+1);
            TwoDimensionalDayArray[i][1] = String.valueOf(da.get(i).inventoryCars);
            TwoDimensionalDayArray[i][2] = String.valueOf(da.get(i).showroomCars);
            TwoDimensionalDayArray[i][3] = String.valueOf(da.get(i).demand);
            TwoDimensionalDayArray[i][4] = String.valueOf(da.get(i).shortage);
            TwoDimensionalDayArray[i][5] = String.valueOf(da.get(i).pendingOrderDay);
            TwoDimensionalDayArray[i][6] = String.valueOf(da.get(i).pendingOrderCars);


        }

        jTable1.setModel(new DefaultTableModel(TwoDimensionalDayArray, new String[]{"Day","inventoryCars","showroomCars","demand","shortage","pendingOrderDay","pendingOrderCars"}));
      
    
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
