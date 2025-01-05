package org.tools.sql.dml;

import java.util.ArrayList;
import java.util.Map;

public class Where {

    public static String build(Map<String, String> filters) {
        ArrayList<String> conditions;
        String filter;

        conditions = new ArrayList<>();
        filters.forEach((key, value) -> {
            conditions.add(String.join(" = ", key, String.join("'", "", value, "")));
        });
        filter = String.join(" " + Operators.AND.getText() + " ", conditions);

        return "WHERE " + filter;
    }
}
