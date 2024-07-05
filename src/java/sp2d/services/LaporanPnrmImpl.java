package sp2d.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp2d.entity.LaporanPnrmMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2d;
import spp.model.SppUp;
import spp.model.Sp2dBatal;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("lapPnrmServices")
public class LaporanPnrmImpl implements LaporanPnrmServices {

    @Autowired
    private LaporanPnrmMapper cetakSp2dMapper;

   
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakSp2dMapper.getnilaiparam(param);
    }

   @Override
    public List<Sp2d> getWilayah(Map param) {
        return cetakSp2dMapper.getWilayah(param);
    }

    @Override
    public List<Skpd> getListSkpdPnrm(Map param) {
        return cetakSp2dMapper.getListSkpdPnrm(param);
    }

    @Override
    public Integer getBanyakListSkpdPnrm(Map param) {
        return cetakSp2dMapper.getBanyakListSkpdPnrm(param);
    }
    
}
