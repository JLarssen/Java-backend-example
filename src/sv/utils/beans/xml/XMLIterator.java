package sv.utils.beans.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import constants.Constants_Allgemein;
import sv.utils.Helper;
import sv.utils.beans.xml.XMLIteratorException;
import sv.utils.beans.xml.XMLIteratorInitializationException;

/**
 * @param <E>
 *            Type ueber den iteriert wird
 *
 */
public class XMLIterator<E> implements Iterator<E> {
    private final Helper helper;

    private final InputStream inputStream;

    private PartialUnmarshaller<E> unmarshaller;

    private String localname;

    private final String filename;

    /**
     * Default-Konstruktor
     *
     * @param filePath
     *            XML-Datei die iteriert wird
     * @param clazz
     *            Klasse die verarbeitet werden soll
     * @throws FileNotFoundException
     *             Wenn die Datei nicht gefunden wird
     * @throws XMLIteratorInitializationException
     *             Wenn ein Fehler beim Initialisieren des Iterators auftritt
     */
    public XMLIterator(final String filePath, final Class<E> clazz, final String request)
            throws FileNotFoundException, XMLIteratorInitializationException {
        this.helper = new Helper();
        this.inputStream = new FileInputStream(filePath);
        this.filename = filePath;
        try {
            init(clazz, new EncodingGuesser().guessFileEncoding(filePath), request);
        } catch (final Exception e) {
            throw new XMLIteratorInitializationException("Fehler beim Initialisieren des Iterators", e);
        }
    }

    /**
     * Default-Konstruktor
     *
     * @param filePath
     *            XML-Datei die iteriert wird
     * @param clazz
     *            Klasse die verarbeitet werden soll
     * @param localname
     *            Suche im XML nach dem erstem Auftreten des Elementnamen
     * @throws FileNotFoundException
     *             Wenn die Datei nicht gefunden wird
     * @throws XMLIteratorInitializationException
     *             Wenn ein Fehler beim Initialisieren des Iterators auftritt
     */
    public XMLIterator(final String filePath, final Class<E> clazz, final String localname, final String request)
            throws FileNotFoundException, XMLIteratorInitializationException {
        this.helper = new Helper();
        this.inputStream = new FileInputStream(filePath);
        this.filename = filePath;
        this.localname = localname;
        try {
            init(clazz, new EncodingGuesser().guessFileEncoding(filePath), request);
        } catch (final Exception e) {
            throw new XMLIteratorInitializationException(createInitializeExceptionMessage(), e);
        }
    }

    /**
     * Default-Konstruktor
     *
     * @param inputStream
     *            XML-Stream der iteriert wird
     * @param clazz
     *            Klasse die verarbeitet werden soll
     * @throws XMLIteratorInitializationException
     *             Wenn ein Fehler beim Initialisieren des Iterators auftritt
     */
    public XMLIterator(final InputStream inputStream, final Class<E> clazz, final String request)
            throws XMLIteratorInitializationException {
        this.helper = new Helper();
        this.inputStream = inputStream;
        this.filename = null;
        try {
            init(clazz, Constants_Allgemein.ENCODING_FILE, request);
        } catch (final Exception e) {
            throw new XMLIteratorInitializationException(createInitializeExceptionMessage(), e);
        }
    }

    /**
     * Initialisiert den Iterator
     *
     * @param clazz
     *            Klasse die verarbeitet werden soll
     */
    private void init(final Class<E> clazz, final String fileEncoding, final String request)
            throws XMLStreamException, FactoryConfigurationError, JAXBException {
        this.unmarshaller = new PartialUnmarshaller<>(this.inputStream, clazz, this.localname, fileEncoding, request);
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        try {
            hasNext = (this.unmarshaller.hasNext());
        } catch (final Exception e) {
            throw new XMLIteratorException(createExceptionMessage(), e);
        }
        return (hasNext);
    }

    /**
     * @return String - Fehlernachricht mit Details zur verarbeiteten Datei
     *         (wenn vorhanden)
     */
    private String createExceptionMessage() {
        final StringBuffer exceptionMessage = new StringBuffer();
        exceptionMessage.append("Fehler im Iterator");
        if ((this.filename != null) && !this.filename.isEmpty()) {
            exceptionMessage.append("! Verarbeitete Datei: '" + this.filename + "'");
        }
        return exceptionMessage.toString();
    }

    /**
     * @return String - Fehlernachricht mit Details zur verarbeiteten Datei
     *         (wenn vorhanden)
     */
    private String createInitializeExceptionMessage() {
        final StringBuffer exceptionMessage = new StringBuffer();
        exceptionMessage.append("Fehler beim Initialisieren des Iterators");
        if ((this.filename != null) && !this.filename.isEmpty()) {
            exceptionMessage.append("! Verarbeitete Datei: '" + this.filename + "'");
        }
        return exceptionMessage.toString();
    }

    @Override
    public E next() {
        Object returnObject = null;
        try {
            if (this.unmarshaller.hasNext()) {
                returnObject = (this.unmarshaller.next());
            } else {
                throw new NoSuchElementException();
            }
        } catch (final XMLStreamException e) {
            throw new XMLIteratorException(createExceptionMessage(), e);
        } catch (final JAXBException e) {
            throw new XMLIteratorException(createExceptionMessage(), e);
        }
        return (this.helper.getGenericObject(returnObject));
    }

    /**
     * Nicht implementierte Methode, wirft bei Aufruf eine Exception
     *
     * @throws UnsupportedOperationException
     *             Bei jedem Aufruf dieser Methode
     */
    @Override
    public void remove() throws UnsupportedOperationException {
        throw (new UnsupportedOperationException());
    }

    /**
     * Schliesst den InputStream
     *
     * @throws IOException
     *             if an I/O error occurs
     */
    public void close() throws IOException {
        this.inputStream.close();
    }
}
