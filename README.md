# mg-benchmarking-tool

Java Data Mining Package - Benchmarking Tool

## Requirements

This project is based on the default Maven JMH archetype described [here](https://openjdk.java.net/projects/code-tools/jmh/).

### Java version

In order to run the benchmarks make sure you have Java 8 as default. Run the following command to know:

```bash
java -version
```

If you don't have Java 8 installed search how to install in your system and then use the following command to set your default version:

```bash
sudo update-alternatives --config java
```

### Maven

Make sure you also have a [Maven](https://maven.apache.org/index.html) version installed compatible with your Java installation. Check proper output of:

```bash
mvn --version
```

### Linux perf profiler

#### Install

The profiler used to measure alongside JMH is `perf`. For its proper execution you'll need to check your running kernel version with:

```bash
uname -r
```

Once you know you can just use `apt` to install your kernel's specific version (e.g. for `5.3.0-42-generic`):

```bash
apt install linux-tools-5.3.0-42-generic
```

#### Enable

You'll need to modify some system flags:

* Change from `3` to `-1` the `/proc/sys/kernel/perf_event_paranoid` file
* Change from `1` to `0` the `/proc/sys/kernel/nmi_watchdog` file

## Usage

### Run the benchmarks

As every JMH project you will need to run the following steps given that you are in the project directory:

```bash
mvn clean install
```

This will create a `target/` directory containing the executable file `benchmarks.jar`. To execute just type:

```bash
java -jar target/benchmarks.jar -prof perf
```

For the assembler alternative use:

```bash
java -jar target/benchmarks.jar -prof perfasm
```