package service.dto.response;

public class InstrumentsItem{
	private String figi;
	private DshortMin dshortMin;
	private String countryOfRisk;
	private int lot;
	private String uid;
	private boolean blockedTcaFlag;
	private Dlong dlong;
	private Nominal nominal;
	private boolean sellAvailableFlag;
	private String currency;
	private First1dayCandleDate first1dayCandleDate;
	private String sector;
	private boolean buyAvailableFlag;
	private boolean weekendFlag;
	private String classCode;
	private String ticker;
	private boolean forQualInvestorFlag;
	private boolean liquidityFlag;
	private boolean forIisFlag;
	private String positionUid;
	private boolean apiTradeAvailableFlag;
	private DlongMin dlongMin;
	private boolean shortEnabledFlag;
	private Kshort kshort;
	private First1minCandleDate first1minCandleDate;
	private String issueSizePlan;
	private MinPriceIncrement minPriceIncrement;
	private boolean otcFlag;
	private Klong klong;
	private Dshort dshort;
	private String name;
	private String issueSize;
	private String exchange;
	private String countryOfRiskName;
	private boolean divYieldFlag;
	private String isin;
	private IpoDate ipoDate;
	private String tradingStatus;

	public String getRealExchange() {
		return realExchange;
	}

	private String realExchange;

	public String getShareType() {
		return shareType;
	}

	private String shareType;

	public String getTradingStatus() {
		return tradingStatus;
	}


	public String getFigi(){
		return figi;
	}

	public DshortMin getDshortMin(){
		return dshortMin;
	}

	public String getCountryOfRisk(){
		return countryOfRisk;
	}

	public int getLot(){
		return lot;
	}

	public String getUid(){
		return uid;
	}

	public boolean isBlockedTcaFlag(){
		return blockedTcaFlag;
	}

	public Dlong getDlong(){
		return dlong;
	}

	public Nominal getNominal(){
		return nominal;
	}

	public boolean isSellAvailableFlag(){
		return sellAvailableFlag;
	}

	public String getCurrency(){
		return currency;
	}

	public First1dayCandleDate getFirst1dayCandleDate(){
		return first1dayCandleDate;
	}

	public String getSector(){
		return sector;
	}

	public boolean isBuyAvailableFlag(){
		return buyAvailableFlag;
	}

	public boolean isWeekendFlag(){
		return weekendFlag;
	}

	public String getClassCode(){
		return classCode;
	}

	public String getTicker(){
		return ticker;
	}

	public boolean isForQualInvestorFlag(){
		return forQualInvestorFlag;
	}

	public boolean isLiquidityFlag(){
		return liquidityFlag;
	}

	public boolean isForIisFlag(){
		return forIisFlag;
	}

	public String getPositionUid(){
		return positionUid;
	}

	public boolean isApiTradeAvailableFlag(){
		return apiTradeAvailableFlag;
	}

	public DlongMin getDlongMin(){
		return dlongMin;
	}

	public boolean isShortEnabledFlag(){
		return shortEnabledFlag;
	}

	public Kshort getKshort(){
		return kshort;
	}

	public First1minCandleDate getFirst1minCandleDate(){
		return first1minCandleDate;
	}

	public String getIssueSizePlan(){
		return issueSizePlan;
	}

	public MinPriceIncrement getMinPriceIncrement(){
		return minPriceIncrement;
	}

	public boolean isOtcFlag(){
		return otcFlag;
	}

	public Klong getKlong(){
		return klong;
	}

	public Dshort getDshort(){
		return dshort;
	}

	public String getName(){
		return name;
	}

	public String getIssueSize(){
		return issueSize;
	}

	public String getExchange(){
		return exchange;
	}

	public String getCountryOfRiskName(){
		return countryOfRiskName;
	}

	public boolean isDivYieldFlag(){
		return divYieldFlag;
	}

	public String getIsin(){
		return isin;
	}

	public IpoDate getIpoDate(){
		return ipoDate;
	}
}
