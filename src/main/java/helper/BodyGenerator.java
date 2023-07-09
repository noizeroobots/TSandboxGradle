package helper;

import database.model.CandleModel;
import lombok.experimental.UtilityClass;
import service.marketDataService.dto.request.GetCandlesRequest;
import service.sandboxService.dto.request.*;

@UtilityClass
public class BodyGenerator {

    public static EmptyBodyRequest.EmptyBodyRequestBuilder getEmptyBody() {
        return EmptyBodyRequest.builder();
    }

    public static SandboxPortfolioRequest.SandboxPortfolioRequestBuilder getSandboxPortfolio() {
        return SandboxPortfolioRequest.builder();
    }

    /**
     * Получение исторических рыночных данных
     */
    public static GetHistoryDataRequest.GetHistoryDataRequestBuilder getHistoryData() {
        return GetHistoryDataRequest.builder();
    }

    /**
     * Метод получения списка акций.
     * 0 - Значение не определено.
     * 1 - Базовый список инструментов (по умолчанию). Инструменты доступные для торговли через TINKOFF INVEST API.
     * 2 - Список всех инструментов.
     */
    public static SharesRequest.SharesRequestBuilder getShares() {
        return SharesRequest.builder();
    }

    public static GetCandlesRequest.GetCandlesRequestBuilder getCandles() {
        return GetCandlesRequest.builder();
    }

    /**
     * Метод пополнения счёта в песочнице.
     */
    public static SandboxPayInRequest.SandboxPayInRequestBuilder getSandboxPayIn() {
        return SandboxPayInRequest.builder();
    }

    /**
     * Метод изменения выставленной заявки.
     */
    public static ReplaceSandboxOrderRequest.ReplaceSandboxOrderRequestBuilder getReplaceSandboxOrder() {
        return ReplaceSandboxOrderRequest.builder();
    }

    /**
     * Метод выставления торгового поручения в песочнице.
     */
    public static PostSandboxOrderRequest.PostSandboxOrderRequestBuilder getPostSandboxOrder() {
        return PostSandboxOrderRequest.builder();
    }

    /**
     * Метод регистрации счёта в песочнице.
     */
    public static OpenSandboxAccountRequest.OpenSandboxAccountRequestBuilder getOpenSandboxAccount() {
        return OpenSandboxAccountRequest.builder();
    }

    /**
     * Метод получения доступного остатка для вывода средств в песочнице.
     */
    public static GetSandboxWithdrawLimitsRequest.GetSandboxWithdrawLimitsRequestBuilder getSandboxWithdrawLimits() {
        return GetSandboxWithdrawLimitsRequest.builder();
    }

    /**
     * Метод получения позиций по виртуальному счёту песочницы.
     */
    public static GetSandboxPositionsRequest.GetSandboxPositionsRequestBuilder getSandboxPositions() {
        return GetSandboxPositionsRequest.builder();
    }

    /**
     * Метод получения списка активных заявок по счёту в песочнице.
     */
    public static GetSandboxOrdersRequest.GetSandboxOrdersRequestBuilder getSandboxOrders() {
        return GetSandboxOrdersRequest.builder();
    }

    /**
     * Метод получения статуса заявки в песочнице.
     */
    public static GetSandboxOrderStateRequest.GetSandboxOrderStateRequestBuilder getSandboxOrdersState() {
        return GetSandboxOrderStateRequest.builder();
    }

    /**
     * Метод отмены торгового поручения в песочнице.
     */
    public static CancelSandboxOrderRequest.CancelSandboxOrderRequestBuilder cancelSandboxOrder() {
        return CancelSandboxOrderRequest.builder();
    }

    /**
     * Метод закрытия счёта в песочнице.
     */
    public static CloseSandboxAccountRequest.CloseSandboxAccountRequestBuilder closeSandboxAccount() {
        return CloseSandboxAccountRequest.builder();
    }

    /**
     * Метод получения счетов в песочнице.
     */
    public static GetSandboxAccountsRequest.GetSandboxAccountsRequestBuilder getSandboxAccounts() {
        return GetSandboxAccountsRequest.builder();
    }

    /**
     * Метод получения операций в песочнице по номеру счёта.
     */
    public static GetSandboxOperationsRequest.GetSandboxOperationsRequestBuilder getSandboxOperations() {
        return GetSandboxOperationsRequest.builder();
    }

    /**
     *
     * @return тело свечки
     */
    public static CandleModel.CandleModelBuilder getCandleModel() {return CandleModel.builder();}
}
