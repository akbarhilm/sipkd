package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Setor;
import spp.model.SetorRinci;

/**
 *
 * @author Zainab
 */
public interface SetorJukorServices {

    List<Setor> getSetor(Map param);

    Integer getCountSetor(Map param);

    List<SetorRinci> getKegiatan(Map<String, Object> param);

    List<SetorRinci> getForAddRinci(Map param);

    Integer getCountForRinci(Map param);

    void insertSetor(Setor param);

    List<Setor> getSetorBtl(Map param);

    Integer getCountSetorBtl(Map param);

    List<SetorRinci> getForAddRinciBtl(Map param);

    Integer getCountForAddRinciBtl(Map param);

    void insertSetorBtl(Setor param);

    List<Setor> getEditSetor(Map param);

    Integer getCountEditSetor(Map param);

    List<SetorRinci> getKegiatanById(Map<String, Object> param);

    void updateSetor(Setor param);

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

    void deleteSetor(SetorRinci param);

    List<Setor> getListJukorBtl(Map param);

    Integer getBanyakJukorBtl(Map param);

    List<SetorRinci> getListAkunJukorBtl(Map param);

    Integer getBanyakAkunJukorBtl(Map param);

}
