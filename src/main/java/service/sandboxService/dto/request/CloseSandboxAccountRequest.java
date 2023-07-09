package service.sandboxService.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод закрытия счёта в песочнице.
 * https://invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.SandboxService/CloseSandboxAccount
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class CloseSandboxAccountRequest {

    @Builder.Default
    String accountId = "accountId";
}
