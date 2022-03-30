package project.arcadia;

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
        File file = FileUtils.getFile(filepath);
        if (!file.canRead()) {
            log.error("File cannot be read: " + filepath);
            return Optional.empty();
        } else if (!file.exists()) {
            log.error("File does not exist: " + file.getName());
            return Optional.empty();
        }
        return Optional.of(file);
    }

}
