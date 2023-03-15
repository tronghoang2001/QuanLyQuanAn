package service;
import DTO.DoanhThu;
import DTO.ThongKeThucDon;
import java.util.List;
public interface ThongKeService {
	public List<DoanhThu> GetListThongKeDoanhThu();
	public List<ThongKeThucDon> GetListThongKeThucDon();
}
