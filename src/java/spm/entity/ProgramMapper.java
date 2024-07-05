/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.entity;

import java.util.List;
import java.util.Map;
import spp.model.Program;
import spp.model.Urusan;

/**
 *
 * @author maman sulaeman
 */
public interface ProgramMapper {

    List<Program> getProgram(Map param);

    Integer getBanyakAllProgram(Map param);

    List<Urusan> getUrusan(Map param);

    Integer getBanyakAllUrusan(Map param);

    void insertProgram(Program param);

    void updateProgram(Program param);

    Program getProgramById(Integer id);

    void deleteProgram(Integer id);
}
