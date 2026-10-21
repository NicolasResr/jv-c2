package school.sptech.exceptions;

public class ItemNaoEncontradoException extends RuntimeException{
// checked = exception
    public ItemNaoEncontradoException(String message) {
        super(message);
    }
}