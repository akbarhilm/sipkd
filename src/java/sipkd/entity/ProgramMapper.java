/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.entity;

import java.util.List;
import java.util.Map;
import sipkd.model.RefProgram;

/**
 *
 * @author R Tarman
 */
public interface ProgramMapper {
     List<RefProgram> getlistprogram();
     Integer getBanyakAllUrusan(Map<String, Object> param);
     List<RefProgram> getAllUrusan(Map<String, Object> param);
     Integer getBanyakAllProgram(Map<String, Object> param);
     List<RefProgram> getAllProgram(Map<String, Object> param);
}
