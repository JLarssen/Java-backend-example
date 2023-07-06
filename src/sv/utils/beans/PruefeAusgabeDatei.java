package sv.utils.beans;

import java.io.File;

/**
 * @author SV-Benutzer
 *
 */
public class PruefeAusgabeDatei extends PruefeParameter {
    /**
     * Default-Konstruktor
     *
     */
    public PruefeAusgabeDatei(final String ausgabeDatei) {
        super();
        this.parameter = ausgabeDatei;
    }

    @Override
    public boolean pruefeParameter(final String request) {
        boolean gueltig = false;
        if (!this.helperAllgemein.isEmptyString(this.parameter)) {
            final File file = new File(this.parameter);
            if (!file.exists()) {
                gueltig = true;
            } else {
                this.log.error(this.exceptionHandlerAllgemein
                        .getErrorMeldung(new Exception("Datei=" + this.parameter + " existiert bereits"), request));
            }
        } else {
            this.log.error(this.exceptionHandlerAllgemein
                    .getErrorMeldung(new Exception("Datei=" + this.parameter + " ist null"), request));
        }
        return (gueltig);
    }
}
