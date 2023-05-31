package helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EndPoints {

    /**
     * Метод регистрации счёта в песочнице.
     */
    public static final String OPEN_SANDBOX_ACCOUNT_PATH = "/OpenSandboxAccount";

    /**
     * Метод получения счетов в песочнице.
     */
    public static final String GET_SANDBOX_ACCOUNTS_PATH = "/GetSandboxAccounts";

    /**
     * Метод получения портфолио в песочнице.
     */
    public static final String GET_SANDBOX_PORTFOLIO_PATH = "/GetSandboxPortfolio";

    /**
     * Метод получения позиций по виртуальному счёту песочницы.
     */
    public static final String GET_SANDBOX_POSITIONS_PATH = "/GetSandboxPositions";

    /**
     * Метод пополнения счёта в песочнице.
     */
    public static final String SANDBOX_IN_PAY_PATH = "/SandboxPayIn";

    /**
     * Метод получения доступного остатка для вывода средств в песочнице.
     */
    public static final String GET_SANDBOX_WITHDRAW_LIMITS_PATH = "/GetSandboxWithdrawLimits";

    /**
     * Получение исторических рыночных данных
     */
    public static final String GET_HISTORY_DATA_PATH = "/history-data";

    /**
     * Метод получения списка акций.
     */
    public static final String GET_SHARES_PATH = "/Shares";

    /**
     * Запрос исторических свечей.
     */
    public static final String GET_CANDLES_PATH = "/GetCandles";

    /**
     * Метод выставления торгового поручения в песочнице.
     */
    public static final String POST_SANDBOX_ORDER_PATH = "/PostSandboxOrder";

    /**
     * Метод получения списка активных заявок по счёту в песочнице.
     */
    public static final String GET_SANDBOX_ORDERS_PATH = "/GetSandboxOrders";

    /**
     * Метод получения статуса заявки в песочнице.
     */
    public static final String GET_SANDBOX_ORDER_STATE_PATH = "/GetSandboxOrderState";

    /**
     * Метод изменения выставленной заявки.
     */
    public static final String REPLACE_SANDBOX_ORDER_PATH = "/ReplaceSandboxOrder";

    /**
     * Метод отмены торгового поручения в песочнице.
     */
    public static final String CANCEL_SANDBOX_ORDER_PATH = "/CancelSandboxOrder";

    /**
     * Метод закрытия счёта в песочнице.
     */
    public static final String CLOSE_SANDBOX_ACCOUNT_PATH = "/CloseSandboxAccount";

    /**
     * Метод получения операций в песочнице по номеру счёта.
     */
    public static final String GET_SANDBOX_OPERATIONS_PATH = "/GetSandboxOperations";

    /**
     * Метод получения операций в песочнице по номеру счета с пагинацией.
     */
    public static final String GET_SANDBOX_OPERATIONS_BY_CURSOR_PATH = "/GetSandboxOperationsByCursor";
}
