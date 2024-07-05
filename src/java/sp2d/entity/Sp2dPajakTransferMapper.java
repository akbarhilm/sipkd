package sp2d.entity;

import java.util.List;
import java.util.Map;
import spp.model.Sp2dPajakJson;
import spp.model.Sp2dPajakTransfer;

/**
 *
 * @author Zainab
 */
public interface Sp2dPajakTransferMapper {

    List<Sp2dPajakTransfer> getListIndex(Map<String, Object> par);

    Integer getBanyakListIndex(Map<String, Object> par);

    void updateInquiry(Map param);

    Sp2dPajakTransfer getPajak(Map param);

    void insertPajakBank(Sp2dPajakTransfer sp2d);

    void updatePajakBank(Sp2dPajakTransfer sp2d);

    void updateSpmPot(Sp2dPajakTransfer sp2d);

    List<Sp2dPajakTransfer> getTglSp2dSah(Map<String, Object> par);

    void updateSpmPotReInq(Sp2dPajakTransfer sp2d);

    List<Sp2dPajakTransfer> getListIndexCetak(Map<String, Object> par);

    Integer getBanyakListIndexCetak(Map<String, Object> par);

    List<Sp2dPajakTransfer> getTglSp2dSahCetak(Map<String, Object> par);

    void updateCountCetak(Sp2dPajakTransfer sp2d);

    void updatePajakBankCreateBilling(Sp2dPajakTransfer sp2d);

    void updatePajakBankPaymentBilling(Sp2dPajakTransfer sp2d);

    void updatePajakBankReInquiryBilling(Sp2dPajakTransfer sp2d);

    void updateSpmPotCreateBilling(Sp2dPajakTransfer sp2d);

    void updateSpmPotPaymentBilling(Sp2dPajakTransfer sp2d);

    public void insertJson(Sp2dPajakJson sp2dPajakJson);

}
