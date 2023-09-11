package com.yedy.oauth.servicesimpl.db;

import com.yedy.oauth.consts.BCrypto;
import com.yedy.oauth.consts.ErrorMessages;
import com.yedy.oauth.entitys.User;
import com.yedy.oauth.exception.Assert;
import com.yedy.oauth.repositories.UserDao;
import com.yedy.oauth.services.HelperService;
import com.yedy.oauth.services.db.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final UserDao dao;
    private final HelperService helperService;

    public UserServiceImpl(UserDao dao, HelperService helperService) {
        super(dao);
        this.dao = dao;
        this.helperService = helperService;
    }

    @Override
    public User save(User user, boolean flush) {
        Assert.isEmpty(user.getUserName(), ErrorMessages.USER_NAME_NULL);
        Assert.isEmpty(user.getUserPass(), ErrorMessages.USER_PASS_NULL);
        Assert.isEmpty(user.getEmail(), ErrorMessages.USER_EMAIL_NULL);
        if (user.getId() == null) {
            Assert.isFalse(helperService.isPassRange(user.getUserPass()), ErrorMessages.PASS_NOT_RANGE);
            Assert.notNull(findByUserName(user.getUserName()), ErrorMessages.SAME_USER_NAME);
            user.setUserPass(BCrypto.getEncoder().encode(user.getUserPass()));
        } else {
            User u = findById(user.getId());
            if (!u.getUserName().equals(user.getUserName()))
                Assert.notNull(findByUserName(user.getUserName()), ErrorMessages.SAME_USER_NAME);
        }
        return super.save(user, flush);
    }

    @Override
    public User findByUserName(String userName) {
        Assert.isEmpty(userName, ErrorMessages.USER_NAME_NULL);
        return dao.findByUserNameAndDeletedFalse(userName);
    }
}
