package beans.enums;

/**
 * @author SV-Benutzer
 *
 */
public enum Katalog {
    /**
     *
     */
    GESAMTKATALOG(Integer.valueOf(0), "Gesamtkatalog"),
    /**
     *
     */
    REGIONALKATALOG_WIEN(Integer.valueOf(1), "Mein Regionalkatalog"),
    /**
     *
     */
    REGIONALKATALOG_NIEDEROESTERREICH(Integer.valueOf(2), "Mein Regionalkatalog"),
    /**
             *
             */
    REGIONALKATALOG_BURGENLAND(Integer.valueOf(3), "Mein Regionalkatalog"),
    /**
             *
             */
    REGIONALKATALOG_OBEROESTERREICH(Integer.valueOf(4), "Mein Regionalkatalog"),
    /**
             *
             */
    REGIONALKATALOG_STEIERMARK(Integer.valueOf(5), "Mein Regionalkatalog"),
    /**
             *
             */
    REGIONALKATALOG_KAERNTEN(Integer.valueOf(6), "Mein Regionalkatalog"),
    /**
             *
             */
    REGIONALKATALOG_SALZBURG(Integer.valueOf(7), "Mein Regionalkatalog"),
    /**
             *
             */
    REGIONALKATALOG_TIROL(Integer.valueOf(8), "Mein Regionalkatalog"),
    /**
             *
             */
    REGIONALKATALOG_VORARLBERG(Integer.valueOf(9), "Mein Regionalkatalog"),
    /**
             *
             */
    WIEN(Integer.valueOf(1), "Wien"),
    /**
             *
             */
    NIEDEROESTERREICH(Integer.valueOf(2), "Niederösterreich"),
    /**
             *
             */
    BURGENLAND(Integer.valueOf(3), "Burgenland"),
    /**
             *
             */
    OBEROESTERREICH(Integer.valueOf(4), "Oberösterreich"),
    /**
             *
             */
    STEIERMARK(Integer.valueOf(5), "Steiermark"),
    /**
             *
             */
    KAERNTEN(Integer.valueOf(6), "Kärnten"),
    /**
             *
             */
    SALZBURG(Integer.valueOf(7), "Salzburg"),
    /**
             *
             */
    TIROL(Integer.valueOf(8), "Tirol"),
    /**
             *
             */
    VORARLBERG(Integer.valueOf(9), "Vorarlberg"),
    /**
             *
             */
    KURZPOSITIONEN(Integer.valueOf(10), "Kurzpositionen"),
    /**
             *
             */
    BUNDESWEIT_REGIONALKATALOG(Integer.valueOf(11), "Bundesweite und eigene Regionaltarife"),
    /**
                     *
                     */
    NUR_BUNDESWEIT(Integer.valueOf(12), "Nur bundesweite Tarife");

    private Katalog(final Integer code, final String text) {
        this.katalog_code = code;
        this.katalog_text = text;
    }

    private Integer katalog_code;

    private String katalog_text;

    /**
     * @return the katalog_code
     */
    public Integer getKatalog_code() {
        return (this.katalog_code);
    }

    /**
     * @return the katalog_text
     */
    public String getKatalog_text() {
        return (this.katalog_text);
    }

    @Override
    public String toString() {
        return (this.katalog_text);
    }
}
