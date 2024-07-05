/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.ProgramMapper;
import spp.model.Program;
import spp.model.Urusan;

/**
 *
 * @author maman sulaeman
 */
@Transactional(readOnly = true)
@Service("programServices")

public class ProgramImpl implements ProgramServices {

    @Autowired
    private ProgramMapper programmapper;

    @Override
    public List<Urusan> getUrusan(Map param) {
        return programmapper.getUrusan(param);
    }

    @Override
    public List<Program> getProgram(Map param) {
        return programmapper.getProgram(param);
    }

    @Override
    public Integer getBanyakAllProgram(Map param) {
        return programmapper.getBanyakAllProgram(param);
    }

    @Override
    public Integer getBanyakAllUrusan(Map param) {
        return programmapper.getBanyakAllUrusan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertProgram(Program param) {
        programmapper.insertProgram(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateProgram(Program param) {
        programmapper.updateProgram(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProgram(Integer id) {
        programmapper.deleteProgram(id);
    }

    @Override
    public Program getProgramById(Integer id) {
        return programmapper.getProgramById(id);
    }

}
