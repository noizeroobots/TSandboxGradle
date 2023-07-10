package candlesTests;

import database.candles.dao.CandlesDaoImpl;
import delegats.candles.GetCandles;
import delegats.candles.ParseCandle;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;

@Epic("Get history data test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MNTR_04_GetCandlesTest_in_five_days_cycle {

    private int dateToInit = 5;
    private int dateFromInit = 0;
    private final String FIGI = "BBG00178PGX3"; // VCKO
    private GetCandles getCandles;
    private ParseCandle parseCandle;
    private CandlesDaoImpl candlesDaoImpl;
    private GetCandlesResponse candlesResponse;

    @BeforeAll
    void init() {
        getCandles = new GetCandles();
        parseCandle = new ParseCandle();
        candlesDaoImpl = new CandlesDaoImpl();
        candlesDaoImpl.dropTable();
        candlesDaoImpl.createTable();
    }

    @AfterAll
    void after() {
        //candlesDaoImpl.deleteDuplicateRows();
    }

    @Test
    @DisplayName("Get candles test")
    public void testCandlesTest() throws IOException {
        Allure.step("Шаг 1. Получить набор свечек за 5-дневный период в трёх итерациях", () -> {
            int helper = 1;
            for (int j = 0; j <= 50; j++) {

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
    }
}
