import config.BaseConfig;
import delegats.FindOpenCloseLevel;
import enums.Candle_Interval;
import helper.BodyGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.SandBoxClient;
import service.dto.request.GetCandlesRequest;
import service.dto.response.candlesresponse.CandlesResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Epic("Get history data test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindHighLowCloseLevelTest {

    private SandBoxClient sandBoxClient;
    private final String FIGI = "BBG004S68507";
    private FindOpenCloseLevel findOpenCloseLevel;

    @BeforeAll
    void setUp() {
        BaseConfig config = ConfigFactory.create(BaseConfig.class);

        RequestSpecification tSandBox = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                //.addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setBaseUri(config.candlesHostname())
                .build();

        sandBoxClient = new SandBoxClient(tSandBox);

        findOpenCloseLevel = new FindOpenCloseLevel();
    }

    @Test
    @DisplayName("Get candles test")
    public void testCandlesTest() throws IOException {
        String dateFrom = LocalDateTime.now().minusHours(6).toString();
        String dateTo = LocalDateTime.now().toString();
        String dateFromEd = dateFrom.substring(0, dateFrom.length() - 6) + "Z";
        String dateToEd = dateTo.substring(0, dateTo.length() - 6) + "Z";

        GetCandlesRequest body = BodyGenerator.getCandles()
                .withFigi(FIGI)
                .withFrom(dateFromEd)
                .withTo(dateToEd)
                .withInterval(Candle_Interval.CANDLE_INTERVAL_1_MIN)
                .please();


        CandlesResponse candles = sandBoxClient.getCandles(body);
        System.out.println("Количество свечек - " + candles.getCandles().size());
        for (int i = 0; i <= candles.getCandles().size() - 1; i++) {
            String highUnits = candles.getCandles().get(i).getHigh().getUnits();
            String highNano = String.valueOf(candles.getCandles().get(i).getHigh().getNano());

            String lowUnits = candles.getCandles().get(i).getLow().getUnits();
            String lowNano = String.valueOf(candles.getCandles().get(i).getLow().getNano());

            String closeUnits = candles.getCandles().get(i).getClose().getUnits();
            String closeNano = String.valueOf(candles.getCandles().get(i).getClose().getNano());

            String time = candles.getCandles().get(i).getTime();

            String high = findOpenCloseLevel.findPrice(highUnits, highNano);
            String low = findOpenCloseLevel.findPrice(lowUnits, lowNano);
            String close = findOpenCloseLevel.findPrice(closeUnits, closeNano);

            String res = i + ") High: " + high + "; Low: " + low + "; " + "Close: " + close + " | " + time;

            FileWriter writer = new FileWriter("HighLowClose.txt", true);
            writer.write(res);
            writer.append('\n');
            writer.flush();

            System.out.println("High: " + high + "; Low: " + low + "; " + "Close: " + close + " | " + time);
        }
    }
}
