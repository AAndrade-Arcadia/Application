package project.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private FileObject fileObject;

	@Autowired
	private FileObjectCharCounter fileObjectCharCounter;

	@Override
	public void run(String... args) throws Exception {
		Map<Integer, Integer> finalLineMapToCountOfA;
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
		Optional<File> opFile = fileObject.getFile();
		if (opFile.isPresent()) {
			finalLineMapToCountOfA = fileObjectCharCounter.countCharInFile(opFile.get());
		}
	}
}
