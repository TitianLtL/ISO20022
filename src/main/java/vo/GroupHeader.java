package vo;

import util.AbstractKeyWord;

/*
 		<GrpHdr>
			<MsgId>PG12</MsgId>
			<CreDtTm>2014-03-04T09:30:47+01:00</CreDtTm>
			<NbOfTxs>1</NbOfTxs>
			<InitgPty>
				<Id>
					<OrgId>
						<Othr>
							<Id>PAYABLETEST</Id>
							<SchmeNm>
								<Cd>CUST</Cd>
							</SchmeNm>
						</Othr>
					</OrgId>
				</Id>
			</InitgPty>
		</GrpHdr>
 */
public class GroupHeader extends AbstractKeyWord{
	private String msgId;
	private String creDtTm;
	private String nbOfTxs;
	private String ctrlSum;
	private String id  = keyWord.getSignerId();
	private String cd ="CUST";

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getCreDtTm() {
		return creDtTm;
	}

	public void setCreDtTm(String creDtTm) {
		this.creDtTm = creDtTm;
	}

	public String getNbOfTxs() {
		return nbOfTxs;
	}

	public void setNbOfTxs(String nbOfTxs) {
		this.nbOfTxs = nbOfTxs;
	}

	public String getCtrlSum() {
		return ctrlSum;
	}

	public void setCtrlSum(String ctrlSum) {
		this.ctrlSum = ctrlSum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

}
