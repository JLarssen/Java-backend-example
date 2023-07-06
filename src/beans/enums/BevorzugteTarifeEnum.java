package beans.enums;

/**
 * @author Leonjo
 *
 */
public enum BevorzugteTarifeEnum {
    /**
     *
     */
    POSNRBINF("posnrbinf"),
    /**
     *
     */
    VERORDNUNGSREFERENZ("verordnugnsreferenz");

    private final String value;

    BevorzugteTarifeEnum(final String v) {
        this.value = v;
    }

    /**
     * @return the value
     */
    public String value() {
        return this.value;
    }

    /**
     * @param v
     *            the value
     * @return the TariftypEnum
     */
    public static BevorzugteTarifeEnum fromValue(final String v) {
        for (final BevorzugteTarifeEnum c : BevorzugteTarifeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
