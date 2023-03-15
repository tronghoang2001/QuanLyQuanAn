package DTO;

public class LoaiThucDon {
	private int MaLoaiTD;
	private String TenLoaiTD;
	
	public LoaiThucDon() {
		
	}
	
	public LoaiThucDon(String TenLoaiTD, int MaLoaiTD) {
		this.TenLoaiTD = TenLoaiTD;
		this.MaLoaiTD = MaLoaiTD;
	}
	
	public String getTenLoaiTD() {
		return TenLoaiTD;
	}
	public void setTenLoaiTD(String TenLoaiTD) {
		this.TenLoaiTD = TenLoaiTD;
	}

	public int getMaLoaiTD() {
		return MaLoaiTD;
	}

	public void setMaLoaiTD(int maLoaiTD) {
		MaLoaiTD = maLoaiTD;
	}
	
	
	
	
}
