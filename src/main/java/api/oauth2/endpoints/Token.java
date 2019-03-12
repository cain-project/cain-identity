package api.oauth2.endpoints;

import api.ErrorResponse;
import api.JSONResponse;
import api.Request;
import api.Response;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class Token implements RequestHandler<Request, Response> {


    public Response handleRequest(final Request request, final Context context) {

        if ( request.getParameters().get(Constants.PARAM_GRANT_TYPE)
                .equals(Constants.PARAM_GRANT_TYPE_AUTH_CODE) )
        {
            // Issue new access token
            return this.issueNewAccessToken(request, context);

        }
        else if ( request.getParameters().get(Constants.PARAM_GRANT_TYPE)
                .equals(Constants.PARAM_GRANT_TYPE_REFRESH_TOKEN) )
        {
            // Refresh expired token
            return this.refreshAccessToken(request, context);
        }

        return new ErrorResponse(400, "Usupported grant type.");
    }

    private JSONResponse issueNewAccessToken(final Request request, final Context context)
    {
        String autorizationCode = request.getParameters().get(Constants.PARAM_AUTH_CODE);

        JSONResponse response = new JSONResponse();
        response.getBodyContents().put("access_token", "asd123asd123");
        response.getBodyContents().put("refresh_token", "asd123asd123_refresh");
        response.getBodyContents().put("scope", "openid");

        return response;
    }

    private JSONResponse refreshAccessToken(final Request request, final Context context)
    {
        JSONResponse response = new JSONResponse();
        response.getBodyContents().put("access_token", "asd123asd123_rereshed");
        response.getBodyContents().put("refresh_token", "asd123asd123_refreshed_refresh");
        response.getBodyContents().put("scope", "openid");

        return response;

    }
}
