package spp.services;

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
import spp.model.Pengguna;
import spp.model.UserAttempts;
import spp.util.SipkdHelpers;

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
        final UserAttempts userAttempts = new UserAttempts();
        userAttempts.setUsername(nama);
        // hasil.setPassword(password);
        log.debug(new StringBuilder().append(hasil).append(" xxxxxxxxxxxxxxx = ").append(nameTahun).append("  = ").append(nama).append("  = ").append(password).toString());
        //if (hasil != null && hasil.isEnabled() && nameTahun.equals(hasil.getUsername()) && hasil.getPassword().equals(password)) 
        if (hasil != null && hasil.isIsAktif() && Objects.equals(nama, hasil.getNamaPengguna()) && hasil.getPassPengguna().equals(password)) {
            Collection<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority(hasil.getKodeGrup()));
            AbstractAuthenticationToken auth = new UsernamePasswordAuthenticationToken(nameTahun, password, grantedAuths);
            auth.setDetails(hasil);
            uimpl.resetLoginAttemps(userAttempts);
            return auth;
        } else {            
            uimpl.insertLoginAttemps(userAttempts);
            return null;
        }
        
        
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
