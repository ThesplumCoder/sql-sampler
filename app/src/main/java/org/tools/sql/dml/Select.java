package org.tools.sql.dml;

import java.util.ArrayList;

public abstract class Select {
    private final String DEFAULT = "*";

    public static String build(String... columns) {
        String part = "SELECT";
        return String.join(" ", part, String.join(", ", columns));
    }

    public static String buildDistinct(String modifier, String... columns) {
        String part = "SELECT";
        return String.join(" ", part, modifier, String.join(", ", columns));
    }
}
