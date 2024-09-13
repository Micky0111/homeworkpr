package model;

public class Student {
	private Integer id;
	private String name;
	private Integer chi;
	private Integer eng;
	private Integer sum;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, Integer chi, Integer eng) {
		super();
		this.name = name;
		this.chi = chi;
		this.eng = eng;
		sum=chi+eng;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getChi() {
		return chi;
	}
	public void setChi(Integer chi) {
		this.chi = chi;
	}
	public Integer getEng() {
		return eng;
	}
	public void setEng(Integer eng) {
		this.eng = eng;
	}
	public Integer getSum() {
		
		sum=chi+eng;
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	
	
	
	
	

}
