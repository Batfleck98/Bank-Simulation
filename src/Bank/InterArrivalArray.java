package Bank;

import java.util.ArrayList;

public class InterArrivalArray {

    
    
     ArrayList<InterArrivalArray> ariv = new ArrayList<>();
    
    int InterArrival;
    double Probablities;
    double ComProbablities;
    double StartingRange , EndingRange;

    public InterArrivalArray(int InterArrival, double Probablities, double ComProbablities, double StartingRange, double EndingRange) {
        this.InterArrival = InterArrival;
        this.Probablities = Probablities;
        this.ComProbablities = ComProbablities;
        this.StartingRange = StartingRange;
        this.EndingRange = EndingRange;
    }

    InterArrivalArray() {
       }
   


}
