//import algorithms.SuperTrend;
//import config.BaseConfig;
//import delegats.candles.FindOpenCloseLevel;
//import enums.Candle_Interval;
//import helper.BodyGenerator;
//import io.qameta.allure.Epic;
//import io.qameta.allure.restassured.AllureRestAssured;
//import io.restassured.builder.RequestSpecBuilder;
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
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDateTime;
//
//@Epic("Get history data test")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class FindHighLowCloseLevelTestWithSuperTrend {
//
//    private SandBoxClient sandBoxClient;
//    private final String FIGI = "BBG004S68507";
//    private FindOpenCloseLevel findOpenCloseLevel;
//    private SuperTrend superTrendClient;
//
//    @BeforeAll
//    void setUp() {
//        BaseConfig config = ConfigFactory.create(BaseConfig.class);
//
//        RequestSpecification tSandBox = new RequestSpecBuilder()
//                .setContentType(ContentType.JSON)
//                .addFilter(new AllureRestAssured())
//                //.addFilter(new RequestLoggingFilter(LogDetail.ALL))
//                //.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
//                .setBaseUri(config.marketDataServiceHostname())
//                .build();
//
//        sandBoxClient = new SandBoxClient(tSandBox);
//
//        findOpenCloseLevel = new FindOpenCloseLevel();
//    }
//
//    @Test
//    @DisplayName("Get candles test")
//    public void testCandlesTest() throws IOException {
//        String dateFrom = LocalDateTime.now().minusHours(6).toString();
//        String dateTo = LocalDateTime.now().toString();
//        String dateFromEd = dateFrom.substring(0, dateFrom.length() - 6) + "Z";
//        String dateToEd = dateTo.substring(0, dateTo.length() - 6) + "Z";
//
//        GetCandlesRequest body = BodyGenerator.getCandles()
//                .withFigi(FIGI)
//                .withFrom(dateFromEd)
//                .withTo(dateToEd)
//                .withInterval(Candle_Interval.CANDLE_INTERVAL_HOUR)
//                .please();
//
//        double[] HIGH = new double[180];
//        double[] LOW = new double[180];
//        double[] CLOSE = new double[180];
//        CandlesResponse candles = sandBoxClient.getCandles(body);
//        System.out.println("Количество свечек - " + candles.getCandles().size());
//        for (int i = 0; i <= candles.getCandles().size() - 1; i++) {
//            String highUnits = candles.getCandles().get(i).getHigh().getUnits();
//            String highNano = String.valueOf(candles.getCandles().get(i).getHigh().getNano());
//
//            String lowUnits = candles.getCandles().get(i).getLow().getUnits();
//            String lowNano = String.valueOf(candles.getCandles().get(i).getLow().getNano());
//
//            String closeUnits = candles.getCandles().get(i).getClose().getUnits();
//            String closeNano = String.valueOf(candles.getCandles().get(i).getClose().getNano());
//
//            String time = candles.getCandles().get(i).getTime();
//
//            String high = findOpenCloseLevel.findPrice(highUnits, highNano);
//
//            HIGH[i] = Double.parseDouble(high);
//
//            String low = findOpenCloseLevel.findPrice(lowUnits, lowNano);
//
//            LOW[i] = Double.parseDouble(low);
//
//            String close = findOpenCloseLevel.findPrice(closeUnits, closeNano);
//
//            CLOSE[i] = Double.parseDouble(close);
//
//            String res = i + ") High: " + high + "; Low: " + low + "; " + "Close: " + close + " | " + time;
//
//            FileWriter writer = new FileWriter("HighLowClose.txt", true);
//            writer.write(res);
//            writer.append('\n');
//            writer.flush();
//        }
//
//
//
//            final int PERIOD = 10;
//            final double MULTIPLIER = 2.2;
//            final double ATR_FACTOR = 50;
//            superTrendClient = new SuperTrend(HIGH, LOW, CLOSE, PERIOD, MULTIPLIER, ATR_FACTOR, "time");
//
//    }
//}
