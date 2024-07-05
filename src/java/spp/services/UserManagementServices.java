package spp.services;

import spp.model.Pengguna;
import spp.model.UserAttempts;

/**
 *
 * @author sapto
 */
public interface UserManagementServices {

    Pengguna loadUserByUsername(String usernam, String pass);

    Pengguna loadPenggunaByUsername(String usernam);

    void insertLoginAttemps(UserAttempts userAttempts);

    void resetLoginAttemps(UserAttempts userAttempts);
}

