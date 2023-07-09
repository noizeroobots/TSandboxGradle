package otherTests;

import algorithms.SuperTrendIndicator;
import config.BaseTest;
import delegats.CalculateTR;
import delegats.candles.ConvertCandlesToDoubleArrays;
import enums.Candle_Interval;
import helper.BodyGenerator;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import service.marketDataService.dto.request.GetCandlesRequest;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Epic("Connection test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SuperTrendIndicatorTest extends BaseTest {

    //private final String FIGI = "BBG004S68507";
    private final String FIGI = "BBG004S68598"; // KMAZ
    BarSeries series;
    SuperTrendIndicator superTrendIndicator;


    @BeforeAll
    void setUp() {
        init();
        series = new BaseBarSeriesBuilder().build();
        BaseTest baseTest = new BaseTest();
        CalculateTR calculateTR = new CalculateTR();
        ConvertCandlesToDoubleArrays convertCandlesToDoubleArrays = new ConvertCandlesToDoubleArrays();

    }

    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR)")
    public void returnListDoubleTrueRange() throws IOException {
        String dateFrom = LocalDateTime.now().minusDays(6).toString();
        String dateTo = LocalDateTime.now().toString();

        String dateFromEd = dateFrom.substring(0, dateFrom.length() - 6) + "Z";
        String dateToEd = dateTo.substring(0, dateTo.length() - 6) + "Z";

        GetCandlesRequest body = BodyGenerator.getCandles()
                .withFigi(FIGI)
                .withFrom(dateFromEd)
                .withTo(dateToEd)
                .withInterval(Candle_Interval.CANDLE_INTERVAL_HOUR)
                .please();

        GetCandlesResponse candles = marketDataServiceClient.getCandles(body);

        double open;
        double high;
        double low;
        double close;
        double volume;
        String date;
        ZonedDateTime zonedDateTime;

        for (int i = 0; i < candles.getCandles().size(); i++) {
            open = combineUnitsAndNano(candles.getCandles().get(i).getOpen().getNano(), candles.getCandles().get(i).getOpen().getUnits());
            high = combineUnitsAndNano(candles.getCandles().get(i).getHigh().getNano(), candles.getCandles().get(i).getHigh().getUnits());
            low = combineUnitsAndNano(candles.getCandles().get(i).getLow().getNano(), candles.getCandles().get(i).getLow().getUnits());
            close = combineUnitsAndNano(candles.getCandles().get(i).getClose().getNano(), candles.getCandles().get(i).getClose().getUnits());
            volume = Double.parseDouble(candles.getCandles().get(i).getVolume());
            date = candles.getCandles().get(i).getTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
            zonedDateTime = ZonedDateTime.parse(date, formatter);
            series.addBar(zonedDateTime, open, high, low, close, volume);
        }
        superTrendIndicator = new SuperTrendIndicator(series, 1.2, 10);
        for (int i = 1; i < candles.getCandles().size(); i++) {
            String date1 = candles.getCandles().get(i).getTime();
            System.out.println(superTrendIndicator.getSignal(i) + " | " + date1);
        }
    }
}
