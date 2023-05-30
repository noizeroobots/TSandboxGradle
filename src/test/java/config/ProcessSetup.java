package config;

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
import service.SandBoxClient;

import java.time.Duration;

import static org.awaitility.Awaitility.await;


public abstract class ProcessSetup {

    protected SandBoxClient sandBoxClient;
    protected FindOpenCloseLevel findOpenCloseLevel;
    protected BaseConfig config;
    protected static final ConditionFactory WAIT = await()
            .atMost(Duration.ofHours(24))
            .pollInterval(Duration.ofSeconds(10))
            .pollDelay(Duration.ofSeconds(50));

    protected void init(){
        config = ConfigFactory.create(BaseConfig.class);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RequestSpecification tSandBox = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setBaseUri(config.candlesHostname())
                .build();

        RequestSpecification postSandboxOrder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                //.addFilter(new RequestLoggingFilter(LogDetail.ALL))
                //.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setBaseUri(config.sandboxHostname())
                .build();

        sandBoxClient = new SandBoxClient(tSandBox);
        sandBoxClient = new SandBoxClient(postSandboxOrder);
        findOpenCloseLevel = new FindOpenCloseLevel();
    }
}
