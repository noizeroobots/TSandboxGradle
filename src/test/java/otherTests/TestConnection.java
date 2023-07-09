package otherTests;

import delegats.CalculateATR;
import algorithms.SuperTrendOriginal;
import config.BaseConfig;
import delegats.CalculateTR;
import delegats.candles.ConvertCandlesToDoubleArrays;
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
import service.marketDataService.dto.response.GetCandlesResponse;
import service.sandboxService.SandBoxClient;
import service.sandboxService.dto.request.SandboxPortfolioRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Epic("Connection test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestConnection {

    private SandBoxClient sandBoxClient;
    private ConvertCandlesToDoubleArrays convertCandlesToDoubleArrays;
    private SuperTrendOriginal superTrendOriginal;
    private CalculateATR calculateART;
    private CalculateTR calculateTR;
    private String FIGI = "BBG00178PGX3"; // VCKO

    @BeforeAll
    void setUp() {
        BaseConfig config = ConfigFactory.create(BaseConfig.class);
        calculateTR = new CalculateTR();
        convertCandlesToDoubleArrays = new ConvertCandlesToDoubleArrays();
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

    @Test
    public void testSuper() throws IOException {
        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(2, FIGI);
        superTrendOriginal = new SuperTrendOriginal(convertCandlesToDoubleArrays.getHigh(candlesResponse)
                , convertCandlesToDoubleArrays.getLow(candlesResponse)
                , convertCandlesToDoubleArrays.getClose(candlesResponse)
                , 12
                , 1
                , convertCandlesToDoubleArrays.getTime(candlesResponse));
        double[] sprtrnd = superTrendOriginal.getSupertrend();

//        for (int i = sprtrnd.length - 1; i >= 0; i--) {
//            System.out.println(sprtrnd[i]);
//        }
    }


    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR)")
    public void testTr() throws IOException {
        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(2, FIGI);
        List<String> trueRange = calculateTR.calculateTrueRangeReturnStringList(candlesResponse);
        for (int i = 0; i < trueRange.size(); i++) {
            System.out.println("index = " + i + " | True range: " + trueRange.get(i));
        }
    }


    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR), используя List<Double>")
    public void testTrL() throws IOException {
        int period = 10;

        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(2, FIGI);
        List<Double> trueRange = calculateTR.calculateTrueRangeReturnDoubleList(candlesResponse);
        List<Double> atr = new ArrayList<>();

        for (int k = 0; k < 30; k++) {
            List<Double> sec = new ArrayList<>();
            double sum = 0;
            for (int i = 1; i <= period; i++) {
                double element = trueRange.get(trueRange.size() - i - k - 3);
                sec.add(element);
            }
            for (int j = 0; j < sec.size(); j++) {
                sum += sec.get(j);
                // System.out.println("Номер " + j + " в выборке по периоду " + period + " | " + sec.get(j));
            }
            double average = sum / period;
            atr.add(average);
            // System.out.println("ATR по выборке = " + average);
        }
        for (int j = 0; j < atr.size(); j++) {
            System.out.println("Номер ATR " + j + " в выборке по периоду " + period + " | " + atr.get(j));
        }
    }


    @Test
    @DisplayName("Определение ATR 14-06-2023")
    public void testTrATR() throws IOException {
        int period = 10;
        double summOfPeriodValue = 0;
        double averageOfSummOfPeriodValue = 0;
        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(2, FIGI);
        List<Double> trueRange = calculateTR.calculateTrueRangeReturnDoubleList(candlesResponse);
        System.out.println("Размер List<Double> trueRange = " + trueRange.size());
        List<Double> periodTrueRange = new ArrayList<>(period);
        for (int i = 0; i < period; i++) {
            summOfPeriodValue += trueRange.get(i);
            periodTrueRange.add(trueRange.get(i));

        }
        averageOfSummOfPeriodValue = summOfPeriodValue / period;
        System.out.println("Размер List<Double> periodTrueRange = " + periodTrueRange.size());
        System.out.println("Сумма 10 элементов List<Double> periodTrueRange = " + summOfPeriodValue);
        System.out.println("Среднее значение 10 элементов List<Double> periodTrueRange = " + averageOfSummOfPeriodValue);

    }


    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR)")
    public void testTrA() throws IOException {
        int period = 10;
        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(2, FIGI);
        double[] trueRange = calculateTR.calculateTrueRangeReturnDoubleArray(candlesResponse);
        double[] periodArray = new double[period];
        for (int i = 0; i < trueRange.length; i++) {
            // System.out.println("index = " + i + " | " + trueRange[i]);
        }
        System.arraycopy(trueRange, trueRange.length - 13, periodArray, 0, period);
        // System.out.println(Arrays.toString(periodArray));
        double sum = 0;
        for (int i = 0; i < periodArray.length; i++) {
            sum += periodArray[i];
        }
        double average = sum / periodArray.length;
        System.out.println("Среднее значение: " + average);
    }
}
