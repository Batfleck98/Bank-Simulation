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
public class SimulationData {

    double[] StartingRange = {0, 0, 0, 0, 0, 0};
    double[] EndingRange = {0, 0, 0, 0, 0, 0};
    int[] demand = {0, 1, 2, 3, 4};
    int[] leadTime = {1, 2, 3};

    float[] demandProbablities = {0.05f, 0.28f, 0.37f, 0.20f, 0.10f};
    float[] demandComProbablities = {0f, 0f, 0f, 0f, 0f};

    float[] leadTimeProbablities = {0.55f, 0.35f, 0.1f};
    float[] leadComProb = {0f, 0f, 0f};
}
