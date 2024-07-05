package sp2d.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp2d.entity.CetakSp2dMapper;
import sp2d.entity.GenerateIdMapper;
import sp2d.entity.Sp2dTransferMapper;
import spp.model.GenerateId;
import spp.model.HariKerja;
import spp.model.SppUp;
import spp.model.Sp2dBatal;
import spp.model.Sp2dSahTransfer;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("Sp2dTransferServices")
public class Sp2dTransferImpl implements Sp2dTransferServices {

    @Autowired
    private CetakSp2dMapper cetakSp2dMapper;

    @Autowired
    private Sp2dTransferMapper transferSp2dMapper;

    @Autowired
    private GenerateIdMapper genMapper;

    //------------------------ yang baru -------------------------
    @Override
    public List<Sp2dSahTransfer> getlistsp2dtransfer(Map param) {
        return transferSp2dMapper.getlistsp2dtransfer(param);
    }

    @Override
    public Integer getbanyaksp2dtransfer(Map map) {
        return transferSp2dMapper.getbanyaksp2dtransfer(map);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dBank(Sp2dSahTransfer sp2d) {
        transferSp2dMapper.insertSp2dBank(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dBank(Sp2dSahTransfer sp2d) {
        transferSp2dMapper.updateSp2dBank(sp2d);
    }

    @Override
    public Sp2dSahTransfer getSysdate(Map param) {
        return transferSp2dMapper.getSysdate(param);
    }

    @Override
    public Sp2dSahTransfer getKodeStan(Map param) {

        GenerateId kodestan = new GenerateId();
        kodestan.setNamaTabel("KODESTAN");
        genMapper.insertGetIdSp2d(kodestan);
        Integer newkodestan = kodestan.getId();
        Sp2dSahTransfer sp2dTransfer = new Sp2dSahTransfer();
        sp2dTransfer.setKodeStan(new DecimalFormat("000000").format(newkodestan));
        sp2dTransfer.setNomorRef(new DecimalFormat("0000000000").format(newkodestan));
        sp2dTransfer.setTglProses(transferSp2dMapper.getSysdate(param).getTglProses());
        return sp2dTransfer;

    }

    @Override
    public Sp2dSahTransfer getBanyakTf(Map param) {
        return transferSp2dMapper.getBanyakTf(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dManual(Sp2dSahTransfer sp2d) {
        transferSp2dMapper.insertSp2dManual(sp2d);
    }

}
