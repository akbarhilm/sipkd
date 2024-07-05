/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.services;

/**
 *
 * @author User
 */
public interface UserManagementServices {
    
     String loadUserByUsername(String usernam, String pass);
    
}
