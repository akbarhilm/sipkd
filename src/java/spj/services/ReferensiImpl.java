/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spj.entity.ReferensiMapper;
import spj.entity.SkpdMapper;
import spp.model.Akun;
import spp.model.Bendahara;
import spp.model.PejabatPpkd;
import spp.model.Skpd;
import spp.model.SpmProses;
import spp.model.SppPaguUp;
import spp.model.Urusan;

/**
 *
 * @author R Tarman
 */
@Transactional(readOnly = true)
@Service("referensiServices")
public class ReferensiImpl implements ReferensiServices {
   private static final Logger log = LoggerFactory.getLogger(ReferensiImpl.class);
    @Autowired
    private ReferensiMapper referensiMapper;
    
    

    @Autowired
    private SkpdMapper skpdMapper;

    @Override
    public List<SppPaguUp> getAllSppPaguUp(Map<String, Object> param) {
        return referensiMapper.getAllSppPaguUp(param);
    }

    @Override
    public Integer getBanyakSppPaguUp(Map<String, Object> param) {
        return referensiMapper.getBanyakSppPaguUp(param);
    }

    @Override
    public void updateSppPaguUp(SppPaguUp sppPaguUp) {
        referensiMapper.updateSppPaguUp(sppPaguUp);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSppPaguUpTahun(Map map) {
        referensiMapper.pindahSppPaguTahun(map);
        referensiMapper.hapusSppPaguTahun(map);
    }

    @Override
    public List<Skpd> getAllSkpdBL(Map<String, Object> param) {
        return skpdMapper.getAllSkpdBL(param);
    }

    @Override
    public Integer getBanyakSkpdBL(Map<String, Object> param) {
        return skpdMapper.getBanyakSkpdBL(param);
    }

    @Override
    public Skpd getDetailSkpdById(Integer idSkpd) {
        return skpdMapper.getDetailSkpdById(idSkpd);
    }

    @Override
    public List<PejabatPpkd> getAllPejabatPpkd(Map<String, Object> param) {
        return skpdMapper.getAllPejabatPpkd(param);
    }

    @Override
    public Integer getBanyakPejabatPPKD(Map<String, Object> param) {
        return skpdMapper.getBanyakPejabatPPKD(param);
    }

    @Override
    public List<Skpd> getAllSkpdAnak(Map<String, Object> param) {
        return skpdMapper.getAllSkpdAnak(param);
    }

    @Override
    public List<Map<String, Object> > getAllSkpdRoot() {
        return skpdMapper.getAllSkpdRoot();
    }

    @Override
    public List<Map<String, Object>> getAllAkunRoot() {
        return referensiMapper.getAllAkunRoot();
    }

    @Override
    public List<Map<String, Object>> getAllAkunAnak(Map<String, Object> param) {
        log.debug(" >>>>>>>>>>>  " + param.toString());
        return referensiMapper.getAllAkunAnak(param);
    }
    
     @Override
    public Skpd getSkpdById(Integer id) {
        return referensiMapper.getSkpdById(id);
    }
     @Override
    public Integer getBanyakAllUrusan(Map param) {
        return referensiMapper.getBanyakAllUrusan(param);
    }
    
     @Override
    public List<Urusan> getUrusan(Map param) {
        return referensiMapper.getUrusan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void editSkpd(Skpd param) {
        referensiMapper.editSkpd(param);
    }
    
     
    @Override
    public Akun getAkunByIdTambah(Integer id) {
        return referensiMapper.getAkunByIdTambah(id);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateAkun(Akun param) {
        referensiMapper.updateAkun(param);
    }

    @Override
    public Akun getAkunById(Integer id) {
        return referensiMapper.getAkunById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSkpd(Skpd param) {
        referensiMapper.insertSkpd(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertAkun(Akun param) {
        referensiMapper.insertAkun(param);
    }

    @Override
    public Integer getBanyakBendaharaSpp(Map param) {
       return referensiMapper.getBanyakBendaharaSpp(param);
    }

    @Override
    public List<Bendahara> getAllBendaharaSpp(Map param) {
        return referensiMapper.getAllBendaharaSpp(param);
    }

    @Override
    public List<Skpd> getSkpdAll(Map<String, Object> param) {
    return skpdMapper.getSkpdAll(param);
    }

    @Override
    public Integer getBanyakSkpdAll(Map<String, Object> param) {
    return skpdMapper.getBanyakSkpdAll(param);
    }

    @Override
    public List<Skpd> getSkpdWil(Map<String, Object> param) {
    return skpdMapper.getSkpdWil(param);
   }

    @Override
    public Integer getBanyakSkpdWil(Map<String, Object> param) {
    return skpdMapper.getBanyakSkpdWil(param);
    }

    @Override
    public List<Skpd> getAllSkpdBantuan(Map<String, Object> param) {
    return skpdMapper.getSkpdBantuan(param);
    }

    @Override
    public Integer getBanyakSkpdBantuan(Map<String, Object> param) {
    return skpdMapper.getBanyakSkpdBantuan(param);
    }

    @Override
    public SpmProses getSpmBatasProses(Map param) {
        /*
         'KODE JENIS 1:BTL ; 2-BTLBANTUAN ; 3-BL ; 4-BIAYA '
        */
        return referensiMapper.getSpmBatasProses(param);
    }
      
}
