package Bank;

import java.util.ArrayList;


public class NormalServiceArray {
    
       ArrayList<NormalServiceArray> nseriv = new ArrayList<>();
    int ServiceTime;
    float NorProb;
    float NorComProb;
    double StartingRange , EndingRange;

    public NormalServiceArray(int ServiceTime, float NorProb, float NorComProb, double StartingRange, double EndingRange) {
        this.ServiceTime = ServiceTime;
        this.NorProb = NorProb;
        this.NorComProb = NorComProb;
        this.StartingRange = StartingRange;
        this.EndingRange = EndingRange;
    }

    NormalServiceArray() {
        
    }

    
    
}
