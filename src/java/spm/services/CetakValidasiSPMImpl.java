package spm.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.entity.CetakSpmMapper;
import spp.model.HariKerja;
import spp.model.SppUp;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("cetakValidasiSPMServices")
public class CetakValidasiSPMImpl implements CetakValidasiSPMServices {

    @Autowired
    private CetakSpmMapper cetakSpmMapper;

    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakSpmMapper.getnilaiparam(param);
    }

    @Override
    public List<Map> getidkegkon(Map param) {
        return cetakSpmMapper.getidkegkon(param);
    }

    @Override
    public List<Map> getnilai(Map param) {
        return cetakSpmMapper.getnilai(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspmcetak(Map sppUp) {
        final HariKerja hariKerja = cetakSpmMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            sppUp.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            sppUp.put("tglc", tglc);
        }
        cetakSpmMapper.insertspmcetak(sppUp);
    }

    @Override
    public Integer getbanyakspmcetakbtl3(Map map) {
        return cetakSpmMapper.getbanyakspmcetakbtl3(map);
    }

    @Override
    public Integer getbanyakspmcetakbtl4(Map map) {
        return cetakSpmMapper.getbanyakspmcetakbtl4(map);
    }

    @Override
    public Integer getbanyakspmcetakbtl5(Map map) {
        return cetakSpmMapper.getbanyakspmcetakbtl5(map);
    }

    @Override
    public Integer getbanyakspmcetak(Map param) {
        return cetakSpmMapper.getbanyakspmcetak(param);
    }

    @Override
    public List<SppUp> getlistspmcetak(Map param) {
        return cetakSpmMapper.getlistspmcetak(param);
    }

    @Override
    public void deletespmcetak(Integer param) {
        cetakSpmMapper.deletespmcetak(param);
    }

    @Override
    public List<Map> getSisaUmk(Map param) {
        return cetakSpmMapper.getSisaUmk(param);
    }

    @Override
    public List<Map> getKodeUmk(Map param) {
        return cetakSpmMapper.getKodeUmk(param);
    }

    @Override
    public List<SppUp> getPathFile(Map param) {
        return cetakSpmMapper.getPathFile(param);
    }

    @Override
    public List<Map> getKodeSumbDana(Map param) {
        return cetakSpmMapper.getKodeSumbDana(param);
    }

    @Override
    public List<Map> getNilaiBlud(Map param) {
        return cetakSpmMapper.getNilaiBlud(param);
    }
    
    @Override
    public List<Map> getKodeMultiyear(Map param) {
        return cetakSpmMapper.getKodeMultiyear(param);
    }

    @Override
    public List<Map> getNilaiPotonganBtl(Map param) {
        return cetakSpmMapper.getNilaiPotonganBtl(param);
    }

    @Override
    public Map getUmkSah(Map param) {
        return cetakSpmMapper.getUmkSah(param);
    }

    
}
