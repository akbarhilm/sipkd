package spm.services;

import spp.model.Pengguna;

/**
 *
 * @author sapto
 */
public interface UserManagementServices {

    Pengguna loadUserByUsername(String usernam, String pass);

    Pengguna loadPenggunaByUsername(String usernam );
}
