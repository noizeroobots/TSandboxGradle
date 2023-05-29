import config.BaseConfig;
import helper.BodyGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.SandBoxClient;
import service.dto.request.SharesRequest;
import service.dto.response.SharesResponse;

import java.io.FileWriter;
import java.io.IOException;

@Epic("Get history data test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetFigiTest {

    private SandBoxClient sandBoxClient;
    private final String FIGI = "1"; // ОЗОН

    @BeforeAll
    void setUp() {
        BaseConfig config = ConfigFactory.create(BaseConfig.class);

        RequestSpecification tSandBox = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setBaseUri(config.sharesHostname())
                .build();

        sandBoxClient = new SandBoxClient(tSandBox);
    }

    @Test
    @DisplayName("Get history data test")
    public void testGetHistoryDataTest() throws IOException {
        //sandBoxClient.getHistoryData(figi, 2023);
        SharesRequest bodyShare = BodyGenerator.getShares().withInstrument_status(2).please();

        SharesResponse sharesResponse = new SharesResponse();
        //sandBoxClient.getShares(bodyShare);
        sharesResponse = sandBoxClient.getShares(bodyShare);
        for (int i = 0; i <= sharesResponse.getInstruments().size() - 1; i++) {
            String classCode = sharesResponse.getInstruments().get(i).getClassCode();
            String figi = sharesResponse.getInstruments().get(i).getFigi();
            String ticker = sharesResponse.getInstruments().get(i).getTicker();
            String countryOfRisk = sharesResponse.getInstruments().get(i).getCountryOfRisk();
            if (countryOfRisk.equals("RU") && (ticker.length() == 4)) {
                String res = i + " - " + "TIKER: " + ticker + " | " + "FIGI: " + figi + " | " + "CLASSCODE: " + classCode;

                FileWriter writer = new FileWriter("Figi_is_here.txt", true);
                writer.write(res);
                writer.append('\n');
                writer.flush();
            }
            System.out.println(i + " - " + "TIKER: " + ticker + " | " + "FIGI: " + figi + " | " + "CLASSCODE: " + classCode);
        }


    }
}
