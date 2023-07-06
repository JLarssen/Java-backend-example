package sv.utils.beans.xml;

import java.util.List;

/**
 * @author SV-Benutzer
 *
 */
public class XmlAnfrageParameter {
    private String vsnr_gruppe;

    private List<XmlSuchParameter> xmlSuchParameter;

    /**
     * @return the vsnr_gruppe
     */
    public final String getVsnr_gruppe() {
        return (this.vsnr_gruppe);
    }

    /**
     * @param vsnr_gruppe
     *            the vsnr_gruppe to set
     */
    public final void setVsnr_gruppe(final String vsnr_gruppe) {
        this.vsnr_gruppe = vsnr_gruppe;
    }

    /**
     * @return the xmlSuchParameter
     */
    public final List<XmlSuchParameter> getXmlSuchParameter() {
        return (this.xmlSuchParameter);
    }

    /**
     * @param xmlSuchParameter
     *            the xmlSuchParameter to set
     */
    public final void setXmlSuchParameter(final List<XmlSuchParameter> xmlSuchParameter) {
        this.xmlSuchParameter = xmlSuchParameter;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("XmlAnfrageParameter [vsnr_gruppe=");
        builder.append(this.vsnr_gruppe);
        builder.append(", xmlSuchParameter=");
        builder.append(this.xmlSuchParameter);
        builder.append("]");
        return builder.toString();
    }
}
