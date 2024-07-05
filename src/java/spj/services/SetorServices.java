

package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Setor;
import spp.model.SetorRinci;
/**
 *
 * @author Husen
 */
public interface SetorServices {
    
    List<SetorRinci> getForRinci(Map param);
    
    List<SetorRinci> getForAddRinci(Map param);
    
    Integer getCountForRinci(Map param);
    
    List<SetorRinci> getForRinciBiaya(Map param);
    
    List<SetorRinci> getForAddRinciBiaya(Map param);
    
    Integer getCountForRinciBiaya(Map param);
    
    Integer getCountForAddRinciBiaya(Map param);
    
    List<SetorRinci> getForRinciBtl(Map param);
    
    List<SetorRinci> getForAddRinciBtl(Map param);
    
    Integer getCountForRinciBtl(Map param);
    
    Integer getCountForAddRinciBtl(Map param);
    
    List<SetorRinci> getForRinciBantuan(Map param);
    
    Integer getCountForRinciBantuan(Map param);
    
    List<SetorRinci> getKegiatan(Map<String, Object> param);
    
    List<SetorRinci> getKegiatanBantuan(Map<String, Object> param);
    
    List<SetorRinci> getKegiatanById(Map<String, Object> param);
    
    List<SetorRinci> getKegiatanBantuanById(Integer idSetor);
    
    String getKodeKegiatanBantuan (String idSetor);
   
    void insertSetor(Setor param);
    
    void insertSetorUPGU(Setor param);
    
    void insertSetorBiaya(Setor param);
    
    void insertSetorBtl(Setor param);
    
    void insertSetorBantuan(Setor param);
    
    void insertSetorRinci(SetorRinci SetorRinci, Setor setor);
    
    void insertSetorRinciUPGU(SetorRinci SetorRinci, Setor setor);
    
    void insertSetorRinciBiaya(SetorRinci SetorRinci, Setor setor);
    
    void insertSetorRinciBtl(SetorRinci SetorRinci, Setor setor);
    
    void insertSetorRinciBantuan(SetorRinci SetorRinci, Setor setor);
    
    void updateSetor(Setor param);
    
    void updateSetorUPGU(Setor param);
    
    void updateSetorBiaya(Setor param);
    
    void updateSetorBtl(Setor param);
    
    void updateSetorBantuan(Setor param);
    
    void updateRinci(SetorRinci SetorRinci, Setor setor);
    
    void updateRinciUPGU(SetorRinci SetorRinci, Setor setor);
        
    void updateRinciBiaya(SetorRinci SetorRinci, Setor setor);
    
    List<Setor> getSetor(Map param);
    
    List<Setor> getSetorTU(Map param);
    
    List<Setor> getSetorUP(Map param); 

    List<Setor> getSetorBiaya(Map param); 
    
    List<Setor> getSetorBtl(Map param); 
    
    List<Setor> getSetorBantuan(Map param); 

    Integer getCountSetor(Map param); 
    
    Integer getCountSetorUpGu(Map param);
    
    Integer getCountSetorTU(Map param);
    
    Integer getCountSetorBiaya(Map param);
    
    List<Setor> getEditSetor(Map param);
    
    List<Setor> getEditSetorBiaya(Map param);
    
   // List<Setor> getEditSetorBtl(Map param);
    
    Integer getCountEditSetor(Map param);
    
    Integer getCountEditSetorBiaya(Map param);
    
    //Integer getCountEditSetorBtl(Map param);
    
    Setor getSetorById(Integer idSetor);
    
    Setor getSetorByIdUP(Integer idSetor);
    
    Setor getSetorByIdBiaya(Integer idSetor);
    
    Setor getSetorByIdBtl(Integer idSetor);
    
    Setor getSetorByIdBantuan(Integer idSetor);
    
    Integer getIdSetorBiaya();
   
    void deleteSetorMaster(Integer idSetor);
    
    String getNilaiSisaUP(Map param);

    String getNilaiSisaTU(Map param);

    Integer getBanyakKegiatanTU(Map param);

    List<SetorRinci> getKegiatanTU(Map param);

    Integer getBebanSetorUP(Map param);

    List<Setor> getKegiatanLS(Map<String, Object> param);

    Integer getBanyakKegiatanLS(Map param);

    List<SetorRinci> getListBlLs(Map param);

    Integer getBanyakListBlLs(Map param);
    
    List<Setor> getKegiatanBTL(Map<String, Object> param);

    Integer getBanyakKegiatanBTL(Map param);

    List<SetorRinci> getListBtlLs(Map param);

    Integer getBanyakListBtlLs(Map param);
    
    List<Setor> getDataBku(Map<String, Object> param);

    Integer getBanyakListTU(Map param);
    
    List<SetorRinci> getListTU(Map<String, Object> param);

    List<Setor> getSalsoAwalLs(Map<String, Object> param);

    Integer getNilaiSetorSA(Integer idSetor);
    
}

