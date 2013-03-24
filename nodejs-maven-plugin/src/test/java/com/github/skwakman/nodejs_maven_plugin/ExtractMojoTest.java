package com.github.skwakman.nodejs_maven_plugin;


import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ExtractMojoTest {

  @Test
  public void executeTest() throws MojoExecutionException, IOException {
    File destinationFile = new File("target/test/nodejs/node");
    FileUtils.deleteQuietly(destinationFile);

    ExtractMojo mojo = new ExtractMojo();
    mojo.setTargetLocation(destinationFile.getAbsolutePath());

    mojo.execute();

    Assert.assertTrue(destinationFile.exists());
    Assert.assertTrue(destinationFile.canExecute());
    Assert.assertTrue(destinationFile.length() > 20000);

  }
}
