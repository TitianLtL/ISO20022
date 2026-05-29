package vo;

/*
 * <CdtTrfTxInf>
				<PmtId>
				<EndToEndId>PG12</EndToEndId>
				</PmtId>
				<Amt>
					<InstdAmt Ccy="SEK">100.00</InstdAmt>
				</Amt>
				<Cdtr>
					<Nm>NAME OF CREDITOR</Nm>
					<PstlAdr>
						<Ctry>SE</Ctry>
					</PstlAdr>
				</Cdtr>
				<CdtrAcct>
					<Id>
						<Othr>
							<Id>1234567</Id>
							<SchmeNm>
								<Cd>BBAN</Cd>
							</SchmeNm>
						</Othr>
					</Id>
				</CdtrAcct>
				<RmtInf>
					<Ustrd>payment of invoice number 789456 2014-03-01</Ustrd>
				</RmtInf>
			</CdtTrfTxInf>
 */
public class CreditVo {
	private String bbanAcoount ;
	private String rmtInf;
	private String amount;
	private String currency;
	private String instrId;
	private String endToEndId;
	private String executionDate;
	private NameAndCountry nameAndCountry = new NameAndCountry();

	public CreditVo(String[] valuse) {
		this.nameAndCountry.setCompanyName(valuse[0]);
		this.bbanAcoount = valuse[1];
		this.amount  = valuse[2];
		this.currency = valuse[3];
		this.rmtInf = valuse[4];
	}

	public String getBbanAcoount() {
		return bbanAcoount;
	}

	
	public String getRmtInf() {
		return rmtInf;
	}

	public String getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public NameAndCountry getNameAndCountry() {
		return nameAndCountry;
	}

	public void setNameAndCountry(NameAndCountry nameAndCountry) {
		this.nameAndCountry = nameAndCountry;
	}

	public String getInstrId() {
		return instrId;
	}

	public void setInstrId(String instrId) {
		this.instrId = instrId;
	}

	public String getEndToEndId() {
		return endToEndId;
	}

	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}

	public String getExecutionDate() {
		return executionDate;
	}

}
