package generator.iso.level.PmtInf;

import generator.iso.level.AbstractBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.BranchAndFinancialInstitutionIdentification4;
import iso.std.iso._20022.tech.xsd.pain_001_001.FinancialInstitutionIdentification7;
import iso.std.iso._20022.tech.xsd.pain_001_001.PostalAddress6;

public class DebtorAgentBuilder extends AbstractBuilder {
	
	public static BranchAndFinancialInstitutionIdentification4 builder(){
		BranchAndFinancialInstitutionIdentification4 finInstnId = isoFactory.createBranchAndFinancialInstitutionIdentification4();
		FinancialInstitutionIdentification7 bic = isoFactory.createFinancialInstitutionIdentification7();
		bic.setBIC(keyWord.getDebtorBic(keyWord.getDebtorCountry()));
		PostalAddress6 posAd= isoFactory.createPostalAddress6();
		posAd.setCtry(keyWord.getDebtorCountry());
		bic.setPstlAdr(posAd);
		finInstnId.setFinInstnId(bic);
		return finInstnId;
	}

}
