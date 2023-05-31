package enums;

/**
 * Тип цены.
 */
public enum PriceType {

    /**
     * Значение не определено.
     */
    PRICE_TYPE_UNSPECIFIED,

    /**
     * Цена в пунктах (только для фьючерсов и облигаций).
     */
    PRICE_TYPE_POINT,

    /**
     * Цена в валюте расчётов по инструменту.
     */
    PRICE_TYPE_CURRENCY
}
