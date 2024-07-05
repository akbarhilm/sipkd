/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.Spd;

/**
 *
 * @author erzy
 */
public interface SpdServices {
    
     List<Spd> getSpd(Map param);
     
     Integer getBanyakSpd(Map param);
    
}
