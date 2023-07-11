package delegats;

import database.candles.dao.CandlesDaoImpl;
import database.candles.entity.Candles;

public class CalculateTR_new {

    private final CandlesDaoImpl candlesDaoImpl = new CandlesDaoImpl();
    private Integer sizeOfTableCandles;

    /**
     * Определение истинного диапазона (True Range, TR)
     * Возвращает List Double для дальнейшей работы
     */
    public void calculateTrueRange(int sizeOfTableCandles) {

        for (int i = sizeOfTableCandles; i >= 2; i--) {

            Candles candle_first = candlesDaoImpl.selectCandleByCandleId(sizeOfTableCandles);
            Candles candle_second = candlesDaoImpl.selectCandleByCandleId(sizeOfTableCandles - 1);

            double trElement = Math.max((Double.parseDouble(candle_second.getHigh()) - Double.parseDouble(candle_second.getLow()))
                    , Math.max(Math.abs(Double.parseDouble(candle_second.getHigh()) - Double.parseDouble(candle_first.getClose()))
                            , Math.abs(Double.parseDouble(candle_second.getLow()) - Double.parseDouble(candle_first.getClose()))));
            System.out.println("i = " + i + "; " + trElement);
            sizeOfTableCandles = sizeOfTableCandles - 1;
        }
    }
}
