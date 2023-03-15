	package DTO;

public class TaiKhoan {
	private int MaTK;
	private String TenDN;
	private String MatKhau;
	private String QuyenDN;
	private int MaNV;
	
	public TaiKhoan() {
		
	}
	public TaiKhoan(String TenDN,String MatKhau, String QuyenDN, int MaNV, int MaTK) {
		this.TenDN = TenDN;
		this.MatKhau = MatKhau;
		this.QuyenDN = QuyenDN;
		this.MaNV = MaNV;
		this.MaTK = MaTK;
	}
	
	
	public String getTenDN() {
		return TenDN;
	}
	public void setTenDN(String TenDN) {
		this.TenDN = TenDN;
	}
	
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String MatKhau) {
		this.MatKhau = MatKhau;
	}
	
	public String getQuyenDN() {
		return QuyenDN;
	}
	public void setQuyenDN(String QuyenDN) {
		this.QuyenDN = QuyenDN;
	}
	
	public int getMaNV() {
		return MaNV;
	}
	public void setMaNV(int MaNV) {
		this.MaNV = MaNV;
	}
	public int getMaTK() {
		return MaTK;
	}
	public void setMaTK(int maTK) {
		MaTK = maTK;
	} 
}
