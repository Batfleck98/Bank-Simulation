/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventorySystem;

/**
 *
 * @author PC
 */
public class Day {
    int demand, shortage, inventoryCars, showroomCars, pendingOrderDay,pendingOrderCars;

    public Day(int demand, int shortage, int inventoryCars, int showroomCars, int pendingOrderDay, int pendingOrderCars) {
        this.demand = demand;
        this.shortage = shortage;
        this.inventoryCars = inventoryCars;
        this.showroomCars = showroomCars;
        this.pendingOrderDay = pendingOrderDay;
        this.pendingOrderCars = pendingOrderCars;
    }

    public Day() {
    }
       @Override
    public String toString() {
        return "Day{" + "demand=" + demand + ", shortage=" + shortage + ", inventoryCars=" + inventoryCars + ", showroomCars=" + showroomCars + ", pendingOrderDay=" + pendingOrderDay + ", pendingOrderCars=" + pendingOrderCars + '}';
    }
    
}
