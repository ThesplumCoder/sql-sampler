package org.tools.sql.dml;

import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WhereTest {

    @Test public void concatenateFilters() {
        String expFilter = "WHERE filter1 = 'value1' AND filter2 = 'value2'";

        var filters = new LinkedHashMap<String, String>();
        filters.put("filter1", "value1");
        filters.put("filter2", "value2");
        assertEquals(expFilter, Where.build(filters));
    }
}
