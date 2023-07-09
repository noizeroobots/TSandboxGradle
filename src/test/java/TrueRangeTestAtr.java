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
public class TrueRangeTestAtr {

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
     * Возвращает List<Double> для дальнейшей работы
     */
    @Test
    @DisplayName("Определение истинного диапазона (True Range, TR)")
    public void returnListDoubleTrueRange() throws IOException {
        GetCandlesResponse candlesResponse = convertCandlesToDoubleArrays.getCandles(3, FIGI);
        List<Double> trueRange = calculateTR.calculateTrueRangeReturnDoubleList(candlesResponse);
        List<Double> result = new ArrayList<>();

        for (int i = 0; i <= trueRange.size() - 10; i++) {
            Double sum = trueRange.get(i)
                    + trueRange.get(i + 1)
                    + trueRange.get(i + 2)
                    + trueRange.get(i + 3)
                    + trueRange.get(i + 4)
                    + trueRange.get(i + 5)
                    + trueRange.get(i + 6)
                    + trueRange.get(i + 7)
                    + trueRange.get(i + 8)
                    + trueRange.get(i + 9);
            Double average = sum / 10;
            result.add(average);
        }
//        for (int i = 0; i < trueRange.size(); i++) {
//            System.out.println("index = " + i + " | True range: " + trueRange.get(i));
//        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println("index = " + i + " | ATR: " + result.get(i));
        }

    }
}
