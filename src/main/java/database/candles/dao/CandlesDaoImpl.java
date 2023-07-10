package database.candles.dao;

import database.candles.JdbiConnection;
import database.candles.entity.Candles;
import io.qameta.allure.Step;

public class CandlesDaoImpl {

    private final CandlesDao candlesDao = JdbiConnection
            .getConnection()
            .getJdbi()
            .onDemand(CandlesDao.class);

    @Step("Удалить candle из БД по его time")
    public void deleteCandle(final String time) {
        candlesDao.deleteCandle(time);
    }

    @Step("Создать таблицу candle в БД")
    public void createTable() {
        candlesDao.createTable();
    }

    @Step("Удалить таблицу candle из БД")
    public void dropTable() {
        candlesDao.dropTableCandles();
    }

    @Step("Добавить свечку candle в БД")
    public void insertCandle(String time, double high, double low, double open, double close, double volume, boolean is_complete) {
        candlesDao.insertCandle(time, high, low, open, close, volume, is_complete);
    }

    @Step("Получить свечку по time")
    public Candles selectCandle(String time) {
        return candlesDao.findCandleByTime(time);
    }

    @Step("Удалить дублирующиеся строки в таблице candles")
    public void deleteDuplicateRows() {
        candlesDao.deleteDuplicateRows();
    }
}
