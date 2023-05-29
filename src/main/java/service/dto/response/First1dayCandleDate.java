package service.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class First1dayCandleDate{

    public First1dayCandleDate(String first1dayCandleDate) {
        this.first1dayCandleDate = first1dayCandleDate;
    }

    public String getFirst1dayCandleDate() {
        return first1dayCandleDate;
    }

    private  String first1dayCandleDate;

}
