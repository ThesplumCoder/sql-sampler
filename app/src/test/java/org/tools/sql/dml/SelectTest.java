package org.tools.sql.dml;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelectTest {

    @Test public void concatenateColumns() {
        String test = "SELECT column1, column2";
        assertEquals(test, Select.build("column1", "column2"));
    }
}
