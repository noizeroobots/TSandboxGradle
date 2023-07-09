package database.candles.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candles {

    private String time;
    private String high;
    private String low;
    private String open;
    private String close;
    private String volume;
    private String isComplete;
}
