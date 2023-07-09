package service.marketDataService.dto.request;

import enums.Candle_Interval;
import lombok.Builder;
import lombok.Data;

import static enums.Candle_Interval.CANDLE_INTERVAL_1_MIN;

/**
 * Метод запроса исторических свечей по инструменту.
 * https://tinkoff.github.io/investAPI/swagger-ui/#/MarketDataService/MarketDataService_GetCandles
 * https://invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.MarketDataService/GetCandles
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetCandlesRequest {
    /**
     * Deprecated Figi-идентификатор инструмента. Необходимо использовать instrument_id.
     */
    @Builder.Default
    String figi = "BBG004S68507";

    /**
     * Начало запрашиваемого периода в часовом поясе UTC.
     */
    @Builder.Default
    String from = "2023-05-25T12:00:00.015Z";

    /**
     * Окончание запрашиваемого периода в часовом поясе UTC.
     */
    @Builder.Default
    String to = "2023-05-25T12:00:20.015Z";

    /**
     * Интервал запрошенных свечей.
     */
    @Builder.Default
    Candle_Interval interval = CANDLE_INTERVAL_1_MIN;

    /**
     * Идентификатор инструмента, принимает значение figi или instrument_uid.
     */
    @Builder.Default
    String instrument_id = "";
}
