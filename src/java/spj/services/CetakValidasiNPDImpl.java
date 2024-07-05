package spj.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.CetakNpdMapper;
import spj.entity.CetakNpdMapper;
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
@Service("cetakValidasiNPDServices")
public class CetakValidasiNPDImpl implements CetakValidasiNPDServices {

    @Autowired
    private CetakNpdMapper cetakNpdpMapper;

  
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakNpdpMapper.getnilaiparam(param);
    }

   
     @Override
    @Transactional(readOnly = false)
    public void insertnpdcetak(Map npd) {
        final HariKerja hariKerja = cetakNpdpMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            npd.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            npd.put("tglc", tglc);
        }
        cetakNpdpMapper.insertnpdcetak(npd);
    }

   
     @Override
    public Integer getbanyaknpdcetak(Map param) {
        return cetakNpdpMapper.getbanyaknpdcetak(param);
    }

    @Override
    public List<Npd> getlistnpdcetak(Map param) {
        return cetakNpdpMapper.getlistnpdcetak(param);
    }

   
    @Override
    public void deletenpdcetak(Integer param) {
        cetakNpdpMapper.deletenpdcetak(param);
    }

   
}
