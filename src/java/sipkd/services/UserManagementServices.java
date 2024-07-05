package sipkd.services;

import java.util.List;
import java.util.Map;
import sipkd.model.Berita;
import sipkd.model.Pengguna;

/**
 *
 * @author sapto
 */
public interface UserManagementServices {

    Pengguna loadUserByUsername(String usernam, String pass);

    Pengguna loadPenggunaByUsername(String usernam );
    
    List<Berita> getBerita(Map<String, Object> param);
}
