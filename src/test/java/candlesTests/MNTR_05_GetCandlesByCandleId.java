package candlesTests;

import database.candles.dao.CandlesDaoImpl;
import delegats.CalculateTR_new;
import delegats.candles.GetCandles;
import delegats.candles.ParseCandle;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;

@Epic("Get history data test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MNTR_05_GetCandlesByCandleId {

    private int sizeOfRowsInTableCandles;
    private int dateToInit = 5;
    private int dateFromInit = 0;
    private final String FIGI = "BBG00178PGX3"; // VCKO
    private GetCandles getCandles;
    private ParseCandle parseCandle;
    private CandlesDaoImpl candlesDaoImpl;
    private GetCandlesResponse candlesResponse;
    private CalculateTR_new calculateTR_new;

    @BeforeAll
    void init() {
        getCandles = new GetCandles();
        parseCandle = new ParseCandle();
        candlesDaoImpl = new CandlesDaoImpl();
        calculateTR_new = new CalculateTR_new();
        candlesDaoImpl.dropTable();
        candlesDaoImpl.createTable();
    }

    @Test
    @DisplayName("Get candles test")
    public void testCandlesTest() throws IOException {
        Allure.step("Шаг 1. Получить набор свечек за 5-дневный период в трёх итерациях", () -> {
            int helper = 1;
            for (int j = 0; j <= 2; j++) {

                candlesResponse = getCandles.getCandles(FIGI, dateToInit, dateFromInit);

                for (int i = candlesResponse.getCandles().size() - helper; i >= 0; i--) {
                    parseCandle.parseCandles(candlesResponse, i);
                    candlesDaoImpl.insertCandle(parseCandle.getTime()
                            , parseCandle.getHigh()
                            , parseCandle.getLow()
                            , parseCandle.getOpen()
                            , parseCandle.getClose()
                            , parseCandle.getVolume()
                            , parseCandle.is_complete());
                }

                dateFromInit = dateFromInit + 5;
                dateToInit = dateToInit + 5;
                helper = 2;
            }
        });

        Allure.step("Шаг 2. Получить свечку по candle_id", () -> {
            System.out.println(candlesDaoImpl.selectCandleByCandleId(10));
        });

        Allure.step("Шаг 3. Определить кол-во строк в таблице candles", () -> {
            sizeOfRowsInTableCandles = candlesDaoImpl.getSizeOfTableCandles();
            System.out.println("Кол-во строчек в таблице candles: " + sizeOfRowsInTableCandles);
        });

        Allure.step("Шаг 4. Определить истинный диапазон (True Range, TR)", () -> {
            calculateTR_new.calculateTrueRange(sizeOfRowsInTableCandles);
        });
    }
}
