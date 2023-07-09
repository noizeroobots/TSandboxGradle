package delegats.candles;

import lombok.Data;
import service.marketDataService.dto.response.GetCandlesResponse;

@Data
public class ParseCandle {

    private String time;
    private boolean is_complete;
    private double volume;
    private double high;
    private double low;
    private double open;
    private double close;

    /**
     * Метод преобразует свечку в набор его атрибутов
     */
    public void parseCandles(GetCandlesResponse candlesResponse, int index) {
        time = candlesResponse.getCandles().get(index).getTime().toString();
        is_complete = candlesResponse.getCandles().get(index).getIsComplete();
        volume = Double.parseDouble(candlesResponse.getCandles().get(index).getVolume());
        high = combineUnitsAndNano(candlesResponse.getCandles().get(index).getHigh().getNano(), candlesResponse.getCandles().get(index).getHigh().getUnits());
        low = combineUnitsAndNano(candlesResponse.getCandles().get(index).getLow().getNano(), candlesResponse.getCandles().get(index).getLow().getUnits());
        open = combineUnitsAndNano(candlesResponse.getCandles().get(index).getOpen().getNano(), candlesResponse.getCandles().get(index).getOpen().getUnits());
        close = combineUnitsAndNano(candlesResponse.getCandles().get(index).getClose().getNano(), candlesResponse.getCandles().get(index).getClose().getUnits());
    }

    public static double combineUnitsAndNano(int nano, String units) {
        String closeNanoString = String.valueOf(nano);
        String closeS = units + "." + closeNanoString;
        return Double.parseDouble(closeS);
    }
}
