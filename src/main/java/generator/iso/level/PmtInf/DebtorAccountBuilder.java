package generator.iso.level.PmtInf;

import generator.iso.level.AbstractBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.AccountIdentification4Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.CashAccount16;

public class DebtorAccountBuilder extends AbstractBuilder{
	public static CashAccount16 builder (){
		
		AccountIdentification4Choice accountId = isoFactory.createAccountIdentification4Choice();
		accountId.setIBAN(keyWord.getDebtorAccountIban());
		CashAccount16 account = isoFactory.createCashAccount16();
		account.setId(accountId);
		account.setCcy(keyWord.getDebtorCurrency(keyWord.getDebtorCountry()));
		return account;
	}
	
}
