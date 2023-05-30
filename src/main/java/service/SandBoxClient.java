package service;

import helper.EndPoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import service.dto.request.*;
import service.dto.response.SharesResponse;
import service.dto.response.candlesresponse.CandlesResponse;
import service.dto.response.postsandboxorder.PostSandboxOrderResponse;

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
                .post(EndPoints.ADD_ACCOUNT_PATH)
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
                .post(EndPoints.GET_SHARES_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().as(SharesResponse.class);
    }

    @Step("Метод получения списка акций.")
    public CandlesResponse getCandles(final GetCandlesRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.GET_CANDLES)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().as(CandlesResponse.class);
    }

    @Step("Метод выставления торгового поручения в песочнице.")
    public PostSandboxOrderResponse postSandboxOrder(final PostSandboxOrderRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.POST_SANDBOX_ORDER)
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
                .post(EndPoints.GET_SANDBOX_ORDERS)
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
                .post(EndPoints.CANCEL_SANDBOX_ORDER)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
