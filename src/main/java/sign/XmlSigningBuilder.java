package sign;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import generator.iso.level.AbstractBuilder;
import sign.vo.SignVo;

import org.springframework.core.io.ClassPathResource;
public class XmlSigningBuilder extends AbstractBuilder {


	public SignVo signbuilder(String fileToBeSignedPath, String password)
			throws Exception {
		SignVo vo = new SignVo();
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
		vo.setSignatureFactory(fac);
		Reference ref = referenceBuilder(fac);
		vo.setRef(ref);
		SignedInfo si = getSignedInfo(fac, ref);
		vo.setSi(si);
		PrivateKeyEntry keyEntry = privatekeyBuilder(password);
		vo.setKeyEntry(keyEntry);
		KeyInfo ki = keyInfoBuilder(keyEntry, fac);
		vo.setKi(ki);
		Document doc = documnetBuilder(fileToBeSignedPath);
		vo.setDoc(doc);
		return vo;
	}

	private Reference referenceBuilder(XMLSignatureFactory signatureFactory) throws Exception {
		DigestMethod digestMethod = signatureFactory.newDigestMethod(DigestMethod.SHA1, null);
		Transform transform = signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
		List<Transform> list = Collections.singletonList(transform);
		Reference reference = signatureFactory.newReference("", digestMethod, list, null, null);
		return reference;
	}

	private SignedInfo getSignedInfo(XMLSignatureFactory signatureFactory, Reference ref) throws Exception {
		CanonicalizationMethod canonicalizationMethod = signatureFactory
				.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null);
		SignatureMethod signatureMethod = signatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
		SignedInfo signedInfo = signatureFactory.newSignedInfo(canonicalizationMethod, signatureMethod,
				Collections.singletonList(ref));
		return signedInfo;
	}

	private PrivateKeyEntry privatekeyBuilder(String password) throws Exception {
		char[] passCh = password.toCharArray();
		KeyStore keyStore = KeyStore.getInstance(keyWord.getKeyStoreType());
		InputStream inputStream = new ClassPathResource(keyWord.getCertificateFilePath()).getInputStream();
//		FileInputStream inputStream = new FileInputStream(keyWord.getCertificateFilePath());
		keyStore.load(inputStream, passCh);
		String nextElement = keyStore.aliases().nextElement();
		KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(passCh);
		PrivateKeyEntry entry = (PrivateKeyEntry) keyStore.getEntry(nextElement, passwordProtection);
		return (PrivateKeyEntry) entry;
	}

	private KeyInfo keyInfoBuilder(PrivateKeyEntry keyEntry, XMLSignatureFactory fac) {
		X509Certificate certificate = (X509Certificate) keyEntry.getCertificate();
		KeyInfoFactory keyInfoFactory = fac.getKeyInfoFactory();
		String name = certificate.getIssuerDN().getName();
		BigInteger serialNumber = certificate.getSerialNumber();
		X509IssuerSerial issuerSerial = keyInfoFactory.newX509IssuerSerial(name, serialNumber);
		List<Object> x509Content = new ArrayList<Object>();
		x509Content.add(issuerSerial);
		x509Content.add(certificate);
		X509Data data = keyInfoFactory.newX509Data(x509Content);
		KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(data));
		return keyInfo;
	}

	private Document documnetBuilder(String filePath) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(new InputSource(new FileInputStream(filePath)));
		doc = fixSpacingProblems(doc);
		return doc;
	}

	private Document fixSpacingProblems(Document doc) throws Exception {
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
		String xmlString = sw.toString();

		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		dbfac.setNamespaceAware(true);
		doc = dbfac.newDocumentBuilder().parse(new InputSource(new StringReader(xmlString)));
		return doc;
	}


}
