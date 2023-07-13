package delegats;

import database.candles.dao.CandlesDaoImpl;
import database.candles.dao.daoTR.CandlesDaoImplTR;
import database.candles.entity.Candles;
import database.candles.entity.CandlesWithTrueRange;

public class CalculateTR_new {

    private final CandlesDaoImplTR candlesDaoImplTR = new CandlesDaoImplTR();
    private final CandlesDaoImpl candlesDaoImpl = new CandlesDaoImpl();

    /**
     * Определение истинного диапазона (True Range, TR)
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

    /**
     * Определение истинного диапазона (True Range, TR)
     */
    public void calculateTrueRangeAndUpdateTable(int sizeOfTableCandles) {
        for (int i = 1; i <= sizeOfTableCandles - 1; i++) {

            CandlesWithTrueRange candle_first = candlesDaoImplTR.selectCandleByCandleIdWithTrueRange(i);
            CandlesWithTrueRange candle_second = candlesDaoImplTR.selectCandleByCandleIdWithTrueRange(i + 1);

            double trElement = Math.max((Double.parseDouble(candle_first.getHigh()) - Double.parseDouble(candle_first.getLow()))
                    , Math.max(Double.parseDouble(candle_first.getHigh()) - Double.parseDouble(candle_second.getClose())
                            , Double.parseDouble(candle_second.getClose()) - Double.parseDouble(candle_first.getLow())));
            candlesDaoImplTR.updateCandleTRWithTrueRange(trElement, candle_first.getCandle_id());
        }
    }
}
