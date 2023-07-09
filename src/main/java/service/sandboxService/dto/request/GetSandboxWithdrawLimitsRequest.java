package service.sandboxService.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод получения доступного остатка для вывода средств в песочнице.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetSandboxWithdrawLimitsRequest {
}
