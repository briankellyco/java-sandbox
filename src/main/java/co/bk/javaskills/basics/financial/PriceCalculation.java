package co.bk.javaskills.basics.financial;

        import java.math.BigDecimal;
        import java.math.MathContext;
        import java.math.RoundingMode;

/**
 * Created by brian on 7/8/16.
 */
public class PriceCalculation {

    private static int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    private static int DECIMALS = 5;

    /*
     * Avoid rounding errors. http://stackoverflow.com/questions/10603651/what-causes-non-terminating-decimal-expansion-exception-from-bigdecimal-divide
     */
    private static MathContext mc = new MathContext(DECIMALS, RoundingMode.HALF_UP);

    public static void main(String args[]) {

        BigDecimal price = new BigDecimal("60");
        Long marginPercentage = Long.valueOf(25);

        BigDecimal outputPrice = PriceCalculation.calculateMarkupPrice(price, marginPercentage);
        System.out.println("outputPrice: " + outputPrice.toString());

        BigDecimal discountedPrice = PriceCalculation.calculateDiscountedPrice(price, marginPercentage);
        System.out.println("discountedPrice: " + discountedPrice.toString());


        /**
         *         Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
         *
         * at java.math.BigDecimal.divide(BigDecimal.java:1690)
         * at com.babelforce.usage.pricing.loader.Test.calculateGrossMarginPrice(Test.java:73)
         * at com.babelforce.usage.pricing.loader.Test.main(Test.java:30)
         * SOLUTION:
         *   http://stackoverflow.com/questions/10603651/what-causes-non-terminating-decimal-expansion-exception-from-bigdecimal-divide
         *
         */
        BigDecimal grossMarginPrice = PriceCalculation.calculateGrossMarginPrice(price, marginPercentage);
        System.out.println("grossMarginPrice: " + grossMarginPrice.toString());

    }


    /**
     * Markup is from a "cost" perspective.
     *
     * SalePrice = (MarkupPercentage * CostPrice) + CostPrice = (50% * $60) + $60 = $90
     * Markup Percentage = (Sales Price – Unit Cost)/Unit Cost = ($90 – $60)/$60 = 50%
     *
     * @param price
     * @param markupPercentage
     * @return
     */
    private static BigDecimal calculateMarkupPrice(BigDecimal price, Long markupPercentage) {

        final double percentDecimal = (double) markupPercentage.longValue() / 100l;
        BigDecimal asPercent = new BigDecimal(new Double(percentDecimal).toString()).setScale(DECIMALS, ROUNDING_MODE);
        BigDecimal marginApplied = price.multiply(asPercent, mc).setScale(DECIMALS, ROUNDING_MODE);
        return marginApplied.add(price).setScale(DECIMALS, ROUNDING_MODE);
    }

    /**
     * GM is from a "salesPrice" perspective.
     * <p>
     * Following are all true:
     * "GM% = profit / salesPrice = (salesPrice - cost) / salesPrice"
     * "GM = salesPrice - cost"
     * "salesPrice = cost / (1-GM%) = 60 / (1-0.25) = 80"
     *
     * @param price
     * @param grossMarginPercentage
     * @return price with gross margin percentage applied
     */

    private static BigDecimal calculateGrossMarginPrice(BigDecimal price, Long grossMarginPercentage) {

        final double percentDecimal = (double) grossMarginPercentage.longValue() / 100l;
        final double oneLessPercentDecimal = 1d - percentDecimal;
        BigDecimal divisor = new BigDecimal(new Double(oneLessPercentDecimal).toString()).setScale(DECIMALS, ROUNDING_MODE);

        BigDecimal grossMarginPrice = price.divide(divisor, mc).setScale(DECIMALS, ROUNDING_MODE);
        return grossMarginPrice;
        //BigDecimal profit = price.multiply(asPercent).setScale(DECIMALS, ROUNDING_MODE);
        //return marginApplied.add(price).setScale(DECIMALS, ROUNDING_MODE);
    }

    /**
     * Decrease price by specified percentage
     *
     * @param price
     * @param discountPercentage e.g 5 represents 5% discount
     * @return discountedPrice
     */
    private static BigDecimal calculateDiscountedPrice(BigDecimal price, Long discountPercentage) {
        return price.multiply(new BigDecimal((100d - discountPercentage.doubleValue()) / 100)).setScale(DECIMALS, ROUNDING_MODE);
    }

}
