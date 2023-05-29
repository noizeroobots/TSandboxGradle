import delegats.Graph;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

@Epic("Get history data test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GraphTest {

    Graph graph = new Graph("sdf");

    @Test
    public void test(){

        //graph.createDemoPanel();
    }

}
