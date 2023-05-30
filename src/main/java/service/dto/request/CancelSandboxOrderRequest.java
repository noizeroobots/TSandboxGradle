package service.dto.request;


import lombok.Builder;
import lombok.Data;

/**
 * Метод отмены торгового поручения в песочнице.
 * https://invest-public-api.tinkoff.ru/rest//tinkoff.public.invest.api.contract.v1.SandboxService/CancelSandboxOrder
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class CancelSandboxOrderRequest {

    @Builder.Default
    String accountId = "accountId";

    @Builder.Default
    String orderId = "orderId";
}
