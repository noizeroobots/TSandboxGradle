package service.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод регистрации счёта в песочнице.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class OpenSandboxAccountRequest {
}
