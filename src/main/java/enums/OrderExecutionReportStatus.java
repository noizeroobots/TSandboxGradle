package enums;

/**
 * Текущий статус заявки (поручения)
 */
public enum OrderExecutionReportStatus {

    /**
     * none
     */
    EXECUTION_REPORT_STATUS_UNSPECIFIED,

    /**
     * Исполнена
     */
    EXECUTION_REPORT_STATUS_FILL,

    /**
     * Отклонена
     */
    EXECUTION_REPORT_STATUS_REJECTED,

    /**
     * Отменена пользователем
     */
    EXECUTION_REPORT_STATUS_CANCELLED,

    /**
     * Новая
     */
    EXECUTION_REPORT_STATUS_NEW,

    /**
     * Частично исполнена
     */
    EXECUTION_REPORT_STATUS_PARTIALLYFILL
}
