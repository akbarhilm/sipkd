package spj.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.CetakSetorMapper;
import spj.entity.CetakSetorMapper;
import spp.model.HariKerja;
import spp.model.SppUp;
import spp.model.Spj;
import spp.model.Setor;
import spp.model.Npd;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("cetakValidasiSETORServices")
public class CetakValidasiSETORImpl implements CetakValidasiSETORServices {

    @Autowired
    private CetakSetorMapper cetakSetorMapper;

   
   
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakSetorMapper.getnilaiparam(param);
    }

       
     @Override
    @Transactional(readOnly = false)
    public void insertsetorcetak(Map setor) {
        final HariKerja hariKerja = cetakSetorMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            setor.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            setor.put("tglc", tglc);
        }
        cetakSetorMapper.insertsetorcetak(setor);
    }
    
  
    @Override
    public Integer getbanyaksetorcetak(Map param) {
        return cetakSetorMapper.getbanyaksetorcetak(param);
    }

    @Override
    public List<Setor> getlistsetorcetak(Map param) {
        return cetakSetorMapper.getlistsetorcetak(param);
    }
    
   
     @Override
    public void deletesetorcetak(Integer param) {
        cetakSetorMapper.deletesetorcetak(param);
    }
    
   
}
