import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Collection;

public class ExtractTest {


	@Test
	public void testExtracted() {
		File extractedDir = new File(System.getProperty("testOutputDir") + "/nodejs/extracted/");
		Assert.assertTrue("extractedDir " + extractedDir.getAbsolutePath() + " does not exist", extractedDir.exists());

    Collection<File> foundFiles = FileUtils.listFiles(extractedDir, new IOFileFilter() {
      @Override
      public boolean accept(File file) {
        return file.getName().startsWith("node");
      }

      @Override
      public boolean accept(File dir, String name) {
        return name.startsWith("node");
      }
    }, null);

    Assert.assertEquals(1, foundFiles.size());

    File extractedNodeJsExec = foundFiles.iterator().next();
		Assert.assertTrue("extracted node JS executable does not exist", extractedNodeJsExec.exists());
		Assert.assertTrue("extracted node JS executable is empty", extractedNodeJsExec.length() > 1000);
		Assert.assertTrue("extracted node JS executable did not have executable rights", extractedNodeJsExec.canExecute());
	}
}
