package api.oauth2.endpoints;

import api.ErrorResponse;
import api.Request;
import api.Response;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;

public class Authorization implements RequestHandler<Request, Response> {

    @Override
    public Response handleRequest(Request request, Context context) {
        String responseType = request.getParameters().get(Constants.PARAM_RESPONSE_TYPE);
        if ( !responseType.equalsIgnoreCase(Constants.PARAM_AUTH_CODE) )
        {
            return new ErrorResponse(400, "Unsupported response_type.");
        }

        String scope = request.getParameters().get(Constants.PARAM_SCOPE);
        //  @TODO check scope

        String redirectURI = request.getParameters().get(Constants.PARAM_REDIRECT_URI);
        String state = request.getParameters().get(Constants.PARAM_STATE);

        String authorizationCode = "asdasdasd123";
        redirectURI += "?code=" + authorizationCode;
        redirectURI += "&state=" + state;

        Response response = new Response();
        response.setStatusCode(302);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Location", redirectURI);

        response.setHeaders(headers);
        return response;
    }

}
