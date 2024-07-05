
package spj.services;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import spp.model.Pengguna;

/**
 *
 * @author sapto
 */
@Service("userNamePassAuthProvider")
public class UserNamePassAuthProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(UserNamePassAuthProvider.class);

    @Override
    public AbstractAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {
        AbstractAuthenticationToken auth = null;
        if (authentication != null && authentication.getName() != null && authentication.getCredentials() != null) {
            final UserManagementImpl uimpl = new UserManagementImpl();
            final String name = authentication.getName();
            final String password = authentication.getCredentials().toString();
            final Pengguna hasil = uimpl.loadUserByUsername(name, password);
            // log.debug(new StringBuilder().append(hasil).append(" = ").append(name).append("  = ").append(password).toString());
            if (hasil != null && hasil.isIsAktif() && Objects.equals(name, hasil.getNamaPengguna()) && Objects.equals(hasil.getPassPengguna(), password)) {
                final List<SimpleGrantedAuthority> grantedAuths = new ArrayList<SimpleGrantedAuthority>();
                grantedAuths.add(new SimpleGrantedAuthority(hasil.getKodeGrup()));
                User user = new User(hasil.getNamaPengguna(), hasil.getPassPengguna(), hasil.isIsAktif(), true, true, true, grantedAuths);
                auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
                auth.setDetails(user);

            }
        }
        return auth;
    }

    /*
     @Override
     public Authentication authenticate(Authentication authentication) throws AuthenticationException {
     UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
     String username = String.valueOf(auth.getPrincipal());
     String password = String.valueOf(auth.getCredentials());

     logger.info("username:" + username);
     logger.info("password:" + password); // Don't log passwords in real app

     // 1. Use the username to load the data for the user, including authorities and password.
     YourUser user = ....

     // 2. Check the passwords match.
     if (!user.getPassword().equals(password)) {
     throw new BadCredentialsException("Bad Credentials");
     }

     // 3. Preferably clear the password in the user object before storing in authentication object
     user.clearPassword();

     // 4. Return an authenticated token, containing user data and authorities  

     return UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()) ;
     }
    
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
