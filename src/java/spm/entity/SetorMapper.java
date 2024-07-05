/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;
import java.util.List;
import java.util.Map;
import spp.model.Setor;
import spp.model.SetorRinci;
/**
 *
 * @author Husen
 */
public interface SetorMapper {
    
     List<Setor> getSetor(Map param);
     
     List<Setor> getSetorUP(Map param);
     
     List<Setor> getSetorBiaya(Map param);
     
     List<Setor> getSetorBantuan(Map param);
     
     Integer getCountSetor(Map param);
    
     Integer getCountSetorBiaya(Map param);
    
     List<Setor> getEditSetor(Map param);
     
     List<Setor> getEditSetorBiaya(Map param);
     
     Integer getCountEditSetor(Map param);
     
     Integer getCountEditSetorBiaya(Map param);
     
     List<SetorRinci> getKegiatan(Map<String, Object> param);
     
     List<SetorRinci> getKegiatanById(Map<String, Object> param);
    
     List<SetorRinci> getForRinci(Map param);
     
     Integer getCountForRinci(Map param);
    
     List<SetorRinci> getForRinciBiaya(Map param);
     
     Integer getCountForRinciBiaya(Map param);
     
     List<SetorRinci> getForRinciBantuan(Map param);
     
     Integer getCountForRinciBantuan(Map param);
    
     
    
  //   Integer getKegiatan(Map param);
     
    // Integer getIdSetor(Setor param);
    
    void insertSetor(Setor param);

    void insertSetorUPGU(Setor param);
    
    void insertSetorBiaya(Setor param);

    void insertSetorBantuan(Setor param);

    void insertSetorRinci(SetorRinci Setor);

    void insertSetorRinciUPGU(SetorRinci Setor);
    
    void insertSetorRinciBiaya(SetorRinci Setor);

    void insertSetorRinciBantuan(SetorRinci Setor);

    public void getIdSetor(SetorRinci setorRinci);

    Setor getSetorById(Integer idSetor);
    
    Setor getSetorByIdUP(Integer idSetor);
    
    Setor getSetorByIdBiaya(Integer idSetor);
    
    Setor getSetorByIdBantuan(Integer idSetor);
   
    void updateSetor(Setor param);
    
    void updateSetorUPGU(Setor param);

    void updateSetorBiaya(Setor param);
    
    void updateRinci(SetorRinci Setor);

    void updateRinciUPGU(SetorRinci Setor);

    void updateRinciBiaya(SetorRinci Setor);

    void deleteSetorMaster(Integer idspp);

    void deleteSetorRinci(Integer idspp);
    
    void deleteSetorRinciBiaya(SetorRinci idspp);
    
    Integer getIdSetorBiaya();

    public SetorRinci getKegiatanById(Integer idSetor);
}
