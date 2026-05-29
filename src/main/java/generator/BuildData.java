package generator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import generator.iso.constants.Iso20022;
import generator.iso.level.AbstractBuilder;
import generator.iso.level.GroupHeaderBuilder;
import generator.iso.level.PaymentInstructionInformationBuilder;
import generator.iso.level.PmtInf.CreditorBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.CreditTransferTransactionInformation10;
import iso.std.iso._20022.tech.xsd.pain_001_001.CustomerCreditTransferInitiationV03;
import iso.std.iso._20022.tech.xsd.pain_001_001.Document;
import iso.std.iso._20022.tech.xsd.pain_001_001.GroupHeader32;
import iso.std.iso._20022.tech.xsd.pain_001_001.PaymentInstructionInformation3;
import iso.std.iso._20022.tech.xsd.pain_001_001.PaymentMethod3Code;
import iso.std.iso._20022.tech.xsd.pain_001_001.Priority2Code;
import util.DateUtil;
import vo.CreditVo;
import vo.CreditsVo;
import vo.DebitVo;
import vo.FormVo;


public class BuildData extends AbstractBuilder{
	
	private static Date date = new Date();

	public static void main(String[] args)  throws Exception {
		/*
		 * run first.
		 * workspace\projects\generator\src\test\resources\xsd>xjc pain.001.001.03.xsd -d ..\..\..\main\java
		 */
		BuildData data = new BuildData ();
		data.bulidFile();
	}
	
	public String   bulidFile(FormVo formVo, DebitVo debit, CreditsVo credits) throws Exception{
		Document doc = isoFactory.createDocument();
		CustomerCreditTransferInitiationV03 createCustomerCreditTransferInitiationV03 = isoFactory.createCustomerCreditTransferInitiationV03();
		doc.setCstmrCdtTrfInitn(createCustomerCreditTransferInitiationV03);
		Date reqExecutionDate = DateUtil.parseLocalDateWithHyphens(formVo.getExecutionDate());
		PaymentInstructionInformation3 paymentInstructionInformation3 = 
				PaymentInstructionInformationBuilder.builder(reqExecutionDate, debit.getPaymentMtd(), debit.getInstrPrty(), Iso20022.NORMAL_URGENCY, Iso20022.SUPPLIER_PAYMENT);
		
		createCustomerCreditTransferInitiationV03.getPmtInf().add(paymentInstructionInformation3);
		int index  = 0;
		for (CreditVo vo : credits.getVos() ){
			index ++; 
			CreditTransferTransactionInformation10 creditor = CreditorBuilder.builder(formVo,vo, index);
			paymentInstructionInformation3.getCdtTrfTxInf().add(creditor);
		}
		
		GroupHeader32 createGroupHeader32 = GroupHeaderBuilder.build(date,Integer.toString(index),credits.getLumpsum());
		createCustomerCreditTransferInitiationV03.setGrpHdr(createGroupHeader32);
		
		
		JAXBContext context = JAXBContext.newInstance("iso.std.iso._20022.tech.xsd.pain_001_001");
		JAXBElement<Document> element = isoFactory.createDocument(doc);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
//		marshaller.marshal(element,System.out);
		String folder = keyWord.getStoreFolder();
		FileOutputStream fileOuputStream = new FileOutputStream(folder);
		
		marshaller.marshal(element,fileOuputStream);
		fileOuputStream.close();
		return folder;
	}
	
	@Deprecated
	public void bulidFile() throws JAXBException, FileNotFoundException{
		Document doc = isoFactory.createDocument();
		CustomerCreditTransferInitiationV03 createCustomerCreditTransferInitiationV03 = isoFactory.createCustomerCreditTransferInitiationV03();
		doc.setCstmrCdtTrfInitn(createCustomerCreditTransferInitiationV03);

		PaymentInstructionInformation3 paymentInstructionInformation3 = 
				PaymentInstructionInformationBuilder.builder(date, PaymentMethod3Code.TRF, Priority2Code.NORM, Iso20022.NORMAL_URGENCY, Iso20022.SUPPLIER_PAYMENT);
		
		createCustomerCreditTransferInitiationV03.getPmtInf().add(paymentInstructionInformation3);
		// loop
		CreditTransferTransactionInformation10 creditor = CreditorBuilder.builder("endToEndId", null, BigDecimal.TEN ,"Tian 1", "DK","1234599","BBAN","free text1", "free text2");
		paymentInstructionInformation3.getCdtTrfTxInf().add(creditor);
		
		creditor =  CreditorBuilder.builder("endToEndId", null, BigDecimal.ONE ,"Tian 2", "DKK","DK1234567890111",null,"free text1", "free text2");
		paymentInstructionInformation3.getCdtTrfTxInf().add(creditor);
		
		GroupHeader32 createGroupHeader32 = GroupHeaderBuilder.build( date ,"1",BigDecimal.TEN);
		createCustomerCreditTransferInitiationV03.setGrpHdr(createGroupHeader32);
		
		
		JAXBContext context = JAXBContext.newInstance("iso.std.iso._20022.tech.xsd.pain_001_001");
		JAXBElement<Document> element = isoFactory.createDocument(doc);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
		marshaller.marshal(element,System.out);
//		String folder = keyWord.getStoreFolder();
////		FileOutputStream fileOuputStream = new FileOutputStream(folder);
//		XMLStreamBufferResult result= new XMLStreamBufferResult ();
//		marshaller.marshal(element,result);
//		MutableXMLStreamBuffer xmlStreamBuffer = result.getXMLStreamBuffer();
		
	}
	 

}
