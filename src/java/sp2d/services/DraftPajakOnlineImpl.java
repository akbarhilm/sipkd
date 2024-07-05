/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import spp.entity.GenNumberMapper;
import sp2d.entity.GenNumberMapper;
import sp2d.entity.DraftPajakOnlineMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.DraftPajakOnline;

@Transactional(readOnly = true)
@Service("draftPajakOnlineServices")
public class DraftPajakOnlineImpl implements DraftPajakOnlineServices {

    private static final Logger log = LoggerFactory.getLogger(DraftPajakOnlineImpl.class);
    @Autowired
    private DraftPajakOnlineMapper draftMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Map> getListDraftPajakOnline(Map<String, Object> param) {
        return draftMapper.getListDraftPajakOnline(param);
    }

    @Override
    public Integer getBanyakDraftPajakOnline(Map param) {
        return draftMapper.getBanyakDraftPajakOnline(param);
    }

    
}
