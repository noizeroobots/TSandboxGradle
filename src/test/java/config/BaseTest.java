package config;

import enums.Candle_Interval;
import enums.OrderDirection;
import enums.OrderType;
import helper.BodyGenerator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import service.dto.request.GetCandlesRequest;
import service.dto.request.PostSandboxOrderRequest;
import service.dto.response.candlesresponse.CandlesResponse;

import java.time.LocalDateTime;

@Slf4j
@Getter
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest extends ProcessSetup {

    public void testingCandlesTest(final String FIGI) {
        init();
        String dateFrom = LocalDateTime.now().minusDays(1).toString();
        String dateTo = LocalDateTime.now().toString();
        String dateFromEd = dateFrom.substring(0, dateFrom.length() - 6) + "Z";
        String dateToEd = dateTo.substring(0, dateTo.length() - 6) + "Z";

        GetCandlesRequest body = BodyGenerator.getCandles()
                .withFigi(FIGI)
                .withFrom(dateFromEd)
                .withTo(dateToEd)
                .withInterval(Candle_Interval.CANDLE_INTERVAL_3_MIN)
                .withInstrument_id(FIGI)
                .please();


        CandlesResponse candles = sandBoxClient.getCandles(body);
        System.out.println("Количество свечек - " + candles.getCandles().size());
        for (int i = 0; i <= candles.getCandles().size() - 1; i++) {
            String openUnits = candles.getCandles().get(i).getOpen().getUnits();
            String openNano = String.valueOf(candles.getCandles().get(i).getOpen().getNano());
            String closeUnits = candles.getCandles().get(i).getClose().getUnits();
            String closeNano = String.valueOf(candles.getCandles().get(i).getClose().getNano());
            String time = candles.getCandles().get(i).getTime();
            String open = findOpenCloseLevel.findPrice(openUnits, openNano);
            String close = findOpenCloseLevel.findPrice(closeUnits, closeNano);
            System.out.println("Open price: " + open + "; Close price: " + close + "; " + time);
        }
    }

    /**
     * Метод выставления торгового поручения в песочнице.
     */
    public void postSandboxOrder(String FIGI, String quantity, OrderDirection orderDirection, OrderType orderType, String accountId, String instrumentId) {
        init();
        PostSandboxOrderRequest body = BodyGenerator.getPostSandboxOrder()
                .withFigi(FIGI)
                .withQuantity(quantity)
                .withOrderType(orderType)
                .withDirection(orderDirection)
                .withAccountId(accountId)
                .withInstrumentId(FIGI)
                .please();
        sandBoxClient.postSandboxOrder(body);
    }

}
