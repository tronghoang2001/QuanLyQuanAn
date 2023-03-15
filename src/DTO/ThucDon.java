package DTO;

public class ThucDon {
	private int MaTD;
	private String TenTD;
	private int GiaTien;
	private String TenLoaiTD;
	
	public ThucDon() {
		
	}
	
	public ThucDon(String TenTD, int GiaTien, String TenLoaiTD, int MaTD) {
		this.TenTD = TenTD;
		this.GiaTien = GiaTien;
		this.TenLoaiTD = TenLoaiTD;
		this.MaTD = MaTD;
	}
	
	public String getTenTD() {
		return TenTD;
	}
	public void setTenTD(String TenTD) {
		this.TenTD = TenTD;
	}

	public int getGiaTien() {
		return GiaTien;
	}
	public void setGiaTien(int GiaTien) {
		this.GiaTien = GiaTien;
	}
	
	public String getTenLoaiTD() {
		return TenLoaiTD;
	}
	public void setTenLoaiTD(String TenLoaiTD) {
		this.TenLoaiTD = TenLoaiTD;
	}

	public int getMaTD() {
		return MaTD;
	}

	public void setMaTD(int maTD) {
		MaTD = maTD;
	}
}
