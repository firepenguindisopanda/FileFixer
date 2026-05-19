# FileFixer
This is the original code base for the application FileFixer

# Description
```text
The myElearning content management platform provides students with a medium to submit files such as PDFs or word documents. 
Submissions can be individually or batch  downloaded by lecturers for grading and annotations, and then re-uploaded to 
provide feedback to the relevant students. 
Files submitted on the myElearning platform must conform to a specific naming convention in order to be accepted by the platform. 
A common problem which arises when students submit files with names which do not conform to the myElearning naming convention, 
is that the platform rejects submissions by the lecturer when they’re attempting to reupload the graded file. 

The lecturer is then faced with the tedious task of individually renaming the files to meet the platform’s naming convention.
To mitigate this problem, the FileFixer java application will be able to locate and 
process zero or more PDF files of a particular directory that are to be renamed. 
This will be done by accepting a CSV file containing students marks and feedback information as well as a 
collection of marked up assignment files named according to a specific convention (convention 1). 
The application will extract the relevant information from the CSV file and use this information to rename the 
collection of assignment files according to a specific naming convention (convention 2). 

The FileFixer application will also produce a list of missing submission files based on the CSV file’s student list. 
In addition, a test suite will be developed for evaluation of the application’s performance. 
The application code will be documented on a GitHub repository.

The FileFixer application will require the user, in this case the lecturer, to place the CSV file and
marked up assignment files in a specific folder called “filesToRename” and then execute the main class. 

After execution, the renamed files will be placed in a nested folder named “renamedFiles”, within the “filesToRename” folder. 
The successful outcome of the FileFixer application will be measured by the collection of renamed assignment 
files conforming to a specific convention, 
(convention 2), that is acceptable by myElearning for upload.

The application is intended to be completed by November 17, 2021. 
Additionally, the application will be developed using two design patterns namely, Composite and Iterator, 
which conform to SOLID design principles and will be evaluated using a Test-Driven Design.
The team members Nicholas Smith, Jeremy De Freitas, Lorenzo La Caille and Nickal Winter 
will use their understanding of the SOLID design principles and Design patterns to produce a flexible, 
resilient and easy to maintain project.
```


# Installation
Download repo as zip and extract it. Open the entire folder in your IDE (preferably VScode).
Run the program in your IDE and follow on screen instructions or type HELP.

# Usage

**How to run:**
* To run the program, place the program folder, _FileFixer_, on the Desktop of your machine.
* Open the program folder in the IDE of your choice and run the main class, _FileFixer.java_

# Members
- Jeremy De Freitas jeremydefreitas@hotmail.com
- Lorenzo La Caille lorenzo.lacaille@hotmail.com
- Nickal Winter nickwinter01@gmail.com
- Nicholas Smith nicosmith.smith3@gmail.com

# Developer Setup

## Prerequisites

| Tool | Minimum Version | Check |
|------|----------------|-------|
| JDK | 11 (recommended: 17 or 21) | `java -version` |
| Maven | 3.6+ | `mvn -version` |
| Git | 2.x | `git --version` |

Install JDK and Maven if you don't have them:

```bash
# Ubuntu/Debian
sudo apt install openjdk-17-jdk maven

# macOS (Homebrew)
brew install openjdk@17 maven

# Windows (Scoop)
scoop install openjdk17 maven
```

## Quick Start

```bash
# 1. Clone the repository
git clone <repo-url>
cd FileFixer

# 2. Verify the project compiles
cd filefixer
mvn clean compile

# 3. Run all tests
mvn test

# 4. Run the application
mvn exec:java -Dexec.mainClass="com.example.FileFixer"

# Or run directly from the IDE by executing FileFixer.java
```

## Useful Maven Commands

| Command | What it does |
|---------|-------------|
| `mvn clean compile` | Clean and compile source code |
| `mvn test` | Run all unit tests |
| `mvn clean package` | Build the JAR file (output in `target/`) |
| `mvn clean install` | Build and install to local Maven repository |
| `mvn exec:java -Dexec.mainClass="com.example.FileFixer"` | Run the application |
| `mvn dependency:tree` | Show full dependency tree |
| `mvn dependency:analyze` | Find unused or undeclared dependencies |
| `mvn javafx:run` | Launch the desktop UI |
| `mvn clean package` | Build the fat JAR (12MB) |
| `mvn test` | Run all tests |
| `java -jar target/filefixer-1.0-SNAPSHOT.jar` | Run the CLI from the built JAR |

## Adding Dependencies to `pom.xml`

Open `filefixer/pom.xml` and add a `<dependency>` block inside the `<dependencies>` section:

```xml
<dependencies>
    <!-- Existing JUnit dependency -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.10.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Add new dependencies below this line -->
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.9</version>
    </dependency>
</dependencies>
```

Then refresh:

```bash
# Download the new dependency and verify it resolves
mvn clean compile
```

```xml
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.9</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.12</version>
</dependency>
<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <version>3.25.3</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.16.1</version>
</dependency>
```

---

### Prerequisites
- **Java 17+** — [Download](https://adoptium.net/) if you don't have it
- **Maven 3.6+** — for building from source (not needed to run the JAR)

### Run the Desktop App
```bash
mvn javafx:run
```

### Run the CLI
```bash
java -jar target/filefixer-1.0-SNAPSHOT.jar
```

## How It Works

1. Place your CSV grading export and PDF assignment files in a folder
2. Open the app and select that folder (or press Enter in CLI mode)
3. The app matches each PDF to a student by name, ID, or PID
4. Renamed copies are placed in a `renamedFiles/` subfolder
5. A `missingSubmissions.txt` report lists unmatched files and missing students

### Naming Conventions Supported

| Type | Example Input | Output |
|------|--------------|--------|
| Simple | `John_Doe_assignsubmission_file_A1.pdf` | `John Doe_601725_assignsubmission_file_A1.pdf` |
| Standard | `1409121490-602637_John_Doe_601683_A1.pdf` | `John Doe_601725_assignsubmission_file_A1.pdf` |
| Complex | `Assignment 1 John Doe.pdf` | `John Doe_601725_assignsubmission_file_Assignment 1.pdf` |

The CLI and desktop UI share the same service layer. No business logic duplication.

## Distribution

### For Users
Download the `filefixer-1.0-SNAPSHOT.jar` (12MB) and run:
```bash
java -jar filefixer-1.0-SNAPSHOT.jar
```

### For the Desktop UI
Users need JavaFX installed system-wide, or developers can run:
```bash
mvn javafx:run
```

### Building a Native Installer (Optional)
For a standalone `.deb`/`.dmg`/`.exe` without requiring Java:
```bash
mvn clean package
jpackage --input target/ --name FileFixer --main-jar filefixer-1.0-SNAPSHOT.jar --main-class com.filefixer.ui.FileFixerApp --type deb

jpackage --input target/ --name FileFixer --main-jar filefixer-1.0-SNAPSHOT.jar --main-class com.filefixer.ui.FileFixerApp --type exe
```
Requires `jpackage` (included in JDK 17+) and platform-specific tools (`dpkg-deb` on Linux).


## Contributing

1. Run `mvn test` before submitting changes
2. Keep changes focused. one feature or fix per PR
