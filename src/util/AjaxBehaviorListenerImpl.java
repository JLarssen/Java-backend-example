package util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.BehaviorEvent;

import org.slf4j.LoggerFactory;

/**
 * @author SV-Benutzer
 *
 */
public class AjaxBehaviorListenerImpl implements AjaxBehaviorListener {
    /**
     */
    public void init() {
        // nix
    }

    @Override
    public void processAjaxBehavior(final AjaxBehaviorEvent e) throws AbortProcessingException {
        final FacesContext fc = FacesContext.getCurrentInstance();
        final Application application = fc.getApplication();
        final ExpressionFactory ef = application.getExpressionFactory();
        final MethodExpression me = ef.createMethodExpression(fc.getELContext(), "#{testController.findKap}", null,
                new Class<?>[] { BehaviorEvent.class });
        final ELContext elc = fc.getELContext();
        LoggerFactory.getLogger(getClass()).debug("Now invoking ...");
        LoggerFactory.getLogger(getClass()).debug("" + elc);
        LoggerFactory.getLogger(getClass()).debug("" + me);
        LoggerFactory.getLogger(getClass()).debug("" + e);
        me.invoke(elc, new Object[] { e });
    }
}
