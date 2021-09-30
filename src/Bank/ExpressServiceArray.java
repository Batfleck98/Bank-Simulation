package Bank;

import java.util.ArrayList;


public class ExpressServiceArray {
    
    ArrayList<ExpressServiceArray> seriv = new ArrayList<>();
    int ServiceTime;
    float ExProb;
    float ExComProb;
    double StartingRange , EndingRange;
    int ExpressTeller , ExpressQueue;
    public ExpressServiceArray(int ServiceTime, float ExProb, float ExComProb, double StartingRange, double EndingRange) {
        this.ServiceTime = ServiceTime;
        this.ExProb = ExProb;
        this.ExComProb = ExComProb;
        this.StartingRange = StartingRange;
        this.EndingRange = EndingRange;
    }



    ExpressServiceArray() {
   }




}
