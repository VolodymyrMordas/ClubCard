package com.clubcard.resources;

import com.clubcard.ejb.repository.UserRepository;
import com.clubcard.entities.User;
import com.clubcard.rest.model.RequestModel;
import com.clubcard.rest.ApplicationException;
import com.sun.istack.internal.NotNull;

import javax.ejb.EJB;

public class BaseResource<T> {

    private User user;

    @EJB
    private UserRepository userRepository;

    private void processRequest(@NotNull RequestModel requestModel) throws ApplicationException {
        if (requestModel == null) {
            throw new ApplicationException(ApplicationException.Error.E_EMPT_RQST_BODY);
        }

        String authKey = requestModel.getAuthorization().getAuthKey();
        if (authKey == null) {
            throw new ApplicationException(ApplicationException.Error.E_PERM_DENY);
        }

        User authUser = userRepository.findByAuthKey(authKey);
        if (authUser == null) {
            throw new ApplicationException(ApplicationException.Error.E_PERM_DENY);
        }

        this.setUser(authUser);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
