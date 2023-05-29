package helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EndPoints {

    /**
     * Метод регистрации счёта в песочнице.
     */
    public static final String ADD_ACCOUNT_PATH = "OpenSandboxAccount";

    /**
     * Метод получения счетов в песочнице.
     */
    public static final String GET_SANDBOX_ACCOUNTS_PATH = "GetSandboxAccounts";

    /**
     * Метод получения портфолио в песочнице.
     */
    public static final String GET_SANDBOX_PORTFOLIO_PATH = "GetSandboxPortfolio";

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
    public static final String GET_CANDLES = "/GetCandles";

    /**
     * Метод выставления торгового поручения в песочнице.
     */
    public static final String POST_SANDBOX_ORDER = "/PostSandboxOrder";
}
