package Equinix.OrgChartEmployee;

//Object for Personal file
public class Personal {
	private String first;
	private String last;
	private String phone;
	private String address;
	private String id;
	private String title = "";
	private String organization = "";
	
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAdress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public String getOrganization() {
		return organization;
	}

	@Override
	public String toString() {
		return first + " " + last + " " + title + organization + phone;
	}
}
