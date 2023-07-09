package config;

import enums.OperationState;
import enums.OrderDirection;
import enums.OrderType;
import helper.BodyGenerator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import service.marketDataService.dto.response.GetCandlesResponse;
import service.sandboxService.dto.request.CancelSandboxOrderRequest;
import service.sandboxService.dto.request.GetSandboxOrdersRequest;
import service.sandboxService.dto.request.PostSandboxOrderRequest;
import service.sandboxService.dto.response.postsandboxorder.PostSandboxOrderResponse;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest extends ProcessSetup {

//    public void testingCandlesTest(final String FIGI) {
//        init();
//        String dateFrom = LocalDateTime.now().minusDays(1).toString();
//        String dateTo = LocalDateTime.now().toString();
//        String dateFromEd = dateFrom.substring(0, dateFrom.length() - 6) + "Z";
//        String dateToEd = dateTo.substring(0, dateTo.length() - 6) + "Z";
//
//        GetCandlesRequest body = BodyGenerator.getCandles()
//                .withFigi(FIGI)
//                .withFrom(dateFromEd)
//                .withTo(dateToEd)
//                .withInterval(Candle_Interval.CANDLE_INTERVAL_3_MIN)
//                .withInstrument_id(FIGI)
//                .please();
//
//
//        GetCandlesResponse candles = marketDataServiceClient.getCandles(body);
//        System.out.println("Количество свечек - " + candles.getCandles().size());
//        for (int i = 0; i <= candles.getCandles().size() - 1; i++) {
//            String openUnits = candles.getCandles().get(i).g().getUnits();
//            String openNano = String.valueOf(candles.getCandles().get(i).getOpen().getNano());
//            String closeUnits = candles.getCandles().get(i).getClose().getUnits();
//            String closeNano = String.valueOf(candles.getCandles().get(i).getClose().getNano());
//            String time = candles.getCandles().get(i).getTime();
//            String open = findOpenCloseLevel.findPrice(openUnits, openNano);
//            String close = findOpenCloseLevel.findPrice(closeUnits, closeNano);
//            System.out.println("Open price: " + open + "; Close price: " + close + "; " + time);
//        }
    //}

    /**
     * Метод выставления торгового поручения в песочнице.
     */
    public void postSandboxOrder(String FIGI, String quantity, OrderDirection orderDirection, OrderType orderType, String accountId, String instrumentId) {
        init();
        PostSandboxOrderRequest body = BodyGenerator.getPostSandboxOrder()
                .withFigi(FIGI)
                .withQuantity(quantity)
                .withOrderType(orderType)
                .withDirection(orderDirection)
                .withAccountId(accountId)
                .withInstrumentId(FIGI)
                .please();
        sandBoxClient.postSandboxOrder(body);
    }

    /**
     * Метод выставления торгового поручения в песочнице.
     */
    public PostSandboxOrderResponse postSandboxOrderMarket(String FIGI, String quantity, OrderDirection orderDirection, OrderType orderType, String accountId, String instrumentId) {
        init();
        PostSandboxOrderRequest body = BodyGenerator.getPostSandboxOrder()
                .withFigi(FIGI)
                .withQuantity(quantity)

                .withOrderType(orderType)
                .withDirection(orderDirection)
                .withAccountId(accountId)
                .withInstrumentId(FIGI)
                .please();
        return sandBoxClient.postSandboxOrder(body);
    }

    /**
     * Метод получения списка активных заявок по счёту в песочнице.
     */
    public void getSandboxOrders(String accountId) {
        init();
        GetSandboxOrdersRequest body = BodyGenerator.getSandboxOrders()
                .withAccountId(accountId)
                .please();
        sandBoxClient.getSandboxOrders(body);
    }

    /**
     * Метод отмены торгового поручения в песочнице.
     */
    public void cancelSandboxOrder(String accountId, String orderId) {
        init();
        CancelSandboxOrderRequest body = BodyGenerator.cancelSandboxOrder()
                .withAccountId(accountId)
                .withOrderId(orderId)
                .please();
        sandBoxClient.cancelSandboxOrder(body);
    }

    /**
     * Метод получения счетов в песочнице.
     */
    public void getSandboxAccounts() {
        init();
        sandBoxClient.getSandboxAccounts(BodyGenerator.getSandboxAccounts().please());
    }

    /**
     * Метод закрытия счёта в песочнице.
     */
    public void closeSandboxAccounts(String accountId) {
        init();
        sandBoxClient.closeSandboxAccount(BodyGenerator.closeSandboxAccount().withAccountId(accountId).please());
    }

    /**
     * Метод получения операций в песочнице по номеру счёта.
     */
    public void getSandboxOperations(String accountId, String from, String to, OperationState state, String figi) {
        init();
        sandBoxClient.getSandboxOperations(BodyGenerator.getSandboxOperations()
                .withAccountId(accountId)
                .withFrom(from)
                .withTo(to)
                .withState(state)
                .withFigi(figi)
                .please());
    }

    /**
     * Метод расчета среднего истинного диапазона (ATR)
     */
    public static double calculateATR(GetCandlesResponse candles, int period) {
        double atr = 0;
        double tr;

        double prevClose = combineUnitsAndNano(candles.getCandles().get(0).getClose().getNano(), candles.getCandles().get(0).getClose().getUnits());
        for (int i = 1; i < period; i++) {
            GetCandlesResponse.CandlesItem candle = candles.getCandles().get(i);

            double high = combineUnitsAndNano(candle.getHigh().getNano(), candle.getHigh().getUnits());
            double low = combineUnitsAndNano(candle.getLow().getNano(), candle.getLow().getUnits());
            double close = combineUnitsAndNano(candle.getClose().getNano(), candle.getClose().getUnits());

            double highLow = Math.max(Math.abs(high - prevClose), Math.abs(low - prevClose));
            tr = Math.max(highLow, Math.abs(high - low));
            atr += tr;
            prevClose = close;
        }

        atr = atr / period;

        return atr;
    }

    /**
     *
     */
    public static double combineUnitsAndNano(int nano, String units){
        String closeNanoString = String.valueOf(nano);
        String closeS = units + "." + closeNanoString;
        return  Double.parseDouble(closeS);
    }

    public List<Double> calculateATRQ(List<Double> trueRange, int period) {
        List<Double> atrList = new ArrayList<>();
        double atr = trueRange.get(0);
        for (int i = 1; i < trueRange.size(); i++) {
            double tr = trueRange.get(i);
            atr = ((period - 1) * atr + tr) / period;
            atrList.add(atr);
        }
        return atrList;
    }
}
