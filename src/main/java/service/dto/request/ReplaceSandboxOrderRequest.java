package service.dto.request;

import enums.PriceType;
import helper.Helper;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Метод изменения выставленной заявки.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class ReplaceSandboxOrderRequest {
    /**
     * Номер счёта
     */
    @Builder.Default
    String accountId = "accountId";

    /**
     * Идентификатор запроса выставления поручения для целей идемпотентности в формате UID. Максимальная длина 36 символов.
     */
    @Builder.Default
    UUID orderId = Helper.getRandomUUID();

    /**
     * Время жизни ключа идемпотентности на стороне брокера
     */
    @Builder.Default
    String idempotencyKey = Helper.getRandomUUID().toString();

    /**
     * Количество лотов.
     */
    @Builder.Default
    String quantity = "string";

    @Builder.Default
    private Price price = Price.builder().build();

    /**
     * Цена за 1 инструмент. Для получения стоимости лота требуется умножить на лотность инструмента. Игнорируется для рыночных поручений.
     */
    @Data
    @Builder
    public static class Price {

        /**
         * целая часть суммы, может быть отрицательным числом
         */
        @Builder.Default
        Integer nano = 605;

        /**
         * дробная часть суммы, может быть отрицательным числом
         */
        @Builder.Default
        String units = "100";
    }

    /**
     * Количество лотов.
     */
    @Builder.Default
    PriceType priceType = PriceType.PRICE_TYPE_CURRENCY;

}
