package api.oauth2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class AccessToken {

    private static final String TOKEN_TYPE_BEARER = "bearer";

    static AccessToken fromEncoded(String encodedToken)
    {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(encodedToken);
        return new AccessToken(jwt);
    }

    private DecodedJWT jwt;

    AccessToken(DecodedJWT jwt)
    {
       this.jwt = jwt;
    }

    public String getEncoded()
    {
        return jwt.getToken();
    }

    public String getRefreshToken()
    {
        return "asdasd";
    }

    public int getExpiresInSeconds()
    {
        return 60;
    }

    Map<String, Object> getProperties()
    {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("token_type", TOKEN_TYPE_BEARER);
        properties.put("access_token", this.getEncoded());
        properties.put("expires_in", this.getExpiresInSeconds());
        properties.put("refresh_token", this.getRefreshToken());
        return properties;
    }

}
