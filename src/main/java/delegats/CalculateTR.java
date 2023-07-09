package delegats;

import delegats.candles.ConvertCandlesToDoubleArrays;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Определение истинного диапазона (True Range, TR)
 */
public class CalculateTR {

    private final ConvertCandlesToDoubleArrays convertCandlesToDoubleArrays = new ConvertCandlesToDoubleArrays();

    /**
     * Определение истинного диапазона (True Range, TR)
     * Возвращает List стрингов для лучшего визуального воспиятия
     */
    public List<String> calculateTrueRangeReturnStringList(GetCandlesResponse candlesResponse) throws IOException {
        double[] high = convertCandlesToDoubleArrays.getHigh(candlesResponse);
        double[] low = convertCandlesToDoubleArrays.getLow(candlesResponse);
        double[] prevClose = convertCandlesToDoubleArrays.getClose(candlesResponse);
        List<String> trWithData = new ArrayList<>();
        for (int i = 1; i < candlesResponse.getCandles().size(); i++) {
            double trElement = Math.max((high[i] - low[i]),
                    Math.max(Math.abs(high[i] - prevClose[i - 1]), Math.abs(low[i] - prevClose[i - 1])));
            trWithData.add(String.format("%.3f", trElement).replace(",", ".") + " | " + candlesResponse.getCandles().get(i).getTime());
        }
        return trWithData;
    }

    /**
     * Определение истинного диапазона (True Range, TR)
     * Возвращает List Double для дальнейшей работы
     */
    public List<Double> calculateTrueRangeReturnDoubleList(GetCandlesResponse candlesResponse) throws IOException {
        double[] high = convertCandlesToDoubleArrays.getHigh(candlesResponse);
        double[] low = convertCandlesToDoubleArrays.getLow(candlesResponse);
        double[] prevClose = convertCandlesToDoubleArrays.getClose(candlesResponse);
        List<Double> tr = new ArrayList<>();
        for (int i = 1; i < candlesResponse.getCandles().size(); i++) {
            double trElement = Math.max((high[i] - low[i]),
                    Math.max(Math.abs(high[i] - prevClose[i - 1]), Math.abs(low[i] - prevClose[i - 1])));
            String resBuffer = String.format("%.3f", trElement).replace(",", ".");
            tr.add(Double.parseDouble(resBuffer));
        }
        return tr;
    }

    /**
     * Определение истинного диапазона (True Range, TR)
     * Возвращает массив Double для дальнейшей работы
     */
    public double[] calculateTrueRangeReturnDoubleArray(GetCandlesResponse candlesResponse) throws IOException {
        double[] high = convertCandlesToDoubleArrays.getHigh(candlesResponse);
        double[] low = convertCandlesToDoubleArrays.getLow(candlesResponse);
        double[] prevClose = convertCandlesToDoubleArrays.getClose(candlesResponse);
        double[] tr = new double[candlesResponse.getCandles().size()];
        for (int i = 1; i < candlesResponse.getCandles().size(); i++) {
            double trElement = Math.max((high[i] - low[i]),
                    Math.max(Math.abs(high[i] - prevClose[i - 1]), Math.abs(low[i] - prevClose[i - 1])));
            tr[i] = trElement;
        }
        return tr;
    }
}
