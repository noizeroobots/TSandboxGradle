package service.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Метод получения позиций по виртуальному счёту песочницы.
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetSandboxPositionsRequest {

    @Builder.Default
    String accountId = "accountId";
}
