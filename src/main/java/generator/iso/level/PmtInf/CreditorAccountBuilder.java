package generator.iso.level.PmtInf;

import generator.iso.level.AbstractBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.AccountIdentification4Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.AccountSchemeName1Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.CashAccount16;
import iso.std.iso._20022.tech.xsd.pain_001_001.GenericAccountIdentification1;

public class CreditorAccountBuilder extends AbstractBuilder{
	
	public static CashAccount16 builderBban (String toAccount,String ban){
		
		AccountIdentification4Choice accountId = isoFactory.createAccountIdentification4Choice();
		
		AccountSchemeName1Choice sche = isoFactory.createAccountSchemeName1Choice();
		sche.setCd(ban);
		
		GenericAccountIdentification1 account = isoFactory.createGenericAccountIdentification1();
		accountId.setOthr(account);
		account.setId(toAccount);
		account.setSchmeNm(sche);
		
		CashAccount16 cashAccount = isoFactory.createCashAccount16();
		cashAccount.setId(accountId);
		
		return cashAccount;
	}
	
	public static CashAccount16 builderIban (String iban){
		AccountIdentification4Choice accountId = isoFactory.createAccountIdentification4Choice();
		accountId.setIBAN(iban);
		
		CashAccount16 cashAccount = isoFactory.createCashAccount16();
		cashAccount.setId(accountId);
		return cashAccount;
	}

}
