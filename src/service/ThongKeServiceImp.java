package service;

import java.util.List;

import DTO.DoanhThu;
import DAO.DoanhThuDAO;
import DTO.ThongKeThucDon;
import DAO.ThongKeThucDonDAO;


public class ThongKeServiceImp implements ThongKeService{
	private DoanhThuDAO doanhThuDAO = null;
	private ThongKeThucDonDAO tktdDAO = null;

    public ThongKeServiceImp() {
        this.doanhThuDAO = new DoanhThuDAO();
        this.tktdDAO = new ThongKeThucDonDAO();
    }
	@Override
	public List<DoanhThu> GetListThongKeDoanhThu() {
		// TODO Auto-generated method stub
		return DoanhThuDAO.getInstance().GetListThongKeDoanhThu();
	}
	@Override
	public List<ThongKeThucDon> GetListThongKeThucDon() {
		// TODO Auto-generated method stub
		return ThongKeThucDonDAO.getInstance().GetListThongKeThucDon();
	}
}
