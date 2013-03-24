package com.github.skwakman.nodejs_maven_plugin;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import java.io.*;

/**
 * Mojo for adding the NodeJS executable to a build environment.
 *
 * @goal extract
 * @phase process-classes
 */
public class ExtractMojo extends AbstractMojo {

  /**
   * @parameter default-value="${project}"
   * @required
   * @readonly
   */
  private MavenProject project;

  /**
   * Specifies where to extract the node executable
   *
   * @parameter default-value=${project.build.outputDirectory}/nodejs
   */
  private String targetLocation;

  /**
   * Optimize files.
   *
   * @throws org.apache.maven.plugin.MojoExecutionException
   *          if there is a problem optimizing files.
   */
  public void execute() throws MojoExecutionException {
    try {
      InputStream fileStream = getClass().getClassLoader().getResourceAsStream("nodejs/node");
      File destinationFile = new File(targetLocation);
      FileUtils.copyInputStreamToFile(fileStream, destinationFile);
      destinationFile.setExecutable(true);
      IOUtils.closeQuietly(fileStream);
    } catch (FileNotFoundException e) {
      throw new MojoExecutionException("Error occured while executing extraction of nodejs", e);
    } catch (IOException e) {
      throw new MojoExecutionException("Error occured while executing extraction of nodejs", e);
    }
  }

  void setTargetLocation(String targetLocation) {
    this.targetLocation = targetLocation;
  }
}
