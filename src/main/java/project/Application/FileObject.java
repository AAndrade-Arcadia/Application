package project.Application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        /*String relativeFilepath = getRelativePath(filepath).get();
        if (relativeFilepath == null || relativeFilepath.isEmpty()) {
            log.error("Relative filepath is not valid: " + filepath);
            return null;
        }*/
        Optional<File> file = Optional.ofNullable(FileUtils.getFile(filepath));
        if (file.isEmpty()) {
            log.error("File does not exist: " + filepath);
            return Optional.empty();
        } else {
            return file;
        }
    }

    private Optional<String> getRelativePath(String filepath) {
        Path absFilepathPath = Paths.get(filepath);
        File file = new File("TestFile.txt");
        String absProjectStr = file.getAbsolutePath();
        file.delete();
        String rootOfFilepath = filepath.substring(0, filepath.indexOf('/'));
        if (!absProjectStr.startsWith(rootOfFilepath)) {
            log.error("Filepath does not contain root directory: " + filepath);
            return Optional.empty();
        }
        Path absProjectPath = Paths.get(absProjectStr);
        Path pathRelative = absProjectPath.relativize(absFilepathPath);
        Optional<String> pathRelativeStr = Optional.ofNullable(pathRelative.toString());
        return pathRelativeStr;
    }
}
