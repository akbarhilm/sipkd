package spp.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Service;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.UserAttempts;

/**
 *
 * @author sapto
 */
@Service("userManagementServices")
public class UserManagementImpl extends JdbcDaoSupport implements UserManagementServices {

    private static final Logger log = LoggerFactory.getLogger(UserManagementImpl.class);
    private static final String SQLINSERTATTEMPS = "INSERT INTO USER_ATTEMPTS (ID,USERNAME, ATTEMPTS, LASTMODIFIED) VALUES(?,?,?,?)";
    private static final String SQLMAKSIDLOGINATTEMPS = "SELECT NVL(MAX (ID),0)+1 AS MAK  FROM USER_ATTEMPTS";
    private static final String SQLMAKSLOGINATTEMPS = "SELECT NVL(MAX (a.ATTEMPTS),0)+1 AS MAK  FROM USER_ATTEMPTS a  where a. username = ? ";
    private static final String SQLCEKUSEREXIST = "SELECT NVL (COUNT (username), 0) as ada   FROM USER_ATTEMPTS  WHERE username = ? ";
    private static final String SQLUPDATEATTEMPS = " UPDATE USER_ATTEMPTS     SET ATTEMPTS =?  WHERE USERNAME =? ";
    private static final String SQLDELATTEMPS = " DELETE USER_ATTEMPTS  WHERE USERNAME = ? ";

    @Autowired
    public UserManagementImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public UserManagementImpl() {
    }

    @Override
    public Pengguna loadUserByUsername(final String username, final String pass) {
        try {
            final String sql = " SELECT   T.I_ID  , T.I_PGUN, trim(crypto16.DECRYPT (T.I_SANDI)) AS I_SANDI ,T.C_AKTIF,  'ROLE_'||C_PGUN_GROUP AS AUTHORITY     FROM   TMPENGGUNA T  WHERE   T.I_PGUN = ?        AND T.C_AKTIF  = 1 ";
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
         ds.setMinEvictableIdleTimeMillis(1800000);
         ds.setTimeBetweenEvictionRunsMillis(1800000);
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
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
        final String sql = " SELECT   T.I_ID,T.C_PGUN_GROUP, T.I_PGUN, trim(crypto16.DECRYPT (T.I_SANDI)) AS I_SANDI, T.I_IP_ADDRESS, T.N_EMAIL, T.I_NIP_PEG, T.I_NRK_PEG, T.N_PEG, T.C_AKTIF,  C_WIL_SP2DPROSES,C_PGUN_GROUP       FROM   TMPENGGUNA T  WHERE   T.I_PGUN = ?   ";
        final Pengguna pengguna = jdbcTemplate.queryForObject(sql, new RowMapper<Pengguna>() {
            @Override
            public Pengguna mapRow(ResultSet rs, int i) throws SQLException {
                final Pengguna pengguna = new Pengguna();
                pengguna.setIdPengguna(rs.getInt("I_ID"));
                pengguna.setNamaPengguna(rs.getString("I_PGUN"));
                pengguna.setPassPengguna(rs.getString("I_SANDI")); // TAMBAHAN
                pengguna.setNip(rs.getString("I_NIP_PEG"));
                pengguna.setNrk(rs.getString("I_NRK_PEG"));
                pengguna.setIpAddress(rs.getString("I_IP_ADDRESS"));
                pengguna.setKodeProses(rs.getString("C_WIL_SP2DPROSES"));
                pengguna.setKodeGrup(rs.getString("C_PGUN_GROUP"));
                return pengguna;
            }

        }, usernam/*SipkdHelpers.splitString(usernam, "|", 0)*/);
        final String sqlskpd = " SELECT   T.I_IDPENGGUNA, T.I_IDSKPD, T.C_AKTIF,D.C_SKPD_LEVEL,D.N_SKPD,D.C_SKPD,D.C_PENDAPATAN  FROM   TMPENGGUNASKPD T left join trskpd D on D.I_ID = T.I_IDSKPD where T.I_IDPENGGUNA = ? ";
        final List<Skpd> listSkpd = jdbcTemplate.query(sqlskpd, new RowMapper<Skpd>() {
            @Override
            public Skpd mapRow(ResultSet rs, int i) throws SQLException {
                final Skpd skpd = new Skpd();
                skpd.setIdSkpd(rs.getInt("I_IDSKPD"));
                skpd.setIsAktif(rs.getString("C_AKTIF"));
                skpd.setKodeSkpd(rs.getString("C_SKPD"));
                skpd.setNamaSkpd(rs.getString("N_SKPD"));
                skpd.setLevelSkpd(rs.getString("C_SKPD_LEVEL"));
                skpd.setIsPendapatan(rs.getString("C_PENDAPATAN"));
                return skpd;
            }
        }, pengguna.getIdPengguna());
        pengguna.setSkpd(listSkpd);
        return pengguna;

    }

    @Override
    public void insertLoginAttemps(UserAttempts userAttempts) {
     /*   final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
        final Integer maksimalIdLogin = jdbcTemplate.queryForObject(SQLMAKSIDLOGINATTEMPS, Integer.class);
        final Integer maksimalAttemps = jdbcTemplate.queryForObject(SQLMAKSLOGINATTEMPS, new Object[]{userAttempts.getUsername()}, Integer.class);
        final Integer cekusereksis = jdbcTemplate.queryForObject(SQLCEKUSEREXIST, new Object[]{userAttempts.getUsername()}, Integer.class);
        if (cekusereksis == 0) {
            jdbcTemplate.update(SQLINSERTATTEMPS, new Object[]{maksimalIdLogin, userAttempts.getUsername(), 1, new Timestamp(System.currentTimeMillis())});
        } else if (cekusereksis > 0) {
            jdbcTemplate.update(SQLUPDATEATTEMPS, new Object[]{maksimalAttemps, userAttempts.getUsername()});
        }*/

    }

    @Override
    public void resetLoginAttemps(UserAttempts userAttempts) {
      /*  final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
        jdbcTemplate.update(SQLDELATTEMPS, new Object[]{userAttempts.getUsername()});*/
    }

    private class PenggunaMapper implements ParameterizedRowMapper<Pengguna> {

        @Override
        public Pengguna mapRow(ResultSet rs, int arg1) throws SQLException {
            final boolean aktif = rs.getInt("C_AKTIF") > 0;
            final Pengguna pengguna = new Pengguna();
            pengguna.setNamaPengguna(rs.getString("I_PGUN"));
            pengguna.setPassPengguna(rs.getString("I_SANDI"));
            pengguna.setKodeGrup(rs.getString("AUTHORITY"));
            pengguna.setIsAktif(aktif);
            return pengguna;
            /* SimpleGrantedAuthority sga = new SimpleGrantedAuthority(rs.getString("AUTHORITY"));
             final List<SimpleGrantedAuthority> listSimpleGrantedAuthority = new ArrayList<SimpleGrantedAuthority>();
             return new User(rs.getString("I_PGUN"), rs.getString("I_SANDI"), aktif, true, true, true, listSimpleGrantedAuthority);*/
        }
    }

    /* 
     private class UserMapper implements ParameterizedRowMapper<User> {

     @Override
     public User mapRow(ResultSet rs, int arg1) throws SQLException {
     final User user = new User();
     user.setIdUser(rs.getLong("id_usr"));
     user.setPassword(rs.getString("password"));
     user.setUserId(rs.getString("username"));
     user.setUserGroupID(rs.getString("authority"));
     user.setUserGroupNama(rs.getString("nm_grp"));
     user.setUserName(rs.getString("name"));
     user.setIsActive(rs.getInt("enabled"));
     // log.debug(new StringBuilder().append("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX enable ").append(user).append(" pass   ").append(rs.getString("password")).append("  authorities ").toString());
     return user;
     }
     }
    
     */
}
