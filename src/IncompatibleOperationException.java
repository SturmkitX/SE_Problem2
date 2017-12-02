public class IncompatibleOperationException extends Exception {
    public IncompatibleOperationException() {
        super("Operation incompatible with current state!");
    }

    public IncompatibleOperationException(String msg) {
        super(msg + "\nOperation incompatible with current state!");
    }

}
