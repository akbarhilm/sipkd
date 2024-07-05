/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.services;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import sipkd.model.Pengguna;
import sipkd.util.SipkdHelpers;

/**
 *
 * @author sapto
 */
@Service("userNamePassAuthProvider")
public class UserNamePassAuthProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(UserNamePassAuthProvider.class);

    @Override
    public AbstractAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {
        final UserManagementImpl uimpl = new UserManagementImpl();
        final String nameTahun = authentication.getName();
        final String nama = SipkdHelpers.splitString(nameTahun, "|", 0);
        final String password = authentication.getCredentials().toString();
        Pengguna hasil = uimpl.loadUserByUsername(nama, password);
        // hasil.setPassword(password);
        log.debug(new StringBuilder().append(hasil).append(" xxxxxxxxxxxxxxx = ").append(nameTahun).append("  = ").append(nama).append("  = ").append(password).toString());
        //if (hasil != null && hasil.isEnabled() && nameTahun.equals(hasil.getUsername()) && hasil.getPassword().equals(password)) 
        if (hasil != null && hasil.isIsAktif() && Objects.equals(nama, hasil.getNamaPengguna()) && hasil.getPassPengguna().equals(password)) {
            Collection<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority(hasil.getKodeGrup()));
            AbstractAuthenticationToken auth = new UsernamePasswordAuthenticationToken(nameTahun, password, grantedAuths);
            auth.setDetails(hasil);

            return auth;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
