package generator.iso.level.PmtInf;

import generator.iso.level.AbstractBuilder;

import generator.iso.level.common.Party6ChoiceBuilder;
import generator.iso.level.common.PartyIdentification32Builder;
import iso.std.iso._20022.tech.xsd.pain_001_001.Party6Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.PartyIdentification32;
import static generator.iso.constants.Iso20022.*;

public class DebtorBuilder extends AbstractBuilder{
	public static PartyIdentification32 builder() {
		PartyIdentification32 debtorAgent = PartyIdentification32Builder.builder(keyWord.getCompanyName(), keyWord.getDebtorCountry());
		Party6Choice party6Choice = Party6ChoiceBuilder.builder(keyWord.getDebtorAgreement(), BANK);
		debtorAgent.setId(party6Choice);
		return debtorAgent;
	}

}
