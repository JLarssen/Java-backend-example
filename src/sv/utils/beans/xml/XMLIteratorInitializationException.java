/**
 *
 */
package sv.utils.beans.xml;

/**
 *
 */
public class XMLIteratorInitializationException extends Exception {
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
    public XMLIteratorInitializationException(final String message) {
        super(message);
    }

    /**
     * Default-Konstruktor
     *
     * @param message
     *            Fehlermessage
     * @param t
     *            Rootcause
     */
    public XMLIteratorInitializationException(final String message, final Throwable t) {
        super(message, t);
    }
}
