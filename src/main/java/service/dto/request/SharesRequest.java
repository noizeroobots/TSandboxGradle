package service.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод получения списка акций.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class SharesRequest {

    /**
     * Базовый список инструментов
     */
    @Builder.Default
    Integer instrument_status = 1;
}
