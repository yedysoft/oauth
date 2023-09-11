package com.yedy.oauth.servicesimpl.db;

import com.yedy.oauth.consts.ErrorMessages;
import com.yedy.oauth.entitys.User;
import com.yedy.oauth.entitys.UserRole;
import com.yedy.oauth.exception.Assert;
import com.yedy.oauth.repositories.UserRoleDao;
import com.yedy.oauth.services.db.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {
    private final UserRoleDao dao;

    public UserRoleServiceImpl(UserRoleDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public List<UserRole> findByUser(User user) {
        return dao.findByUserAndDeletedFalse(user);
    }

    @Override
    public List<String> getRoles(UUID userId) {
        return dao.getRoles(userId);
    }

    @Override
    public UserRole save(UserRole userRole, boolean flush) {
        Assert.isNull(userRole.getUser(), ErrorMessages.ROLE_NAME_NULL);
        Assert.isNull(userRole.getRole(), ErrorMessages.ROLE_CODE_NULL);
        return super.save(userRole, flush);
    }
}
