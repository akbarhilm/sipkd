/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import spp.model.Sp2dPajakJson;
import spp.model.Sp2dPajakTransfer;

public interface Sp2dPajakTransferServices {

    List<Sp2dPajakTransfer> getListIndex(Map<String, Object> par);

    Sp2dPajakTransfer getPajak(Map param);

    Integer getBanyakListIndex(Map<String, Object> par);

    Sp2dPajakTransfer getKodeStan();

    String getGeneratedId(String table);

    void insertPajakBank(Sp2dPajakTransfer sp2d);

    void updatePajakBank(Sp2dPajakTransfer sp2d);

    void updateSpmPot(Sp2dPajakTransfer sp2d);

    List<Sp2dPajakTransfer> getTglSp2dSah(Map<String, Object> par);

    void updateSpmPotReInq(Sp2dPajakTransfer sp2d);

    List<Sp2dPajakTransfer> getListIndexCetak(Map<String, Object> par);

    Integer getBanyakListIndexCetak(Map<String, Object> par);

    List<Sp2dPajakTransfer> getTglSp2dSahCetak(Map<String, Object> par);

    void updateCountCetak(Sp2dPajakTransfer sp2d);

    void updateInquiry(Map<String, Object> param);

    void updatePajakBankCreateBilling(Sp2dPajakTransfer sp2d);

    void updatePajakBankPaymentBilling(Sp2dPajakTransfer sp2d);

    void updatePajakBankReInquiryBilling(Sp2dPajakTransfer sp2d);

    void updateSpmPotCreateBilling(Sp2dPajakTransfer sp2d);

    void updateSpmPotPaymentBilling(Sp2dPajakTransfer sp2d);

    void updateEC(Sp2dPajakTransfer sp2d);

    JSONObject validateJson(JSONObject bodyElement, JSONObject dataResponseBody, String billing);

    void insertJson(Sp2dPajakJson sp2dPajakJson);

}
