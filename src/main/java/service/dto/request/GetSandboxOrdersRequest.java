package service.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод получения списка активных заявок по счёту в песочнице.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetSandboxOrdersRequest {
}
