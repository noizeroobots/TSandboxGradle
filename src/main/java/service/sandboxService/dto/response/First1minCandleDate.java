package service.sandboxService.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class First1minCandleDate{
    public First1minCandleDate(String first1minCandleDate) {
        this.first1minCandleDate = first1minCandleDate;
    }

    private  String first1minCandleDate;

    public String getFirst1minCandleDate(){
        return first1minCandleDate;
    }
}
