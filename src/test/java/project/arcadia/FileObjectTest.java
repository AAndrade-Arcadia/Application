package project.arcadia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileObjectTest {

    @Autowired
    private FileObject fileObjectTest;

    @Test
    public void testFilePathNotEmpty() {
        String actualOutput = fileObjectTest.getFilepath();
        Assertions.assertNotNull(actualOutput);
    }

}