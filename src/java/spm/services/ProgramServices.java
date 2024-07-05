/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.Program;
import spp.model.Urusan;

/**
 *
 * @author maman sulaeman
 */
public interface ProgramServices {

    List<Urusan> getUrusan(Map param);

    Integer getBanyakAllProgram(Map param);

    List<Program> getProgram(Map param);

    Integer getBanyakAllUrusan(Map param);

    void insertProgram(Program param);

    void updateProgram(Program program);
    
    void deleteProgram(Integer id);
     
     Program getProgramById(Integer id);

}
