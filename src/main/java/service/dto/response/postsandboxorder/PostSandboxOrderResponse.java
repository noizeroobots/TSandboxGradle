package service.dto.response.postsandboxorder;

import lombok.Data;

@Data
public class PostSandboxOrderResponse {
	private String orderType;
	private String orderId;
	private String figi;
	private InitialOrderPrice initialOrderPrice;
	private InitialCommission initialCommission;
	private String message;
	private String lotsExecuted;
	private TotalOrderAmount totalOrderAmount;
	private String lotsRequested;
	private String instrumentUid;
	private ExecutedOrderPrice executedOrderPrice;
	private ExecutedCommission executedCommission;
	private InitialSecurityPrice initialSecurityPrice;
	private String executionReportStatus;
	private String direction;

	public String getOrderType(){
		return orderType;
	}

	public String getOrderId(){
		return orderId;
	}

	public String getFigi(){
		return figi;
	}

	public InitialOrderPrice getInitialOrderPrice(){
		return initialOrderPrice;
	}

	public InitialCommission getInitialCommission(){
		return initialCommission;
	}

	public String getMessage(){
		return message;
	}

	public String getLotsExecuted(){
		return lotsExecuted;
	}

	public TotalOrderAmount getTotalOrderAmount(){
		return totalOrderAmount;
	}

	public String getLotsRequested(){
		return lotsRequested;
	}

	public String getInstrumentUid(){
		return instrumentUid;
	}

	public ExecutedOrderPrice getExecutedOrderPrice(){
		return executedOrderPrice;
	}

	public ExecutedCommission getExecutedCommission(){
		return executedCommission;
	}

	public InitialSecurityPrice getInitialSecurityPrice(){
		return initialSecurityPrice;
	}

	public String getExecutionReportStatus(){
		return executionReportStatus;
	}

	public String getDirection(){
		return direction;
	}
}
