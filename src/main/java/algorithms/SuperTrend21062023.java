package algorithms;

import delegats.candles.ConvertCandlesToDoubleArrays;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SuperTrend21062023 {

    private ConvertCandlesToDoubleArrays convertCandlesToDoubleArrays;
    private String FIGI = "BBG00178PGX3"; // VCKO
    GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(3, FIGI);

    List<Double> closePrices = convertCandlesToDoubleArrays.getCloseList(candlesResponse); // список значений цены закрытия
    List<Double> highPrices = convertCandlesToDoubleArrays.getHighList(candlesResponse); // список значений максимальной цены
    List<Double> lowPrices = convertCandlesToDoubleArrays.getLowList(candlesResponse); // список значений минимальной цены
    List<Double> trueRange = new ArrayList<>() ; // список значений true range
    int period = 10 ; // период для расчетов
    double multiplier = 3 ; // множитель для расчета верхней и нижней линий SuperTrend
    List<Double> superTrendUpper = new ArrayList<>();
    List<Double> superTrendLower = new ArrayList<>();

    public void testSuperTrend() {
    for (int i = period; i < closePrices.size(); i++) {
        double high = highPrices.get(i);
        double low = lowPrices.get(i);
        double previousClose = closePrices.get(i - 1);
        double trueRangeValue = Math.max((high - low),
                Math.max(Math.abs(high - previousClose)
                        , Math.abs(low - previousClose)));
        trueRange.add(trueRangeValue);

        if (i == period) {
            double sum = 0;
            for (int j = 0; j < period; j++) {
                sum += trueRange.get(j);
            }
            double averageTrueRange = sum / period;
            superTrendUpper.add((high + low) / 2 + multiplier * averageTrueRange);
            superTrendLower.add((high + low) / 2 - multiplier * averageTrueRange);
        } else {
            double previousSuperTrendUpper = superTrendUpper.get(superTrendUpper.size() - 1);
            double previousSuperTrendLower = superTrendLower.get(superTrendLower.size() - 1);

            if (closePrices.get(i) <= previousSuperTrendUpper) {
                double superTrendValue = previousSuperTrendUpper;
                superTrendUpper.add(superTrendValue);
                superTrendLower.add(superTrendLower.get(superTrendLower.size() - 1));
            } else {
                double sum = 0;
                for (int j = i - period; j < i; j++) {
                    sum += trueRange.get(j);
                }
                double averageTrueRange = sum / period;
                double currentSuperTrendUpper = (high + low) / 2 + multiplier * averageTrueRange;
                superTrendUpper.add(currentSuperTrendUpper);

                if (currentSuperTrendUpper <= previousSuperTrendLower) {
                    superTrendLower.add(currentSuperTrendUpper);
                } else {
                    superTrendLower.add(previousSuperTrendLower);
                }
            }
        }
    }}

    public SuperTrend21062023() throws IOException {
    }
}
