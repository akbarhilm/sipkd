/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.services;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.entity.BOSMapper;

/**
 *
 * @author User
 */

@Service("akunServices")
public class BOSImpl implements BOSServices{
    
    private static final Logger log = LoggerFactory.getLogger(BOSImpl.class);
    @Autowired
    BOSMapper bosmapper;

    @Override
    public List<Map<String, Object>> getHeaderk7a(Map<String, Object> param) {
        return bosmapper.getHeaderk7a(param);
    }
    
    @Override
    public List<Map<String, Object>> getDetailk7a(Map<String, Object> param) {
        return bosmapper.getDetailk7a(param);
    }
    
    @Override
    public List<Map<String, Object>> getSummary7a(Map<String, Object> param) {
        return bosmapper.getSummary7a(param);
    }
    
    
    
}
