package service.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод изменения выставленной заявки.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class ReplaceSandboxOrderRequest {
}
