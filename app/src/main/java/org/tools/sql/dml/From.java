package org.tools.sql.dml;

public abstract class From {

    public static String build(String tableName) {
        return "FROM " + ((tableName != null)? tableName : " ");
    }
}
