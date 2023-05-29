import algorithms.SuperTrend;
import config.BaseConfig;
import delegats.FindOpenCloseLevel;
import io.qameta.allure.Epic;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@Epic("Supertrend test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SuperTrendTest {

    private SuperTrend superTrendClient;
    private FindOpenCloseLevel findOpenCloseLevel;

    @BeforeAll
    void setUp() {
        BaseConfig config = ConfigFactory.create(BaseConfig.class);

    }

    @Test
    @DisplayName("Get candles test")
    public void testCandlesTest() throws IOException {
        final double[] HIGH = new double[] {39.975,39.970,39.970,39.990,39.975,39.975,40.000,40.000,39.995,40.000,40.300,40.100,40.250,40.200,40.150,40.150};
        final double[] LOW = new double[] {39.970,39.965,39.955,39.970,39.975,39.970,39.970,39.995,39.970,39.975,39.990,39.995,39.995,40.000,39.995,39.985};
        final double[] CLOSE = new double[] {39.975,39.965,39.970,39.980,39.975,39.975,40.000,39.995,39.975,40.000,40.200,40.000,40.200,40.000,40.000,39.990};
        final int PERIOD = 3;

        final double MULTIPLIER = 0.5;
        final double ATR_FACTOR = 1;
        superTrendClient = new SuperTrend(HIGH, LOW, CLOSE, PERIOD, MULTIPLIER, ATR_FACTOR, "time");
    }
}


