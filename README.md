# Plagiarism Detector

A Desktop Application designed to take work from multiple submissions and detect cases of plagiarism. It can be used be both the Professor as well as the TAs. 

## Getting Started

To get you a copy of the project up and running on your local machine for development and testing purposes, please follow the steps provided. 
To use the application, you can simply copy the contents of the 'stand-alone-system' folder to you local computer and run the jar. Please refer to the README file specific to that folder and the User Guide provided to see how to run and use the applicatoin.

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
: plagiarism-detector $ mvn install:install-file -Dfile=jfxrt.jar -DgroupId=com.oracle -DartifactId=javaFX -Dversion=2.2 -Dpackaging=jar
```

Now you can simply import the project in Eclipse using Import -> Exisiting Maven Project and provide the plagiarism-detector folder as the root.

## Running the tests

From your IDE, once you have imported the project you can find all the tests in the src/test/java folder and run all or individually as JUnit tests. All tests are using JUnit 4.

You can run this from terminal/cmd using maven. Use the compile and test commands as follows:

```
: plagiarism-detector $mvn compile
......
....
..
: plagiarism-detector $mvn test
......
....
..
```

## Built With

* [ANTLR](http://www.antlr.org/) - The AST generator
* [Maven](https://maven.apache.org/) - Dependency Management
* [jbootx](https://github.com/dicolar/jbootx) - Bootstrap theme for JavaFx

## Versioning

We have released the first version of the application in the stand-alone-system folder. 

## Authors

**Team-107**

* **Student-109** - [Darshan Panse](https://github.ccs.neu.edu/darshanpanse)
* **Student-137** - [Samanjate Sood](https://samanjate.github.io/)
* **Student-153** - [Shail Shah](https://github.ccs.neu.edu/shail)
* **Student-146** - [Vaibhav Dave](https://github.ccs.neu.edu/vaibhavdave5)

See also the list of [contributors](https://github.ccs.neu.edu/cs5500/team-107/contributors) who participated in this project.

## Jenkins

You can find our build infomation [here](http://ec2-34-217-206-11.us-west-2.compute.amazonaws.com:8080)

## Acknowledgments

* Special thanks to the StackOverflow community for constantly resolving issues we faced while developing the project.

