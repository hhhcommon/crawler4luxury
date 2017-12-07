package model;

/**
 * Created by yang on 2017/6/22.
 */
public class ChristianPrize {

    /**
     * productId : 59584
     * priceFormat : {"pattern":"\u20ac%s","precision":2,"requiredPrecision":2,"decimalSymbol":".","groupSymbol":",","groupLength":3,"integerRequired":1}
     * includeTax : true
     * showIncludeTax : true
     * showBothPrices : false
     * productPrice : 875
     * productOldPrice : 875
     * priceInclTax : 875
     * priceExclTax : 875
     * skipCalculate : 1
     * defaultTax : 19
     * currentTax : 19
     * idSuffix : _clone
     * oldPlusDisposition : 0
     * plusDisposition : 0
     * oldMinusDisposition : 0
     * minusDisposition : 0
     */

    private String productId;
    private PriceFormatBean priceFormat;
    private String includeTax;
    private boolean showIncludeTax;
    private boolean showBothPrices;
    private int productPrice;
    private int productOldPrice;
    private int priceInclTax;
    private int priceExclTax;
    private int skipCalculate;
    private int defaultTax;
    private int currentTax;
    private String idSuffix;
    private int oldPlusDisposition;
    private int plusDisposition;
    private int oldMinusDisposition;
    private int minusDisposition;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public PriceFormatBean getPriceFormat() {
        return priceFormat;
    }

    public void setPriceFormat(PriceFormatBean priceFormat) {
        this.priceFormat = priceFormat;
    }

    public String getIncludeTax() {
        return includeTax;
    }

    public void setIncludeTax(String includeTax) {
        this.includeTax = includeTax;
    }

    public boolean isShowIncludeTax() {
        return showIncludeTax;
    }

    public void setShowIncludeTax(boolean showIncludeTax) {
        this.showIncludeTax = showIncludeTax;
    }

    public boolean isShowBothPrices() {
        return showBothPrices;
    }

    public void setShowBothPrices(boolean showBothPrices) {
        this.showBothPrices = showBothPrices;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductOldPrice() {
        return productOldPrice;
    }

    public void setProductOldPrice(int productOldPrice) {
        this.productOldPrice = productOldPrice;
    }

    public int getPriceInclTax() {
        return priceInclTax;
    }

    public void setPriceInclTax(int priceInclTax) {
        this.priceInclTax = priceInclTax;
    }

    public int getPriceExclTax() {
        return priceExclTax;
    }

    public void setPriceExclTax(int priceExclTax) {
        this.priceExclTax = priceExclTax;
    }

    public int getSkipCalculate() {
        return skipCalculate;
    }

    public void setSkipCalculate(int skipCalculate) {
        this.skipCalculate = skipCalculate;
    }

    public int getDefaultTax() {
        return defaultTax;
    }

    public void setDefaultTax(int defaultTax) {
        this.defaultTax = defaultTax;
    }

    public int getCurrentTax() {
        return currentTax;
    }

    public void setCurrentTax(int currentTax) {
        this.currentTax = currentTax;
    }

    public String getIdSuffix() {
        return idSuffix;
    }

    public void setIdSuffix(String idSuffix) {
        this.idSuffix = idSuffix;
    }

    public int getOldPlusDisposition() {
        return oldPlusDisposition;
    }

    public void setOldPlusDisposition(int oldPlusDisposition) {
        this.oldPlusDisposition = oldPlusDisposition;
    }

    public int getPlusDisposition() {
        return plusDisposition;
    }

    public void setPlusDisposition(int plusDisposition) {
        this.plusDisposition = plusDisposition;
    }

    public int getOldMinusDisposition() {
        return oldMinusDisposition;
    }

    public void setOldMinusDisposition(int oldMinusDisposition) {
        this.oldMinusDisposition = oldMinusDisposition;
    }

    public int getMinusDisposition() {
        return minusDisposition;
    }

    public void setMinusDisposition(int minusDisposition) {
        this.minusDisposition = minusDisposition;
    }

    public static class PriceFormatBean {
        /**
         * pattern : €%s
         * precision : 2
         * requiredPrecision : 2
         * decimalSymbol : .
         * groupSymbol : ,
         * groupLength : 3
         * integerRequired : 1
         */

        private String pattern;
        private int precision;
        private int requiredPrecision;
        private String decimalSymbol;
        private String groupSymbol;
        private int groupLength;
        private int integerRequired;

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public int getPrecision() {
            return precision;
        }

        public void setPrecision(int precision) {
            this.precision = precision;
        }

        public int getRequiredPrecision() {
            return requiredPrecision;
        }

        public void setRequiredPrecision(int requiredPrecision) {
            this.requiredPrecision = requiredPrecision;
        }

        public String getDecimalSymbol() {
            return decimalSymbol;
        }

        public void setDecimalSymbol(String decimalSymbol) {
            this.decimalSymbol = decimalSymbol;
        }

        public String getGroupSymbol() {
            return groupSymbol;
        }

        public void setGroupSymbol(String groupSymbol) {
            this.groupSymbol = groupSymbol;
        }

        public int getGroupLength() {
            return groupLength;
        }

        public void setGroupLength(int groupLength) {
            this.groupLength = groupLength;
        }

        public int getIntegerRequired() {
            return integerRequired;
        }

        public void setIntegerRequired(int integerRequired) {
            this.integerRequired = integerRequired;
        }
    }
}
