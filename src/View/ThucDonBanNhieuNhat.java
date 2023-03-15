package View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import DTO.ThongKeThucDon;
import service.ThongKeService;
import service.ThongKeServiceImp;

/**
 *
 * @author TVD
 */
public class ThucDonBanNhieuNhat {

	private ThongKeService thongKeService = null;
	
	public ThucDonBanNhieuNhat() {
		this.thongKeService = new ThongKeServiceImp();
	}
	
	public void setDataToChart1(JPanel jpnItem) {
		List<ThongKeThucDon> listItem = thongKeService.GetListThongKeThucDon();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (ThongKeThucDon item : listItem) {
                dataset.addValue(item.getSoLuong(), item.getTenTD(), item.getNgay());
            }
        }
        
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê thực đơn bán nhiều nhất".toUpperCase(),
                "Ngày", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
	}

    public static void main(String[] args) {
        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ BarChart");
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        ThucDonBanNhieuNhat bdtk = new ThucDonBanNhieuNhat();
        bdtk.setDataToChart1(chartPanel);
    }

}


