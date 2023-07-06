package sv.utils.beans;

/**
 * @author SV-Benutzer
 */
public class VersicherterStatusDTO {
    private String versicherter_status;

    private String versicherter_kv_status;

    private String versicherter_adresseart;

    private String ang_status;

    /**
     * @return the versicherter_status
     */
    public String getVersicherter_status() {
        return (this.versicherter_status);
    }

    /**
     * @param versicherter_status
     *            the versicherter_status to set
     */
    public void setVersicherter_status(final String versicherter_status) {
        this.versicherter_status = versicherter_status;
    }

    /**
     * @return the versicherter_kv_status
     */
    public String getVersicherter_kv_status() {
        return (this.versicherter_kv_status);
    }

    /**
     * @param versicherter_kv_status
     *            the versicherter_kv_status to set
     */
    public void setVersicherter_kv_status(final String versicherter_kv_status) {
        this.versicherter_kv_status = versicherter_kv_status;
    }

    /**
     * @return the versicherter_adresseart
     */
    public String getVersicherter_adresseart() {
        return (this.versicherter_adresseart);
    }

    /**
     * @param versicherter_adresseart
     *            the versicherter_adresseart to set
     */
    public void setVersicherter_adresseart(final String versicherter_adresseart) {
        this.versicherter_adresseart = versicherter_adresseart;
    }

    /**
     * @return the ang_status
     */
    public String getAng_status() {
        return (this.ang_status);
    }

    /**
     * @param ang_status
     *            the ang_status to set
     */
    public void setAng_status(final String ang_status) {
        this.ang_status = ang_status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("VersicherterStatusDTO [versicherter_status=");
        builder.append(this.versicherter_status);
        builder.append(", versicherter_kv_status=");
        builder.append(this.versicherter_kv_status);
        builder.append(", versicherter_adresseart=");
        builder.append(this.versicherter_adresseart);
        builder.append(", ang_status=");
        builder.append(this.ang_status);
        builder.append("]");
        return builder.toString();
    }
}
