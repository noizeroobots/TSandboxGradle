package service.marketDataService.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCandlesResponse {


    List<CandlesItem> candles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CandlesItem {
        String volume;
        High high;
        Low low;
        String time;
        Close close;
        Open open;
        @JsonIgnoreProperties
        private Boolean isComplete;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Close {
        int nano;
        String units;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class High {
        int nano;
        String units;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Low {
        int nano;
        String units;
    }

   @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Open {
        int nano;
        @JsonIgnoreProperties
        String units;
    }
}
