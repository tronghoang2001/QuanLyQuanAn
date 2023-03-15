package DTO;

public class DoanhThu {
	private String Ngay;
	private int DoanhThu;
	public DoanhThu() {
		
	}
	public DoanhThu(String Ngay, int DoanhThu) {
		this.Ngay = Ngay;
		this.DoanhThu = DoanhThu;
	}
	public String getNgay() {
		return Ngay;
	}
	public void setNgay(String ngay) {
		Ngay = ngay;
	}
	public int getDoanhThu() {
		return DoanhThu;
	}
	public void setDoanhThu(int doanhThu) {
		DoanhThu = doanhThu;
	}
}
