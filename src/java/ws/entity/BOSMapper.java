/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.entity;

/**
 *
 * @author User
 */
import java.util.Map;
import java.util.List;

public interface BOSMapper {

    List<Map<String, Object>> getHeaderk7a(Map<String, Object> param);

    List<Map<String, Object>> getDetailk7a(Map<String, Object> param);

    List<Map<String, Object>> getSummary7a(Map<String, Object> param);

}
