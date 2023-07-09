package calculateSMA;

import config.BaseTest;
import enums.Candle_Interval;
import helper.BodyGenerator;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.marketDataService.dto.request.GetCandlesRequest;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@Epic("Get history data test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculateSMA extends BaseTest {

    @BeforeAll
    void setUp() {
        init();
        BaseTest baseTest = new BaseTest();
    }

    @Test
    @DisplayName("Вытащить данные цен закрытия свечек за 9 дней на часовых свечках")
    public void testCandlesTest() throws IOException {
        // YNDX
        String FIGI = "TCSS09805522";
        GetCandlesRequest body = BodyGenerator.getCandles()
                .withFigi(FIGI)
                .withFrom(convertData.convertData(LocalDateTime.now().minusHours(6).toString()))
                .withTo(convertData.convertData(LocalDateTime.now().minusHours(5).toString()))
                .withInterval(Candle_Interval.CANDLE_INTERVAL_HOUR)
                .withInstrument_id(FIGI)
                .please();

/**
 *  "from": "2023-06-02T22:33:27.798Z",
 *   "to": "2023-06-02T22:33:27.798Z",
 *
 *
 * { //проверить утром
 *   "figi": "TCSS09805522",
 *   "from": "2023-06-02T10:33:27.798Z",
 *   "to": "2023-06-02T15:33:27.798Z",
 *   "interval": "CANDLE_INTERVAL_1_MIN",
 *   "instrument_id": ""
 *
 * }
 */
        GetCandlesResponse candles = marketDataServiceClient.getCandles(body);
        System.out.println(candles.getCandles().size());
    }
}
