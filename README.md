# mg-benchmarking-tool

Java Data Mining Package - Benchmarking Tool

## Usage

This project is based on the default Maven JMH archetype described [here](https://openjdk.java.net/projects/code-tools/jmh/).

### Java version

In order to run the benchmarks make sure you have Java 8 as default. Run the following command to know:

```bash
$ java -version
```

If you don't have Java 8 installed search how to install in your system and then use the following command to set your default version:

```bash
$ sudo update-alternatives --config java
```

### Maven

Make sure you also have a Maven version installed comatible with your Java. Check proper output of:

```bash
$ mvn --version
```

### Run the benchmarks

As every JMH project you will need to run the following steps given that you are in the project directory:

```bash
$ mvn clean install
```

This will create a `target/` directory containing the executable file `benchmarks.jar`. To execute just type:

```bash
$ java -jar target/benchmarks.jar
```

