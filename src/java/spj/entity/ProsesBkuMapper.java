/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.BukuKasUmum;
import spp.model.ProsesBku;

/**
 *
 * @author Zainab
 */
public interface ProsesBkuMapper {
   
  void insertProsesBku(BukuKasUmum param);
  
  void insertProsesBkuPPKD(BukuKasUmum param);
  
  List<Map> getnilaiparam(Map param);
  
  List<ProsesBku> getListJurnal(Map<String, Object> param);
 
  Integer getBanyakListJournal(Map<String, Object> param);

  List<ProsesBku> getListJurnalXls(Map<String, Object> param);
 
  Integer getBanyakListJournalXls(Map<String, Object> param);

    
}

