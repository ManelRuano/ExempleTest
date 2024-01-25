package com.project.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainOutput() {
        // You need to have a preset environment or mock the database interactions
        // for this to work consistently.
        Main.main(new String[]{});
        String expectedOutput = "1: Employee 1 Aes, 55\r\n" + //
                "2: Employee 2 Bes, 2000\r\n" + //
                "3: Employee 3 Ces, 3000\r\n" + //
                "1: Contact 1, ves@a.com, Employees: [2, Employee 2, Bes, 2000 | 3, Employee 3, Ces, 300]\r\n" + //
                "2: Contact 1, ves@a.com, Employees: [1, Employee 1, Aes, 55 | 2, Employee 2, Bes, 200]\r\n" + //
                "3: Contact 3, xes@ga.com, Employees: []\r\n" + //
                "4: Contact 4, yes@ga.com, Employees: []";

        // Normalize newlines for both expected and actual output
        String normalizedExpectedOutput = normalizeNewlines(expectedOutput);
        String normalizedActualOutput = normalizeNewlines(outContent.toString().trim());

        // Compare the normalized outputs
        assertEquals(normalizedExpectedOutput, normalizedActualOutput);
    }

    private String normalizeNewlines(String input) {
        return input.replace("\r\n", "\n").replace("\r", "\n");
    }
}
