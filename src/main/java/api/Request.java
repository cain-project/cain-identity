package api;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.api.client.http.UrlEncodedParser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Request extends APIGatewayProxyRequestEvent {

    public Map<String, String> getParameters()
    {
        HashMap<String, String> params = new HashMap<>();

        if ( this.getQueryStringParameters() != null )
        {
            params.putAll(this.getQueryStringParameters());
        }

        Map<String, Object> bodyContents = this.getBodyContents();
        if ( bodyContents != null )
        {
            for ( Map.Entry<String, Object> entry: bodyContents.entrySet() )
            {
                String key = entry.getKey();
                String value;
                if ( entry.getValue() instanceof ArrayList )
                {
                    value = (String) ((ArrayList) entry.getValue()).get(0);
                }
                else
                {
                    value = (String) entry.getValue();

                }

                params.put(key, value);
            }
        }

        return params;
    }

    public Map<String, Object> getBodyContents()
    {
        if ( this.getHeaders() == null || this.getHeaders().get("Content-Type") == null )
        {
            return null; // No contents.
        }

        String contentType = this.getHeaders().get("Content-Type");
        String body = this.getBody();
        Map <String, Object> bodyContents = null;

        if (contentType.startsWith("application/json"))
        {
            Gson gson = new Gson();
            bodyContents = gson.fromJson(body, Map.class);
        }
        else if ( contentType.startsWith("application/x-www-form-urlencoded") )
        {
            bodyContents = new HashMap<>();
            UrlEncodedParser.parse(body, bodyContents);
        }

        return bodyContents;
    }

    public String getBearerToken()
    {
        String authHeader = this.getHeaders().get("Authorization");
        if ( authHeader == null )
        {
            return null;
        }

        String[] components = authHeader.split(" ");
        if ( components.length != 2 || !components[1].equals("Bearer") )
        {
            return null;  // Throw exception?
        }

        return components[1];
    }
}
