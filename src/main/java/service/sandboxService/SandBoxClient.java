package service.sandboxService;

import helper.EndPoints;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import service.sandboxService.dto.request.*;
import service.sandboxService.dto.response.SharesResponse;
import service.sandboxService.dto.response.postsandboxorder.PostSandboxOrderResponse;

import static io.restassured.RestAssured.given;

/**
 * https://tinkoff.github.io/investAPI/swagger-ui/#/
 */
@RequiredArgsConstructor
public class SandBoxClient {

    private final RequestSpecification requestSpecification;

    @Step("Метод регистрации счёта в песочнице.")
    public String addAccount(final EmptyBodyRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.OPEN_SANDBOX_ACCOUNT_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getString("accountId");
    }

    @Step("Метод получения счетов в песочнице.")
    public ValidatableResponse getSandboxAccounts(final EmptyBodyRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.GET_SANDBOX_ACCOUNTS_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Метод получения портфолио в песочнице.")
    public ValidatableResponse getSandboxPortfolio(final SandboxPortfolioRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.GET_SANDBOX_PORTFOLIO_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Метод получения исторических рыночных данных.")
    public ValidatableResponse getHistoryData(final String figi, final Integer year){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .param("figi", figi)
                .param("year", year)
                .when()
                .get(EndPoints.GET_HISTORY_DATA_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Метод получения списка акций.")
    public SharesResponse  getShares(final SharesRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK).extract().as(SharesResponse.class);
    }

    @Step("Метод выставления торгового поручения в песочнице.")
    public PostSandboxOrderResponse postSandboxOrder(final PostSandboxOrderRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.POST_SANDBOX_ORDER_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().as(PostSandboxOrderResponse.class);
    }

    @Step("Метод получения списка активных заявок по счёту в песочнице.")
    public ValidatableResponse getSandboxOrders(final GetSandboxOrdersRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.GET_SANDBOX_ORDERS_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }


    @Step("Метод отмены торгового поручения в песочнице.")
    public ValidatableResponse cancelSandboxOrder(final CancelSandboxOrderRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.CANCEL_SANDBOX_ORDER_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Метод закрытия счёта в песочнице.")
    public ValidatableResponse closeSandboxAccount(final CloseSandboxAccountRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.CLOSE_SANDBOX_ACCOUNT_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Метод получения счетов в песочнице.")
    public ValidatableResponse getSandboxAccounts(final GetSandboxAccountsRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.GET_SANDBOX_ACCOUNTS_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Метод получения операций в песочнице по номеру счёта.")
    public ValidatableResponse getSandboxOperations(final GetSandboxOperationsRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.GET_SANDBOX_OPERATIONS_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
