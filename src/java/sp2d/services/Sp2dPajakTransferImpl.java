/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp2d.entity.GenerateIdMapper;
import sp2d.entity.Sp2dPajakTransferMapper;
import spp.model.GenerateId;
import spp.model.Sp2dPajakJson;
import spp.model.Sp2dPajakTransfer;

@Transactional(readOnly = true)
@Service("sp2dPajakTransferServices")
public class Sp2dPajakTransferImpl implements Sp2dPajakTransferServices {

    private static final Logger log = LoggerFactory.getLogger(Sp2dPajakTransferImpl.class);
    @Autowired
    private Sp2dPajakTransferMapper sp2dMapper;
    @Autowired
    private GenerateIdMapper genMapper;

    @Override
    public List<Sp2dPajakTransfer> getListIndex(Map<String, Object> param) {
        return sp2dMapper.getListIndex(param);
    }

    @Override
    public Integer getBanyakListIndex(Map param) {
        return sp2dMapper.getBanyakListIndex(param);
    }

    @Override
    public Sp2dPajakTransfer getKodeStan() {
        GenerateId kodestan = new GenerateId();
        kodestan.setNamaTabel("IDREQUEST");
        genMapper.insertGetId(kodestan);

        GenerateId bulkid = new GenerateId();
        bulkid.setNamaTabel("BULKIDREQUEST");
        genMapper.insertGetId(bulkid);

        Sp2dPajakTransfer pajakTransfer = new Sp2dPajakTransfer();
        pajakTransfer.setKodeStan(kodestan.getId().toString());
        pajakTransfer.setBulkIdRequest(bulkid.getId().toString());
        //bkuTransfer.setNomorRef(new DecimalFormat("0000000000").format(newkodestan));

        return pajakTransfer;
    }

    @Override
    public String getGeneratedId(String table) {
        GenerateId generateId = new GenerateId();
        generateId.setNamaTabel(table);
        generateId.setKodeApp("01"); // 01 UNTUK PAJAK SP2D
        genMapper.insertGetId(generateId);
        return generateId.getIdFormat().toString();
    }

    @Override
    @Transactional(readOnly = false)
    public void insertPajakBank(Sp2dPajakTransfer sp2d) {
        sp2dMapper.insertPajakBank(sp2d);
    
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePajakBank(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updatePajakBank(sp2d);

    }

    @Override
    @Transactional(readOnly = false)
    public void updateSpmPot(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updateSpmPot(sp2d);

    }

    @Override
    public List<Sp2dPajakTransfer> getTglSp2dSah(Map<String, Object> param) {
        return sp2dMapper.getTglSp2dSah(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSpmPotReInq(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updateSpmPotReInq(sp2d);

    }

    @Override
    public Integer getBanyakListIndexCetak(Map param) {
        return sp2dMapper.getBanyakListIndexCetak(param);
    }

    @Override
    public List<Sp2dPajakTransfer> getListIndexCetak(Map<String, Object> param) {
        return sp2dMapper.getListIndexCetak(param);
    }

    @Override
    public List<Sp2dPajakTransfer> getTglSp2dSahCetak(Map<String, Object> param) {
        return sp2dMapper.getTglSp2dSahCetak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateCountCetak(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updateCountCetak(sp2d);

    }

    @Override
    public Sp2dPajakTransfer getPajak(Map param) {
        return sp2dMapper.getPajak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateInquiry(Map<String, Object> param) {
        sp2dMapper.updateInquiry(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePajakBankCreateBilling(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updatePajakBankCreateBilling(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePajakBankPaymentBilling(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updatePajakBankPaymentBilling(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePajakBankReInquiryBilling(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updatePajakBankReInquiryBilling(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSpmPotCreateBilling(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updateSpmPotCreateBilling(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSpmPotPaymentBilling(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updateSpmPotPaymentBilling(sp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateEC(Sp2dPajakTransfer sp2d) {
        sp2dMapper.updatePajakBankPaymentBilling(sp2d);
        sp2dMapper.updateSpmPotPaymentBilling(sp2d);
    }

    @Override
    public JSONObject validateJson(JSONObject request, JSONObject response, String action) {
        JSONObject json = response;
        String error = "";
        switch (action) {
            case "BILLING":
                if (!request.getString("idrequest").equals(response.getString("idrequest"))) {
                    error += "ID Request";
                }
                if (!request.getString("taxid").equals(response.getString("taxid"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "NPWP Pemungut";

                }
                if (!request.getString("typeoftax").equals(response.getString("typeoftax"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Akun Pajak";
                }
                if (!request.getString("subtypeoftax").equals(response.getString("subtypeoftax"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Jenis Setor";
                }
                if (!request.getString("taxpayerid").equals(response.getString("taxpayerid"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "NPWP Penyetor";
                }
                if (!request.getString("idapp").equals(response.getString("idapp"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "ID Aplikasi";
                }
                if (!request.getString("kodeapp").equals(response.getString("kodeapp"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Aplikasi";
                }
                if (error.isEmpty()) {
                    json.put("response", "valid");
                } else {
                    json.put("response", "invalid");
                    json.put("error", error);
                }
                break;
            case "INQUIRY":
                if (!request.getString("idrequest").equals(response.getString("idrequest"))) {
                    error += "ID Request";
                }
                if (!request.getString("taxid").equals(response.getString("taxid"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "NPWP Pemungut";

                }
                if (!request.getString("typeoftax").equals(response.getString("typeoftax"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Akun Pajak";
                }
                if (!request.getString("subtypeoftax").equals(response.getString("subtypeoftax"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Jenis Setor";
                }
                if (!request.getString("billingcode").equals(response.getString("billingcode"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Billing";
                }
                if (Integer.parseInt(request.getString("paymentamount")) != Integer.parseInt(response.getString("paymentamount"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Nilai";
                }
                if (!request.getString("idapp").equals(response.getString("idapp"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "ID Aplikasi";
                }
                if (!request.getString("kodeapp").equals(response.getString("kodeapp"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Aplikasi";
                }
                if (error.isEmpty()) {
                    json.put("response", "valid");
                } else {
                    json.put("response", "invalid");
                    json.put("error", error);
                }
                break;
            case "PAYMENT":
                if (!request.getString("idrequest").equals(response.getString("idrequest"))) {
                    error += "ID Request";
                }
                if (!request.getString("taxid").equals(response.getString("taxid"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "NPWP Pemungut";

                }
                if (!request.getString("typeoftax").equals(response.getString("typeoftax"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Akun Pajak";
                }
                if (!request.getString("subtypeoftax").equals(response.getString("subtypeoftax"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Jenis Setor";
                }
                if (!request.getString("billingcode").equals(response.getString("billingcode"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Billing";
                }
                if (!request.getString("taxpayerid").equals(response.getString("taxpayerid"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "NPWP Penyetor";
                }
                if (!request.getString("idapp").equals(response.getString("idapp"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "ID Aplikasi";
                }
                if (!request.getString("kodeapp").equals(response.getString("kodeapp"))) {
                    if (!error.isEmpty()) {
                        error += ", ";
                    }
                    error += "Kode Aplikasi";
                }
                if (error.isEmpty()) {
                    json.put("response", "valid");
                } else {
                    json.put("response", "invalid");
                    json.put("error", error);
                }
                break;
            default:
                break;
        }

        return json;
    }

    @Override
    @Transactional(readOnly = false)
    public void insertJson(Sp2dPajakJson sp2dPajakJson) {
        sp2dMapper.insertJson(sp2dPajakJson);
    }
}
