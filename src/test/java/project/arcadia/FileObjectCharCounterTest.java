package project.arcadia;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
class FileObjectCharCounterTest {

    @Autowired
    private FileObjectCharCounter fileObjectCharCounterTest;

    private static final String testFilepathFibAllA = "src/test/resources/testingResources/FibAllA.txt";
    private static final String testFilepathFibNotAllA = "src/test/resources/testingResources/FibNotAllA.txt";

    private static final Map<Integer, Integer> expectedOutput = new TreeMap<>();

    @BeforeEach
    private void setUp() {
        expectedOutput.put(0, 1);
        expectedOutput.put(1, 2);
        expectedOutput.put(2, 3);
        expectedOutput.put(3, 5);
        expectedOutput.put(4, 8);
    }

    @Test
    public void testCountOccurrenceOfAInFileOnFibAllA() throws IOException {
        File file = FileUtils.getFile(testFilepathFibAllA);
        Map<Integer, Integer> actualOutput = fileObjectCharCounterTest.countCharInFile(file);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCountOccurrenceOfAInFileOnFibNotAllA() throws IOException {
        File file = FileUtils.getFile(testFilepathFibNotAllA);
        Map<Integer, Integer> actualOutput = fileObjectCharCounterTest.countCharInFile(file);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

}