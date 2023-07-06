package sv.utils.beans;

/**
 *
 *
 * GENERATED CONTENT - DO NOT EDIT!
 *
 * Disclaimer: this content gets replaced with every run of the generator!
 *
 * @author sca-mda-generator
 *
 */
public class SelectPseudoBean {
    /**
     * serialVersionUID = 38467869L
     */
    private static final long serialVersionUID = 38467869L;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable key zurueck
     */
    public String getKey() {
        return this.key;
    }

    /**
     * setzt die Variable key
     */
    public void setKey(final String newvalue) {
        this.key = newvalue;
    }

    /**
     * @return gibt die Variable value zurueck
     */
    public String getValue() {
        return this.value;
    }

    /**
     * setzt die Variable value
     */
    public void setValue(final String newvalue) {
        this.value = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("SelectPseudoBean [");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("key = ").append(this.key).append(", ");
        buffer.append("value = ").append(this.value).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
