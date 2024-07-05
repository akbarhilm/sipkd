/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sp2d.entity;

import java.util.List;
import java.util.Map;
import spp.model.Spd;

public interface SpdAndroidMapper {
     List<Spd> getSpdAndroid(Map<String, Object> param);
     Integer getCountSpdAndroid(Map<String, Object> param);
}
