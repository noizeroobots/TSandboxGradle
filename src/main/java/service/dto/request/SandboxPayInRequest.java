package service.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод пополнения счёта в песочнице.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class SandboxPayInRequest {
}
