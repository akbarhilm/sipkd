/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.Fungsi;
import spp.model.Urusan;

public interface UrusanServices {

    List<Urusan> getUrusan(Map param);

    Integer getBanyakAllUrusan(Map param);

    List<Fungsi> getAllFungsi(Map param);

    Integer getBanyakAllFungsi(Map param);
    
     void insertUrusan(Urusan param);
     
    void updateUrusan(Urusan param);

    void deleteUrusan(Integer id);
     
   Urusan getUrusanById(Integer id);
}
