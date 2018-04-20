# Plagiarism Detector

A Desktop Application designed to take work from multiple submissions by the students, for code written in C, and detect cases of plagiarism. It can be used be both the Professor as well as the TAs. 

## Authors

**Team-107**

* **Student-109** - [Darshan Panse](https://github.ccs.neu.edu/darshanpanse)
* **Student-137** - [Samanjate Sood](https://samanjate.github.io/)
* **Student-146** - [Vaibhav Dave](https://github.ccs.neu.edu/vaibhavdave5)
* **Student-153** - [Shail Shah](https://github.ccs.neu.edu/shail)

See also the list of [contributors](https://github.ccs.neu.edu/cs5500/team-107/contributors) who participated in this project.

## Download and Versioning

You can download the latest verision of the software [here](https://plagiarism-detector-107.herokuapp.com/).

## Project Videos

Learn more about the application by watching the following videos:
* [System Demo](https://www.youtube.com/watch?v=u0dAf760Viw&feature=youtu.be)
* [System Setup]()
* [Final Presentation](https://www.youtube.com/watch?v=880URnyB5nc&feature=youtu.be)

## Getting Started

To get you a copy of the project up and running on your local machine for development and testing purposes, please follow the steps provided. 

To use the application, you can simply copy the contents of the 'stand-alone-systems' folder to you local computer and run the jar. Please refer to the README file specific to that folder and the User Guide provided to see how to run and use the applicatoin.

### Prerequisites

While the system is designed to work across all platforms and IDE's the following instructions provided to build the project locally may require some modifications depending on the developer's environement. The instructions assume that you are using Eclipse and Java 8. You will also need Git installed on your computer and appropriate permissions to clone the repository.

Please download the latest [maven](https://maven.apache.org/download.cgi) version and have the 'mvn' command up and running on your terminal/cmd. You can verify this by using the following command.

```
$ mvn --version
```

### Installing

You will first need to clone the project to your local system. You can do this by using the following command. This shall prompt you to enter your username and password. You will need the necessary permissions to perform this step.

```
$ git clone https://github.ccs.neu.edu/cs5500/team-107.git
```

Next you will need to set a maven local repository with the jfx jar path defined for the maven dependency provided in the project. You can do this by simply 'changing directory' to the plagiarism-detector folder and running this command.

```
$ cd /path/to/git/repo/team-107/plagiarism-detector
$ mvn install:install-file -Dfile=jfxrt.jar -DgroupId=com.oracle -DartifactId=javaFX -Dversion=2.2 -Dpackaging=jar
```

Now you can simply import the project in Eclipse using Import -> Exisiting Maven Project and provide the plagiarism-detector folder as the root.

## Running the tests

From your IDE, once you have imported the project you can find all the tests in the src/test/java folder and run all or individually as JUnit tests. All tests are using JUnit 4.

You can run this from terminal/cmd using maven. Use the compile and test commands as follows:

```
$ mvn compile
.......
.....
...
.
$ mvn test
.......
.....
...
.
```

You can also run the tests and run the application using the following commands:

```
$ mvn jfx:run
.......
.....
...
.
```

You can run it with native installers as well.

```
$ mvn jfx:native
.......
.....
...
.
```

## Built With

* [ANTLR](http://www.antlr.org/) - The AST generator
* [Maven](https://maven.apache.org/) - Dependency Management
* [jbootx](https://github.com/dicolar/jbootx) - Bootstrap theme for JavaFx

## Jenkins

You can find our build infomation [here](http://ec2-54-149-142-26.us-west-2.compute.amazonaws.com:8080)

## Acknowledgments

* Special thanks to the StackOverflow community for constantly resolving issues we faced while developing the project.

