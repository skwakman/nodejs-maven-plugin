nodejs-maven-plugin
===================

Extracts a NodeJS executable to a Maven build environment. Requires Maven 3.x or higher.

### Usage
The following POM plugin configuration will extract the NodeJs executable to file `target/nodejs/node`

    <plugins>
      <plugin>
        <groupId>com.github.skwakman.nodejs-maven-plugin</groupId>
        <artifactId>nodejs-maven-plugin-parent</artifactId>
        <version>1.0-SNAPSHOT</version>
        <executions>
          <execution>
            <goals>
              <goal>extract</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
            <!-- target directory for node binaries -->
            <targetDirectory>
                ${basedir}/target/nodejs/
            </targetDirectory>
        </configuration>
      </plugin>
    </plugins>

### Supported platforms

The plugin currently supplies a NodeJS binary for the following platforms:

* Windows (32 and 64 bit)
* Mac OS (32 and 64 bit)
* Linux (i386 and amd64)
