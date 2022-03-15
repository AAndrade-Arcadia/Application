package project.Application;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Getter
@Slf4j
@Service
public class FileObjectCharCounter {

    private final char charToCount;

    public FileObjectCharCounter(@Value("${charToCount}") char charToCount) {
        this.charToCount = charToCount;
    }

    public Map<Integer, Integer> countCharInFile(@NonNull File file) throws IOException {
        Map<Integer, Integer> lineMapToCount = new TreeMap<>();
        if (!file.getAbsoluteFile().canRead()) {
            log.error("File cannot be read: " + file.getAbsolutePath());
        } else if (!file.getAbsoluteFile().exists()) {
            log.error("File does not exist: " + file.getAbsolutePath());
        } else {
            LineIterator lineItr = FileUtils.lineIterator(file);
            int lineNum = 0;
            try {
                while (lineItr.hasNext()) {
                    lineMapToCount.put(lineNum++, countCharInLine(lineItr.next()));
                }
            } finally {
                lineItr.close();
            }
        }
        return lineMapToCount;
    }

    private int countCharInLine(@NonNull String line) {
        int count = 0;
        int indexOfChar = line.indexOf(charToCount);
        while (indexOfChar >= 0) {
            ++count;
            line = line.substring(++indexOfChar);
            indexOfChar = line.indexOf(charToCount);
        }
        return count;
    }

}
