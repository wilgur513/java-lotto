package lotto.view.exception;

public class ApplicationFinishedException extends RuntimeException{

    public ApplicationFinishedException() {
        super();
    }

    public ApplicationFinishedException(Throwable cause) {
        super(cause);
    }
}
