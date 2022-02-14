import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.sql.*;

public class ThongKe extends JFreeChart {
    public ChartPanel chartPanel;
    public ThongKe(String o) {
        super(new Plot() {
            @Override
            public String getPlotType() {
                return null;
            }

            @Override
            public void draw(Graphics2D graphics2D, Rectangle2D rectangle2D, Point2D point2D, PlotState plotState, PlotRenderingInfo plotRenderingInfo) {

            }
        });
        chartPanel = new ChartPanel(createChart(o));

    }

    private JFreeChart createChart(String o) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ BỆNH NHÂN NHẬP VIỆN",
                o, "Số người",
                createDataset(o), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset(String o) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlyhosobenhan","dangnguyen","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            if (o.equals("Month")){
                resultSet = statement.executeQuery("select count(id),concat(month(ngaytiepnhan),'/',(year(ngaytiepnhan))) from patients group by  month(ngaytiepnhan),year(ngaytiepnhan) order by ngaytiepnhan ASC;");
            } else if (o.equals("Day")){
                resultSet = statement.executeQuery("select count(id),concat(day(ngaytiepnhan),'/',month(ngaytiepnhan),'/',(year(ngaytiepnhan))) from patients group by day(ngaytiepnhan),month(ngaytiepnhan),year(ngaytiepnhan) order by ngaytiepnhan ASC;");
            } else if (o.equals("Year")){
                resultSet = statement.executeQuery("select count(id),(year(ngaytiepnhan)) from patients group by year(ngaytiepnhan) order by ngaytiepnhan ASC;");
            } else {
                resultSet = null;
            }


            while (resultSet.next()){
                dataset.addValue(Double.parseDouble(resultSet.getString(1)), "Số người", resultSet.getString(2));
            }
            resultSet.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return dataset;
    }
}
