/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spj.entity.ReferensiMapper;
import spj.entity.RekananMapper;
import spp.model.Rekanan;
import spp.model.SppPaguUp;

/**
 *
 * @author R Tarman
 */
@Transactional(readOnly = true)
@Service("rekananServices")
public class RekananImpl implements RekananServices {

    @Autowired
    private RekananMapper rekananMapper;
    @Override
    public List<Rekanan> getRekanan(Map<String, Object> param) {
    return rekananMapper.getRekanan(param);
    }

    @Override
    public Integer getCountRekanan(Map param) {
    return rekananMapper.getCountRekanan(param);
    
    }

    
    @Override
    @Transactional(readOnly = false)
    public void insertRekanan(Rekanan param) {
        rekananMapper.insertRekanan(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateRekanan(Rekanan param) {
        rekananMapper.updateRekanan(param);
    }
    @Override
    public Rekanan getRekananById(Integer id) {
        return rekananMapper.getRekananById(id);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteRekanan(Integer id) {
        rekananMapper.deleteRekanan(id);
    }
}
