package postOrders;

import config.BaseTest;
import enums.OrderDirection;
import enums.OrderType;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.dto.response.postsandboxorder.PostSandboxOrderResponse;

/**
 * Figi для акции YNDX : "BBG006L8G4H1"
 */
@Slf4j
@Epic("d")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostOrderTest extends BaseTest {

    private BaseTest baseTest;
    private PostSandboxOrderResponse postSandboxOrderResponse;
    private final String FIGI = "BBG006L8G4H1";
    private final String quantity = "1";
    private final String accountId = "62c0b4fd-6af6-4db0-9367-d7dbbb295b82";

    @BeforeAll
    void setUp() {
        init();
        baseTest = new BaseTest();
    }

    @Test
    void create() {
        Allure.step("Выставление торгового поручения в песочнице", () -> {
            postSandboxOrderResponse = baseTest.postSandboxOrderMarket(FIGI, quantity, OrderDirection.ORDER_DIRECTION_BUY, OrderType.ORDER_TYPE_MARKET, accountId, FIGI);
            log.debug("Текущий статус заявки: {}", postSandboxOrderResponse.getOrderId());
            log.info("Текущий статус заявки: {}", postSandboxOrderResponse.getExecutionReportStatus());
            log.info("Цена исполнения заявки: {}.{} {}", postSandboxOrderResponse.getExecutedOrderPrice().getUnits(), postSandboxOrderResponse.getExecutedOrderPrice().getNano(), postSandboxOrderResponse.getExecutedOrderPrice().getCurrency());
            log.info("FIGI: {}", postSandboxOrderResponse.getFigi());
            log.info("Направление операции: {}", postSandboxOrderResponse.getDirection());
            log.info("Тип заявки: {}", postSandboxOrderResponse.getOrderType());
            log.info("Количество лотов запрошено: {}", postSandboxOrderResponse.getLotsRequested());
            log.info("Количество лотов выполнено: {}", postSandboxOrderResponse.getLotsExecuted());
            System.out.println(postSandboxOrderResponse.getOrderId());
        });
    }

    @Test
    void check() {
        Allure.step("Получение списка активных заявок по счёту в песочнице", () -> {
            baseTest.getSandboxOrders(accountId);
        });
    }

    @Test
    void cancelOrderTest() {
        Allure.step("Тест отмены торгового поручения в песочнице.", () -> {
            baseTest.getSandboxOrders(accountId);
        });
    }
}