package calculateATR;

import algorithms.SuperTrend21062023;
import config.BaseTest;
import delegats.CalculateTR;
import delegats.ConvertCandlesToDoubleArrays;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Epic("Connection test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Try1_CalculateATR {

    private CalculateTR calculateTR;
    private ConvertCandlesToDoubleArrays convertCandlesToDoubleArrays;
    private String FIGI = "BBG00178PGX3"; // VCKO
    BaseTest baseTest;
    private SuperTrend21062023 superTrend21062023;

    @BeforeAll
    void setUp() throws IOException {
        calculateTR = new CalculateTR();
        convertCandlesToDoubleArrays = new ConvertCandlesToDoubleArrays();
         baseTest = new BaseTest();
         superTrend21062023 = new SuperTrend21062023();

    }

    /**
     * Определение истинного диапазона (True Range, TR)
     * Возвращает List стрингов для лучшего визуального воспиятия
     */
    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR) - точно работает")
    public void returnListStringTrueRange() throws IOException {
        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(3, FIGI);
        List<String> trueRange = calculateTR.calculateTrueRangeReturnStringList(candlesResponse);
        for (int i = 0; i < trueRange.size(); i++) {
            //System.out.println("index = " + i + " | True range: " + trueRange.get(i));
            System.out.println(trueRange.get(i));
        }
    }

    /**
     * Определение истинного диапазона (True Range, TR)
     * Возвращает массив Double для дальнейшей работы
     */
    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR)")
    public void returnArrayDoubleTrueRange() throws IOException {
        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(3, FIGI);
        //double[] tr = new double[candlesResponse.getCandles().size()];
        int period = 10;
        int sizeOfArray = calculateTR.calculateTrueRangeReturnDoubleArray(candlesResponse).length;
        System.out.println("Размер массива TrueRange = " + sizeOfArray + "\n");
        for (int i = 0; i < sizeOfArray; i++) {
            System.out.println("index = " + i + " | True range: " + calculateTR.calculateTrueRangeReturnDoubleArray(candlesResponse)[i]);
        }


        double atr = calculateTR.calculateTrueRangeReturnDoubleArray(candlesResponse)[0];
        for (int i = 1; i < calculateTR.calculateTrueRangeReturnDoubleArray(candlesResponse).length; i++) {
            double tr = calculateTR.calculateTrueRangeReturnDoubleArray(candlesResponse)[i];
            atr = ((period - 1) * atr + tr) / period;
            System.out.println(atr);
        }
        System.out.println("______" + atr);
    }

    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR)")
    public void testSuperTrend21062023() throws IOException {
       superTrend21062023.testSuperTrend();
    }

}




