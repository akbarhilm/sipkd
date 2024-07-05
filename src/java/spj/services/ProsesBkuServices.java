package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.BukuKasUmum;
import spp.model.ProsesBku;

/**
 *
 * @author Zainab
 */
public interface ProsesBkuServices {

    void insertProsesBku(BukuKasUmum param);

    void insertProsesBkuPPKD(BukuKasUmum param);

    List<Map> getnilaiparam(Map param);

    List<ProsesBku> getListJurnal(Map<String, Object> param);

    Integer getBanyakListJournal(Map<String, Object> param);

    List<ProsesBku> getListJurnalXls(Map<String, Object> param);

    Integer getBanyakListJournalXls(Map<String, Object> param);

}
