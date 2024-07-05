package spj.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.CetakSpjMapper;
import spj.entity.CetakSpjMapper;
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
@Service("cetakValidasiSPJServices")
public class CetakValidasiSPJImpl implements CetakValidasiSPJServices {

   

    @Autowired
    private CetakSpjMapper cetakSpjMapper;

   
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakSpjMapper.getnilaiparam(param);
    }

       
     @Override
    public Map getspjsahbyidspj(Integer param) {
        return cetakSpjMapper.getspjsahbyidspj(param);
    }

   

    @Override
    @Transactional(readOnly = false)
    public void insertspjsah(Map spj) {
        final HariKerja hariKerja = cetakSpjMapper.getHariKerjaSpj(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            spj.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            spj.put("tglc", tglc);
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        cetakSpjMapper.insertspjsah(spj);
    }

   
    @Override
    public List<Spj> getSpjCetakBl(Map<String, Object> param) {
        return cetakSpjMapper.getSpjCetakBl(param);
    }

    @Override
    public List<Spj> getlistspjsah(Map<String, Object> param) {
        return cetakSpjMapper.getlistspjsah(param);
    }

    
    @Override
    public Integer getBanyakgetSpjCetakBl(Map param) {
        return cetakSpjMapper.getBanyakgetSpjCetakBl(param);
    }

    @Override
    public Integer getbanyakspjsah(Map param) {
        return cetakSpjMapper.getbanyakspjsah(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSpjCetak(Map spj) {
        final HariKerja hasilKerja = cetakSpjMapper.getHariKerjaSpj(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            spj.put("tglcetak", hasilKerja.getTglCetak());
        } else {
            spj.put("tglcetak", spj.get("tglentry"));
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        cetakSpjMapper.insertSpjCetak(spj);
    }

    @Override
    public void deleteSpjCetak(Integer idspj) {
        cetakSpjMapper.deleteSpjCetak(idspj);
    }
}
