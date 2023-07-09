package candlesTests;

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
import java.util.ArrayList;
import java.util.List;

@Epic("Get history data test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetCandlesTest extends BaseTest {


    private final String FIGI = "BBG00178PGX3"; // VCKO

    @BeforeAll
    void setUp() {
        init();
        BaseTest baseTest = new BaseTest();
    }

    @Test
    @DisplayName("Get candles test")
    public void testCandlesTest() throws IOException {
        String dateFrom = LocalDateTime.now().minusDays(14).toString();
        String dateTo = LocalDateTime.now().minusDays(7).toString();

        String dateFromEd = dateFrom.substring(0, dateFrom.length() - 6) + "Z";
        String dateToEd = dateTo.substring(0, dateTo.length() - 6) + "Z";

        GetCandlesRequest body = BodyGenerator.getCandles()
                .withFigi(FIGI)
                .withFrom(dateFromEd)
                .withTo(dateToEd)
                .withInterval(Candle_Interval.CANDLE_INTERVAL_HOUR)
                .please();


        GetCandlesResponse candles = marketDataServiceClient.getCandles(body);
        System.out.println("Количество свечек = " + candles.getCandles().size());
        System.out.println("\n\n\n\n");
        String time;
        double open;
        double high;
        double low;
        double close;
        List<Double> highArray = new ArrayList<>();
        Double[] lowArray = new Double[candles.getCandles().size()];
        Double[] closeArray = new Double[candles.getCandles().size()];
        Double[] trueRangeArray = new Double[candles.getCandles().size() - 1];


        for (int i = 0; i < candles.getCandles().size(); i++) {
            time = candles.getCandles().get(i).getTime();
            open = combineUnitsAndNano(candles.getCandles().get(i).getOpen().getNano(), candles.getCandles().get(i).getOpen().getUnits());
            high = combineUnitsAndNano(candles.getCandles().get(i).getHigh().getNano(), candles.getCandles().get(i).getHigh().getUnits());
            low = combineUnitsAndNano(candles.getCandles().get(i).getLow().getNano(), candles.getCandles().get(i).getLow().getUnits());
            close = combineUnitsAndNano(candles.getCandles().get(i).getClose().getNano(), candles.getCandles().get(i).getClose().getUnits());
            highArray.add(high);
            lowArray[i] = low;
            closeArray[i] = close;
        }

        System.out.println("Количество свечек = " + candles.getCandles().size());
        System.out.println("Размер List highArray = " + highArray.size());
        System.out.println("Размер массива lowArray = " + lowArray.length);
        System.out.println("Размер массива closeArray = " + closeArray.length);


//        for (int i = 1; i < candles.getCandles().size(); i++) {
//
//            double trElement = Math.max((highArray[i] - lowArray[i]),
//                    Math.max(Math.abs(highArray[i] - closeArray[i - 1]), Math.abs(lowArray[i] - closeArray[i - 1])));
//            //System.out.println("True Range = " + trElement);
//            trueRangeArray[i - 1] = trElement;
//        }
//        for (int i = 0; i < trueRangeArray.length; i++) {
//            System.out.println(i + ") true range from trueRangeArray | " + trueRangeArray[i]);
//        }
//
//        for (int j = 0; j < trueRangeArray.length - 3; j++) {
//            double summ = 0;
//            for (int i = j; i < 10 + j; i++) {
//                summ += trueRangeArray[i];
//            }
//
//            double av = summ / 10;
//            System.out.println("Average = " + av);
//        }

//        List<String> ll = new ArrayList<>();
//        for (int i = 0; i < candles.getCandles().size(); i++) {
//            ll.add(candles.getCandles().get(i).getClose().getUnits() + "." + String.valueOf(candles.getCandles().get(i).getClose().getNano()));
//            System.out.println(candles.getCandles().get(i).getClose().getUnits() + "." + candles.getCandles().get(i).getClose().getNano());
//        }
//        System.out.println("Количество свечек: " + candles.getCandles().size());
//
//        double atr = calculateATR(candles, 13);
//        System.out.println(atr);

    }
}
