package delegats;

import config.BaseConfig;
import enums.Candle_Interval;
import helper.BodyGenerator;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.awaitility.core.ConditionFactory;
import service.marketDataService.MarketDataServiceClient;
import service.marketDataService.dto.request.GetCandlesRequest;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.awaitility.Awaitility.await;

public class ConvertCandlesToDoubleArrays {

    protected MarketDataServiceClient marketDataServiceClient;
    protected FindOpenCloseLevel findOpenCloseLevel;
    private GetCandlesResponse candlesResponse;
    protected BaseConfig config;


    protected ConvertData convertData;

    protected static final ConditionFactory WAIT = await()
            .atMost(Duration.ofHours(24))
            .pollInterval(Duration.ofSeconds(10))
            .pollDelay(Duration.ofSeconds(50));

    protected void init() {
        config = ConfigFactory.create(BaseConfig.class);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RequestSpecification marketDataService = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                //.addFilter(new RequestLoggingFilter(LogDetail.ALL))
                //.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setBaseUri(config.marketDataServiceHostname())
                .build();

        marketDataServiceClient = new MarketDataServiceClient(marketDataService);
        findOpenCloseLevel = new FindOpenCloseLevel();
        convertData = new ConvertData();
    }


    /**
     * Метод получения значения цены
     */


    /**
     * Метод соединяет целую и дробную части цены
     * @param nano дробная часть цены
     * @param units целая часть цены
     * @return double значение цены
     */
    public static double combineUnitsAndNano(int nano, String units) {
        String closeNanoString = String.valueOf(nano);
        String closeS = units + "." + closeNanoString;
        return Double.parseDouble(closeS);
    }

    /**
     * Главный метод для добычи свечек!
     * @return массив свечек
     */
    public GetCandlesResponse getCandles(int minusDays, String FIGI) throws IOException {
        init();
        GetCandlesRequest body = BodyGenerator.getCandles()
                .withFigi(FIGI)
                .withFrom(convertData.convertData(LocalDateTime.now().minusDays(minusDays).toString()))
                .withTo(convertData.convertData(LocalDateTime.now().toString()))
                .withInterval(Candle_Interval.CANDLE_INTERVAL_HOUR)
                .withInstrument_id(FIGI)
                .please();

        //System.out.println("Количество свечек: " + candlesResponse.getCandles().size());
        return marketDataServiceClient.getCandles(body);
    }

    /**
     * Метод извлекает из листа свечек значения high и преобразует в массив
     *
     * @return массив high значений
     */
    public double[] getHigh(GetCandlesResponse candlesResponse) throws IOException {
        double[] high = new double[candlesResponse.getCandles().size()];
        for (int i = 0; i < high.length; i++) {
            high[i] = combineUnitsAndNano(candlesResponse.getCandles().get(i).getHigh().getNano(), candlesResponse.getCandles().get(i).getHigh().getUnits());
        }
        return high;
    }

    public List<Double> getHighList(GetCandlesResponse candlesResponse) throws IOException {
        List<Double> high = new ArrayList<>();
        for (int i = 0; i < candlesResponse.getCandles().size(); i++) {
            high.add(combineUnitsAndNano(candlesResponse.getCandles().get(i).getHigh().getNano(), candlesResponse.getCandles().get(i).getHigh().getUnits()));
        }
        return high;
    }

    /**
     * Метод извлекает из листа свечек значения low и преобразует в массив
     *
     * @return массив low значений
     */
    public double[] getLow(GetCandlesResponse candlesResponse) throws IOException {
        double[] low = new double[candlesResponse.getCandles().size()];
        for (int i = 0; i < low.length; i++) {
            low[i] = combineUnitsAndNano(candlesResponse.getCandles().get(i).getLow().getNano(), candlesResponse.getCandles().get(i).getLow().getUnits());
        }
        return low;
    }
    public List<Double> getLowList(GetCandlesResponse candlesResponse) throws IOException {
        List<Double> low = new ArrayList<>();
        for (int i = 0; i < candlesResponse.getCandles().size(); i++) {
            low.add(combineUnitsAndNano(candlesResponse.getCandles().get(i).getLow().getNano(), candlesResponse.getCandles().get(i).getLow().getUnits()));
        }
        return low;
    }
    /**
     * Метод извлекает из листа свечек значения close и преобразует в массив
     *
     * @return массив close значений
     */
    public double[] getClose(GetCandlesResponse candlesResponse) throws IOException {
        double[] close = new double[candlesResponse.getCandles().size()];
        for (int i = 0; i < close.length; i++) {
            close[i] = combineUnitsAndNano(candlesResponse.getCandles().get(i).getClose().getNano(), candlesResponse.getCandles().get(i).getClose().getUnits());
        }
        return close;
    }
    public List<Double> getCloseList(GetCandlesResponse candlesResponse) throws IOException {
        List<Double> close = new ArrayList<>();
        for (int i = 0; i < candlesResponse.getCandles().size(); i++) {
            close.add(combineUnitsAndNano(candlesResponse.getCandles().get(i).getClose().getNano(), candlesResponse.getCandles().get(i).getClose().getUnits()));
        }
        return close;
    }



    /**
     * Метод извлекает из листа свечек значения close и преобразует в массив
     *
     * @return массив close значений
     */
    public String[] getTime(GetCandlesResponse candlesResponse) throws IOException {
        String[] time = new String[candlesResponse.getCandles().size()];
        for (int i = 0; i < time.length; i++) {
            time[i] = candlesResponse.getCandles().get(i).getTime();
        }
        return time;
    }
}