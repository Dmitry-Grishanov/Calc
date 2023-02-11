public class Main {
    public static void main(String[] args) {




        int startAmount = 2000;

        int currentWeek = 1;

        float percentPerTradeFromStartAmount =     1.F;
        float percentAfterReducing =               1.5F;
        int   whenToReduce =                       12000;



        int weeks = 52 ;
        int tradesPerWeek =  7;




        // ToDo add limit for max bid
        int PLPercentPerTrade = 2;


        // ToDo create method to overwrite number of trades
        int tradesPerWeekAfter25K =10;

        int resultCalc = calcProcDay(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek, PLPercentPerTrade, percentAfterReducing,currentWeek,whenToReduce);
        int tradesToReducedPercent = tradesToReducedPercent(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek, percentAfterReducing, whenToReduce);
        int tradesToEven = tradesToEven(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek);
        //resultCalc = calcAmountDay(50, 2000, 130);


        System.out.println("Start amount = "+startAmount);
        System.out.println("Total Account = "+resultCalc/1000+"k in "+ weeks+" weeks");
        System.out.println("Trades to even (you get your $"+startAmount+" back) = "+tradesToEven);

        System.out.println("Weeks to even (you get your $"+startAmount+" back) = "+tradesToEven/tradesPerWeek);
        System.out.println("Trades to reduced Percent (reduce after you made $"+whenToReduce+" ) = " +tradesToReducedPercent);
        System.out.println("Weeks  to reduced Percent (reduce after you made $"+whenToReduce+" ) = "+tradesToReducedPercent/tradesPerWeek);

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



            if
                //(
            (currentWeek==week)
            //||((currentWeek+1)==week))
            {
                System.out.println(
                        "  ||  Week # = "+week+
                                "  ||  Trade # = "+i+
                                "  ||  percent per trade from start Amount = "+percentPerTradeFromStartAmount+" %"+

                                //"  ||  Trade# "+i+
                                "  ||  Minimum P/L per Trade = "+ PL+
                                "  ||  Total = "+result +
                                "  ||  minimum bid ("+minimumBidPercentOfTotal+" % of grow for P/L )= " + minimumBid);
            }




        }
        return result;
    }
    public static int tradesToReducedPercent(Float proc, Integer start, Integer weeks, int tradesPerWeek, float percentAfterReducing, int whenToReduce){
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