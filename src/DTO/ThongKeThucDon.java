package DTO;

public class ThongKeThucDon {
	private String Ngay;
	private String TenTD;
	private int SoLuong;
	
	public ThongKeThucDon() {
		
	}
	
	public ThongKeThucDon(String ngay, String tenTD, int soLuong) {
		this.Ngay = ngay;
		this.SoLuong = soLuong;
		this.TenTD = tenTD;
	}

	public String getNgay() {
		return Ngay;
	}

	public void setNgay(String ngay) {
		Ngay = ngay;
	}
	
	public String getTenTD() {
		return TenTD;
	}

	public void setTenTD(String tenTD) {
		TenTD = tenTD;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	
	
}
