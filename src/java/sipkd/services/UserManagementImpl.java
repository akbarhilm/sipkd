package sipkd.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import sipkd.entity.BeritaLoginMapper;
import sipkd.model.Berita;
import sipkd.model.Pengguna;
import sipkd.util.SipkdHelpers;

/**
 *
 * @author sapto
 */
@Service("userManagementServices")
public class UserManagementImpl extends JdbcDaoSupport implements UserManagementServices {

    private static final Logger log = LoggerFactory.getLogger(UserManagementImpl.class);

     @Autowired
    private BeritaLoginMapper beritaMapper;
     
    @Autowired
    public UserManagementImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public UserManagementImpl() {
    }

    @Override
    public Pengguna loadUserByUsername(final String username, final String pass) {
        try {
            final String sql = " SELECT   T.I_ID  , T.I_PGUN, trim(crypto16.DECRYPT (T.I_SANDI)) AS I_SANDI ,T.C_AKTIF,  'ROLE_'||C_PGUN_GROUP AS AUTHORITY     "
                    + "FROM   TMPENGGUNA T  WHERE   T.I_PGUN = ?    AND T.C_AKTIF  = 1 "; //AND crypto16.DECRYPT (T.I_SANDI)  = ? 
            final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, new PenggunaMapper());
        } catch (Exception c) {
            c.printStackTrace();
            return null;
        }

    }

    private static DataSource ambilDataSource() {
        /* org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
         try {
         ds.setDriverClassName(PropertiesAplikasi.DRIVER);
         ds.setUsername(PropertiesAplikasi.USERNAME);
         ds.setPassword(PropertiesAplikasi.PASSWORD);
         ds.setInitialSize(1);
         ds.setMaxActive(4);
         ds.setMaxIdle(2);
         ds.setMinIdle(1);
         ds.setMinEvictableIdleTimeMillis(18000);
         ds.setTimeBetweenEvictionRunsMillis(18000);
         ds.setNumTestsPerEvictionRun(2);
         ds.setTestOnBorrow(true);
         ds.setTestWhileIdle(true);
         ds.setTestOnReturn(true);
         ds.setValidationQuery("SELECT 1");
         ds.setUrl(PropertiesAplikasi.URL);
            
         } catch (Exception e) {
         e.printStackTrace();
         }
         return ds;*/
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/spdDB");
        return dataSource;
    }

    @Override
    public Pengguna loadPenggunaByUsername(String usernam) {
       log.debug("  loadPenggunaByUsername   "+SipkdHelpers.splitString(usernam, "|", 0));
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
        final String sql = " SELECT   T.I_ID, T.C_PGUN_GROUP, T.I_PGUN, trim(crypto16.DECRYPT (T.I_SANDI)) AS I_SANDI,  T.I_IP_ADDRESS,  T.N_EMAIL,   I_NIP_PEG as I_NIP,  I_NRK_PEG as I_NRK,  T.N_PEG,         T.C_AKTIF ,C_WIL_SP2DPROSES           FROM   TMPENGGUNA T  WHERE   T.I_PGUN = ?   ";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Pengguna>() {
            @Override
            public Pengguna mapRow(ResultSet rs, int i) throws SQLException {
                final Pengguna pengguna = new Pengguna();
                pengguna.setIdPengguna(rs.getInt("I_ID"));
                pengguna.setNamaPengguna(rs.getString("I_PGUN"));
                pengguna.setPassPengguna(rs.getString("I_SANDI")); // TAMBAHAN
                pengguna.setNip(rs.getString("I_NIP"));
                pengguna.setNrk(rs.getString("I_NRK"));
                pengguna.setIpAddress(rs.getString("I_IP_ADDRESS"));
                pengguna.setKodeProses(rs.getString("C_WIL_SP2DPROSES"));
                return pengguna;

            }

        },  usernam);

    }

    private class UserMapper implements ParameterizedRowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int arg1) throws SQLException {
            final boolean aktif = rs.getInt("C_AKTIF") > 0;
            return new User(rs.getString("I_PGUN"), rs.getString("I_SANDI"), aktif, true, true, true, new ArrayList<SimpleGrantedAuthority>());
        }
    }

    private class PenggunaMapper implements ParameterizedRowMapper<Pengguna> {

        @Override
        public Pengguna mapRow(ResultSet rs, int arg1) throws SQLException {
            final boolean aktif = rs.getInt("C_AKTIF") > 0;
            final Pengguna pengguna = new Pengguna();
            pengguna.setIdPengguna(rs.getInt("I_ID"));
            pengguna.setNamaPengguna(rs.getString("I_PGUN"));
            pengguna.setPassPengguna(rs.getString("I_SANDI"));
            pengguna.setKodeGrup(rs.getString("AUTHORITY"));
            pengguna.setIsAktif(aktif);
            return pengguna;
        }
    }
    
    
    public List<Berita> getBerita(Map<String, Object> param) {
        
        return beritaMapper.getBerita(param);
       
    }
}
