package sp2d.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp2d.entity.RekonBankDkiMapper;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("rekonbankdkiServices")
public class RekonBankDkiImpl implements RekonBankDkiServices {

    @Autowired
    private RekonBankDkiMapper rekonMapper;

    
    @Override
    public List<Map> getListRekon(Map param) {
        return rekonMapper.getListRekon(param);
    }

    @Override
    public Integer getBanyakRekon(Map map) {
        return rekonMapper.getBanyakRekon(map);
    }

    
}
