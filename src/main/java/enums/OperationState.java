package enums;

/**
 * Статус запрашиваемых операций.
 */
public enum OperationState {

    /**
     * Статус операции не определён
     */
    OPERATION_STATE_UNSPECIFIED,

    /**
     * Исполнена.
     */
    OPERATION_STATE_EXECUTED,

    /**
     * Отменена.
     */
    OPERATION_STATE_CANCELED,

    /**
     * Исполняется.
     */
    OPERATION_STATE_PROGRESS
}
