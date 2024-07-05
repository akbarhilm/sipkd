package eset.entity;

import java.util.List;
import java.util.Map;
import eset.model.PejabatPpkd;
import eset.model.Skpd;

public interface SkpdMapper {

    List<Skpd> getAllSkpdBL(Map<String, Object> param);

    Integer getBanyakSkpdBL(Map<String, Object> param);

    List<Skpd> getSkpdAll(Map<String, Object> param);

    Integer getBanyakSkpdAll(Map<String, Object> param);

    List<Skpd> getSkpdWil(Map<String, Object> param);

    Integer getBanyakSkpdWil(Map<String, Object> param);

    List<Skpd> getSkpdBantuan(Map<String, Object> param);

    Integer getBanyakSkpdBantuan(Map<String, Object> param);

    Skpd getDetailSkpdById(Integer idSkpd);

    List<PejabatPpkd> getAllPejabatPpkd(Map<String, Object> param);

    Integer getBanyakPejabatPPKD(Map<String, Object> param);

    List<Map<String, Object>> getAllSkpdRoot();

    List<Skpd> getAllSkpdAnak(Map<String, Object> param);
}
