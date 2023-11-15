package instapay.Modules.Response;

public class Response {
    private boolean isSucceeded;
    private Object data;
    private String errorMessage;

    public Response(boolean isSucceeded, Object data, String errorMessage) {
        this.isSucceeded = isSucceeded;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public Response(boolean isSucceeded, String errorMessage) {
        this(isSucceeded, null, errorMessage);
    }

    public Response(boolean isSucceeded, Object data) {
        this(isSucceeded, data, "");
    }

    public Response(boolean isSucceeded) {
        this(isSucceeded, null, "");
    }

    public Object getData() {
        return data;
    }

    public boolean succeeded() {
        return isSucceeded;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public <T> T to(Class<T> targetType) {
        try {
            return targetType.cast(this.data);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
