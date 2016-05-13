nodejs-maven-plugin
===================

Extracts a NodeJS executable to a Maven build environment. Requires Maven 3.x or higher.

### Configuration
This plugin has two configuration parameters:
* `targetDirectory` contains the directory in which the node binaries will be extracted
* `overwrite` set to `false` to prevent this plugin from extracting the binary if it already exists in the `targetDirectory`. This helps speed up the build.

### Usage
The following POM plugin configuration will extract the NodeJs executable to directory `${basedir}/target/nodejs/`

    <plugins>
      <plugin>
        <groupId>com.github.skwakman.nodejs-maven-plugin</groupId>
        <artifactId>nodejs-maven-plugin</artifactId>
        <version>1.0.5-node-0.10.25</version>
        <executions>
          <execution>
            <goals>
              <goal>extract</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
            <!-- target directory for node binaries -->
            <targetDirectory>${basedir}/target/nodejs/</targetDirectory>
            <!-- dont extract the binary if it already exists -->
            <overwrite>false</overwrite>
        </configuration>
      </plugin>
    </plugins>

### Supported platforms

The plugin supplies a NodeJS binary for the following platforms:

* Windows (32 and 64 bit)
* Mac OS (32 and 64 bit)
* Linux (i386 and amd64)
