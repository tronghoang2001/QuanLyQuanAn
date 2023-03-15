package DTO;

public class HoaDon {
	private String MaHD;
	private String SoBan;
	private String NgayXuat;
	private int TongTien;
	private int MaNV;
	
	public HoaDon() {
		
	}
	public HoaDon(String MaHD, String SoBan, String NgayXuat, int TongTien, int MaNV) {
		this.MaHD = MaHD;
		this.SoBan = SoBan;
		this.NgayXuat = NgayXuat;
		this.TongTien = TongTien;
		this.MaNV = MaNV;
	}
	
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String MaHD) {
		this.MaHD = MaHD;
	}
	
	public String getSoBan() {
		return SoBan;
	}
	public void setSoBan(String SoBan) {
		this.SoBan = SoBan;
	}
	
	public String getNgayXuat() {
		return NgayXuat;
	}
	public void setNgayXuat(String NgayXuat) {
		this.NgayXuat = NgayXuat;
	}
	
	public int getTongTien() {
		return TongTien;
	}
	public void setTongTien(int TongTien) {
		this.TongTien = TongTien;
	}
	
	public int getMaNV() {
		return MaNV;
	}
	public void setMaNV(int MaNV) {
		this.MaNV = MaNV;
	}
}
