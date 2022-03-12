package project.Application;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
class FileObjectCharCounterTest {

    @Autowired
    private FileObject fileObjectTest;

    @Autowired
    private FileObjectCharCounter fileObjectCharCounterTest;

    private final String testFilepathFibAllA = "./src/main/resources/testingResources/FibAllA.txt";
    private final String testFilepathFibNotAllA = "./src/main/resources/testingResources/FibNotAllA.txt";

    @Test
    public void testCountOccurrenceOfAInFileOnFibAllA() throws IOException {
        File file = FileUtils.getFile(testFilepathFibAllA);
        Map<Integer, Integer> expectedOutput = new TreeMap<>();
        expectedOutput.put(0, 1);
        expectedOutput.put(1, 2);
        expectedOutput.put(2, 3);
        expectedOutput.put(3, 5);
        expectedOutput.put(4, 8);
        Map<Integer, Integer> actualOutput = fileObjectCharCounterTest.countCharInFile(file);
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void testCountOccurrenceOfAInFileOnFibNotAllA() throws IOException {
        File file = FileUtils.getFile(testFilepathFibNotAllA);
        Map<Integer, Integer> actualOutput;
        Map<Integer, Integer> expectedOutput = new TreeMap<>();
        expectedOutput.put(0, 1);
        expectedOutput.put(1, 2);
        expectedOutput.put(2, 3);
        expectedOutput.put(3, 5);
        expectedOutput.put(4, 8);
        actualOutput = fileObjectCharCounterTest.countCharInFile(file);
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void testFilePathNotEmpty() {
        String actualOutput = fileObjectTest.getFilepath();
        Assertions.assertNotNull(actualOutput);
    }
}