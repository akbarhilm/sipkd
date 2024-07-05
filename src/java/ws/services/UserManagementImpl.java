/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("userManagementServices")
public class UserManagementImpl  implements UserManagementServices {
    
    @Override
    public String loadUserByUsername(final String username, final String pass) {
        try {
            String auth =null;
            if(username.equals("siap") && pass.equals("123")){
                auth = "admin";
            }
            return auth;
        } catch (Exception c) {
            c.printStackTrace();
            return null;
        }

    }
    
}
