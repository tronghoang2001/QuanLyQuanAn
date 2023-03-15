package DTO;

public class NhanVien {
	private int MaNV;
	private String TenNV;
	private String ChucVu;
	private String GioiTinh;
	private String SDT;
	
	public NhanVien() {
		
	}
	public NhanVien(String TenNV, String ChucVu, String GioiTinh, String SDT, int MaNV) {
		this.TenNV = TenNV;
		this.ChucVu = ChucVu;
		this.GioiTinh = GioiTinh;
		this.SDT = SDT;
		this.MaNV = MaNV;
	}
	
	public String getTenNV() {
		return TenNV;
	}
	public void setTenNV(String TenNV) {
		this.TenNV = TenNV;
	}
	
	public String getChucVu() {
		return ChucVu;
	}
	public void setChucVu(String ChucVu) {
		this.ChucVu = ChucVu;
	}
	
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String GioiTinh) {
		this.GioiTinh = GioiTinh;
	}
	
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String SDT) {
		this.SDT = SDT;
	}
	public int getMaNV() {
		return MaNV;
	}
	public void setMaNV(int maNV) {
		MaNV = maNV;
	}
	
}
