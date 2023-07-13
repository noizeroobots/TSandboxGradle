package database.candles.dao.daoTR;

import database.candles.JdbiConnection;
import database.candles.entity.CandlesWithTrueRange;
import io.qameta.allure.Step;

public class CandlesDaoImplTR {

    private final CandlesDaoTR candlesDao = JdbiConnection
            .getConnection()
            .getJdbi()
            .onDemand(CandlesDaoTR.class);

    @Step("Определить кол-во строк в таблице candles candles_tr ")
    public Integer getSizeOfTableCandles() {
        return candlesDao.getSizeOfTableCandles();
    }

    @Step("Создать таблицу candles_tr в БД")
    public void createTableWithTrueRange() {
        candlesDao.createTableWithTrueRange();
    }

    @Step("Добавить свечку candles_tr в БД")
    public void insertCandleWithTrueRange(String time, double high, double low, double open, double close, double volume, boolean is_complete) {
        candlesDao.insertCandleWithTrueRange(time, high, low, open, close, volume, is_complete);
    }

    @Step("Удалить таблицу candles_tr из БД")
    public void dropTableCandlesTR() {
        candlesDao.dropTableWithTrueRange();
    }

    @Step("Обновить таблицу candles_tr вычисленными значениями TR")
    public void updateCandleTRWithTrueRange(double tr, int candle_id) {
        candlesDao.updateCandleTRWithTrueRange(tr, candle_id);
    }

    @Step("Получить свечку по candle_id из таблицы candles_tr")
    public CandlesWithTrueRange selectCandleByCandleIdWithTrueRange(int candle_id) {
        return candlesDao.selectCandleFromDBWithTrueRange(candle_id);
    }
}
