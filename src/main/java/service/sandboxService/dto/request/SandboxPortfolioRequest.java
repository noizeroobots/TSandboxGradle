package service.sandboxService.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class SandboxPortfolioRequest {

    @Builder.Default
    String accountId = "accountId";

    @Builder.Default
    String currency = "RUB";
}
