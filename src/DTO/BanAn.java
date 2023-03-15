package DTO;

public class BanAn {
	private String SoBan;
	private int SoLuongGhe;
	
	public BanAn() {
		
	}
	public BanAn(String SoBan,int SoLuongGhe) {
		this.SoBan = SoBan;
		this.SoLuongGhe = SoLuongGhe;
	}
	
	public String getSoBan() {
		return SoBan;
	}
	public void setSoBan(String SoBan) {
		this.SoBan = SoBan;
	}
	
	public int getSoLuongGhe() {
		return SoLuongGhe;
	}
	public void setSoLuongGhe(int SoLuongGhe) {
		this.SoLuongGhe = SoLuongGhe;
	}
}
