package dash.services;

import dash.model.LoginForm;

/**
 *
 * @author Zainab
 */
public interface UbahPasswordServices {

    void updatePass(LoginForm param);

    Integer checkPassword(LoginForm param);

}
