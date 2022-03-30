package project.Application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.Optional;

@Getter
@NoArgsConstructor
@Slf4j
@Component
public class FileObject {

    @Value("${filepath}")
    private String filepath;

    public Optional<File> getFile() {
        if (filepath == null || filepath.isEmpty()) {
            log.error("Filepath is not provided.");
            return Optional.empty();
        }

        /*URL classResource = getClass().getClassLoader().getResource("testingResources/FibAllA.txt");
        if (classResource==null) {
            log.error("Class resource cannot be loaded: " + filename);
            return  Optional.empty();
        }*/
        // /user/application-resources
        File file = FileUtils.getFile(filepath);
        file.setReadable(true);
        if (!file.canRead()) {
            log.error("File cannot be read: " + filepath);
            return Optional.empty();
        } else
        if (!file.exists()) {
            log.error("File does not exist: " + file.getName());
            return Optional.empty();
        }
        Optional<File> fileOp = Optional.ofNullable(file);
        //Optional<File> file = Optional.ofNullable(FileUtils.getFile(filepath));
        if (fileOp.isEmpty()) {
            log.error("File does not exist: " + filepath);
            return Optional.empty();
        } else {
            return fileOp;
        }
    }

}
