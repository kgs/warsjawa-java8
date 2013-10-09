# Warsjawa 2013 - Java 8 workshop

## Smoke testing

### Maven

Run `mvn test` to make sure you have JDK 8 and all dependencies in place.

### IDE

Run `Lesson00_HelloWorldTest.java` from your favourite IDE and make sure it compiles and passes.

## Troubleshooting

### Error `invalid target release: 1.8` during maven build

If you see this error message during maven build:

	[INFO] BUILD FAILURE
	...
	[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project lazyseq:
	Fatal error compiling: invalid target release: 1.8 -> [Help 1]

it means you are not compiling using Java 8. [Download JDK 8 with lambda support](https://jdk8.java.net/lambda/) and let maven use it:

	$ export JAVA_HOME=/path/to/jdk8
