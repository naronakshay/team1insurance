package insurance.premium.backend.Exceptions;

public class MemberNotFoundException extends  RuntimeException{
    public MemberNotFoundException(String message) {
        super(message);
    }
}