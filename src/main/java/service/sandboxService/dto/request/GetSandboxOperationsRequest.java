package service.sandboxService.dto.request;

import enums.OperationState;
import lombok.Builder;
import lombok.Data;

/**
 * Метод получения операций в песочнице по номеру счёта.
 * https://invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.SandboxService/GetSandboxAccounts
 */
@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
public class GetSandboxOperationsRequest {

    /**
     * Deprecated Figi-идентификатор инструмента. Необходимо использовать instrument_id.
     */
    @Builder.Default
    String figi = "BBG004S68507";

    /**
     * Начало запрашиваемого периода в часовом поясе UTC.
     */
    @Builder.Default
    String from = "2023-05-25T12:00:00.015Z";

    /**
     * Окончание запрашиваемого периода в часовом поясе UTC.
     */
    @Builder.Default
    String to = "2023-05-25T12:00:20.015Z";

    /**
     * Интервал запрошенных свечей.
     */
    @Builder.Default
    OperationState state = OperationState.OPERATION_STATE_EXECUTED;

    /**
     * Идентификатор счёта клиента.
     */
    @Builder.Default
    String accountId = "62c0b4fd-6af6-4db0-9367-d7dbbb295b82";
}
