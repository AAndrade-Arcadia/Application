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

    //TODO this always reverts to default
    @Value("${filepath:/src/main/resources/testingResources/FibAllA.txt}")
    private String filepath;

    public Optional<File> getFile() {
        if (filepath == null || filepath.isEmpty()) {
            log.error("filepath is empty");
        } else {
            Optional<File> file = Optional.ofNullable(FileUtils.getFile(filepath));
            if (file.isPresent()) {
                return file;
            } else log.error("file at filepath does not exist");
        }
        return null;
    }
}
