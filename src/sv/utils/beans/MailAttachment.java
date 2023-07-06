package sv.utils.beans;

import java.io.File;

/**
 * repraesentiert ein Mail-Attachment, welches im Mailservice versendet werden
 * kann
 *
 * @author SV-Benutzer
 */
public class MailAttachment {
    private byte[] attachment_as_bytes;

    private File attachment_as_file;

    private String name;

    private String mime_type;

    /**
     * @return the attachment_as_bytes
     */
    public byte[] getAttachment_as_bytes() {
        return (this.attachment_as_bytes);
    }

    /**
     * @param attachment_as_bytes
     *            the attachment_as_bytes to set
     */
    public void setAttachment_as_bytes(final byte[] attachment_as_bytes) {
        this.attachment_as_bytes = attachment_as_bytes;
    }

    /**
     * @return the attachment_as_file
     */
    public File getAttachment_as_file() {
        return (this.attachment_as_file);
    }

    /**
     * @param attachment_as_file
     *            the attachment_as_file to set
     */
    public void setAttachment_as_file(final File attachment_as_file) {
        this.attachment_as_file = attachment_as_file;
    }

    /**
     * @return the name
     */
    public String getName() {
        return (this.name);
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the mime_type
     */
    public String getMime_type() {
        return (this.mime_type);
    }

    /**
     * @param mime_type
     *            the mime_type to set
     */
    public void setMime_type(final String mime_type) {
        this.mime_type = mime_type;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MailAttachment [attachment_as_bytes=");
        builder.append((this.attachment_as_bytes != null) ? String.valueOf(this.attachment_as_bytes.length) : "null");
        builder.append(", attachment_as_file=");
        builder.append(this.attachment_as_file);
        builder.append(", name=");
        builder.append(this.name);
        builder.append(", mime_type=");
        builder.append(this.mime_type);
        builder.append("]");
        return builder.toString();
    }
}
