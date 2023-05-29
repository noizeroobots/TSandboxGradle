package service.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод получения основной информации об инструменте.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetInstumentByRequest {

    /**
     * Тип идентификатора инструмента. Возможные значения: figi, ticker.
     */
    @Builder.Default
    String id_type = "POLY";

    /**
     * Идентификатор class_code. Обязателен при id_type = ticker.
     */
    @Builder.Default
    String class_code = "RX";

    /**
     * Идентификатор запрашиваемого инструмента.
     */
    @Builder.Default
    String id = "RUB";
}