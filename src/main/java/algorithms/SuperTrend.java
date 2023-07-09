package algorithms;

public class SuperTrend {

        private final double[] HIGH;
        private final double[] LOW;
        private final double[] CLOSE;
        private final int PERIOD;

        private final double MULTIPLIER;
        private final double ATR_FACTOR;

        private final double[] BASIC_UPPER;
        private final double[] BASIC_LOWER;

        private final double[] FINAL_UPPER;
        private final double[] FINAL_LOWER;

        private final double[] SUPERTREND;

        public SuperTrend(double[] high, double[] low, double[] close, int period, double multiplier, double atrFactor, String time) {
            this.HIGH = high;
            this.LOW = low;
            this.CLOSE = close;
            this.PERIOD = period;
            this.MULTIPLIER = multiplier;
            this.ATR_FACTOR = atrFactor;

            this.BASIC_UPPER = new double[high.length];
            this.BASIC_LOWER = new double[low.length];
            this.FINAL_UPPER = new double[high.length];
            this.FINAL_LOWER = new double[low.length];
            this.SUPERTREND = new double[close.length];

            calculateBasicBands();
            calculateFinalBands();
            calculateSupertrend(time);
        }

        private void calculateBasicBands() {
            for (int i = 0; i < BASIC_UPPER.length -1; i++) {
                double highValue = HIGH[i];
                double lowValue = LOW[i];

                BASIC_UPPER[i] = (highValue + lowValue) / 2.0 + MULTIPLIER * calculateTrueRange(i);
                BASIC_LOWER[i] = (highValue + lowValue) / 2.0 - MULTIPLIER * calculateTrueRange(i);
            }
        }

        private void calculateFinalBands() {
            for (int i = 0; i < FINAL_UPPER.length; i++) {
                if (i < PERIOD) {
                    FINAL_UPPER[i] = 0.0;
                    FINAL_LOWER[i] = 0.0;
                } else {
                    FINAL_UPPER[i] = (BASIC_UPPER[i] + FINAL_UPPER[i - 1]) / 2.0;
                    FINAL_LOWER[i] = (BASIC_LOWER[i] + FINAL_LOWER[i - 1]) / 2.0;
                }
            }
        }

        private void calculateSupertrend(String time) {
            for (int i = 0; i < SUPERTREND.length; i++) {
                if (CLOSE[i] <= FINAL_UPPER[i]) {
                    SUPERTREND[i] = FINAL_UPPER[i];

                } else {
                    SUPERTREND[i] = FINAL_LOWER[i];
                    System.out.println("\thigh - " + time);
                }
            }
        }

        private double calculateTrueRange(int index) {
            double highLowRange = HIGH[index] - LOW[index];
            double highCloseRange = Math.abs(HIGH[index] - CLOSE[index - 1]);
            double lowCloseRange = Math.abs(LOW[index] - CLOSE[index - 1]);

            return Math.max(highLowRange, Math.max(highCloseRange, lowCloseRange)) * ATR_FACTOR;
        }

        public double[] getSupertrend() {
            return SUPERTREND;
        }
    }

