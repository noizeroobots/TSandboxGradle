package algorithms;

import java.util.ArrayList;
import java.util.List;

public class SuperTrendOriginal {
    private final double[] HIGH;
    private final double[] LOW;
    private final double[] CLOSE;
    private final int PERIOD;
    private final String[] TIME;

    private final double MULTIPLIER;
    //private final double ATR_FACTOR;

    private final double[] BASIC_UPPER;
    private final double[] BASIC_LOWER;

    private final double[] FINAL_UPPER;
    private final double[] FINAL_LOWER;

    private final double[] SUPERTREND;

    public SuperTrendOriginal(double[] high, double[] low, double[] close, int period, double multiplier, String[] time) {
        this.HIGH = high;
        this.LOW = low;
        this.CLOSE = close;
        this.PERIOD = period;
        this.MULTIPLIER = multiplier;
//        this.ATR_FACTOR = atrFactor;
        this.TIME = time;

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
        for (int i = 1; i < BASIC_UPPER.length; i++) {
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

    private void calculateSupertrend(String[] time) {
        for (int i = 0; i < SUPERTREND.length; i++) {
            if (CLOSE[i] >= FINAL_UPPER[i]) {
                SUPERTREND[i] = FINAL_UPPER[i];
                System.out.println("BUY | " + time[i]);
            } else {
                SUPERTREND[i] = FINAL_LOWER[i];
                System.out.println("  SELL | " + time[i]);
            }
        }
    }

    private double calculateTrueRange(int index) {
        return Math.max((HIGH[index] - LOW[index]),
                Math.max(Math.abs(HIGH[index] - CLOSE[index - 1]), Math.abs(LOW[index] - CLOSE[index - 1])));

    }

    public double[] getSupertrend() {
        return SUPERTREND;
    }
}
