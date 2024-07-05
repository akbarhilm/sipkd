package sp2d.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp2d.entity.CetakSp2dMapper;
import spp.model.HariKerja;
import spp.model.SppUp;
import spp.model.Spj;
import spp.model.Setor;
import spp.model.Npd;
import spp.model.Sp2d;
import spp.model.Sp2dBatal;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("cetakValidasiSP2DServices")
public class CetakValidasiSP2DImpl implements CetakValidasiSP2DServices {

    @Autowired
    private CetakSp2dMapper cetakSp2dMapper;

    @Override
    public void updatenamafile(Map map) {
        cetakSp2dMapper.updatenamafile(map);
    }

    @Override
    public List<SppUp> getProsesCetakSp2d1(Map param) {
        return cetakSp2dMapper.getProsesCetakSp2d1(param);
    }

    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakSp2dMapper.getnilaiparam(param);
    }

    @Override
    public Map getsp2dsahbyidsp2d(Integer param) {
        return cetakSp2dMapper.getsp2dsahbyidsp2d(param);
    }

    @Override
    public Map getsp2dsahid(Integer param) {
        return cetakSp2dMapper.getsp2dsahid(param);
    }

    @Override
    public Map getsp2dsahnomor(Integer param) {
        return cetakSp2dMapper.getsp2dsahnomor(param);
    }

    @Override
    public Integer getbanyaksp2dcetakbtl3(Map map) {
        return cetakSp2dMapper.getbanyaksp2dcetakbtl3(map);
    }

    @Override
    public Integer getbanyaksp2dcetakbtl4(Map map) {
        return cetakSp2dMapper.getbanyaksp2dcetakbtl4(map);
    }

    @Override
    public String getskpdid(Map map) {
        return cetakSp2dMapper.getskpdid(map);
    }

    @Override
    public Integer getNoBa(Map map) {
        return cetakSp2dMapper.getNoBa(map);
    }

    @Override
    public Integer getbanyaksp2dcetakres3(Map map) {
        return cetakSp2dMapper.getbanyaksp2dcetakres3(map);
    }

    @Override
    public String getnamaWilayah(String param) {
        return cetakSp2dMapper.getnamaWilayah(param);
    }

    @Override
    public List<SppUp> getlistsp2dcetak(Map param) {
        return cetakSp2dMapper.getlistsp2dcetak(param);
    }

    @Override
    public List<SppUp> getlistsp2dsah(Map param) {
        return cetakSp2dMapper.getlistsp2dsah(param);
    }

    @Override
    public Integer getbanyaksp2dcetak(Map param) {
        return cetakSp2dMapper.getbanyaksp2dcetak(param);
    }

    @Override
    public Integer getbanyaksp2dsah(Map param) {
        return cetakSp2dMapper.getbanyaksp2dsah(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatesp2dcetak(Map sp2d) {
        final HariKerja hariKerja = cetakSp2dMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            sp2d.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            sp2d.put("tglc", tglc);
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        cetakSp2dMapper.updatesp2dcetak(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatesp2dcetak1(Map sp2d) {
        final HariKerja hariKerja = cetakSp2dMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            sp2d.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            sp2d.put("tglc", tglc);
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        cetakSp2dMapper.updatesp2dcetak1(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatesp2dsah(Map sp2d) {
        final HariKerja hariKerja = cetakSp2dMapper.getharikerjaspp(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            sp2d.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            sp2d.put("tglc", tglc);
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        cetakSp2dMapper.updatesp2dsah(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletesp2dcetak(Integer param) {
        cetakSp2dMapper.deletesp2dcetak(param);
    }

    @Override
    public Integer getbanyaksp2dbatalsah(Map param) {
        return cetakSp2dMapper.getbanyaksp2dbatalsah(param);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sp2dBatal> getlistsp2dbatalsah(Map param) {
        return cetakSp2dMapper.getlistsp2dbatalsah(param);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sp2dBatal getSp2dByIdSp2d(Integer idsp2d) {
        return cetakSp2dMapper.getSp2dByIdSp2d(idsp2d);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false)
    public void batalSp2dSah(Sp2dBatal sp2dbatal) {
        cetakSp2dMapper.batalSp2dSah(sp2dbatal);
        cetakSp2dMapper.insertTabelSp2dBatal(sp2dbatal);
    }

    @Override
    public List<SppUp> getPathFile(Map param) {
        return cetakSp2dMapper.getPathFile(param);
    }

    @Override
    public List<SppUp> getPathFile1(Map param) {
        return cetakSp2dMapper.getPathFile1(param);
    }

    @Override
    public Sp2d getUmkSah(Map param) {
        return cetakSp2dMapper.getUmkSah(param);
    }

}
