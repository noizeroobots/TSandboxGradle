package delegats.candles;

import config.ProcessSetup;
import enums.Candle_Interval;
import helper.BodyGenerator;
import service.marketDataService.dto.request.GetCandlesRequest;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.time.LocalDateTime;

public class GetCandles extends ProcessSetup {

    public GetCandlesResponse getCandles(String figi, int dateFromInit, int dateToInit) {
        init();
        String dateFrom = LocalDateTime.now().minusDays(dateFromInit).toString();
        String dateTo = LocalDateTime.now().minusDays(dateToInit).toString();

        String dateFromEd = dateFrom.substring(0, dateFrom.length() - 6) + "Z";
        String dateToEd = dateTo.substring(0, dateTo.length() - 6) + "Z";

        GetCandlesRequest body = BodyGenerator.getCandles()
                .withFigi(figi)
                .withFrom(dateFromEd)
                .withTo(dateToEd)
                .withInterval(Candle_Interval.CANDLE_INTERVAL_HOUR)
                .please();

        GetCandlesResponse candlesResponse = marketDataServiceClient.getCandles(body);
        System.out.println("Количество свечек = " + candlesResponse.getCandles().size());
        return candlesResponse;
    }
}
