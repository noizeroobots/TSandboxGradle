package otherTests;

import delegats.CalculateTR;
import delegats.candles.ConvertCandlesToDoubleArrays;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.marketDataService.dto.response.GetCandlesResponse;

import java.io.IOException;
import java.util.List;

@Epic("Connection test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrueRangeTest {

    private CalculateTR calculateTR;
    private ConvertCandlesToDoubleArrays convertCandlesToDoubleArrays;
    private String FIGI = "BBG00178PGX3"; // VCKO

    @BeforeAll
    void setUp() {
        calculateTR = new CalculateTR();
        convertCandlesToDoubleArrays = new ConvertCandlesToDoubleArrays();

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
            System.out.println("index = " + i + " | True range: " + trueRange.get(i));
        }
    }

    /**
     * Определение истинного диапазона (True Range, TR)
     * Возвращает List<Double> для дальнейшей работы
     */
    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR)")
    public void returnListDoubleTrueRange() throws IOException {
        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(3, FIGI);
        List<Double> trueRange = calculateTR.calculateTrueRangeReturnDoubleList(candlesResponse);
        for (int i = 0; i < trueRange.size(); i++) {
            System.out.println("index = " + i + " | True range: " + trueRange.get(i));
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
        int sizeOfArray = calculateTR.calculateTrueRangeReturnDoubleArray(candlesResponse).length;
        System.out.println("Размер массива TrueRange = " + sizeOfArray + "\n");
        for (int i = 0; i < sizeOfArray; i++) {
            System.out.println("index = " + i + " | True range: " + calculateTR.calculateTrueRangeReturnDoubleArray(candlesResponse)[i]);
        }
    }
}
