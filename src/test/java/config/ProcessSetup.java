package config;

import database.steps.InteractionWithDB;
import delegats.ConvertData;
import delegats.FindOpenCloseLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.awaitility.core.ConditionFactory;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;
import service.marketDataService.MarketDataServiceClient;
import service.sandboxService.SandBoxClient;

import java.sql.Connection;
import java.time.Duration;

import static org.awaitility.Awaitility.await;


public abstract class ProcessSetup {

    protected SandBoxClient sandBoxClient;
    protected MarketDataServiceClient marketDataServiceClient;
    protected FindOpenCloseLevel findOpenCloseLevel;
    protected BaseConfig config;
    protected ConvertData convertData;


    protected static final ConditionFactory WAIT = await()
            .atMost(Duration.ofHours(24))
            .pollInterval(Duration.ofSeconds(10))
            .pollDelay(Duration.ofSeconds(50));

    protected void init(){
        config = ConfigFactory.create(BaseConfig.class);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RequestSpecification marketDataService = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setBaseUri(config.marketDataServiceHostname())
                .build();

        RequestSpecification postSandboxOrder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setBaseUri(config.sandboxHostname())
                .build();

        marketDataServiceClient = new MarketDataServiceClient(marketDataService);
        sandBoxClient = new SandBoxClient(postSandboxOrder);
        findOpenCloseLevel = new FindOpenCloseLevel();
        convertData = new ConvertData();

    }
}
