package vo;

public class NameAndCountry {
	private String companyName= "new";
	private String companyCountry ="DK";
	

	public NameAndCountry() {
	}

	public NameAndCountry(String companyName, String companyCountry) {
		this.companyName = companyName;
		this.companyCountry = companyCountry;
	}
	
	

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyCountry() {
		return companyCountry;
	}

}
