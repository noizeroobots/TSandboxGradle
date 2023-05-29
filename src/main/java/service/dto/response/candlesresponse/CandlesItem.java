package service.dto.response.candlesresponse;

public class CandlesItem{
	private String volume;
	private High high;
	private Low low;
	private String time;
	private Close close;
	private Open open;
	private boolean isComplete;

	public String getVolume(){
		return volume;
	}

	public High getHigh(){
		return high;
	}

	public Low getLow(){
		return low;
	}

	public String getTime(){
		return time;
	}

	public Close getClose(){
		return close;
	}

	public Open getOpen(){
		return open;
	}

	public boolean isIsComplete(){
		return isComplete;
	}
}
