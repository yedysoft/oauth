package com.yedy.oauth.repositories;

import com.yedy.muk.dtos.Place;
import com.yedy.muk.entitys.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RepositoryRestResource
public interface UserDao extends BaseRepository<User> {
    User findByUserNameAndDeletedFalse(String userName);

    @Query("""
            select new com.yedy.muk.dtos.Place(u.id, u.userName)
            from User u
                     inner join UserRole ur on ur.user.id = u.id
                     inner join Role r on r.id = ur.role.id
            where (:useId = false or u.id in (:ids))
              and r.code = :role
              and u.deleted = false
              and ur.deleted = false
              and r.deleted = false""")
    List<Place> getPlaces(Set<UUID> ids, Boolean useId, String role);
}
