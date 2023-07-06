package beans.enums;

/**
 * @author SV-Benutzer
 *
 */
public enum TarifIndexEnum {
    /**
     *
     */
    INDEX_VOLL("voll"),
    /**
     *
     */
    INDEX_KAPITEL("kapitel"),
    /**
     *
     */
    INDEX_SBTEXT("sbtext");

    private final String value;

    TarifIndexEnum(final String v) {
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
    public static TarifIndexEnum fromValue(final String v) {
        for (final TarifIndexEnum c : TarifIndexEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
