package service.sandboxService.dto.response.postsandboxorder;

public class ExecutedOrderPrice{
	private int nano;
	private String currency;
	private String units;

	public int getNano(){
		return nano;
	}

	public String getCurrency(){
		return currency;
	}

	public String getUnits(){
		return units;
	}
}
