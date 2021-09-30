package Bank;

import java.util.Random;

public class MSProject {

    public void Simulation(int CustomersNumber, SimulationGUI s) {

        SimulationData MS = new SimulationData(); // An object of Class SimulationData
        InterArrivalArray ia = new InterArrivalArray(); //An object of Class InterArrivalArray
        ExpressServiceArray e = new ExpressServiceArray();//An object of Class ExpressServiceArray
        NormalServiceArray n = new NormalServiceArray();//An object of Class NormalServiceArray
        Random rn = new Random();
        Customer C = new Customer(); //An object of Class Customer
        int nCounter = 0; // Used to check if the normal teller served it first customer 
        int normalCounter; // Used to loop over the Customer arraylist until it finds the previous Normal customer 
        int ExpCounter = 0; // Used to loop over the Customer arraylist until it finds the previous Express customer
        double AverageExpressServiceTime = 0, AverageNormalServiceTime = 0, AverageExpressWaitingTime = 0, AverageNomralWaitingTime = 0, AverageInterArrival = 0, ProbOfwaitingInNQ = 0, IATi = 0;
        ia.ariv.add(InterArrivalProbCalculator(ia, MS)); // A function that calculates the Interarrival Values then stores them in an arraylist of type InterArrivalArray 
        n.nseriv.add(NormalProbCalculator(n, MS)); // A function that calculates the normal teller Values then stores them in an arraylist of type NormalServiceArray
        e.seriv.add(ExpressProbCalculator(e, MS)); // A function that calculates the express teller  Values then stores them in an arraylist of type ExpressServiceArray

        for (int i = 0; i < CustomersNumber; i++) { // For loop that loops according to the value of CustomersNumber variable. CustomersNumber is an input that is taken from the use throught a GUI.

            int r = rn.nextInt(100) + 1;

            C.CustomerID = i + 1; // Sets the CustomerID 

            if (i == 0) { // This case should be accesed only during the first ittertion of the loop, naturally the first customer is always an express customer.  

                C = ValuesCalculator(C, r, e);// A function that returns a Customer object. More details in the function itself.
                e.ExpressTeller = C.timeServiceEnds; // ExpressTeller = the time the express teller is going to finish it's current customer hence having it equal the C.timeserviceEnds
                e.ExpressQueue = 0; //ExpressQueue  = the time the express teller queue is going to to be empty hence having it equal the C.timeserviceEnds

            } else {

                C.interarrivalTime = InterarrivalSetter(r, ia);// Sets the Interarrival for all customers according to their randome number

                C.arrivalTime = C.c.get(i - 1).arrivalTime + C.interarrivalTime;

                if (C.arrivalTime >= e.ExpressTeller) { // Checks if the arrival time is bigger or equal the ExpressTeller 

                    int count = (i - 1); // count is is equal i - 1 so I can access the previous index
                    while (!C.c.get(count).TellerType.equals("Exp")) { // Keeps checking whether the teller type of this index is equal to exp or not 

                        count--;
                        ExpCounter = count;
                    }

                    C = ExpressValuesCalculator(C, i, ExpCounter, r, e);// A function that returns a Customer object. More details in the function itself.
                    e.ExpressTeller = C.timeServiceEnds;

                } else if (C.arrivalTime >= e.ExpressQueue) {// Checks if arrival is bigger or equal to ExpressQueue

                    C = ExpressQueueValuesCalculator(C, i, r, e.ExpressTeller, e);// A function that returns a Customer object. More details in the function itself.
                    e.ExpressQueue = C.timeServiceEnds;

                } else {// The program only makes it that far if the Express teller and it Queue are occupied so it proceedes to assign the customer to the normal teller

                    if (nCounter < 1) {// This case should be accesed only if it is the first normal customer of the day.  

                        C = NormalValuesCalculator(C, i, r, n);// A function that returns a Customer object. More details in the function itself.

                        nCounter++;

                    } else {// Every other normal customer goes through here

                        normalCounter = (i - 1);// normalCounter is is equal i - 1 so I can access the previous index
                        while (!C.c.get(normalCounter).TellerType.equals("N")) {// Keeps checking whether the teller type of this index is equal to exp or not 

                            normalCounter--;

                        }
                        normalCounter--;
                        C = NormalValuesCalculator(C, i, normalCounter, r, n, ia);// A function that returns a Customer object. More details in the function itself.
                    }
                }

            }

            C.c.add(new Customer(C));// Finally it adds the cusomer to the customer ArrayList
        }

        for (int i = 0; i < C.c.size(); i++) {

            double NCnum = 0;
            double NTotalS = 0;
            double NWait = 0;
            double ECnum = 0;
            double EWait = 0;
            double ETotalS = 0;
            double IAT = 0;
            double w = 0, cw = 0;
            if (C.c.get(i).TellerType.equalsIgnoreCase("Exp") || C.c.get(i).TellerType.equalsIgnoreCase("EQ")) {

                ECnum++;
                ETotalS = ETotalS + C.c.get(i).serviceTime;
                EWait = EWait + C.c.get(i).waitingTime;
            }
            if (C.c.get(i).TellerType.equalsIgnoreCase("N")) {

                NCnum++;
                NTotalS = NTotalS + C.c.get(i).serviceTime;
                NWait = NWait + C.c.get(i).waitingTime;

                if (C.c.get(i).waitingTime > 0) {

                    cw++;

                }
            }
            IAT = IAT + C.c.get(i).interarrivalTime;
            AverageExpressServiceTime = ETotalS / ECnum;
            AverageNormalServiceTime = NTotalS / NCnum;
            AverageExpressWaitingTime = EWait / ECnum;
            AverageNomralWaitingTime = NWait / NCnum;
            AverageInterArrival = IAT / C.c.size();
            ProbOfwaitingInNQ = cw / C.c.size();
            IATi = AverageInterArrival;
        }

        

        String q1 = String.valueOf(AverageExpressServiceTime);
        String q2 = String.valueOf(IATi);
        String q3 = String.valueOf(AverageNormalServiceTime);
        String q4 = String.valueOf(AverageExpressWaitingTime);
        String q5 = String.valueOf(AverageNomralWaitingTime);
        
        String q6 = String.valueOf(ProbOfwaitingInNQ);
        
        
        String Answers = " The average service time of the express teller:" + q1 +
                "\n" +""
                + " Average interarrival:" + q2+ "\n" +
                " The average Normal time of the express teller:" + q3+ "\n" +
                " Average express Waiting Time:"  + q4+ "\n" +
                " Average Normal Waiting Time:" + q5+ "\n" +
                " Probabilty of waiting in normal queue" + q6; 
        
        
        s.dispose(); // Used to dispose the SimulationGUI after Simulate is pressed 
        SimulationTable ST = new SimulationTable(C.c , Answers);// Passes the ArrayList to the GUI so it can printed  
        ST.setVisible(true);

    }

    int ExpressServicesetter(int i, ExpressServiceArray e) {

        // What this function basically does is as follows, it takes two parameter a random number(i) and an ExpressServiceArray(e) so it can access the starting and ending ranges 
        // after finishing the comparisons it returns x which should be now the Express Service of the customer
        int x = 0;

        if (i >= e.seriv.get(0).StartingRange && i < (e.seriv.get(0).EndingRange + 1)) {
            x = 1;

        } else if (i >= e.seriv.get(1).StartingRange && i < (e.seriv.get(1).EndingRange + 1)) {
            x = 2;

        } else if (i >= e.seriv.get(2).StartingRange && i < (e.seriv.get(2).EndingRange + 1)) {
            x = 3;

        } else if (i >= e.seriv.get(3).StartingRange && i < (e.seriv.get(3).EndingRange + 1)) {
            x = 4;

        }

        return x;

    }

    int NormalServicesetter(int i, NormalServiceArray n) {

        // What this function basically does is as follows, it takes two parameter a random number(i) and an NormalServiceArray(n) so it can access the starting and ending ranges 
        // after finishing the comparisons it returns x which should be now the Normal Service of the customer
        int x = 0;

        if (i >= n.nseriv.get(0).StartingRange && i < (n.nseriv.get(0).EndingRange + 1)) {
            x = 1;

        } else if (i >= n.nseriv.get(1).StartingRange && i < (n.nseriv.get(1).EndingRange + 1)) {
            x = 2;

        } else if (i >= n.nseriv.get(2).StartingRange && i < (n.nseriv.get(2).EndingRange + 1)) {
            x = 3;

        } else if (i >= n.nseriv.get(3).StartingRange && i < (n.nseriv.get(3).EndingRange + 1)) {
            x = 4;

        }

        return x;

    }

    int InterarrivalSetter(int i, InterArrivalArray a) {

        // What this function basically does is as follows, it takes two parameter a random number(i) and InterArrivalArray(a) so it can access the starting and ending ranges 
        // after finishing the comparisons it returns x which should be now the interarrival of the customer
        int x = 0;

        if (i >= a.ariv.get(0).StartingRange && i < (a.ariv.get(0).EndingRange + 1)) {
            x = 0;

        } else if (i >= a.ariv.get(1).StartingRange && i < (a.ariv.get(1).EndingRange + 1)) {
            x = 1;

        } else if (i >= a.ariv.get(2).StartingRange && i < (a.ariv.get(2).EndingRange + 1)) {
            x = 2;

        } else if (i >= a.ariv.get(3).StartingRange && i < (a.ariv.get(3).EndingRange + 1)) {
            x = 3;

        } else if (i >= a.ariv.get(4).StartingRange && i < (a.ariv.get(4).EndingRange + 1)) {

            x = 4;

        } else if (i >= a.ariv.get(5).StartingRange && i < (a.ariv.get(5).EndingRange + 1)) {

            x = 5;

        }
        return x;
    }

    Customer ValuesCalculator(Customer C, int r, ExpressServiceArray e) {

        // Only used to calculate the first customer number because the first customer's arrival and inter arrival are always Zeros. 
        C.interarrivalTime = 0;
        C.arrivalTime = 0;
        C.serviceTime = ExpressServicesetter(r, e);

        C.waitingTime = 0;
        C.timeServiceBegins = C.arrivalTime + C.waitingTime;
        C.timeServiceEnds = C.timeServiceBegins + C.serviceTime;
        C.timeSpentInSystem = C.timeServiceEnds - C.arrivalTime;
        C.idleTime = 0;

        C.TellerType = "Exp";
        C.r = r;
        return C;
    }

    Customer ExpressValuesCalculator(Customer C, int i, int y, int r, ExpressServiceArray e) {

        C.serviceTime = ExpressServicesetter(r, e);
        if (C.arrivalTime > C.c.get(y).timeServiceEnds) {
            C.timeServiceBegins = C.arrivalTime;
        } else {
            C.timeServiceBegins = C.c.get(y).timeServiceEnds;
        }

        C.waitingTime = C.timeServiceBegins - C.arrivalTime;
        C.timeServiceEnds = C.timeServiceBegins + C.serviceTime;
        C.timeSpentInSystem = C.timeServiceEnds - C.arrivalTime;
        if (C.arrivalTime > C.c.get(y).timeServiceEnds) {

            C.idleTime = C.arrivalTime - C.c.get(y).timeServiceEnds;
        } else {

            C.idleTime = 0;
        }
        C.TellerType = "Exp";
        C.r = r;
        return C;
    }

    Customer ExpressQueueValuesCalculator(Customer C, int i, int r, int e, ExpressServiceArray t) {

        C.serviceTime = ExpressServicesetter(r, t);

        if (C.arrivalTime > C.c.get(i - 1).timeServiceEnds) {
            C.timeServiceBegins = C.arrivalTime;
        } else {
            C.timeServiceBegins = e;
        }
        C.waitingTime = C.timeServiceBegins - C.arrivalTime;
        C.timeServiceEnds = C.timeServiceBegins + C.serviceTime;
        C.timeSpentInSystem = C.timeServiceEnds - C.arrivalTime;
        if (C.arrivalTime > C.c.get(i - 1).timeServiceEnds) {

            C.idleTime = C.arrivalTime - C.c.get(i - 1).timeServiceEnds;
        } else {

            C.idleTime = 0;
        }

        C.TellerType = "EQ";
        C.r = r;
        return C;
    }

    Customer NormalValuesCalculator(Customer C, int i, int r, NormalServiceArray n) {

        C.serviceTime = NormalServicesetter(r, n);

        C.timeServiceBegins = C.arrivalTime;
        C.waitingTime = C.timeServiceBegins - C.arrivalTime;
        C.timeServiceEnds = C.timeServiceBegins + C.serviceTime;
        C.timeSpentInSystem = C.timeServiceEnds - C.arrivalTime;
        if (C.arrivalTime > C.c.get(i - 1).timeServiceEnds) {

            C.idleTime = C.arrivalTime - C.c.get(i - 1).timeServiceEnds;
        } else {

            C.idleTime = 0;
        }
        C.TellerType = "N";
        C.r = r;
        return C;
    }

    Customer NormalValuesCalculator(Customer C, int i, int y, int r, NormalServiceArray n, InterArrivalArray a) {

        C.serviceTime = NormalServicesetter(r, n);
        C.interarrivalTime = InterarrivalSetter(r, a);

        C.arrivalTime = C.c.get(i - 1).arrivalTime + C.interarrivalTime;
        if (C.arrivalTime > C.c.get(y).timeServiceEnds) {
            C.timeServiceBegins = C.arrivalTime;
        } else {
            C.timeServiceBegins = C.c.get(y).timeServiceEnds;
        }

        C.waitingTime = C.timeServiceBegins - C.arrivalTime;
        C.timeServiceEnds = C.timeServiceBegins + C.serviceTime;
        C.timeSpentInSystem = C.timeServiceEnds - C.arrivalTime;

        if (C.arrivalTime > C.c.get(i - 1).timeServiceEnds) {

            C.idleTime = C.arrivalTime - C.c.get(i - 1).timeServiceEnds;
        } else {

            C.idleTime = 0;
        }
        C.TellerType = "N";
        C.r = r;
        return C;
    }

    NormalServiceArray NormalProbCalculator(NormalServiceArray n, SimulationData MS) {

        for (int i = 0; i < MS.NorProb.length; i++) {

            if (i == 0) {

                MS.NorComProb[i] = MS.NorProb[i];
            } else {

                MS.NorComProb[i] = MS.NorProb[i] + MS.NorComProb[i - 1];
            }
            if (i == 0) {
                MS.StartingRange[i] = 0;
                MS.EndingRange[i] = MS.NorComProb[i] * 100;

            } else if (i == MS.NorComProb.length - 1) {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = 100;

            } else {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = MS.NorComProb[i] * 100;
            }

            n.nseriv.add(new NormalServiceArray(MS.ServiceTime[i], MS.NorProb[i], MS.NorComProb[i], MS.StartingRange[i], MS.EndingRange[i]));
        }
        return n;
    }

    ExpressServiceArray ExpressProbCalculator(ExpressServiceArray e, SimulationData MS) {

        for (int i = 0; i < MS.ExProb.length; i++) {

            if (i == 0) {

                MS.ExComProb[i] = MS.ExProb[i];
                MS.NorComProb[i] = MS.NorProb[i];
            } else {

                MS.ExComProb[i] = MS.ExProb[i] + MS.ExComProb[i - 1];
                MS.NorComProb[i] = MS.NorProb[i] + MS.NorComProb[i - 1];
            }
            if (i == 0) {
                MS.StartingRange[i] = 0;
                MS.EndingRange[i] = MS.ExComProb[i] * 100;

            } else if (i == MS.ExProb.length - 1) {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = 100;

            } else {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = MS.ExComProb[i] * 100;
            }
            e.seriv.add(new ExpressServiceArray(MS.ServiceTime[i], MS.ExProb[i], MS.ExComProb[i], MS.StartingRange[i], MS.EndingRange[i]));

        }
        return e;
    }

    InterArrivalArray InterArrivalProbCalculator(InterArrivalArray ia, SimulationData MS) {

        for (int i = 0; i < MS.InterArrival.length; i++) {

            if (i == 0) {

                MS.ComProbablities[i] = MS.Probablities[i];

            } else {

                MS.ComProbablities[i] = MS.Probablities[i] + MS.ComProbablities[i - 1];

            }

            if (i == 0) {
                MS.StartingRange[i] = 0;
                MS.EndingRange[i] = MS.ComProbablities[i] * 100;

            } else if (i == MS.InterArrival.length - 1) {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = 100;

            } else {

                MS.StartingRange[i] = MS.EndingRange[i - 1] + 1;
                MS.EndingRange[i] = MS.ComProbablities[i] * 100;
            }

            ia.ariv.add(new InterArrivalArray(MS.InterArrival[i], MS.Probablities[i], MS.ComProbablities[i], MS.StartingRange[i], MS.EndingRange[i]));
        }
        return ia;
    }
}
