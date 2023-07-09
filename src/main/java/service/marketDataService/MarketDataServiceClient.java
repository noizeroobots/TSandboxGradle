package service.marketDataService;

import helper.EndPoints;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import service.marketDataService.dto.request.GetCandlesRequest;
import service.marketDataService.dto.response.GetCandlesResponse;

import static io.restassured.RestAssured.given;

/**
 * https://tinkoff.github.io/investAPI/swagger-ui/#/
 */
@RequiredArgsConstructor
public class MarketDataServiceClient {

    private final RequestSpecification requestSpecification;

    @Step("Метод запроса исторических свечей по инструменту.")
    public GetCandlesResponse getCandles(final GetCandlesRequest body){
        return given()
                .auth().preemptive().oauth2("t.EhKpdAOrMRX-bR27rRw4qUire7HwjGIOmyPqta8t17Pd9wRBh39AbLl08yGbPToZj6gpvWgqBABzkmvKXIzP2g")
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(EndPoints.GET_CANDLES_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().as(GetCandlesResponse.class);
    }
}
