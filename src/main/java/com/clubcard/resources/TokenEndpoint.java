package com.clubcard.resources;

import com.clubcard.ejb.services.UserService;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.TokenType;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "token")
public class TokenEndpoint {

    @EJB
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response token(@Context HttpServletRequest request){
        try {
            OAuthTokenRequest oauthRequest =
                    new OAuthTokenRequest(request);

            OAuthIssuer oauthIssuerImpl =
                    new OAuthIssuerImpl(new MD5Generator());

            if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE)
                    .equals(GrantType.PASSWORD.toString())) {
//                if (!checkUserPass(oauthRequest.getUsername(),
//                        oauthRequest.getPassword())) {
//                    return buildInvalidUserPassResponse();
//                }
                System.out.println("OAUTH_USERNAME : " + oauthRequest.getUsername());
                System.out.println("OAUTH_PASSWORD : " + oauthRequest.getPassword());
            } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE)
                    .equals(GrantType.REFRESH_TOKEN.toString())) {
                // refresh token is not supported in this implementation
//                buildInvalidUserPassResponse();
                System.out.println("refresh token is not supported in this implementation");
            }
//
            final String accessToken = oauthIssuerImpl.accessToken();
//            database.addToken(accessToken);
            System.out.println("accessToken : " + accessToken);

            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setTokenType(TokenType.BEARER.name())
                    .setRefreshToken(accessToken)
                    .setExpiresIn("3600")
                    .buildJSONMessage();
            return Response.status(response.getResponseStatus())
                    .entity(response.getBody()).build();

        } catch (OAuthProblemException e) {
            System.out.println("ERROR : OAuthProblemException");
            System.out.println("ERROR : " + e.getMessage());
            try {
                OAuthResponse res = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .error(e)
                        .buildJSONMessage();
                return Response
                        .status(res.getResponseStatus()).entity(res.getBody())
                        .build();
            } catch (OAuthSystemException se){
                System.out.println("ERROR : OAuthSystemException");
            }
        } catch (OAuthSystemException se){
            System.out.println("ERROR : OAuthSystemException");
        }

        return Response.ok().build();
    }
}