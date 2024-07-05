package spj.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.BastMapper;
import spj.entity.GenNumberMapper;
import spp.model.Bast;
import spp.model.Kontrak;
import spp.model.Skpd;

@Transactional(readOnly = true)
@Service("BastServices")
public class BastImpl implements BastServices {

    @Autowired
    private BastMapper Bastmapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Skpd> getSKPD(Map param) {
        return Bastmapper.getSKPD(param);
    }

    @Override
    public List<Kontrak> getKontrak(Map param) {
        return Bastmapper.getKontrak(param);
    }

    @Override
    public List<Bast> getBast(Map param) {
        return Bastmapper.getBast(param);
    }

    @Override
    public List<Bast> getAkun(Map param) {
        return Bastmapper.getAkun(param);
    }

    @Override
    public Integer getBanyakAllBast(Map param) {
        return Bastmapper.getBanyakAllBast(param);
    }

    @Override
    public Integer getBanyakAllKontrak(Map param) {
        return Bastmapper.getBanyakAllKontrak(param);
    }

    @Override
    public Integer getBanyakAkun(Map param) {
        return Bastmapper.getBanyakAkun(param);
    }

    @Override
    public Integer getBanyakAllSKPD(Map param) {
        return Bastmapper.getBanyakAllSKPD(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBast(Bast bast) {
        final Map param = new HashMap();
        param.put("tahun", bast.getTahunAnggaran());
        param.put("idskpd", bast.getSkpd().getIdSkpd());
        bast.setNoBast(genNumberMapper.getBASTNo(param));
        Bastmapper.insertBast(bast);
    }

    @Override
    public Bast getBastById(Integer id) {
        return Bastmapper.getBastById(id);
    }

    @Override
    public Bast getKegiatanById(Integer idkegiatan) {
        return Bastmapper.getKegiatanById(idkegiatan);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBast(Bast param) {
        Bastmapper.updateBast(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBast(Integer id) {
        Bastmapper.deleteBast(id);
    }

}
