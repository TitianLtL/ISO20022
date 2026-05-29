package generator.iso.level;

import java.util.Date;

import generator.iso.level.PmtInf.DebtorAccountBuilder;
import generator.iso.level.PmtInf.DebtorAgentBuilder;
import generator.iso.level.PmtInf.DebtorBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.CashAccount16;
import iso.std.iso._20022.tech.xsd.pain_001_001.CategoryPurpose1Choice;
import iso.std.iso._20022.tech.xsd.pain_001_001.PaymentInstructionInformation3;
import iso.std.iso._20022.tech.xsd.pain_001_001.PaymentMethod3Code;
import iso.std.iso._20022.tech.xsd.pain_001_001.PaymentTypeInformation19;
import iso.std.iso._20022.tech.xsd.pain_001_001.Priority2Code;
import iso.std.iso._20022.tech.xsd.pain_001_001.ServiceLevel8Choice;
import util.DateUtil;

public class PaymentInstructionInformationBuilder extends AbstractBuilder {
	
		public static PaymentInstructionInformation3 builder(Date date,PaymentMethod3Code pm ,Priority2Code p2, String slCd, String cpcCd ){
			ServiceLevel8Choice sl = isoFactory.createServiceLevel8Choice();
			sl.setCd(slCd);
			
			CategoryPurpose1Choice cpc = isoFactory.createCategoryPurpose1Choice();
			cpc.setCd(cpcCd);
			
			PaymentTypeInformation19 type = isoFactory.createPaymentTypeInformation19();
			type.setInstrPrty(p2);
			type.setSvcLvl(sl);
			type.setCtgyPurp(cpc);
			
			PaymentInstructionInformation3 pmtInf = isoFactory.createPaymentInstructionInformation3();
			pmtInf.setPmtInfId("PmtInfId"+date.getTime());
			pmtInf.setPmtMtd(pm);
			pmtInf.setPmtTpInf(type);
			pmtInf.setReqdExctnDt(DateUtil.getXMLGregorianCalendarNextDate(date,0));
			pmtInf.setDbtr(DebtorBuilder.builder());
			pmtInf.setDbtrAcct(DebtorAccountBuilder.builder());
			
			pmtInf.setDbtrAgt(DebtorAgentBuilder.builder());
			return pmtInf;
		}

}
