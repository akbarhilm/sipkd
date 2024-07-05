package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.SppUp;
import spp.model.Sp2dBatal;
import spp.model.Sp2dSahTransfer;

/**
 *
 * @author Zainab
 */
public interface Sp2dTransferServices {

    List<Sp2dSahTransfer> getlistsp2dtransfer(Map param);

    Integer getbanyaksp2dtransfer(Map param);

    public void insertSp2dBank(Sp2dSahTransfer sp2d);

    public void updateSp2dBank(Sp2dSahTransfer sp2d);

    Sp2dSahTransfer getSysdate(Map param);

    Sp2dSahTransfer getKodeStan(Map param);

    Sp2dSahTransfer getBanyakTf(Map param);

    public void insertSp2dManual(Sp2dSahTransfer sp2d);

}
