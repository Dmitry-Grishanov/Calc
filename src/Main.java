public class Main {
    public static void main(String[] args) {

        int startAmount = 100000;

        int currentWeek = 1;

        float percentPerTradeFromStartAmount =     1.F;
        float percentAfterReducing =               .5F;

        int minimumBidPercentOfTotal = 15;

        int weeks = 52 ;
        int tradesPerWeek =  10;

        int PLPerWeekToStartWithdraw = 3000;
        int   whenToReduce =
        200000;
                //2*startAmount;

        // ToDo create method to overwrite number of trades
        //int tradesPerWeekAfter25K =10;
        int tradesToReducedPercent = tradesToReducedPercent(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek, percentAfterReducing, whenToReduce);
        int resultCalc = calcProcDay(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek, minimumBidPercentOfTotal, percentAfterReducing,currentWeek,whenToReduce,tradesToReducedPercent);
        int tradesToEven = tradesToEven(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek);
        int weeksToPLBiggerThanSomeAmount = weeksToPLBiggerThanSomeAmount(percentPerTradeFromStartAmount, startAmount, weeks, tradesPerWeek,PLPerWeekToStartWithdraw);
        //resultCalc = calcAmountDay(50, 2000, 130);


        System.out.println("Start amount = "+startAmount);
        System.out.println("Total Account = "+resultCalc/1000+"k in "+ weeks+" weeks");
        System.out.println("Trades to even (you get your $"+startAmount+" back) = "+tradesToEven);

        System.out.println("Weeks to even (you get your $"+startAmount+" back) = "+tradesToEven/tradesPerWeek);
        System.out.println("P/L per week is bigger than $"+PLPerWeekToStartWithdraw+" in = "+weeksToPLBiggerThanSomeAmount+" weeks");
        System.out.println();
        System.out.println("Trades to reduced Percent (reduced after you made $"+whenToReduce+" ) = " +tradesToReducedPercent);
        System.out.println("Weeks  to reduced Percent (reduced after you made $"+whenToReduce+" ) = "+tradesToReducedPercent/tradesPerWeek);


    }

    private static int weeksToPLBiggerThanSomeAmount(float percentPerTradeFromStartAmount, int startAmount, int weeks, int tradesPerWeek, int plPerWeekToStartWithdraw) {

        int result =startAmount;
        int trades = weeks*tradesPerWeek;
        int week ;
        int PL ;
        for (int i=1; i<=trades; i++) {

            PL = (int) (percentPerTradeFromStartAmount * result / 100);

            result = (int) (percentPerTradeFromStartAmount * result / 100 + result);
            week = (i)/tradesPerWeek+1;


            if (PL*tradesPerWeek>plPerWeekToStartWithdraw){
                return week;
            }
        }

        return 0;
    }


    public static int calcProcDay(Float percentPerTradeFromStartAmount, Integer start, Integer weeks, int tradesPerWeek, int minimumBidPercentOfTotal, float percentAfterReducing, int currentWeek, int whenToReduce
            , int tradesToReducedPercent){
        int result =start;
        int PL ;
        int trades= weeks*tradesPerWeek;
        int week =0;
        int minimumBid = 0 ;
        int PLPerCurrentWeek = 0;
        int weekGreaterThan100k = 0;


        for (int i=1; i<=trades; i++) {
            PL = (int) (percentPerTradeFromStartAmount * result / 100);
            result = (int) (percentPerTradeFromStartAmount * result / 100 + result);
            week = (i)/tradesPerWeek+1;
            minimumBid = PL * 100/minimumBidPercentOfTotal;

            if (result>whenToReduce) {
                percentPerTradeFromStartAmount = percentAfterReducing;
            }
            if ((result>100000)&&(result<110000)){
                weekGreaterThan100k = week;
            }

            if
            (currentWeek==week)
            {
                PLPerCurrentWeek = PLPerCurrentWeek+PL;
                System.out.print(
                        "  ||  Week # = "+week+
                                "  ||  Trade # = "+i+
                                "  ||  percent per trade from start Amount = "+percentPerTradeFromStartAmount+" %"+

                                //"  ||  Trade# "+i+
                                "  ||  Minimum P/L per Trade = "+ PL+
                                "  ||  Total = "+result +
                                "  ||  minimum bid ("+minimumBidPercentOfTotal+" % of grow for P/L )= " + minimumBid);

                if (minimumBid>result){System.out.println(" **Bigger Than Start Amount for first "+tradesToReducedPercent/tradesPerWeek+" weeks **");} else System.out.println(" ");
            }




        }
        System.out.println("Week when Total Account > 100,000  = "+weekGreaterThan100k);
        System.out.println("Total P/L for current week = "+PLPerCurrentWeek);
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
    public static int tradesToEven(Float PLPercentPerTrade, Integer start, Integer weeks, int tradesPerWeek){

        int result =start;
        int trades = weeks*tradesPerWeek;
        int tradesToEven =0;
        int difference ;
        for (int i=1; i<=trades; i++) {

            difference =result-start;
            result = (int) (PLPercentPerTrade * result / 100 + result);
            if (start<difference){tradesToEven = i;
                return tradesToEven;
            }
        }
        return tradesToEven;
    }
}