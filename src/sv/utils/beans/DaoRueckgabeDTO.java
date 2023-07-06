package sv.utils.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.ProjectionList;

import sv.utils.Helper;

/**
 * @author SV-Benutzer
 */
public class DaoRueckgabeDTO {
    private final Helper helper;

    private final List<Object[]> list;

    private final Map<String, Integer> aliases;

    /**
     * Konstruktor
     *
     * @param list
     *            Rueckgabe der Hibernate-Abfrage
     * @param projectionList
     *            ProjectionList, die abgefragt wurde (damit die Spaltennamen
     *            bekannt sind)
     */
    public DaoRueckgabeDTO(List<Object[]> list, final ProjectionList projectionList) {
        this.helper = new Helper();
        if ((list != null) && (list.isEmpty())) {
            list = null;
        }
        this.list = list;
        this.aliases = new HashMap<>();
        if (projectionList != null) {
            final String[] als = projectionList.getAliases();
            if (als != null) {
                final int length = als.length;
                for (int i = 0; i < length; i++) {
                    if (!this.helper.isEmptyString(als[i])) {
                        this.aliases.put((als[i]).trim(), Integer.valueOf(i));
                    }
                }
            }
        }
    }

    /**
     * @param alias
     *            Spaltenname aus der in dieser Instanz gespeicherten
     *            ProjectionList
     *
     * @return gibt den Index in der in dieser Instanz gespeicherten Liste fuer
     *         den uebergebenen Spaltennamen zurueck
     */
    public Integer getIndex(final String alias) {
        return (this.helper.getValue(this.aliases, alias));
    }

    /**
     * haengt eine Liste an die in dieser Instanz bereits gespeicherte Liste an
     */
    public void addList(final List<Object[]> list2) {
        if (list2 != null) {
            final List<Object[]> listTemp = list2;
            this.list.addAll(listTemp);
        }
    }

    /**
     * @return the list
     */
    public List<Object[]> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DaoRueckgabeDTO [list=[");
        if (this.list != null) {
            for (final Object[] array : this.list) {
                builder.append(Arrays.toString(array)).append(",");
            }
        }
        builder.append("], aliases=");
        builder.append(this.aliases);
        builder.append("]");
        return builder.toString();
    }
}
