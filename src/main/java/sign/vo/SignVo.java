package sign.vo;

import java.security.KeyStore.PrivateKeyEntry;

import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;

import org.w3c.dom.Document;

public class SignVo {
	private Reference ref;
	private SignedInfo si;
	private PrivateKeyEntry keyEntry;
	private KeyInfo ki;
	private Document doc;
	private XMLSignatureFactory signatureFactory;
	public Reference getRef() {
		return ref;
	}

	public void setRef(Reference ref) {
		this.ref = ref;
	}

	public SignedInfo getSi() {
		return si;
	}

	public void setSi(SignedInfo si) {
		this.si = si;
	}

	public PrivateKeyEntry getKeyEntry() {
		return keyEntry;
	}

	public void setKeyEntry(PrivateKeyEntry keyEntry) {
		this.keyEntry = keyEntry;
	}

	public KeyInfo getKi() {
		return ki;
	}

	public void setKi(KeyInfo ki) {
		this.ki = ki;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public XMLSignatureFactory getSignatureFactory() {
		return signatureFactory;
	}

	public void setSignatureFactory(XMLSignatureFactory signatureFactory) {
		this.signatureFactory = signatureFactory;
	}

}
