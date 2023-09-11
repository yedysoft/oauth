package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.User;
import com.yedy.muk.entitys.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface UserRoleDao extends BaseRepository<UserRole> {
    List<UserRole> findByUserAndDeletedFalse(User user);

    @Query("""
            select r.code
            from User u
                     inner join UserRole ur on ur.user.id = u.id
                     inner join Role r on r.id = ur.role.id
            where u.deleted = false
              and ur.deleted = false
              and r.deleted = false
              and u.id = :userId""")
    List<String> getRoles(UUID userId);
}