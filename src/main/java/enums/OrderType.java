package enums;

/**
 * Тип заявки.
 */
public enum OrderType {

    /**
     * Значение не указано
     */
    ORDER_TYPE_UNSPECIFIED,

    /**
     * Лимитная
     */
    ORDER_TYPE_LIMIT,

    /**
     * Рыночная
     */
    ORDER_TYPE_MARKET,

    /**
     * Лучшая цена
     */
    ORDER_TYPE_BESTPRICE
}
