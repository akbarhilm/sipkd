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
import spm.entity.SpmTuMapper;
import spp.model.SpmTu;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("spmTuServices")
public class SpmTuImpl implements SpmTuServices {

    private static final Logger log = LoggerFactory.getLogger(SpmTuImpl.class);
    @Autowired
    private SpmTuMapper spmTuMapper;

    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<SpmTu> getAllSpmTu(Map param) {
        return spmTuMapper.getAllSpmTu(param);
    }

    @Override
    public Integer getBanyakSpmTu(Map param) {
        return spmTuMapper.getBanyakSpmTu(param);

    }

    @Override
    public SpmTu getSpmTUById(Integer param) {
        return spmTuMapper.getSpmTUById(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSpmTuMaster(SpmTu spmTu) {
        //#{tahun} AND i_idskpd =  #{idskpd}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tahun", spmTu.getTahun());
        map.put("idskpd", spmTu.getSkpd().getIdSkpd());
        spmTu.setNoSpm(genNumberMapper.getSpmNo(map));
        spmTuMapper.insertSpmTuMaster(spmTu);
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
    public void updateSpmTuMaster(SpmTu spmTu) {
        spmTuMapper.updateSpmTuMaster(spmTu);
    }

}
