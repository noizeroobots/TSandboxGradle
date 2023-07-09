package interactionWithDB;

import database.candles.dao.CandlesDaoImpl;
import database.candles.entity.Candles;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Epic("Work with database")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InteractionWithDBTest {

    private CandlesDaoImpl candlesDaoImpl;
    private Candles candles;

    @BeforeAll
    void init() {
        candles = new Candles();
        candlesDaoImpl = new CandlesDaoImpl();
    }

    @Test
    @Description("Добавление данных в таблицу Candles")
    void testInsertDataToCandles() {
        candlesDaoImpl.insertCandle("Mikhail", 639.0, 632.0, 632.0, 633.2, 1962, true);
    }

    @Test
    @Description("Удаление таблицы Candles из БД")
    void dropTableCandles() {
        candlesDaoImpl.dropTable();
    }

    @Test
    @Description("Создание таблицы Candles в БД")
    void createTableCandle() {
        candlesDaoImpl.createTable();
    }

    @Test
    @Description("Получить свечку по time")
    void selectCandle() {
        candles = candlesDaoImpl.selectCandle("Mikhail");
        System.out.println(candles);
    }
}