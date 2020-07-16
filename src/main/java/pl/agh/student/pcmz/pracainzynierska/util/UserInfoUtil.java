package pl.agh.student.pcmz.pracainzynierska.util;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class UserInfoUtil
{
    private Logger logger= LoggerFactory.getLogger(UserInfoUtil.class);

    public String getPreferredUsername(HttpServletRequest httpServletRequest)
    {
        AccessToken accessToken=getAccessToken();

        return accessToken.getPreferredUsername();
    }
    public String getUserGuid()
    {
        return getAccessToken().getSubject();
    }


    private AccessToken getAccessToken()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal keycloakPrincipal= (KeycloakPrincipal) authentication.getPrincipal();
        return keycloakPrincipal.getKeycloakSecurityContext().getToken();
    }
}
