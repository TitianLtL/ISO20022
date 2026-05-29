package generator.iso.level.PmtInf;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import generator.iso.constants.Iso20022;
import generator.iso.level.AbstractBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.ActiveOrHistoricCurrencyAndAmount;
import iso.std.iso._20022.tech.xsd.pain_001_001.AmountType3Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.CreditTransferTransactionInformation10;
import iso.std.iso._20022.tech.xsd.pain_001_001.PaymentIdentification1;
import vo.CreditVo;
import vo.FormVo;

public class CreditorBuilder extends AbstractBuilder {
	@Deprecated
	public static CreditTransferTransactionInformation10 builder(String endToEndId, String currency, BigDecimal value,
			String creditorName, String creditorCountry, String toAccount, String bban, String... freeText) {

		PaymentIdentification1 pmtId = isoFactory.createPaymentIdentification1();
		pmtId.setEndToEndId(endToEndId);
		pmtId.setInstrId(endToEndId);

		ActiveOrHistoricCurrencyAndAmount currencyAndAmount = isoFactory.createActiveOrHistoricCurrencyAndAmount();
		if (StringUtils.isEmpty(currency)) {
			currencyAndAmount.setCcy(keyWord.getDebtorCurrency(keyWord.getDebtorCountry()));
		} else {
			currencyAndAmount.setCcy(currency);
		}
		currencyAndAmount.setValue(value);

		AmountType3Choice amt = isoFactory.createAmountType3Choice();
		amt.setInstdAmt(currencyAndAmount);
		CreditTransferTransactionInformation10 creTraInf = isoFactory.createCreditTransferTransactionInformation10();

		creTraInf.setPmtId(pmtId);
		creTraInf.setAmt(amt);
		creTraInf.setCdtr(CreditorAgentBuilder.builder(creditorName, creditorCountry));
		if (StringUtils.isEmpty(bban)) {
			creTraInf.setCdtrAcct(CreditorAccountBuilder.builderIban(toAccount));
		} else {
			creTraInf.setCdtrAcct(CreditorAccountBuilder.builderBban(toAccount, bban));
		}
		creTraInf.setRmtInf(CreditorRemitInfor.builderUnstructured(freeText));

		return creTraInf;
	}

	public static CreditTransferTransactionInformation10 builder(FormVo formVo, CreditVo credit, int index) {

		PaymentIdentification1 pmtId = isoFactory.createPaymentIdentification1();
		pmtId.setEndToEndId(formVo.getEndToEndId() + "-" + index);
		pmtId.setInstrId(formVo.getInstrutionId() + "-" + index);

		ActiveOrHistoricCurrencyAndAmount currencyAndAmount = isoFactory.createActiveOrHistoricCurrencyAndAmount();
		currencyAndAmount.setCcy(credit.getCurrency());
		BigDecimal amount = new BigDecimal(credit.getAmount());
		currencyAndAmount.setValue(amount);

		AmountType3Choice amt = isoFactory.createAmountType3Choice();
		amt.setInstdAmt(currencyAndAmount);
		CreditTransferTransactionInformation10 creTraInf = isoFactory.createCreditTransferTransactionInformation10();

		creTraInf.setPmtId(pmtId);
		creTraInf.setAmt(amt);
		creTraInf.setCdtr(CreditorAgentBuilder.builder(credit.getNameAndCountry().getCompanyName(),credit.getNameAndCountry().getCompanyCountry()));
		creTraInf.setCdtrAcct(CreditorAccountBuilder.builderBban(credit.getBbanAcoount(), Iso20022.BBAN));
		creTraInf.setRmtInf(CreditorRemitInfor.builderUnstructured(credit.getRmtInf()));

		return creTraInf;
	}

}
