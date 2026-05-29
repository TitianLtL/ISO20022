package generator.iso.level.GrpHdr;

import generator.iso.level.AbstractBuilder;
import generator.iso.level.common.Party6ChoiceBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.Party6Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.PartyIdentification32;
import static generator.iso.constants.Iso20022.CUST;
public class InitiatingPatryBuilder extends AbstractBuilder {
	
	public static PartyIdentification32 build (){
		
		PartyIdentification32 initgPty = isoFactory.createPartyIdentification32();
		Party6Choice party6Choice = Party6ChoiceBuilder.builder(keyWord.getSignerId(),CUST);
		initgPty.setId(party6Choice);
		return initgPty;
	}
	

}
