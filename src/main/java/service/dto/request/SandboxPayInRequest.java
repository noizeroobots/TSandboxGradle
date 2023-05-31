package service.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод пополнения счёта в песочнице.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class SandboxPayInRequest {

    /**
     * Номер счёта
     */
    @Builder.Default
    String accountId = "accountId";

    /**
     * Сумма пополнения счёта в рублях
     */
    @Builder.Default
    private Amount amount = Amount.builder().build();

    /**
     * Денежная сумма в определенной валюте
     */
    @Data
    @Builder
    public static class Amount {

        /**
         * Строковый ISO-код валюты
         */
        @Builder.Default
        String currency = "RUB";

        /**
         * Целая часть суммы, может быть отрицательным числом
         */
        @Builder.Default
        Integer units = 100;

        /**
         * Дробная часть суммы, может быть отрицательным числом
         */
        @Builder.Default
        Integer nano = 100;
    }

}
