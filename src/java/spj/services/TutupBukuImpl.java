/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.TutupBukuMapper;
import spp.model.TutupBuku;
import spp.model.Skpd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("tutupbukuServices")
public class TutupBukuImpl implements TutupBukuServices {

    private static final Logger log = LoggerFactory.getLogger(TutupBukuImpl.class);
    @Autowired
    private TutupBukuMapper journalMapper;

    @Override
    public TutupBuku getBendahara(Map param) {
        String idskpd = param.get("idskpd").toString();
        String kodewil = param.get("kodewil").toString();

        log.debug("kodewil impl ======= " + kodewil);
        if (("761".equals(idskpd) || "1234".equals(idskpd)) && !"9".equals(kodewil)) {
            return journalMapper.getBendaharaPpkd(param);
        } else {
            return journalMapper.getBendahara(param);
        }

    }

    @Override
    public List<TutupBuku> getBulanTutup(Map param) {
        return journalMapper.getBulanTutup(param);
    }

    @Override
    public TutupBuku getNilaiKas(Map param) {
        return journalMapper.getNilaiKas(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertTutupBku(TutupBuku param) {
        journalMapper.deleteTutupBku(param);
        journalMapper.insertTutupBku(param);
        //journalMapper.insertBKUPengeluaran(param);
        //if (kodetombol == "1"){
        journalMapper.updateBkuPengeluaran(param);
        //endif
        journalMapper.insertBKUPengeluaran(param);
        //System.out.println("tes");
        log.debug("tanggalparam ===== " + param.getTglPenutupan());
    }

    @Override
    @Transactional(readOnly = false)
    public void insertTutupBku(TutupBuku param, String kodetomboltutupbuku) {
        journalMapper.deleteTutupBku(param);
        journalMapper.insertTutupBku(param);
        //journalMapper.insertBKUPengeluaran(param);
        log.debug("kode tombol == " + kodetomboltutupbuku);
        if (kodetomboltutupbuku.equals("1")) {
            journalMapper.updateBkuPengeluaran(param);
            log.debug("masukkan kesini? == " + kodetomboltutupbuku);
        }
        //endif
        journalMapper.insertBKUPengeluaran(param);
        //System.out.println("tes");
        log.debug("tanggalparam ===== " + param.getTglPenutupan());
    }

    @Override
    public TutupBuku getNilaiSaldo(Map param) {
        return journalMapper.getNilaiSaldo(param);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getBanyakDataBKUPengeluaran(TutupBuku param) {
        return journalMapper.getBanyakDataBKUPengeluaran(param);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
