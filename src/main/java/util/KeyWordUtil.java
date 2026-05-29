package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KeyWordUtil {
	private static final String COMPANY_NAME = "debtor.company.name";
	private static final String DEBTOR_NAME = "debtor.name";
	private static final String SIGNER_ID = "debtor.signed.Id";
	private static final String DEBTOR_COUNTRY = "debtor.country";
	private static final String DEBTOR_AGREEMENT = "debtor.agreement.id";
	private static final String DEBTOR_ACCOUNT_IBAN = "debtor.account.iban";
	private static final String DEBTOR_BIC_ = "debtor.bic.";
	private static final String DEBTOR_CURRENCY_ = "debtor.currency.";
	private static final String CERTIFICATE_FILE_PATH = "certificate.file.path";
	private static final String KEY_STORE_TYPE = "key.store.type";

	private static final String STORE_FOLDER = "store.folder";
	private static final String WRAP_FILE_EXE = "wrap.file.exe";
	private static final String SIGNED_FILE_EXE = "signed.file.exe";
	private static final String CERTIFICATE_PASSWORD = "certificate.password";
	private static final String RECORD_SEPARATOR= "record.separator";

	private static Properties prop = new Properties();

	public KeyWordUtil() {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("default/setting.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getCompanyName() {
		return getProperty(COMPANY_NAME);
	}

	public String getDebtorName() {
		return getProperty(DEBTOR_NAME);
	}

	public String getSignerId() {
		return getProperty(SIGNER_ID);
	}

	public String getDebtorCountry() {
		return getProperty(DEBTOR_COUNTRY);
	}

	public String getDebtorAgreement() {
		return getProperty(DEBTOR_AGREEMENT);
	}

	public String getDebtorCurrency(String country) {
		return getProperty(DEBTOR_CURRENCY_ + country.toLowerCase());
	}

	public String getDebtorBic(String country) {
		return getProperty(DEBTOR_BIC_ + country.toLowerCase());
	}

	public String getDebtorAccountIban() {
		return getProperty(DEBTOR_ACCOUNT_IBAN);
	}

	public String getCertificateFilePath() {
		return getProperty(CERTIFICATE_FILE_PATH);
	}

	public String getKeyStoreType() {
		return getProperty(KEY_STORE_TYPE);
	}

	public String getWrapFileExe() {
		return getProperty(WRAP_FILE_EXE);
	}

	public String getSignedFileExe() {
		return getProperty(SIGNED_FILE_EXE);
	}

	public String getStoreFolder() {
		return getProperty(STORE_FOLDER);
	}

	public String getCertificatePassword() {
		return getProperty(CERTIFICATE_PASSWORD);
	}

	private String getProperty(String name) {
		return prop.getProperty(name);
	}

	public  String getRecordSeparator() {
		return getProperty(RECORD_SEPARATOR);
	}

	
}
