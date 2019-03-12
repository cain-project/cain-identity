package api.openid.endpoints;

import api.JSONResponse;
import api.Request;
import api.Response;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UserInfo implements RequestHandler<Request, Response> {

    // https://openid.net/specs/openid-connect-core-1_0.html#UserInfo
    public Response handleRequest(final Request request, final Context context) {

        JSONResponse response = new JSONResponse();
        response.getBodyContents().put("sub", "id123123");
        response.getBodyContents().put("name", "Mario Rossi");
        response.getBodyContents().put("given_name", "Mario");
        response.getBodyContents().put("family_name", "Rossi");
        response.getBodyContents().put("preferred_username", "mario.rossi");
        response.getBodyContents().put("email", "mario.rossi@example.org");

        return response;
    }

}
