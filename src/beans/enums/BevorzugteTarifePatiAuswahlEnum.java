package beans.enums;

/**
 * @author Leonjo
 *
 */
public enum BevorzugteTarifePatiAuswahlEnum {
    /**
     * FACHGEBIET("Fachgebiet", "fachgebiet"),
     */
    FACHGEBIET("Fachgebiet", "fachgebiet"),
    /**
     * UNTERSUCHUNGSART("Untersuchungsart", "untersuchungsart");
     */
    UNTERSUCHUNGSART("Untersuchungsart", "untersuchungsart");

    private final String value;

    private final String kategorie;

    BevorzugteTarifePatiAuswahlEnum(final String value, final String kategorie) {
        this.value = value;
        this.kategorie = kategorie;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return (this.value);
    }

    /**
     * @return the kategorie
     */
    public String getKategorie() {
        return (this.kategorie);
    }

    /**
     * @param v
     *            the value
     * @return the BevorzugteTarifePatiAuswahlEnum
     */
    public static BevorzugteTarifePatiAuswahlEnum fromValue(final String v) {
        for (final BevorzugteTarifePatiAuswahlEnum c : BevorzugteTarifePatiAuswahlEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    /**
     * @param v
     *            the kategorie
     * @return the BevorzugteTarifePatiAuswahlEnum
     */
    public static BevorzugteTarifePatiAuswahlEnum fromKategorie(final String v) {
        for (final BevorzugteTarifePatiAuswahlEnum c : BevorzugteTarifePatiAuswahlEnum.values()) {
            if (c.kategorie.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
