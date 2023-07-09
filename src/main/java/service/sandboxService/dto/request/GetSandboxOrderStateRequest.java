package service.sandboxService.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод получения статуса заявки в песочнице.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetSandboxOrderStateRequest {

    @Builder.Default
    String accountId = "accountId";

    @Builder.Default
    String orderId = "accountId";
}
