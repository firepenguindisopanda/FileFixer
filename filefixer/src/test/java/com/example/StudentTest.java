package com.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentTest {

    @Test
    void student_instantiation() {
        Student student = new Student("601725", "John Doe", "81304376", true);

        assertThat(student.getPID()).isEqualTo("601725");
        assertThat(student.getName()).isEqualTo("John Doe");
        assertThat(student.getID()).isEqualTo("81304376");
        assertThat(student.getAttendanceStatus()).isTrue();
    }

    @Test
    void student_fullConstructor() {
        Student student = new Student("601725", "John Doe", "81304376",
            "john@test.com", "Submitted", "85", "100", false, "2024-01-01");

        assertThat(student.getEmail()).isEqualTo("john@test.com");
        assertThat(student.getStatus()).isEqualTo("Submitted");
        assertThat(student.getGrade()).isEqualTo("85");
        assertThat(student.getMaxGrade()).isEqualTo("100");
        assertThat(student.isGradeCanBeChanged()).isFalse();
        assertThat(student.getLastModified()).isEqualTo("2024-01-01");
    }

    @Test
    void setAttendance_changesStatus() {
        Student student = new Student("601725", "John Doe", "81304376", true);
        assertThat(student.getAttendanceStatus()).isTrue();

        student.setAttendance(false);
        assertThat(student.getAttendanceStatus()).isFalse();
    }

    @Test
    void toString_format() {
        Student student = new Student("601725", "John Doe", "81304376", true);

        assertThat(student.toString())
            .contains("601725")
            .contains("John Doe")
            .contains("81304376");
    }

    @Test
    void constructor_throwsOnNullPid() {
        assertThatThrownBy(() -> new Student(null, "John Doe", "81304376", true))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_throwsOnBlankName() {
        assertThatThrownBy(() -> new Student("601725", "", "81304376", true))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_throwsOnNullId() {
        assertThatThrownBy(() -> new Student("601725", "John Doe", null, true))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
