package util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author SV-Benutzer
 *
 */
@Named
@ApplicationScoped
public class FacesContextBroker {
    private static final Helper helper = new Helper();

    /**
     * Liefert ein Faces Context zurueck
     *
     * @return ein FacesContext
     */
    @Produces
    @ConversationScoped
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Liefert ein HttpServletRequest zurueck
     *
     * @return ein HttpServletRequest
     */
    @Produces
    @RequestScoped
    public HttpServletRequest getHttpRequest() {
        return helper.getHttpServletRequest(getFacesContext());
    }
}
