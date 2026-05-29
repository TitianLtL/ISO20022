package generator.iso.level.PmtInf;

import java.util.Arrays;

import generator.iso.level.AbstractBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.CreditorReferenceInformation2;
import iso.std.iso._20022.tech.xsd.pain_001_001.CreditorReferenceType1Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.CreditorReferenceType2;
import iso.std.iso._20022.tech.xsd.pain_001_001.DocumentType3Code;
import iso.std.iso._20022.tech.xsd.pain_001_001.RemittanceInformation5;
import iso.std.iso._20022.tech.xsd.pain_001_001.StructuredRemittanceInformation7;

public class CreditorRemitInfor extends AbstractBuilder {
	public static RemittanceInformation5 builderStructuredRemittanceInformation(String prtry){
		
		CreditorReferenceType1Choice crtc = isoFactory.createCreditorReferenceType1Choice();
		crtc.setCd(DocumentType3Code.SCOR);
		crtc.setPrtry(prtry);
		CreditorReferenceType2 tp = isoFactory.createCreditorReferenceType2();
		tp.setCdOrPrtry(crtc);
		CreditorReferenceInformation2 cri = isoFactory.createCreditorReferenceInformation2();
		cri.setTp(tp);
		StructuredRemittanceInformation7  sri = isoFactory.createStructuredRemittanceInformation7();
		sri.setCdtrRefInf(cri);
		
		RemittanceInformation5 remtInf = isoFactory.createRemittanceInformation5();
		remtInf.getStrd().add(sri);
		return remtInf;
	}
	
	public static RemittanceInformation5 builderUnstructured(String ... texts){
		RemittanceInformation5 remtInf = isoFactory.createRemittanceInformation5();
		remtInf.getUstrd().addAll(Arrays.asList(texts));
		return remtInf;
	}
	
	
	
	

}
