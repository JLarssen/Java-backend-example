package beans.dto;

/**
 * @author Maurov
 *
 */
public class PositionBundeslandDTO {
    /**
     * position_bundesland_id
     */
    private Long position_bundesland_id;

    private Integer bundesland_code;

    /**
     * @return the position_bundesland_id
     */
    public Long getPosition_bundesland_id() {
        return (this.position_bundesland_id);
    }

    /**
     * @param position_bundesland_id
     *            the position_bundesland_id to set
     */
    public void setPosition_bundesland_id(final Long position_bundesland_id) {
        this.position_bundesland_id = position_bundesland_id;
    }

    /**
     * @return the bundesland_code
     */
    public Integer getBundesland_code() {
        return (this.bundesland_code);
    }

    /**
     * @param bundesland_code
     *            the bundesland_code to set
     */
    public void setBundesland_code(final Integer bundesland_code) {
        this.bundesland_code = bundesland_code;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PositionBundeslandDTO [position_landesstellen_id=");
        builder.append(this.position_bundesland_id);
        builder.append(", bundesland_code=");
        builder.append(this.bundesland_code);
        builder.append("]");
        return builder.toString();
    }
}
