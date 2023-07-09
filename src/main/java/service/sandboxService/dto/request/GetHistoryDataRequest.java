package service.sandboxService.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Получение исторических рыночных данных
 * https://tinkoff.github.io/investAPI/get_history/
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetHistoryDataRequest {

    /**
     * Год запроса истории
     */
    @Builder.Default
    Integer year = 2023;

    /**
     * Идентификатор инструмента, принимает значения Figi или instrument_uid
     */
    @Builder.Default
    String instrument_id = "RUB";

    /**
     * Figi-идентификатор инструмента по которому запрашивается история рыночных данных
     */
    @Builder.Default
    String figi = "RUB";
}
