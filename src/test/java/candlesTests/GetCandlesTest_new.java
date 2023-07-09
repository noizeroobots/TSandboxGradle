package candlesTests;

import database.candles.dao.CandlesDaoImpl;
import database.candles.entity.Candles;
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
public class GetCandlesTest_new {

    private final int dateToInit = 3;
    private final int dateFromInit = 0;
    private final String FIGI = "BBG00178PGX3"; // VCKO
    private Candles candles;
    private GetCandles getCandles;
    private ParseCandle parseCandle;
    private CandlesDaoImpl candlesDaoImpl;
    private GetCandlesResponse candlesResponse;

    @BeforeAll
    void init() {
        candles = new Candles();
        getCandles = new GetCandles();
        parseCandle = new ParseCandle();
        candlesDaoImpl = new CandlesDaoImpl();
    }

    @Test
    @DisplayName("Get candles test")
    public void testCandlesTest() throws IOException {
        Allure.step("Шаг 1. Получить свечки за определённый период", () -> {
            candlesResponse = getCandles.getCandles(FIGI, dateToInit, dateFromInit);
        });

        Allure.step("Шаг 2. Получить первую свечку и отобразить его атрибуты", () -> {
            parseCandle.parseCandles(candlesResponse, 1);
        });

        Allure.step("Шаг 3. Получить первую свечку и положить её в БД", () -> {
            candlesDaoImpl.insertCandle(parseCandle.getTime()
                    , parseCandle.getHigh()
                    , parseCandle.getLow()
                    , parseCandle.getOpen()
                    , parseCandle.getClose()
                    , parseCandle.getVolume()
                    , parseCandle.is_complete());
        });
    }
}
