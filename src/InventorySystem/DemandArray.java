/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventorySystem;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class DemandArray {

    ArrayList<DemandArray> Darray = new ArrayList<>();

    int demand;
    double Probablities;
    double ComProbablities;
    double StartingRange, EndingRange;

    public DemandArray(int InterArrival, double Probablities, double ComProbablities, double StartingRange, double EndingRange) {
        this.demand = InterArrival;
        this.Probablities = Probablities;
        this.ComProbablities = ComProbablities;
        this.StartingRange = StartingRange;
        this.EndingRange = EndingRange;
    }

    public DemandArray() {
    }
    
}
