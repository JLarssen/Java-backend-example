package sv.utils;

import java.security.Principal;

import constants.Constants_Allgemein;
import sv.utils.logic.principal.PrincipalToString;

/**
 * @author SV-Benutzer
 */
public class ExceptionHandler extends ExceptionHandlerAllgemein {
    private final PrincipalToString principalToString;

    /**
     * Default-Konstruktor
     */
    public ExceptionHandler() {
        super();
        this.principalToString = new PrincipalToString();
    }

    @Override
    public String principalToString(final Principal principal) {
        final StringBuffer sb = new StringBuffer();
        sb.append("UserPrincipal: ").append(principal).append(Constants_Allgemein.ENDL)
                .append(Constants_Allgemein.ENDL);
        try {
            if (principal != null) {
                sb.append("SVPrincipal: ").append(this.principalToString.principalToString(principal))
                        .append(Constants_Allgemein.ENDL).append(Constants_Allgemein.ENDL);
            }
        } catch (final Throwable t) {
            this.log.error(getErrorMeldung(t,
                    // ganz bewusst null, damit der Request keine Probleme machen kann. Immerhin muss ein Fehler im
                    // ExceptionHandler behandelt werden.
                    null));
        }
        return (sb.toString());
    }
}
