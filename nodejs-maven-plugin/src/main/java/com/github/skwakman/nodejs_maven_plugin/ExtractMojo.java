package com.github.skwakman.nodejs_maven_plugin;


import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.codehaus.plexus.archiver.UnArchiver;
import org.codehaus.plexus.archiver.manager.ArchiverManager;
import org.codehaus.plexus.archiver.manager.NoSuchArchiverException;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Mojo for adding the NodeJS executable to a build environment.
 *
 * @goal extract
 * @phase process-classes
 */
public class ExtractMojo extends AbstractMojo {

	/**
	 * Specifies directory to extract the node files
	 *
	 * @parameter default-value=${project.build.outputDirectory}/nodejs
	 */
	private String targetDirectory;

	/**
	 * @component
	 */
	protected ArchiverManager archiverManager;

	/**
	 * @parameter default-value="${plugin}"
	 * @required
	 * @readonly
	 */
	protected PluginDescriptor pluginDescriptor;

	/**
	 * Extracts NodeJS binaries
	 *
	 * @throws org.apache.maven.plugin.MojoExecutionException
	 *          if there is a problem
	 */
	public void execute() throws MojoExecutionException {
		File targetDir = new File(targetDirectory);

		prepareTargetDirectory(targetDir);
		extractArtifact(findNodeJsBinariesArtifact(pluginDescriptor.getArtifacts()), targetDir);
	}

	private void prepareTargetDirectory(File targetDir) throws MojoExecutionException {
		try {
			targetDir.mkdirs();
			FileUtils.cleanDirectory(targetDir);
		} catch (IOException e) {
			throw new MojoExecutionException("Could not prepare targetDirectory for extraction: " + e.getMessage(), e);
		}

	}

	private Artifact findNodeJsBinariesArtifact(List<Artifact> artifacts) throws MojoExecutionException {
		for (Artifact artifact : artifacts) {
			if (artifact.getArtifactId().equals("nodejs-maven-binaries")) {
				return artifact;
			}
		}
		throw new MojoExecutionException("Could not locate nodejs-maven-binaries dependency of plugin. Is the build environment's platform supported?");
	}

	private void extractArtifact(Artifact artifact, File destinationDirectory) throws MojoExecutionException {
		try {
			UnArchiver unArchiver = archiverManager.getUnArchiver(artifact.getFile());
			unArchiver.setUseJvmChmod(true);
			unArchiver.setIgnorePermissions(false);
			unArchiver.setSourceFile(artifact.getFile());
			unArchiver.setDestDirectory(destinationDirectory);
			unArchiver.extract();
		} catch (NoSuchArchiverException e) {
			throw new MojoExecutionException("Could not find archiver for artifact " + artifact.getArtifactId());
		}
	}
}
