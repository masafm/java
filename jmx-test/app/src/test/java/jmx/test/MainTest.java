/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jmx.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasMain() {
        Main classUnderTest = new Main();
        assertNotNull(classUnderTest.myFunc(), "app should have myFunc method");
    }
}
