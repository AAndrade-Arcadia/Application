package project.Application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

@Getter
@NoArgsConstructor
@Service
public class FileObjectCharCounter {

    //Can we autowire a char? (seems no.) Should I autowire string and convert?
    private final char charCounted = 'A';

    public Map<Integer, Integer> countCharInFile(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        ListIterator<String> listIt = lines.listIterator();
        Map<Integer, Integer> lineMapToCount = new TreeMap<>();
        while (listIt.hasNext()) {
            lineMapToCount.put(listIt.nextIndex(), countCharInLine(listIt.next()));
        }
        return lineMapToCount;
    }

    private int countCharInLine(String line) {
        int count = 0;
        int indexOfChar = line.indexOf(charCounted);
        while (indexOfChar != -1) {
            ++count;
            line = line.substring(++indexOfChar);
            indexOfChar = line.indexOf(charCounted);
        }
        return count;
    }

}
