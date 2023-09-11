package com.yedy.oauth.services;

import com.yedy.muk.entitys.User;
import com.yedy.muk.enums.LiveType;
import org.apache.hc.core5.http.Header;
import org.springframework.security.core.GrantedAuthority;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

public interface HelperService {
    Set<UUID> getLiveIds(LiveType liveType, UUID id);

    Set<GrantedAuthority> getGrantedRoles(User user);

    boolean isPassRange(String pass);

    void getHttpHead(URI uri, Header[] headers) throws Exception;

    String getTextBeetweenTwoWords(String text, String word1, String word2);

    <T> T copyClass(Object from, Class<T> to);

    String toJson(Object src);

    <T> T fromJson(String json, Class<T> classOfT);
}
