package school.sptech.exceptions;

public class ItemIndisponivelException  extends Exception{
// unchecked = RuntimeException

    public ItemIndisponivelException(String message) {
        super(message);
    }
}