package spp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.SppBlMapper;
import spp.model.SppBl;
import spp.model.SppBlRinci;
import spp.entity.GenNumberMapper;
import spp.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("sppBlServices")
public class SppBlImpl implements SppBlServices {

    private static final Logger log = LoggerFactory.getLogger(SppBlImpl.class);
    @Autowired
    private SppBlMapper sppBlMapper;
    private SppBl sppBl;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<SppBl> getAllSppBl(Map param) {
        return sppBlMapper.getAllSppBl(param);
    }

    @Override
    public Integer getBanyakSppBl(Map param) {
        return sppBlMapper.getBanyakSppBl(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSppBlMaster(Integer id) {
        sppBlMapper.deleteSppBlMaster(id);
        sppBlMapper.deleteSppBlRinci(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateSppBl(SppBl sppBl) {
        sppBlMapper.updateSppBlMaster(sppBl);
        final Integer idspp = sppBl.getId();
        final List<SppBlRinci> list = sppBl.getSppBlRinci();
        /*
         sppBlMapper.deleteSppBlMaster(idspp);
         for (SppBlRinci sppBlRinci : list) {
         sppBlRinci.setIdspp(idspp);
         sppBlRinci.setNomorBast(SipkdHelpers.getIntFromString(sppBl.getBast().getNoBast()));
         log.debug("   sppBlRinci.getNomorBast() " + sppBlRinci.getNomorBast());
         sppBlMapper.insertSppBlRinci(sppBlRinci);
         }
         */
    }

    @Override
    public List<SppBlRinci> getAllSpdBl(Map param) {
        return sppBlMapper.getAllSpdBl(param);
    }

    @Override
    public Integer getBanyakSpdBl(Map param) {
        return sppBlMapper.getBanyakSpdBl(param);

    }

    @Override
    @Transactional(readOnly = false)
    public void insertSppBl(SppBl sppBl) {
        final Map param = new HashMap();
        param.put("tahun", sppBl.getTahun());
        param.put("idskpd", sppBl.getSkpd().getIdSkpd());
        sppBl.setNoSpp(genNumberMapper.getSppNo(param));
        Boolean x = sppBl.getKodeUangMuka();
        /* if (x = true) {
         sppBlMapper.insertSppBlMasterExceptIdBast(sppBl);
         } else {
         sppBlMapper.insertSppBlMaster(sppBl);
         } */
        sppBlMapper.insertSppBlMaster(sppBl);
        final List<SppBlRinci> list = sppBl.getSppBlRinci();
        for (SppBlRinci sppBlRinci : list) {
            sppBlRinci.setIdspp(sppBl.getId());
            sppBlRinci.setNoSpp(sppBl.getNoSpp());
            sppBlRinci.setAkun(sppBlRinci.getAkun());
            sppBlRinci.setNomorBast(SipkdHelpers.getIntFromString(sppBl.getBast().getNoBast()));
            log.debug("   sppBlRinci.getNomorBast() " + sppBlRinci.getNomorBast());
            log.debug("  sppBlRinci.getAkun()  " + sppBlRinci.getAkun() + "   sppBl.getId()  " + sppBl.getId() + "    " + sppBlRinci.getAkun().getIdAkun());
            sppBlMapper.insertSppBlRinci(sppBlRinci);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void insertSppBlRinci(SppBlRinci sppBlRinci, SppBl sppBl) {
        sppBlRinci.setIdspp(sppBl.getId());
        sppBlRinci.setKegiatan(sppBl.getKegiatan());
        sppBlRinci.setAkun(sppBl.getAkun());

        sppBlMapper.insertSppBlRinci(sppBlRinci);
    }

    @Override
    public SppBl getSppBlById(Integer idspp) {
        return sppBlMapper.getSppBlById(idspp);
    }

    @Override
    public SppBl getSppBlByIdExceptIdBast(Integer idspp) {

        return sppBlMapper.getSppBlByIdExceptIdBast(idspp);
    }

    @Override
    public List<SppBlRinci> getSppBlRinciByIdSpp(Integer idspp) {
        return sppBlMapper.getSppBlRinciByIdSpp(idspp);
    }

    @Override
    public Map getTotalSPDDanSPP(Map param) {
        return sppBlMapper.getTotalSPDDanSPP(param);
    }

    @Override
    public List<SppBl> getBast(Map param) {
        return sppBlMapper.getBast(param);
    }

    @Override
    public List<SppBl> getBast2(Map param) {
        return sppBlMapper.getBast2(param);
    }

    @Override
    public Integer getCountBast(Map param) {
        return sppBlMapper.getCountBast(param);
    }

    @Override
    public Integer getCountBast2(Map param) {
        return sppBlMapper.getCountBast2(param);
    }
}
