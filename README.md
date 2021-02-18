
![](https://raw.githubusercontent.com/williamniemiec/jdb-api/master/docs/img/logo/logo.jpg?raw=true)

<h1 align='center'>JDB API</h1>
<p align='center'>Simple JDB API for running The Java Debugger (JDB) in code</p>
<p align="center">
	<a href="https://github.com/williamniemiec/jdb-api/actions?query=workflow%3AWindows"><img src="https://img.shields.io/github/workflow/status/williamniemiec/jdb-api/Windows?label=Windows" alt=""></a>
	<a href="https://github.com/williamniemiec/jdb-api/actions?query=workflow%3AMacOS"><img src="https://img.shields.io/github/workflow/status/williamniemiec/jdb-api/MacOS?label=MacOS" alt=""></a>
	<a href="https://github.com/williamniemiec/jdb-api/actions?query=workflow%3AUbuntu"><img src="https://img.shields.io/github/workflow/status/williamniemiec/jdb-api/Ubuntu?label=Ubuntu" alt=""></a>
	<a href="https://codecov.io/gh/williamniemiec/jdb-api"><img src="https://codecov.io/gh/williamniemiec/jdb-api/branch/v1.x/graph/badge.svg?token=R2SFS4SP86" alt="Coverage status"></a>
	<a href="http://java.oracle.com"><img src="https://img.shields.io/badge/java-8+-4c7e9f.svg" alt="Java compatibility"></a>
	<a href="https://github.com/williamniemiec/jdb-api/releases"><img src="https://img.shields.io/github/v/release/williamniemiec/jdb-api" alt="Release"></a>
	<a href="https://github.com/williamniemiec/jdb-api/blob/master/LICENCE"><img src="https://img.shields.io/github/license/williamniemiec/jdb-api" alt="Licence"></a>
</p>

<hr />

## ❇ Introduction
The Java Debugger (JDB) is a simple command-line debugger. The objective of this project is to facilitate the use of this debugger without having to worry about creating a process, redirecting output, among others.

## ❓ How to use
In order to use this API, you need to provide the following information:
* Working directory
* Class paths that will be accessed by the debugger
* Source paths that will be accessed by the debugger

It is necessary to build JDB class. Build it as follows:
<pre>
JDB jdb = new JDB.Builder()
		.workingDirectory(BASE_DIRECTORY)
		.classPath(LIST_OF_COMPILED_FILES)
		.srcPath(LIST_OF_SOURCE_FILES)
		.build();
</pre>

After that, run it as follows:
<pre>
jdb.run();
</pre>

Send commands to the debugger as follows:
<pre>
jdb.send(COMMAND);
</pre>

Read the output as follows:
<pre>
jdb.read();    // Returns output as a string
</pre>

Read all available output as follows:
<pre>
jdb.readAll();    // Returns output as a list of strings
</pre>

See the list of commands [here](#commands).

#### Example
<pre>
Path workingDirectory = Path.of(".", "bin").toAbsolutePath().normalize();
Path sourcePath = workingDirectory
		.resolve(Path.of("..", "tests"))
		.normalize();
List&lt;Path> classpaths = List.of(workingDirectory);
List&lt;Path> sourcePaths = List.of(
		workingDirectory.relativize(sourcePath)
);
		
JDB jdb = new JDB.Builder()
		.workingDirectory(workingDirectory)
		.classPath(classpaths)
		.srcPath(sourcePaths)
		.build();
jdb.run().send("stop at api.jdb.testfiles.Calculator : 8")

String line = jdb.read();

// Runs until it reaches the breakpoint on line 8
while (!line.contains("Breakpoint")) {
	line = jdb.read();
	System.out.println(line);
}

jdb.send("step into");	// Enters the function

line = jdb.read();
System.out.println(line);
</pre>


## 🔌 Installation
#### Maven
[See how to install here](https://github.com/williamniemiec/jdb-api/packages/613128#-installation)

#### Eclipse IDE
1) With your project opened, add [`jdb-api.jar`](https://github.com/williamniemiec/jdb-api/releases/) into project build path.

![step1](https://raw.githubusercontent.com/williamniemiec/jdb-api/master/docs/img/how-to-use/step1.png?raw=true)
![step2](https://raw.githubusercontent.com/williamniemiec/jdb-api/master/docs/img/how-to-use/step2.png?raw=true)
![step3](https://raw.githubusercontent.com/williamniemiec/jdb-api/master/docs/img/how-to-use/step3.png?raw=true)
![step4](https://raw.githubusercontent.com/williamniemiec/jdb-api/master/docs/img/how-to-use/step4.png?raw=true)


## <a name="commands"></a> 🎮 Commands
Here are the main commands that can be sent to the debugger using the send method.

|        Command 	| Description|
|----------------|-------------------------------|
|`stop at <class_name>:<line_number>`|Sets up a breakpoint at a particular line number|
|`stop in <class_name>:<method_name_or_variable_name>`|Sets up a breakpoint on a particular method or on a particular variable|
|`clear`|Removes all breakpoints|
|`exit`|Closes JDB|
|`run`|The execution stops at the first line of the main method.|
|`step into`|Step to the next line of the code. If the next line of the code is a function call, then it enters the function by driving the control at the top line of the function.|
|`step over`|Step to the next line of the code. If the next line is a function call, it executes that function in the background and returns the result.|
|`cont`|Continues execution of the debugged application after a breakpoint, exception, or step. It will stop only if it passes through a breakpoint.|

## 🚩 Changelog
Details about each version are documented in the [releases section](https://github.com/williamniemiec/jdb-api/releases).

## 🤝 Contribute
See the documentation on how you can contribute to the project [here](https://github.com/williamniemiec/jdb-api/blob/master/CONTRIBUTING.md).

## 📁 Files
### /
|        Name 	|Type|Description|
|----------------|-------------------------------|-----------------------------|
|dist |`Directory`|Released versions|
|docs |`Directory`|Documentation files|
|src     |`Directory`| Source files |

## See more
* [Oracle - jdb - The Java Debugger](https://docs.oracle.com/javase/7/docs/technotes/tools/windows/jdb.html)
* [Tutorials point - JDB quick guide](https://www.tutorialspoint.com/jdb/jdb_quick_guide.htm)

