/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.entity;

import java.util.List;
import java.util.Map;
import spp.model.Setor;
import spp.model.SetorRinci;

/**
 *
 * @author Zainab
 */
public interface SetorJukorMapper {

    List<Setor> getSetor(Map param);

    Integer getCountSetor(Map param);

    List<SetorRinci> getKegiatan(Map<String, Object> param);

    List<SetorRinci> getForAddRinci(Map param);

    Integer getCountForRinci(Map param);

    void insertSetor(Setor param);

    void insertSetorRinci(SetorRinci Setor);

    List<Setor> getSetorBtl(Map param);

    Integer getCountSetorBtl(Map param);

    List<SetorRinci> getForAddRinciBtl(Map param);

    Integer getCountForAddRinciBtl(Map param);

    void insertSetorBtl(Setor param);

    void insertSetorRinciBtl(SetorRinci Setor);

    List<Setor> getEditSetor(Map param);

    Integer getCountEditSetor(Map param);

    void updateSetor(Setor param);

    void deleteSetorRinci(SetorRinci param);

    void deleteSetor(SetorRinci param);

    List<SetorRinci> getKegiatanById(Map<String, Object> param);

    Setor getSetorById(Integer idSetor);

    Setor getSetorByIdBtl(Integer idSetor);

    List<SetorRinci> getForRinciBtl(Map param);

    Integer getCountForRinciBtl(Map param);

    void updateSetorBtl(Setor param);

    Integer getIdskpdLama(Map param);

    List<Setor> getListKegJukorBlLs(Map param);

    Integer getBanyakKegJukorBlLs(Map param);

    List<SetorRinci> getListAkunJukorBlLs(Map param);

    Integer getBanyakAkunJukorBlLs(Map param);

    List<Setor> getListJukorBtl(Map param);

    Integer getBanyakJukorBtl(Map param);

    List<SetorRinci> getListAkunJukorBtl(Map param);

    Integer getBanyakAkunJukorBtl(Map param);

}
