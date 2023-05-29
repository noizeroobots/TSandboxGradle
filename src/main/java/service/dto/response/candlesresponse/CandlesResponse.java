package service.dto.response.candlesresponse;

import java.util.List;

public class CandlesResponse {
	private List<CandlesItem> candles;

	public List<CandlesItem> getCandles(){
		return candles;
	}
}