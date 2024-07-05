package spp.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.CetakSppMapper;
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
@Service("cetakValidasiSPPServices")
public class CetakValidasiSPPImpl implements CetakValidasiSPPServices {

    @Autowired
    private CetakSppMapper cetakSppMapper;

  

    @Override
    public List<SppUp> getlistsppcetak(Map param) {
        return cetakSppMapper.getlistsppcetak(param);
    }

    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakSppMapper.getnilaiparam(param);
    }
    
    @Override
    public List<Map> getnilaiparam1(Map param) {
        return cetakSppMapper.getnilaiparam1(param);
    }

    @Override
    public Integer getbanyaksppcetak(Map param) {
        return cetakSppMapper.getbanyaksppcetak(param);
    }
    
    
    @Override
    public Integer getbanyaksppcetakbtl3(Map map) {
        return cetakSppMapper.getbanyaksppcetakbtl3(map);
    }
    
     @Override
    public Integer getbanyaksppcetakbtl4(Map map) {
        return cetakSppMapper.getbanyaksppcetakbtl4(map);
    }

   
    @Override
    @Transactional(readOnly = false)
    public void insertsppcetak(Map sppUp) {
        /*  final HariKerja hasirKerja = cetakSppMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
         if (hasirKerja != null) {
         sppUp.put("tglcetak", hasirKerja.getTglCetak());
         } else {
         sppUp.put("tglcetak", sppUp.get("tglentry"));
         }
         //sppUp.put("tglcetak", hasirKerja.getTglCetak());*/

        final HariKerja hariKerja = cetakSppMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            sppUp.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            sppUp.put("tglc", tglc);
        }

        cetakSppMapper.insertsppcetak(sppUp);
    }

    @Override
    public List<SppUp> getlistsppsah(Map param) {
        return cetakSppMapper.getlistsppsah(param);
    }

    @Override
    public Integer getbanyaksppsah(Map param) {
        return cetakSppMapper.getbanyaksppsah(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertsppsah(Map sppUp) {
        final HariKerja hasirKerja = cetakSppMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
        if (hasirKerja != null) {
            sppUp.put("tglsah", hasirKerja.getTglSah());
        } else {
            sppUp.put("tglsah", sppUp.get("tglentry"));
        }
        cetakSppMapper.insertsppsah(sppUp);
    }

   @Transactional(readOnly = false)
    public void deletesppcetak(Integer param) {
        cetakSppMapper.deletesppcetak(param);
    }

   
     @Override
    public List<SppUp> getPathFile(Map param) {
        return cetakSppMapper.getPathFile(param);
    }
    
     @Override
    public void updateSppBtt(Map param) {
        cetakSppMapper.updateSppBtt(param);
        
        
    }
    
}
