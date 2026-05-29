package generator.iso.level.common;

import generator.iso.level.AbstractBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.PartyIdentification32;
import iso.std.iso._20022.tech.xsd.pain_001_001.PostalAddress6;

public class PartyIdentification32Builder extends AbstractBuilder {
	public static PartyIdentification32 builder(String name ,String country) {
		PostalAddress6 address = isoFactory.createPostalAddress6();
		address.setCtry(country);
//		address.setTwnNm("towm");
//		address.setPstCd("111111");

		PartyIdentification32 agent = isoFactory.createPartyIdentification32();
		agent.setNm(name);
		agent.setPstlAdr(address);
		return agent;
	}
}
