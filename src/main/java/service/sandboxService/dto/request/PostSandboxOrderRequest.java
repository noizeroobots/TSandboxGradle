package service.sandboxService.dto.request;

import enums.OrderDirection;
import enums.OrderType;
import helper.Helper;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static enums.OrderDirection.ORDER_DIRECTION_UNSPECIFIED;
import static enums.OrderType.ORDER_TYPE_UNSPECIFIED;

/**
 * Метод выставления торгового поручения в песочнице.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class PostSandboxOrderRequest {

    /**
     * Deprecated Figi-идентификатор инструмента. Необходимо использовать instrument_id.
     */
    @Builder.Default
    String figi = "string";

    /**
     * Количество лотов.
     */
    @Builder.Default
    String quantity = "string";

//    @Builder.Default
//    private Price price = Price.builder().build();
//
//    /**
//     * Цена за 1 инструмент. Для получения стоимости лота требуется умножить на лотность инструмента. Игнорируется для рыночных поручений.
//     */
//    @Data
//    @Builder
//    public static class Price {
//
//        /**
//         * целая часть суммы, может быть отрицательным числом
//         */
//        @Builder.Default
//        Integer nano = 605;
//
//        /**
//         * дробная часть суммы, может быть отрицательным числом
//         */
//        @Builder.Default
//        String units = "100";
//    }

    /**
     * Направление операции.
     */
    @Builder.Default
    OrderDirection direction = ORDER_DIRECTION_UNSPECIFIED;

    /**
     * Номер счёта.
     */
    @Builder.Default
    String accountId = "string";

    /**
     * Тип заявки.
     */
    @Builder.Default
    OrderType orderType = ORDER_TYPE_UNSPECIFIED;

    /**
     * Идентификатор запроса выставления поручения для целей идемпотентности в формате UID. Максимальная длина 36 символов.
     */
    @Builder.Default
    UUID orderId = Helper.getRandomUUID();

    /**
     * Идентификатор инструмента, принимает значения Figi или Instrument_uid.
     */
    @Builder.Default
    String instrumentId = "string";
}
