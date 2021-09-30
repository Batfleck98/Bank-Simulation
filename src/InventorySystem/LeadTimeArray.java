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
public class LeadTimeArray {

    ArrayList<LeadTimeArray> Ldarray = new ArrayList<>();
    int leadTime;
    float NorProb;
    float NorComProb;
    double StartingRange, EndingRange;

    public LeadTimeArray(int ServiceTime, float NorProb, float NorComProb, double StartingRange, double EndingRange) {
        this.leadTime = ServiceTime;
        this.NorProb = NorProb;
        this.NorComProb = NorComProb;
        this.StartingRange = StartingRange;
        this.EndingRange = EndingRange;
    }

    LeadTimeArray() {

    }

}
