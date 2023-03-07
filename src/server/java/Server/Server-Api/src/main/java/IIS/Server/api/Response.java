package IIS.Server.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Response 
{
    private Response(){}

    public static <T> ResponseEntity<T> error(Class<T> responseType, String error)
    {
        try 
        {
            Optional<String> err = Optional.of(error);
            Method setError = responseType.getMethod("setError", err.getClass());
            Method setSuccess = responseType.getMethod("setSuccess", Boolean.class);
            Constructor<T> resultConstructor = responseType.getDeclaredConstructor();
            T result = resultConstructor.newInstance();
            setError.invoke(result, err);
            setSuccess.invoke(result, false);
            return new ResponseEntity<T>(result, HttpStatus.BAD_REQUEST);
        } 
        catch (Exception e) 
        {
            return error(e);
        }
    }

    public static <T> ResponseEntity<T> error(Exception error)
    {
        final var result = new InternalServerErrorResponse();
        result.setSuccess(false);
        result.setError(Optional.of(error.getMessage()));
        return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
