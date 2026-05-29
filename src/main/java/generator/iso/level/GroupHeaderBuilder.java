package generator.iso.level;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import generator.iso.level.GrpHdr.InitiatingPatryBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.GroupHeader32;
import iso.std.iso._20022.tech.xsd.pain_001_001.ObjectFactory;
import util.DateUtil;
import util.IsoFactoryUtil;

public class GroupHeaderBuilder extends AbstractBuilder {

	public static GroupHeader32 build( Date date ,String transation,BigDecimal ctrlSum ) {
		GroupHeader32 gh = isoFactory.createGroupHeader32();
		XMLGregorianCalendar xmlGregorianCalendar = DateUtil.getXMLGregorianCalendarCurrentDate(date);
		gh.setCreDtTm(xmlGregorianCalendar);
		gh.setMsgId(Long.toString(date.getTime()));
		gh.setNbOfTxs(transation);
//		gh.setCtrlSum(ctrlSum); 
		gh.setInitgPty(InitiatingPatryBuilder.build());
		return gh;
	}

}
