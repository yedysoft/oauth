package com.yedy.oauth.servicesimpl;

import com.google.gson.Gson;
import com.yedy.muk.entitys.User;
import com.yedy.muk.enums.LiveType;
import com.yedy.muk.services.HelperService;
import com.yedy.muk.services.db.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.cache.HttpCacheContext;
import org.apache.hc.client5.http.classic.methods.HttpHead;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.messaging.simp.user.SimpSubscription;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.detailed.*;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HelperServiceImpl implements HelperService {
    private final SimpUserRegistry simpUserRegistry;
    private final UserRoleService userRoleService;

    @Override
    public Set<UUID> getLiveIds(final LiveType liveType, UUID id) {
        Set<UUID> uniqueIds = new HashSet<>();
        if (simpUserRegistry != null) {
            final String p = id == null ? "" : "/" + id;
            Set<SimpSubscription> subs = simpUserRegistry.findSubscriptions(s -> s.getDestination().equals("/live" + liveType.value + p));
            for (SimpSubscription s : subs) {
                UUID uuid = UUID.fromString(s.getSession().getUser().getName());
                uniqueIds.add(uuid);
            }
        }
        return uniqueIds;
    }

    @Override
    public Set<GrantedAuthority> getGrantedRoles(User user) {
        Set<GrantedAuthority> grantedAuths = new HashSet<>();
        List<String> roles = userRoleService.getRoles(user.getId());
        for (String r : roles)
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + r));
        return grantedAuths;
    }

    @Override
    public boolean isPassRange(String pass) {
        return pass != null && pass.length() >= 8 && pass.length() <= 36;
    }

    @Override
    public void getHttpHead(URI uri, Header[] headers) throws Exception {
        HttpHead httpHead = new HttpHead(uri);
        httpHead.setHeaders(headers);
        HttpCacheContext context = HttpCacheContext.create();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpHead, context, resp -> {
            int code = resp.getCode();
            switch (code) {
                case HttpStatus.SC_BAD_REQUEST -> throw new BadRequestException();
                case HttpStatus.SC_UNAUTHORIZED -> throw new UnauthorizedException();
                case HttpStatus.SC_FORBIDDEN -> throw new ForbiddenException();
                case HttpStatus.SC_NOT_FOUND -> throw new NotFoundException();
                case HttpStatus.SC_TOO_MANY_REQUESTS -> {
                    Header header = resp.getFirstHeader("Retry-After");
                    if (header != null)
                        throw new TooManyRequestsException("", Integer.parseInt(header.getValue()));
                    else
                        throw new TooManyRequestsException();
                }
                case HttpStatus.SC_INTERNAL_SERVER_ERROR -> throw new InternalServerErrorException();
                case HttpStatus.SC_BAD_GATEWAY -> throw new BadGatewayException();
                case HttpStatus.SC_SERVICE_UNAVAILABLE -> throw new ServiceUnavailableException();
            }
            return resp;
        });
        response.close();
    }

    @Override
    public String getTextBeetweenTwoWords(String text, String word1, String word2) {
        if (text != null && word1 != null) {
            int index1 = text.indexOf(word1);
            if (index1 != -1) {
                String t = text.substring(index1 + word1.length());
                if (word2 != null) {
                    int index2 = t.indexOf(word2);
                    if (index2 != -1)
                        return text.substring(index1 + word1.length(), index1 + word1.length() + index2);
                }
                return t;
            }
            return text;
        }
        return null;
    }

    @Override
    public <T> T copyClass(Object from, Class<T> to) {
        return fromJson(toJson(from), to);
    }

    @Override
    public String toJson(Object src) {
        Gson g = new Gson();
        return g.toJson(src);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        Gson g = new Gson();
        return g.fromJson(json, classOfT);
    }
}
