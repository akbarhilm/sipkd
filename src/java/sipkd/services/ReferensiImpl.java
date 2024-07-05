/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sipkd.entity.ProgramMapper;
import sipkd.entity.ReferensiMapper;
import sipkd.entity.SkpdMapper;
import sipkd.model.RefProgram;
import sipkd.model.Skpd;
import sipkd.model.SppPaguUp;

/**
 *
 * @author R Tarman
 */
@Transactional(readOnly = true)
@Service("referensiServices")
public class ReferensiImpl implements ReferensiServices {

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private SkpdMapper skpdMapper;

    @Autowired
    private ReferensiMapper referensiMapper;

    @Override
    public List<RefProgram> getprogram() {
        return programMapper.getlistprogram();
    }

    public Integer getBanyakAllUrusan(Map<String, Object> param) {
        return programMapper.getBanyakAllUrusan(param);
    }

    public List<RefProgram> getAllUrusan(Map<String, Object> param) {
        return programMapper.getAllUrusan(param);
    }

    public Integer getBanyakAllProgram(Map<String, Object> param) {
        return programMapper.getBanyakAllProgram(param);
    }

    public List<RefProgram> getAllProgram(Map<String, Object> param) {
        return programMapper.getAllProgram(param);
    }

    @Override
    public Skpd getDetailSKpd(Integer idSkpd) {
        return skpdMapper.getDetailSkpd(idSkpd);
    }

    @Override
    public Skpd getDetailSKpdByLevel(Map<String, Object> param) {
        return skpdMapper.getDetailSkpdByLevel(param);
    }

    @Override
    public List<SppPaguUp> getAllSppPaguUp(Map<String, Object> param) {
        return referensiMapper.getAllSppPaguUp(param);
    }

    @Override
    public Integer getBanyakSppPaguUp(Map<String, Object> param) {
        return referensiMapper.getBanyakSppPaguUp(param);
    }

    @Override
    public void updateSppPaguUp(SppPaguUp sppPaguUp) {
       referensiMapper.updateSppPaguUp(sppPaguUp);
    }

    @Override
    public Integer getIDSpd(Map param) {
       return referensiMapper.getIDSpd(param);
    }
}
