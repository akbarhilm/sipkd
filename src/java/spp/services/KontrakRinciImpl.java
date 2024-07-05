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
import spp.entity.KontrakRinciMapper;
import spp.entity.GenNumberMapper;
import spp.model.KontrakRinci;
import spp.model.Skpd;

@Transactional(readOnly = true)
@Service("KontrakRinciServices")
public class KontrakRinciImpl implements KontrakRinciServices {

    @Autowired
    private KontrakRinciMapper kontrakmapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    private static final Logger log = LoggerFactory.getLogger(KontrakRinciImpl.class);

    @Override
    public List<KontrakRinci> getAkun(Map param) {
        return kontrakmapper.getAkun(param);

    }

    @Override
    public Integer getBanyakAkun(Map param) {
        return kontrakmapper.getBanyakAkun(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertKontrakRinci(List<KontrakRinci> listkontrak) {
        final Map param = new HashMap();
        final KontrakRinci kontrak0 = listkontrak.get(0);
        param.put("tahun", kontrak0.getTahun());
        param.put("idskpd", kontrak0.getIdSkpd());

        for (KontrakRinci kontrak : listkontrak) {
            final BigDecimal nilaiKontrak = kontrak.getNilaiKontrak();

            log.debug("nilai bast == " + nilaiKontrak);

            if (nilaiKontrak.compareTo(BigDecimal.ZERO) == 0) {
                log.debug("-------------- nilai bast == 0 --------------");

            } else {
                kontrakmapper.insertKontrakRinci(kontrak);
                log.debug("****************** ELSE : insert ****************** ");
            }

        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateKontrakRinci(List<KontrakRinci> listkontrak) {
        final Map param = new HashMap();
        final KontrakRinci kontrak0 = listkontrak.get(0);

        param.put("tahun", kontrak0.getTahun());
        param.put("idSkpd", kontrak0.getIdSkpd());
        param.put("idKontrak", kontrak0.getIdKontrak());

        kontrakmapper.updateNolKontrakRinci(kontrak0);

        for (KontrakRinci kontrak : listkontrak) {
            Integer idrinci = kontrak.getIdKontrakRinci();
            log.debug("IMPLEMENTS listkontrak idrinci =========== " + idrinci);
            if (idrinci > 0) {
                kontrakmapper.updateKontrakRinci(kontrak);
            } else {
                kontrakmapper.insertKontrakRinci(kontrak);
            }
        }

        //kontrakmapper.deleteBastByNoBastSkpdAndTahun(param);
        /*for (Bast bast : listbast) {
         final BigDecimal nilaiBast = bast.getNilaiBast();
         final Integer nilai = Integer.valueOf(nilaiBast.intValueExact());//nilaiBast.intValue();

         if (bast != null && bast.getIdBast() != null && nilai > 0) {
         kontrakmapper.insertBastUpdate(bast);
         }
         // kontrakmapper.updateBast(bast);
         }*/
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteKontrakRinci(Integer param) {
        kontrakmapper.deleteKontrakRinci(param);
    }

    @Override
    public KontrakRinci getNilaiKontrak(Map param) {
        return kontrakmapper.getNilaiKontrak(param);

    }
}
