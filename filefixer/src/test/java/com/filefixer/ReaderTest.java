package com.filefixer;

import com.filefixer.exception.FileFixerException;
import com.filefixer.model.Student;
import com.filefixer.model.ToRename;
import com.filefixer.service.Reader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReaderTest {

    @TempDir
    Path tempDir;

    private Reader reader;

    void setup() {
        reader = new Reader(tempDir);
    }

    @Test
    void loadCsvData_parsesValidCsv() throws Exception {
        setup();
        String csv = "Identifier,Full name,ID number,Email,Status,Grade,Max Grade,Can Change,Last Modified\n"
            + "Participant 601725,John Doe,81304376,john@test.com,Submitted,85,100,false,2024-01-01\n"
            + "Participant 601726,Jane Smith,81304377,jane@test.com,Submitted,90,100,false,2024-01-01\n";
        Files.writeString(tempDir.resolve("grades.csv"), csv);

        List<Student> students = reader.loadCsvData();

        assertThat(students).hasSize(2);
        assertThat(students.get(0).getPID()).isEqualTo("601725");
        assertThat(students.get(0).getName()).isEqualTo("John Doe");
        assertThat(students.get(0).getID()).isEqualTo("81304376");
        assertThat(students.get(1).getPID()).isEqualTo("601726");
    }

    @Test
    void loadCsvData_throwsWhenNoCsv() {
        setup();
        assertThatThrownBy(() -> reader.loadCsvData())
            .isInstanceOf(FileFixerException.class)
            .hasMessageContaining("No CSV files found");
    }

    @Test
    void loadCsvData_throwsWhenMultipleCsv() throws Exception {
        setup();
        Files.writeString(tempDir.resolve("a.csv"), "header\nrow1\n");
        Files.writeString(tempDir.resolve("b.csv"), "header\nrow2\n");

        assertThatThrownBy(() -> reader.loadCsvData())
            .isInstanceOf(FileFixerException.class)
            .hasMessageContaining("Multiple CSV files");
    }

    @Test
    void loadCsvData_skipsMalformedRows() throws Exception {
        setup();
        String csv = "Identifier,Full name,ID number\n"
            + "Participant 601725,John Doe,81304376\n"
            + "badrow\n"
            + "Participant 601726,Jane Smith,81304377\n";
        Files.writeString(tempDir.resolve("grades.csv"), csv);

        List<Student> students = reader.loadCsvData();

        assertThat(students).hasSize(2);
    }

    @Test
    void loadDirectoryFiles_returnsAllFiles() throws Exception {
        setup();
        Files.writeString(tempDir.resolve("file1.pdf"), "dummy");
        Files.writeString(tempDir.resolve("file2.pdf"), "dummy");
        Files.writeString(tempDir.resolve("grades.csv"), "header\n");

        ToRename result = reader.loadDirectoryFiles();

        assertThat(result.getFileCount()).isEqualTo(3);
        assertThat(result.getToBeRenamedList()).containsExactlyInAnyOrder("file1.pdf", "file2.pdf", "grades.csv");
    }

    @Test
    void loadDirectoryFiles_throwsWhenEmpty() throws Exception {
        setup();
        assertThatThrownBy(() -> reader.loadDirectoryFiles())
            .isInstanceOf(FileFixerException.class)
            .hasMessageContaining("No files found");
    }

    @Test
    void loadDirectoryFiles_createsDirectoryIfMissing() {
        Path emptyDir = tempDir.resolve("nonexistent");
        Reader r = new Reader(emptyDir);

        assertThatThrownBy(() -> r.loadDirectoryFiles())
            .isInstanceOf(FileFixerException.class)
            .hasMessageContaining("No files found");

        assertThat(Files.exists(emptyDir)).isTrue();
    }
}
