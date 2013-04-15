import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ExtractTest {


	@Test
	public void testExtracted() {
		File extractedDir = new File(System.getProperty("testOutputDir") + "/nodejs/extracted/");
		Assert.assertTrue("extractedDir " + extractedDir.getAbsolutePath() + " does not exist", extractedDir.exists());

		File extractedNodeJsExec = new File(extractedDir, "node.exe");
		Assert.assertTrue("extracted node JS executable does not exist", extractedNodeJsExec.exists());
		Assert.assertTrue("extracted node JS executable is empty", extractedNodeJsExec.length() > 1000);
		Assert.assertTrue("extracted node JS executable did not have executable rights", extractedNodeJsExec.canExecute());
	}
}
