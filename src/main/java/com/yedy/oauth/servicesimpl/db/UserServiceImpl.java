package com.yedy.oauth.servicesimpl.db;

import com.yedy.oauth.consts.BCrypto;
import com.yedy.oauth.consts.ErrorMessages;
import com.yedy.oauth.dtos.PassChange;
import com.yedy.oauth.dtos.Place;
import com.yedy.oauth.entitys.RoomConfig;
import com.yedy.oauth.entitys.User;
import com.yedy.oauth.enums.LiveType;
import com.yedy.oauth.enums.Roles;
import com.yedy.oauth.exception.Assert;
import com.yedy.oauth.repositories.UserDao;
import com.yedy.oauth.services.HelperService;
import com.yedy.oauth.services.db.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final UserDao dao;
    private final RoomConfigService roomConfigService;
    private final HelperService helperService;
    private final TemporaryStorage storage;

    public UserServiceImpl(UserDao dao, RoomConfigService roomConfigService, HelperService helperService, TemporaryStorage storage) {
        super(dao);
        this.dao = dao;
        this.roomConfigService = roomConfigService;
        this.helperService = helperService;
        this.storage = storage;
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

    @Override
    public void passChange(PassChange pc) {
        Assert.isNull(pc.getId(), ErrorMessages.ID_NULL);
        Assert.isEmpty(pc.getOldPass(), ErrorMessages.USER_PASS_NULL);
        Assert.isEmpty(pc.getNewPass(), ErrorMessages.USER_PASS_NULL);
        Assert.isFalse(helperService.isPassRange(pc.getNewPass()), ErrorMessages.PASS_NOT_RANGE);
        User u = findById(pc.getId());
        Assert.isFalse(BCrypto.getEncoder().matches(pc.getOldPass(), u.getUserPass()), ErrorMessages.WRONG_PASS);
        u.setUserPass(BCrypto.getEncoder().encode(pc.getNewPass()));
        dao.saveAndFlush(u);
    }

    @Override
    public List<Place> getPlaces(Roles role) {
        Set<UUID> liveIds = helperService.getLiveIds(LiveType.RoomAdmin, null);
        List<Place> places = dao.getPlaces(liveIds, role != Roles.PLACE, role.toString());
        for (Place place : places) {
            RoomConfig conf = roomConfigService.findByRoomId(place.getId());
            if (conf != null) {
                place.setDescription(conf.getDescription());
                place.setName(conf.getName());
            }
            place.setLiveSong(storage.getPlayingSong(place.getId()));
            place.setPopulation((long) helperService.getLiveIds(LiveType.RoomUser, place.getId()).size());
            place.setIsLive(role != Roles.PLACE || liveIds.contains(place.getId()));
        }
        return places;
    }
}
