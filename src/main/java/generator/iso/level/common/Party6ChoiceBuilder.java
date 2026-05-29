package generator.iso.level.common;

import java.util.List;

import generator.iso.level.AbstractBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.GenericOrganisationIdentification1;
import iso.std.iso._20022.tech.xsd.pain_001_001.OrganisationIdentification4;
import iso.std.iso._20022.tech.xsd.pain_001_001.OrganisationIdentificationSchemeName1Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.Party6Choice;

public class Party6ChoiceBuilder extends AbstractBuilder {

	public static Party6Choice builder (String value ,String cd) {
		
		OrganisationIdentificationSchemeName1Choice schmeNm = isoFactory.createOrganisationIdentificationSchemeName1Choice();
		schmeNm.setCd(cd);
		
		GenericOrganisationIdentification1 oId = isoFactory.createGenericOrganisationIdentification1();

		oId.setId(value);
		oId.setSchmeNm(schmeNm);
		
		OrganisationIdentification4 orgId = isoFactory.createOrganisationIdentification4();
		List<GenericOrganisationIdentification1> othr = orgId.getOthr();
		
		othr.add(oId);
		Party6Choice party6Choice = isoFactory.createParty6Choice();
		party6Choice.setOrgId(orgId);
		return  party6Choice;
	}
}
