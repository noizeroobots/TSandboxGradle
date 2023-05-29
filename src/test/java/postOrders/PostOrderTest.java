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

/**
 * Figi для акции YNDX : "BBG006L8G4H1"
 */
@Slf4j
@Epic("d")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostOrderTest extends BaseTest {

    private BaseTest baseTest;
    private String FIGI = "BBG006L8G4H1";
    private String quantity = "10";
    private String accountId = "62c0b4fd-6af6-4db0-9367-d7dbbb295b82";

    @BeforeAll
    void setUp() {
        init();
        baseTest = new BaseTest();
    }

    @Test
     void create() {
        Allure.step("sdfg", () -> {
            baseTest.postSandboxOrder(FIGI, quantity,  OrderDirection.ORDER_DIRECTION_BUY, OrderType.ORDER_TYPE_LIMIT, accountId, FIGI);
        });
    }
}
