package generator.iso.level.PmtInf;

import generator.iso.level.AbstractBuilder;
import generator.iso.level.common.PartyIdentification32Builder;
import iso.std.iso._20022.tech.xsd.pain_001_001.PartyIdentification32;

public class CreditorAgentBuilder extends AbstractBuilder {
	
	public static PartyIdentification32 builder(String name ,String country){
		PartyIdentification32 creditorAgent = PartyIdentification32Builder.builder(name, country);
		return creditorAgent;
	}
}
