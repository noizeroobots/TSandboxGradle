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
import service.dto.request.SandboxPortfolioRequest;

@Epic("Connection test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestConnection {

    private SandBoxClient sandBoxClient;

    @BeforeAll
    void setUp() {
        BaseConfig config = ConfigFactory.create(BaseConfig.class);

        RequestSpecification tSandBox = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setBaseUri(config.sandboxHostname())
                .build();

        sandBoxClient = new SandBoxClient(tSandBox);
    }

    @Test
    @DisplayName("Open the new account in Sandbox.")
    public void testCreateAccountInSandbox() {
        System.out.println(sandBoxClient.addAccount(BodyGenerator.getEmptyBody().please()));
    }

    @Test
    @DisplayName("Get all accounts in Sandbox.")
    public void testGetAccountsFromSandbox() {
        System.out.println(sandBoxClient.getSandboxAccounts(BodyGenerator.getEmptyBody().please()));
    }

    @Test
    @DisplayName("Get sandbox portfolio.")
    public void testGetPortfolioFromSandbox() {
        SandboxPortfolioRequest sandboxPortfolio = BodyGenerator.getSandboxPortfolio()
                .withAccountId("62c0b4fd-6af6-4db0-9367-d7dbbb295b82")
                .please();
        System.out.println(sandBoxClient.getSandboxPortfolio(sandboxPortfolio));
    }
}
