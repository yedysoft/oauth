package com.yedy.oauth.servicesimpl.db;

import com.yedy.oauth.consts.ErrorMessages;
import com.yedy.oauth.entitys.Role;
import com.yedy.oauth.enums.Roles;
import com.yedy.oauth.exception.Assert;
import com.yedy.oauth.repositories.RoleDao;
import com.yedy.oauth.services.db.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    private final RoleDao dao;

    public RoleServiceImpl(RoleDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public Role findByCode(Roles code) {
        Assert.isNull(code, ErrorMessages.ROLE_CODE_NULL);
        return dao.findByCodeAndDeletedFalse(code);
    }

    @Override
    public Role save(Role role, boolean flush) {
        Assert.isEmpty(role.getName(), ErrorMessages.ROLE_NAME_NULL);
        Assert.isNull(role.getCode(), ErrorMessages.ROLE_CODE_NULL);
        if (role.getId() == null)
            Assert.notNull(findByCode(role.getCode()), ErrorMessages.SAME_ROLE_CODE);
        else {
            Role r = findById(role.getId());
            if (!r.getCode().equals(role.getCode()))
                Assert.notNull(findByCode(role.getCode()), ErrorMessages.SAME_ROLE_CODE);
        }
        return super.save(role, flush);
    }
}
