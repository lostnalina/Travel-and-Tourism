package application;

public class Personel_info {
	private String name;
	 private String id;
	 private String gender;
	 private String phone;
	 private String adress;
	 
	 public Personel_info() {
		// TODO Auto-generated constructor stub
	}
	 
	public Personel_info(String name,String id,String gender,String phone, String adress) {
	this.name=name;
	this.id=id;
	this.gender=gender;
	this.phone=phone;
	this.adress=adress;
		
		
		
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	 	

}
