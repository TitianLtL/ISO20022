package sign;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore.PrivateKeyEntry;

import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import sign.vo.SignVo;
import util.KeyWordUtil;

public class SignFile {
	/*
	 * step23 admin//test-files/sign/
	 * PAYID-26-09-2016-11-08-005.xml_Wrapped_20160926_110814.xml
	 * Certificate_CNA%20SITE%20PS%20CORE%201820337216.p12 pstest
	 */
	private static KeyWordUtil util = new KeyWordUtil();
	
	public static void main(String[] args)  throws Exception {
		SignFile sf = new SignFile();
		XmlSigningBuilder builder = new XmlSigningBuilder();
		String password = util.getCertificatePassword();
		String storeFolder = util.getStoreFolder();
		String fileToBeSignedPath  = storeFolder+util.getWrapFileExe();
		SignVo vo = builder.signbuilder(fileToBeSignedPath, password);
		sf.signXml(vo);
		String fileSignedPath  = storeFolder+util.getSignedFileExe();
		sf.writeResultingDocument(vo.getDoc(),fileSignedPath);
	}
	
	public void signXml(SignVo vo) throws Exception {
		signDocumentAndPlaceSignatureAsLastChildElement(vo.getDoc(), vo.getKeyEntry(), vo.getSignatureFactory(),
				vo.getSi(), vo.getKi());
	}

	protected void signDocumentAndPlaceSignatureAsLastChildElement(Document doc, PrivateKeyEntry keyEntry,
			XMLSignatureFactory signatureFactory, SignedInfo signedInfo, KeyInfo keyInfo) throws Exception {
		DOMSignContext signContext = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement(),
				doc.getDocumentElement().getLastChild());
		XMLSignature signature = signatureFactory.newXMLSignature(signedInfo, keyInfo);
		signature.sign(signContext);
	}

	public void writeResultingDocument(Document doc, String signedFileSavePath) throws Exception {
		OutputStream os = new FileOutputStream(signedFileSavePath);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.transform(new DOMSource(doc), new StreamResult(os));
	}

}
