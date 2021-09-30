package InventorySystem;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(int NumberOfDays, SimulationGUI s) {

        Random r = new Random();
        Storage inventory = new Storage(2, 8);
        Storage showroom = new Storage(4, 4);

        Shipment currentOrder = new Shipment(3, 5);

        SimulationData MS = new SimulationData();
        DemandArray dem = new DemandArray();
        LeadTimeArray ld = new LeadTimeArray();

        ArrayList<Day> days = new ArrayList<>();

        dem.Darray.add(DemandCalculator(dem, MS));
        ld.Ldarray.add(LeadTimeCalculator(ld, MS));

        for (int i = 0; i < NumberOfDays; i++) {

            Day d = new Day();

            d.inventoryCars = inventory.carsInStock;
            d.showroomCars = showroom.carsInStock;

            int randomVariable = r.nextInt(100) + 1;
            int demand = demandSetter(randomVariable, dem);

            d.demand = demand;

            if ((i + 1) == currentOrder.deliverDay) {
                int cars = currentOrder.cars;
                while (cars > 0) {
                    if (showroom.carsInStock < showroom.maximum) {
                        showroom.carsInStock++;
                    } else {
                        inventory.carsInStock++;
                    }
                    cars--;
                }
                currentOrder.cars = 0;
                currentOrder.deliverDay = 0;
            }

            if (demand <= inventory.carsInStock) {
                inventory.carsInStock -= demand;
            } else {
                int tempDemand = demand;
                int shortage = 0;
                while (tempDemand > 0) {
                    if (inventory.carsInStock > 0) {
                        inventory.carsInStock--;
                    } else if (showroom.carsInStock > 0) {
                        showroom.carsInStock--;
                    } else {
                        shortage++;
                    }
                    tempDemand--;
                }
                d.shortage = shortage;
            }
            if ( (i+1) % 2 != 0 && inventory.carsInStock <= 1) {
                int leadTime = leadTimeSetter(randomVariable, ld);

                currentOrder.cars = (inventory.maximum - inventory.carsInStock) + (showroom.maximum - showroom.carsInStock);
                currentOrder.deliverDay = (i + 1) + leadTime;
            }
            d.pendingOrderDay = currentOrder.deliverDay;
            d.pendingOrderCars = currentOrder.cars;
            days.add(d);

        }

        s.dispose(); // Used to dispose the SimulationGUI after Simulate is pressed 
        SimulationTable res = new SimulationTable(days);
        res.setVisible(true);
    }

    static DemandArray DemandCalculator(DemandArray d, SimulationData MS) {

        for (int i = 0; i < MS.demand.length; i++) {

            if (i == 0) {

                MS.demandComProbablities[i] = MS.demandProbablities[i];

            } else {

                MS.demandComProbablities[i] = MS.demandProbablities[i] + MS.demandComProbablities[i - 1];

            }

            if (i == 0) {
                MS.StartingRange[i] = 0;
                MS.EndingRange[i] = MS.demandComProbablities[i] * 100;

            } else if (i == MS.demand.length - 1) {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = 100;

            } else {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = MS.demandComProbablities[i] * 100;
            }

            d.Darray.add(new DemandArray(MS.demand[i], MS.demandProbablities[i], MS.demandComProbablities[i], MS.StartingRange[i], MS.EndingRange[i]));
        }
        return d;
    }

    static LeadTimeArray LeadTimeCalculator(LeadTimeArray ld, SimulationData MS) {

        for (int i = 0; i < MS.leadTime.length; i++) {

            if (i == 0) {

                MS.leadComProb[i] = MS.leadTimeProbablities[i];
            } else {

                MS.leadComProb[i] = MS.leadTimeProbablities[i] + MS.leadComProb[i - 1];
            }
            if (i == 0) {
                MS.StartingRange[i] = 0;
                MS.EndingRange[i] = MS.leadComProb[i] * 100;

            } else if (i == MS.leadTimeProbablities.length - 1) {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = 100;

            } else {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = MS.leadComProb[i] * 100;
            }
            ld.Ldarray.add(new LeadTimeArray(MS.leadTime[i], MS.leadTimeProbablities[i], MS.leadComProb[i], MS.StartingRange[i], MS.EndingRange[i]));

        }
        return ld;
    }

    static int demandSetter(int i, DemandArray e) {

        int x = 0;

        if (i > e.Darray.get(0).StartingRange && i <= e.Darray.get(0).EndingRange) {
            x = 0;
        } else if (i > e.Darray.get(1).StartingRange && i <= e.Darray.get(1).EndingRange) {
            x = 1;
        } else if (i > e.Darray.get(2).StartingRange && i <= e.Darray.get(2).EndingRange) {
            x = 2;
        } else if (i > e.Darray.get(3).StartingRange && i <= e.Darray.get(3).EndingRange) {
            x = 3;
        } else if (i > e.Darray.get(4).StartingRange && i <= e.Darray.get(4).EndingRange) {
            x = 4;
        }

        return x;

    }

    static int leadTimeSetter(int i, LeadTimeArray n) {

        int x = 0;

        if (i > n.Ldarray.get(0).StartingRange && i <= n.Ldarray.get(0).EndingRange) {
            x = 1;

        } else if (i > n.Ldarray.get(1).StartingRange && i <= n.Ldarray.get(1).EndingRange) {
            x = 2;

        } else if (i > n.Ldarray.get(2).StartingRange && i <= n.Ldarray.get(2).EndingRange) {
            x = 3;

        }

        return x;

    }
}
