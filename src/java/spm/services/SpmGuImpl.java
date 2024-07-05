package spm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.entity.GenNumberMapper;
import spm.entity.SpmGuMapper;
import spp.model.SpmGu;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("spmGuServices")
public class SpmGuImpl implements SpmGuServices {

    private static final Logger log = LoggerFactory.getLogger(SpmGuImpl.class);
    @Autowired
    private SpmGuMapper spmGuMapper;

    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<SpmGu> getAllSpmGu(Map param) {
        return spmGuMapper.getAllSpmGu(param);
    }

    @Override
    public Integer getBanyakSpmGu(Map param) {
        return spmGuMapper.getBanyakSpmGu(param);

    }

    @Override
    public SpmGu getSpmGUById(Integer param) {
        return spmGuMapper.getSpmGUById(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSpmGuMaster(SpmGu spmGu) {
        //#{tahun} AND i_idskpd =  #{idskpd}
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tahun", spmGu.getTahun());
        map.put("idskpd", spmGu.getSkpd().getIdSkpd());
        spmGu.setNoSpm(genNumberMapper.getSpmNo(map));
        spmGuMapper.insertSpmGuMaster(spmGu);
        /* final List<SpmUpRinci> spmUpRinci = spmUp.getSpmUpRinci();
         final Integer idspm = spmUp.getIdspm();
         final Integer idspp = spmUp.getId();
         log.debug(" ################ idspm " + idspm);
         for (SpmUpRinci spmUpRinci1 : spmUpRinci) {
         spmUpRinci1.setIdspm(idspm);
         spmUpRinci1.setIdspp(idspp);
         spmUpMapper.insertSpmUpRinci(spmUpRinci1);
         }*/

    }

    @Override
    @Transactional(readOnly = false)
    public void updateSpmGuMaster(SpmGu spmGu) {
        spmGuMapper.updateSpmGuMaster(spmGu);
    }

}
