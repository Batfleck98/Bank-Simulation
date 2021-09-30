package Bank;

import java.util.ArrayList;

public class Customer {

    ArrayList<Customer> c = new ArrayList<>();

    int CustomerID;
    int interarrivalTime;
    int arrivalTime;
    int serviceTime;
    int timeServiceBegins;
    int waitingTime;
    int timeServiceEnds;
    int timeSpentInSystem;
    int idleTime;
    int r;
    String TellerType;

 




 
    public Customer() {
    }
    
    
    public Customer(Customer C) {
        
        this.CustomerID = C.CustomerID;
        this.interarrivalTime = C.interarrivalTime;
        this.arrivalTime = C.arrivalTime;
        this.serviceTime = C.serviceTime;
        this.timeServiceBegins = C.timeServiceBegins;
        this.waitingTime = C.waitingTime;
        this.timeServiceEnds = C.timeServiceEnds;
        this.timeSpentInSystem = C.timeSpentInSystem;
        this.idleTime = C.idleTime;
        this.TellerType = C.TellerType;
        this.r = C.r;
    }

    @Override
    public String toString() {
        return "Customer{" + "c=" + c + ", CustomerID=" + CustomerID + ", interarrivalTime=" + interarrivalTime + ", arrivalTime=" + arrivalTime + ", serviceTime=" + serviceTime + ", timeServiceBegins=" + timeServiceBegins + ", waitingTime=" + waitingTime + ", timeServiceEnds=" + timeServiceEnds + ", timeSpentInSystem=" + timeSpentInSystem + ", idleTime=" + idleTime + ", r=" + r + ", TellerType=" + TellerType + '}';
    }
    
}
