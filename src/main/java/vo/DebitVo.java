package vo;

import iso.std.iso._20022.tech.xsd.pain_001_001.PaymentMethod3Code;
import iso.std.iso._20022.tech.xsd.pain_001_001.Priority2Code;
import util.AbstractKeyWord;

/*
 * 			<Dbtr>
				<Nm>NAME OF DEBTOR</Nm>
				<PstlAdr>
					<Ctry>SE</Ctry>
				</PstlAdr>
				<Id>
					<OrgId>
					<Othr>
						<Id>123456789</Id>
						<SchmeNm>
							<Cd>CUST</Cd>
						</SchmeNm>
					</Othr>
					</OrgId>
				</Id>
			</Dbtr>
			<DbtrAcct>
				<Id>
					<IBAN>SE6795000099604247929021</IBAN>
				</Id>
			</DbtrAcct>
			<DbtrAgt>
				<FinInstnId>
					<BIC>NDEASESS</BIC>
				</FinInstnId>
			</DbtrAgt>
 */
public class DebitVo extends AbstractKeyWord {
	private String iban;
	private PaymentMethod3Code paymentMtd = PaymentMethod3Code.TRF;
	private Priority2Code instrPrty = Priority2Code.NORM;
	private NameAndCountry nameAndCountry;

	public DebitVo(String iban, PaymentMethod3Code paymentMtd, String instrPrty) {
		this.iban = iban;
	}

	public DebitVo() {
		this.iban = keyWord.getDebtorAccountIban();
		this.nameAndCountry = new NameAndCountry(keyWord.getDebtorName(), keyWord.getDebtorCountry());
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public PaymentMethod3Code getPaymentMtd() {
		return paymentMtd;
	}

	public void setPaymentMtd(PaymentMethod3Code paymentMtd) {
		this.paymentMtd = paymentMtd;
	}

	public Priority2Code getInstrPrty() {
		return instrPrty;
	}

	public void setInstrPrty(Priority2Code instrPrty) {
		this.instrPrty = instrPrty;
	}

	public NameAndCountry getNameAndCountry() {
		return nameAndCountry;
	}

	public void setNameAndCountry(NameAndCountry nameAndCountry) {
		this.nameAndCountry = nameAndCountry;
	}

}
