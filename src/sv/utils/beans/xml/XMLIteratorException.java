/**
 *
 */
package sv.utils.beans.xml;

/**
 *
 */
public class XMLIteratorException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -1586802908096350512L;

    /**
     * Default-Konstruktor
     *
     * @param message
     *            Fehlermessage
     */
    public XMLIteratorException(final String message) {
        super(message);
    }

    /**
     * Default-Konstruktor
     *
     * @param message
     *            Fehlermassage
     * @param t
     *            Rootcause
     */
    public XMLIteratorException(final String message, final Throwable t) {
        super(message, t);
    }
}
