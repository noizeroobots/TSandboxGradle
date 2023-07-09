package delegats;

import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;
import java.util.Arrays;

public class CalculateATR {


        public  double calculateATR(GetCandlesResponse prices, int periods) throws IOException {
            double[] atrValues = new double[periods];

            for (int i = 1; i < periods; i++) {
                double trueRange = calculateTrueRange(prices, i);
                atrValues[i] = calculateATRValue(atrValues, trueRange, i, periods);
                System.out.println("atrValues: " + atrValues[i]);
            }

            return Arrays.stream(atrValues).reduce(0.0, Double::sum);
        }

        private static double calculateTrueRange(GetCandlesResponse prices, int index) throws IOException {
            double high = Double.parseDouble(prices.getCandles().get(index).getHigh().getUnits() + "." + prices.getCandles().get(index).getHigh().getNano());
            double low = Double.parseDouble(prices.getCandles().get(index).getLow().getUnits() + "." + prices.getCandles().get(index).getLow().getNano());
            double close = Double.parseDouble(prices.getCandles().get(index).getClose().getUnits() + "." + prices.getCandles().get(index).getClose().getNano());
            double previousClose = Double.parseDouble(prices.getCandles().get(index - 1).getClose().getUnits() + "." + prices.getCandles().get(index - 1).getClose().getNano());

            double highLowRange = high - low;
            double highCloseRange = Math.abs(high - previousClose);
            double lowCloseRange = Math.abs(low - previousClose);

            double trueRange = Math.max(highLowRange, Math.max(highCloseRange, lowCloseRange));
            return trueRange;
        }

        private static double calculateATRValue(double[] atrValues, double trueRange, int index, int periods) {
            if (index == 0) {
                return trueRange;
            } else {
                double sum = 0.0;
                for (int i = 0; i < periods; i++) {
                    sum += atrValues[i];
                }
                return (sum / periods) + (trueRange / periods);
            }
        }
    }

