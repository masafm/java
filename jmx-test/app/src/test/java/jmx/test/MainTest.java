/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jmx.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test void appHasMain() {
        Main classUnderTest = new Main();
        assertNotNull(classUnderTest.print(), "app should have print method");
    }
}
