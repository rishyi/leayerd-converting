package lk.ijse.shop.controller;

import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepotFormController {
    public void btnPrintAllOrders(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/reports/order_details.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/glitchbusters","root","zombi123");
        JRDesignQuery updateQuery = new JRDesignQuery();
        jasperDesign.setQuery(updateQuery);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection);
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void btnPrintAllcustomers(ActionEvent actionEvent) throws JRException, ClassNotFoundException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/reports/customer_report.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/glitchbusters", "root", "zombi123");
        JRDesignQuery updateQuery = new JRDesignQuery();
        jasperDesign.setQuery(updateQuery);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void btnPrintAllItems(ActionEvent actionEvent) throws JRException, ClassNotFoundException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/reports/items_report.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/glitchbusters","root","zombi123");
        JRDesignQuery updateQuery = new JRDesignQuery();
        jasperDesign.setQuery(updateQuery);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection);
        JasperViewer.viewReport(jasperPrint,false);
    }
}
