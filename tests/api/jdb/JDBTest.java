package api.jdb;

import java.nio.file.Path;

import org.junit.Test;

public class JDBTest {

	@Test
	public void test() {
		JDB jdb = new JDB.Builder()
				.workingDirectory(Path.of("./testfiles"))
				.classPath(classPath)
				.srcPath(srcPath)
				.build();
	}
}
