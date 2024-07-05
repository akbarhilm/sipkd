package sp2d.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.SppUp;
import spp.model.Sp2dSahTransfer;
import spp.model.Sp2dBatal;

/**
 *
 * @author Zainab
 */
public interface Sp2dTransferMapper {

    List<Sp2dSahTransfer> getlistsp2dtransfer(Map param);

    Integer getbanyaksp2dtransfer(Map param);

    public void insertSp2dBank(Sp2dSahTransfer sp2d);

    public void updateSp2dBank(Sp2dSahTransfer sp2d);

    Sp2dSahTransfer getSysdate(Map param);

    Sp2dSahTransfer getKodeStan(Map param);

    Sp2dSahTransfer getBanyakTf(Map param);

    Integer getBanyakTfInt(Map param);

    //  public void insertSp2dBank(Sp2dSahTransfer sp2d);
    public void insertSp2dManual(Sp2dSahTransfer sp2d);

}
