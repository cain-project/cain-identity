package api;

import com.google.gson.Gson;

import java.util.HashMap;


public class JSONResponse extends Response {

    private HashMap<String, Object> bodyContents = new HashMap<>();

    public JSONResponse() {
        this.setHeaders(new HashMap<>());
        this.getHeaders().put("Content-Type", "application/json; charset=UTF-8");
    }

    @Override
    public String getBody() {
        Gson gson = new Gson();
        return gson.toJson(this.getBodyContents());
    }

    public HashMap<String, Object> getBodyContents() {
        return bodyContents;
    }

    public void setBodyContents(HashMap<String, Object> bodyContents) {
        this.bodyContents = bodyContents;
    }

}
