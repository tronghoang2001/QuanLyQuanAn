package DTO;

public class PhieuGoiMon {
	private int SoPhieu;
	private String SoBan;
	private String TenLoaiTD;
	private String TenTD;
	private int SoLuong;
	private String NgayLapPhieu;
	
	public PhieuGoiMon() {
		
	}
	public PhieuGoiMon(String SoBan, String TenLoaiTD, String TenTD, int SoLuong, String NgayLapPhieu, int SoPhieu) {
		this.SoBan = SoBan;
		this.TenLoaiTD = TenLoaiTD;
		this.TenTD = TenTD;
		this.SoLuong = SoLuong;
		this.NgayLapPhieu = NgayLapPhieu;
		this.SoPhieu = SoPhieu;
	}
	
	public String getSoBan() {
		return SoBan;
	}
	public void setSoBan(String SoBan) {
		this.SoBan = SoBan;
	}
	
	public String getTenLoaiTD() {
		return TenLoaiTD;
	}
	public void setTenLoaiTD(String TenLoaiTD) {
		this.TenLoaiTD = TenLoaiTD;
	}
	
	public String getTenTD() {
		return TenTD;
	}
	public void setTenTD(String TenTD) {
		this.TenTD = TenTD;
	}
	
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int SoLuong) {
		this.SoLuong = SoLuong;
	}
	public String getNgayLapPhieu() {
		return NgayLapPhieu;
	}
	public void setNgayLapPhieu(String ngayLapPhieu) {
		NgayLapPhieu = ngayLapPhieu;
	}
	public int getSoPhieu() {
		return SoPhieu;
	}
	public void setSoPhieu(int soPhieu) {
		SoPhieu = soPhieu;
	}
	
	
}

