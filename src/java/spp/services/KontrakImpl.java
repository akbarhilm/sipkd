/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.KontrakMapper;
import spp.model.Kontrak;
import spp.model.Metode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional(readOnly = true)
@Service("kontrakServices")
public class KontrakImpl implements KontrakServices {

    @Autowired
    private KontrakMapper kontrakMapper;

    private static final Logger log = LoggerFactory.getLogger(KontrakImpl.class);

    @Override
    public List<Kontrak> getKontrak(Map<String, Object> param) {
        return kontrakMapper.getKontrak(param);
    }

    @Override
    public Integer getCountKontrak(Map param) {
        return kontrakMapper.getCountKontrak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateKontrak(Kontrak param) {
        final Integer idkeg = param.getKegiatan().getIdKegiatan();
        final Integer idkeglama = param.getIdKegiatanLama();
        final Integer idkontrak = param.getIdKontrak();

        log.debug("***** updateKontrak -- idkontrak ===================== " + idkontrak);
        log.debug("***** updateKontrak -- idkeglama ===================== " + idkeglama);
        log.debug("***** updateKontrak -- idkegbaru ===================== " + idkeg);

        if (Objects.equals(idkeg, idkeglama)) {
            log.debug("*******************  KEG == KEG  ******************");
        } else {
            log.debug("*******************  KEG <> KEG  ******************");
            //kontrakMapper.deleteKontrakRinci(idkontrak);
        }
        kontrakMapper.updateKontrak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteKontrak(Integer id) {
        kontrakMapper.deleteKontrakRinci(id);
        kontrakMapper.deleteKontrak(id);
    }

    @Override
    public Kontrak getKontrakById(Integer id) {
        return kontrakMapper.getKontrakById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertKontrak(Kontrak param) {
        kontrakMapper.insertKontrak(param);
        //log.debug("insertKontrak getIdKontrak ********** "+param.get());
    }

    @Override
    public List<Metode> getMetode(Map param) {
        return kontrakMapper.getMetode(param);
    }

    @Override
    public Integer getCountMetode(Map param) {
        return kontrakMapper.getCountMetode(param);
    }

    @Override
    public List<Kontrak> getKegiatan(Map param) {
        return kontrakMapper.getKegiatan(param);
    }

    @Override
    public Integer getCountKegiatan(Map param) {
        return kontrakMapper.getCountKegiatan(param);
    }

    @Override
    public String getnoKontrak(Integer param) {
        return kontrakMapper.getnoKontrak(param);
    }

    @Override
    public String getSisaKontrak(Map param) {
        return kontrakMapper.getSisaKontrak(param);
    }

    @Override
    public boolean validasiNomorKontrak(String noKontrak) {
        return kontrakMapper.validasiNomorKontrak(noKontrak) > 0 ? false : true;
    }

    @Override
    public BigDecimal getTotalNilaiKontrakPerBast(Map<String, Integer> map) {
        return kontrakMapper.getTotalNilaiKontrakPerBast(map);
    }

    @Override
    public Integer getKodeMultiyear(Map param) {
        return kontrakMapper.getKodeMultiyear(param);
    }

    @Override
    public List<Kontrak> getSisaKontrakSpj(Map param) {
        return kontrakMapper.getSisaKontrakSpj(param);
    }

    @Override
    public List<Kontrak> getBankInduk(Map param) {
        return kontrakMapper.getBankInduk(param);
    }

    @Override
    public Integer getCountBankInduk(Map param) {
        return kontrakMapper.getCountBankInduk(param);
    }

    @Override
    public List<Kontrak> getNoKontrakCek(Map param) {
        return kontrakMapper.getNoKontrakCek(param);
    }

    @Override
    public List<Kontrak> getSisaKontrakSpd(Map param) {
        return kontrakMapper.getSisaKontrakSpd(param);
    }

    @Override
    public Kontrak getSisaAnggaran(Map param) {
        return kontrakMapper.getSisaAnggaran(param);
    }

}
