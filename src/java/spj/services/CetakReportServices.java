/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Admin
 */
public interface CetakReportServices {

    public JasperPrint cetakReport(Map<String, Object> map);

    
}
