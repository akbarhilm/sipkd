/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.entity;

import java.util.List;
import java.util.Map;
import spp.model.TutupBuku;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface TutupBukuMapper {

    TutupBuku getBendahara(Map param);

    TutupBuku getBendaharaPpkd(Map param);

    List<TutupBuku> getBulanTutup(Map param);

    TutupBuku getNilaiKas(Map param);

    TutupBuku getNilaiSaldo(Map param);

    void insertTutupBku(TutupBuku param);

    void updateBkuPengeluaran(TutupBuku param);

    void deleteTutupBku(TutupBuku param);

    void insertBKUPengeluaran(TutupBuku param);

    Integer getBanyakDataBKUPengeluaran(TutupBuku param);

}
