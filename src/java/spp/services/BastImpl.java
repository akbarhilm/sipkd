package spp.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.BastMapper;
import spp.entity.GenNumberMapper;
import spp.model.Bast;
import spp.model.Skpd;

@Transactional(readOnly = true)
@Service("BastServices")
public class BastImpl implements BastServices {

    @Autowired
    private BastMapper bastmapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    private static final Logger log = LoggerFactory.getLogger(BastImpl.class);

    @Override
    public List<Skpd> getSKPD(Map param) {
        return bastmapper.getSKPD(param);
    }

    @Override
    public List<Bast> getKontrak(Map<String, Object> param) {
        return bastmapper.getKontrak(param);
    }

    @Override
    public List<Bast> getBast(Map param) {
        return bastmapper.getBast(param);
    }

    @Override
    public List<Bast> getAkun(Map param) {
        log.debug(" +++++++++++++++++++++++++ " + param.toString());
        return bastmapper.getAkunUpdate(param);
        //   return bastmapper.getAkun(param);
    }

    @Override
    public Integer getBanyakAllBast(Map param) {
        return bastmapper.getBanyakAllBast(param);
    }

    @Override
    public Integer getBanyakAllKontrak(Map<String, Object> param) {
        return bastmapper.getBanyakAllKontrak(param);
    }

    @Override
    public Integer getBanyakAkun(Map param) {
        return bastmapper.getBanyakAkun(param);
    }

    @Override
    public Integer getBanyakAllSKPD(Map param) {
        return bastmapper.getBanyakAllSKPD(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBast(Bast bast) {
        final Map param = new HashMap();
        param.put("tahun", bast.getTahunAnggaran());
        param.put("idskpd", bast.getSkpd().getIdSkpd());
        //bast.setNoBast(genNumberMapper.getBASTNo(param));
        final BigDecimal nilaiBast = bast.getNilaiBast();
        final Integer nilai = Integer.valueOf(nilaiBast.intValueExact());
        final String idkegawal = bast.getIdKegAwal();
        final String idkeg = bast.getIdkegiatan();

        final Map paramdel = new HashMap();
        paramdel.put("tahun", bast.getTahunAnggaran());
        paramdel.put("idskpd", bast.getSkpd().getIdSkpd());
        paramdel.put("nobast", bast.getNoBast());

        if (!idkegawal.equals(idkeg)) {
            // jika kegiatan berubah.. data sebelumnya dihapus
            bastmapper.deleteBastByNoBastSkpdAndTahun(paramdel);
        }

        if (nilai > 0) {
            bastmapper.insertBast(bast);
        }

    }

    @Override
    public Bast getBastById(Integer id) {
        return bastmapper.getBastById(id);
    }

    @Override
    public Bast getKegiatanById(Integer idkegiatan) {
        return bastmapper.getKegiatanById(idkegiatan);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBast(Bast param) {
        bastmapper.updateUmkBast(param);
        bastmapper.updateBast(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBast(Integer id) {
        bastmapper.deleteBast(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBastList(List<Bast> listbast) {
        final Map param = new HashMap();
        final Bast bast0 = listbast.get(0);
        param.put("tahun", bast0.getTahunAnggaran());
        param.put("idskpd", bast0.getSkpd().getIdSkpd());
        final String noBast = genNumberMapper.getBASTNo(param);
        for (Bast bast : listbast) {
            final BigDecimal nilaiBast = bast.getNilaiBast();
            bast.setNoBast(noBast);

            // final Integer nilai = Integer.valueOf(nilaiBast.intValueExact());
            log.debug("nilai bast == " + nilaiBast);

            //log.debug("   bast ============ "+bast.getIdkegiatan()+"  getIdAkun  ==> "+bast.getIdAkun()+"  getIdKontrak  ====>  "+bast.getKetBast());
            /*if (nilai > 0) {
             log.debug("-------------- nilai bast > 0 --------------");
             bastmapper.insertBast(bast);
             }*/
            if (nilaiBast.compareTo(BigDecimal.ZERO) == 0) {
                log.debug("-------------- nilai bast == 0 --------------");

            } else {
                bastmapper.insertBast(bast);
                log.debug("****************** ELSE : insert ****************** ");
            }

        }
    }

    @Override
    public List<Bast> getBastByNoBastSkpdAndTahun(Map param) {
        return bastmapper.getBastByNoBastSkpdAndTahun(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBastList(List<Bast> listbast) {
        final Map param = new HashMap();
        final Bast bast0 = listbast.get(0);

        param.put("tahun", bast0.getTahunAnggaran());
        param.put("idskpd", bast0.getSkpd().getIdSkpd());
        //final String noBast = genNumberMapper.getBASTNo(param);
        param.put("tahun", bast0.getTahunAnggaran());
        param.put("idskpd", bast0.getSkpd().getIdSkpd());
        param.put("nobast", bast0.getNoBast());
        log.debug(" XXXXXXXXXXXXXXXXXXXXX " + param.toString());

        bastmapper.deleteBastByNoBastSkpdAndTahun(param);
        for (Bast bast : listbast) {
            final BigDecimal nilaiBast = bast.getNilaiBast();
            final Integer nilai = Integer.valueOf(nilaiBast.intValueExact());//nilaiBast.intValue();

            if (bast != null && bast.getIdBast() != null && nilai > 0) {
                bastmapper.insertBastUpdate(bast);
            }
            // bastmapper.updateBast(bast);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBastByNoBastSkpdAndTahun(Map param) {
        bastmapper.deleteBastByNoBastSkpdAndTahun(param);
    }

    @Override
    public List<Bast> getSisaBast(Map param) {
        return bastmapper.getSisaBast(param);
    }

    @Override
    public String getIdspd(String idkontrak) {
        return bastmapper.getIdspd(idkontrak);
    }

    @Override
    public Integer getStatusUangMuka(Map param) {
        return bastmapper.getStatusUangMuka(param);
    }

    @Override
    public List<Bast> getStatusKontrakRinci(Map param) {
        return bastmapper.getStatusKontrakRinci(param);
    }

    @Override
    public List<Bast> getAkunKontrakRinci(Map param) {
        return bastmapper.getAkunKontrakRinci(param);
    }

    @Override
    public Integer getBanyakAkunKontrakRinci(Map param) {
        return bastmapper.getBanyakAkunKontrakRinci(param);
    }

    @Override
    public Bast getKodeUMK(Map param) {
        return bastmapper.getKodeUMK(param);
    }

    @Override
    public List<Bast> getAkunSpd(Map param) {
        return bastmapper.getAkunSpd(param);
    }

    @Override
    public Integer getBanyakAkunSpd(Map param) {
        return bastmapper.getBanyakAkunSpd(param);
    }

    @Override
    public List<Bast> getAkunKontrak(Map param) {
        return bastmapper.getAkunKontrak(param);
    }

    @Override
    public Integer getBanyakAkunKontrak(Map param) {
        return bastmapper.getBanyakAkunKontrak(param);
    }

}
