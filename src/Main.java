public class Main {
    public static void main(String[] args) {


        int resultCalc = 0;
        int tradesTo25K =0;


        float percentPerTradeFromStartAmount = 2.0F;
        int startAmount = 500;
        int weeks = 52;
        int tradesPerWeek =10;
        int tradesToEven =0;
        int currentWeek = 26;
        // ToDo add limit for max bid
        int PLPercent = 2;
        int whenToReduce = 25000;
        float percentAfterReducing = 1.0F;
        // ToDo create method to overwrite number of trades
        int tradesPerWeekAfter25K =10;

        resultCalc = calcProcDay(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek, PLPercent, percentAfterReducing,currentWeek,whenToReduce);
        tradesTo25K = tradesTo25K(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek, percentAfterReducing, whenToReduce);
        tradesToEven = tradesToEven(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek);
        //resultCalc = calcAmountDay(50, 2000, 130);


        System.out.println("Total= "+resultCalc/1000+"k for "+ weeks+" weeks");
        System.out.println("Trades to even= "+tradesToEven);

        System.out.println("Weeks to even= "+tradesToEven/tradesPerWeek);
        System.out.println("Trades to 25k= "+tradesTo25K);
        System.out.println("Weeks to 25k= "+tradesTo25K/tradesPerWeek);

    }



    public static int calcProcDay(Float percentPerTradeFromStartAmount, Integer start, Integer weeks, int tradesPerWeek, int minimumBidPercentOfTotal, float percentAfter25K, int currentWeek, int whenToReduce){
      int result =start;
      int PL = 0 ;
      int trades= weeks*tradesPerWeek;
      int week =0;
      int minimumBid = 0 ;


       for (int i=1; i<=trades; i++) {
           PL = (int) (percentPerTradeFromStartAmount * result / 100);
           result = (int) (percentPerTradeFromStartAmount * result / 100 + result);
           week = (i)/tradesPerWeek+1;
           minimumBid = PL * 100/minimumBidPercentOfTotal;

           if (result>whenToReduce) {
               percentPerTradeFromStartAmount = percentAfter25K;
           }



           if (currentWeek==week)
           {
               System.out.println(
                       "  ||  Week #= "+week+
                       "  ||  Trade # = "+i+
                       //"  percent per trade = "+percentPerTradeFromStartAmount+

                       "  ||  Trade# "+i+
                       "  ||  Minimum P/L per Trade = "+ PL+
                       "  ||  Total= "+result +
                       "  ||  minimum bid ("+minimumBidPercentOfTotal+" % of grow for P/L )= " + minimumBid);
           }




       }
        return result;
    }
    public static int tradesTo25K(Float proc, Integer start, Integer weeks, int tradesPerWeek, float percentAfterReducing, int whenToReduce){
        int result =start;
        int daily = 0 ;
        int trades= weeks*tradesPerWeek;
        int week =0;
        int whenToReducePercent = 0;
        for (int i=1; i<=trades; i++) {
            daily = (int) (proc * result / 100);
            result = (int) (proc * result / 100 + result);
            if (result>whenToReduce){whenToReducePercent = i;

            week = (i-1)/tradesPerWeek;

                return whenToReducePercent;
            }
        }
        return whenToReducePercent;
    }
    public static int tradesToEven(Float proc, Integer start, Integer weeks, int tradesPerWeek){

        int result =start;
        int trades = weeks*tradesPerWeek;
        int tradesToEven =0;
        int difference = 0;
        for (int i=1; i<=trades; i++) {

            difference =result-start;
            result = (int) (proc * result / 100 + result);
            if (start<difference){tradesToEven = i;
                return tradesToEven;
            }
        }
        return tradesToEven;
    }
}