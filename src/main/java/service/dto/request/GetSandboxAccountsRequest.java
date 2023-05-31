package service.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * Метод получения счетов в песочнице.
 * https://invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.SandboxService/GetSandboxAccounts
 */
@Data
@JsonIgnoreProperties
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetSandboxAccountsRequest {
}
