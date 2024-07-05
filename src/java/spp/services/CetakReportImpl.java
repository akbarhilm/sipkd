/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service("cetakReportServices")

public class CetakReportImpl implements CetakReportServices {

    @Autowired
    DataSource dataSource;

    @Override
    public JasperPrint cetakReport(Map<String, Object> map) {
        Connection con = null;
        JasperPrint jasperPrint = null;
        String pathReport = map.get("pathreport").toString();
        
        try{
            con = dataSource.getConnection();
            jasperPrint = JasperFillManager.fillReport(pathReport, map, con);
            if (con != null) con.close();
        }
        catch (SQLException | JRException e) {
            e.getMessage();
        }
        return jasperPrint;
    }
    
    
}
