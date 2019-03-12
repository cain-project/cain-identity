package api.openid.endpoints;

import api.JSONResponse;
import api.Request;
import api.Response;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Configuration implements RequestHandler<Request, Response> {

    // https://openid.net/specs/openid-connect-core-1_0.html#UserInfo
    public Response handleRequest(final Request request, final Context context) {

        String baseUrl = "https://openid.example.org";
        JSONResponse response = new JSONResponse();
        response.getBodyContents().put("issuer", baseUrl);
        response.getBodyContents().put("authorization_endpoint", baseUrl + "/oauth2/authorize");
        response.getBodyContents().put("token_endpoint", baseUrl + "/oauth2/token");
        response.getBodyContents().put("userinfo_endpoint", baseUrl + "/openid/user_info");
        // response_types_supported ["code", "token", "code token"]
        // scopes_supported         ["openid", "email", "profile"]
        //  "claims_supported": [
        //  "aud",
        //  "email",
        //  "email_verified",
        //  "exp",
        //  "family_name",
        //  "given_name",
        //  "iat",
        //  "iss",
        //  "locale",
        //  "name",
        //  "picture",
        //  "sub"
        // ],
        // "id_token_signing_alg_values_supported": [
        //  "RS256"
        // ],
        return response;
    }

}
