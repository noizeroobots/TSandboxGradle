//import config.BaseConfig;
//import enums.Candle_Interval;
//import helper.BodyGenerator;
//import io.qameta.allure.Epic;
//import io.qameta.allure.restassured.AllureRestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.filter.log.LogDetail;
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
//import org.aeonbits.owner.ConfigFactory;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import service.sandboxService.SandBoxClient;
//import service.sandboxService.dto.response.candlesresponse.CandlesResponse;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//
//@Epic("Get history data test")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class GetOnlineCandlesTest {
//
//    private SandBoxClient sandBoxClient;
//    private final String FIGI = "BBG004S68507";
//
//    @BeforeAll
//    void setUp() {
//        BaseConfig config = ConfigFactory.create(BaseConfig.class);
//
//        RequestSpecification tSandBox = new RequestSpecBuilder()
//                .setContentType(ContentType.JSON)
//                .addFilter(new AllureRestAssured())
//                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
//                //.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
//                .setBaseUri(config.marketDataServiceHostname())
//                .build();
//
//        sandBoxClient = new SandBoxClient(tSandBox);
//    }
//
//    @Test
//    @DisplayName("Get candles test")
//    public void testCandlesTest() throws IOException, InterruptedException {
//        boolean flag = true;
//        //while (flag) {
//            String dateFrom = LocalDateTime.now().minusHours(1).toString();
//            String dateTo = LocalDateTime.now().toString();
//
//            String dateFromEd = dateFrom.substring(0, dateFrom.length() - 6) + "Z";
//            String dateToEd = dateTo.substring(0, dateTo.length() - 6) + "Z";
//
//            GetCandlesRequest body = BodyGenerator.getCandles()
//                    .withFigi(FIGI)
//                    .withFrom(dateFromEd)
//                    .withTo(dateToEd)
//                    .withInterval(Candle_Interval.CANDLE_INTERVAL_3_MIN)
//                    .please();
//
//
//            CandlesResponse candles = sandBoxClient.getCandles(body);
//            System.out.println(candles.getCandles().size());
//            //Thread.sleep(60000);
//        //}
//        //System.out.println("Exit. " + LocalDateTime.now());
//    }
//}
