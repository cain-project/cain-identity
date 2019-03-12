package api;

public class ErrorResponse extends JSONResponse {
    public ErrorResponse(int statusCode, String error) {
        this.setStatusCode(statusCode);
        this.getHeaders().put("Content-Type", "application/json");
        this.getBodyContents().put("error", error);
    }

    public ErrorResponse(int statusCode, String error, String description)
    {
        this(statusCode, error);
        this.getBodyContents().put("error_description", description);
    }

}
