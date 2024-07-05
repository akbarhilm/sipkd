package dash.services;

/**
 *
 * @author User
 */
import dash.model.Pengguna;
import dash.util.SipkdHelpers;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author sapto
 */
@Service("userNamePassAuthProvider")
public class UserNamePassAuthProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(UserNamePassAuthProvider.class);
    @Autowired
    LoginServices loginServices;

    @Autowired
    ServletContext servletContext;

    @Override
    public AbstractAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("****** authentication.getName() ===== " + authentication.getName());
        log.debug("****** authentication.getCredentials().toString() ===== " + authentication.getCredentials().toString());

        AbstractAuthenticationToken auth = null;
        if (authentication != null && authentication.getName() != null && authentication.getCredentials() != null) {
            final String name = authentication.getName();
            final String password = authentication.getCredentials().toString();
            final Pengguna hasil = loginServices.loadUserByUsername(name, password);
            log.debug("qwert"+hasil.getPassPengguna()+" / "+hasil.isIsAktif());
//            final int status = loginServices.getLoginStatus(hasil);
          
            log.debug(new StringBuilder().append(hasil).append(" = ").append(name).append("  = ").append(password).toString());

             if (hasil != null && hasil.isIsAktif() 
                    && Objects.equals(hasil.getPassPengguna().trim(), password)) {
                final List<SimpleGrantedAuthority> grantedAuths = new ArrayList<SimpleGrantedAuthority>();
                grantedAuths.add(new SimpleGrantedAuthority(hasil.getKodeGrup()));
                User user = new User(hasil.getNamaPengguna(), hasil.getPassPengguna(), hasil.isIsAktif(), true, true, true, grantedAuths);
                auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
                auth.setDetails(user);
                //loginServices.loginSuccess(hasil);
               
//                loginServices.setLoginStatus(hasil);
            }else if (hasil != null && hasil.isIsAktif() && !Objects.equals(hasil.getPassPengguna().trim(), password)) {
                log.debug("qwert"+password+hasil.getPassPengguna()+" / "+hasil.isIsAktif());
                throw new BadCredentialsException("Password salah.");
//                loginServices.updateSalahPassword(hasil);
//                if (hasil.getCountSalah() == 2) {
//                    loginServices.updateStatusLock(hasil);
//                    throw new BadCredentialsException("Password salah. <br/> Anda sudah salah password sebanyak 3 kali. <br/> Akun anda dikunci, silahkan hubungi Pusdatikomdik jika ingin diperbaiki.");
//                } else {
//                    throw new BadCredentialsException("Password salah. <br/> Anda sudah salah password sebanyak " + (hasil.getCountSalah() + 1) + " kali.");
//                }
            }  else if (hasil == null) {
                throw new BadCredentialsException("Pengguna dengan NRK " + name + " tidak ada di database.");
            } 
        }

        return auth;
        /*final UserManagementImpl uimpl = new UserManagementImpl();
         final String nameTahun = authentication.getName();
         final String nama = SipkdHelpers.splitString(nameTahun, "|", 0);
         final String password = authentication.getCredentials().toString();
         Pengguna hasil = uimpl.loadUserByUsername(nama, password);
         // hasil.setPassword(password);

         log.debug(new StringBuilder().append(hasil).append(" xxxxxxxxxxxxxxx = ").append(nameTahun).append("  = ").append(nama).append("  = ").append(password).toString());
         //if (hasil != null && hasil.isEnabled() && nameTahun.equals(hasil.getUsername()) && hasil.getPassword().equals(password))
         if (hasil != null && hasil.isIsAktif() && Objects.equals(nama, hasil.getNamaPengguna())) {
         Collection<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
         grantedAuths.add(new SimpleGrantedAuthority(hasil.getKodeGrup()));
         AbstractAuthenticationToken auth = new UsernamePasswordAuthenticationToken(nameTahun, password, grantedAuths);
         auth.setDetails(hasil);

         return auth;
         } else {
         return null;
         }*/
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
