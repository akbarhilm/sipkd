package spj.services;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import spj.util.SipkdHelpers;
import spp.model.Pengguna;
import spp.model.Skpd;

/**
 *
 * @author sapto
 */
@Service("userManagement1Services")
public class UserManagementImpl_tanpa_encrypt extends JdbcDaoSupport implements UserManagementServices {
    
   private static final Logger log = LoggerFactory.getLogger(UserManagementImpl_tanpa_encrypt.class);
    
    @Autowired
    public UserManagementImpl_tanpa_encrypt(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public UserManagementImpl_tanpa_encrypt() {
    }
    
    @Override
    public Pengguna loadUserByUsername(final String username, final String pass) {
        try {
            final String sql = " SELECT   T.I_ID  , T.I_PGUN, T.I_SANDI ,T.C_AKTIF,  'ROLE_'||C_PGUN_GROUP AS AUTHORITY     FROM   TMPENGGUNA T  WHERE   T.I_PGUN = ?        AND T.C_AKTIF  = 1 ";
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
        final String sql = " SELECT T.I_ID, T.C_PGUN_GROUP, T.I_PGUN, T.I_SANDI,  T.I_IP_ADDRESS, T.N_EMAIL, T.I_NIP_PEG, T.I_NRK_PEG, T.N_PEG, T.C_AKTIF,  C_WIL_SP2DPROSES,C_PGUN_GROUP       FROM   TMPENGGUNA T  WHERE   T.I_PGUN = ?   ";
        final Pengguna pengguna = jdbcTemplate.queryForObject(sql, new RowMapper<Pengguna>() {
            @Override
            public Pengguna mapRow(ResultSet rs, int i) throws SQLException {
                final Pengguna pengguna = new Pengguna();
                pengguna.setIdPengguna(rs.getInt("I_ID"));
                pengguna.setNamaPengguna(rs.getString("I_PGUN"));
                pengguna.setNip(rs.getString("I_NIP_PEG"));
                pengguna.setNrk(rs.getString("I_NRK_PEG"));
                pengguna.setIpAddress(rs.getString("I_IP_ADDRESS"));
                pengguna.setKodeProses(rs.getString("C_WIL_SP2DPROSES"));
                pengguna.setKodeGrup(rs.getString("C_PGUN_GROUP"));
                return pengguna;
            }
        },  SipkdHelpers.splitString(usernam, "|", 0));
        final String sqlskpd = " SELECT   T.I_IDPENGGUNA, T.I_IDSKPD, T.C_AKTIF,D.C_SKPD_LEVEL,D.N_SKPD,D.C_SKPD  FROM   TMPENGGUNASKPD T left join trskpd D on D.I_ID = T.I_IDSKPD where T.I_IDPENGGUNA = ? ";
        final List<Skpd> listSkpd = jdbcTemplate.query(sqlskpd, new RowMapper<Skpd>() {
            @Override
            public Skpd mapRow(ResultSet rs, int i) throws SQLException {
                final Skpd skpd = new Skpd();
                skpd.setIdSkpd(rs.getInt("I_IDSKPD"));
                skpd.setIsAktif(rs.getString("C_AKTIF"));
                skpd.setKodeSkpd(rs.getString("C_SKPD"));
                skpd.setNamaSkpd(rs.getString("N_SKPD"));
                skpd.setLevelSkpd(rs.getString("C_SKPD_LEVEL"));
                return skpd;
            }
        }, pengguna.getIdPengguna());
        pengguna.setSkpd(listSkpd);
        
        log.debug("*** LIST SKPD USER IMPL ================ " + listSkpd.toString());
        return pengguna;
        
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
}
