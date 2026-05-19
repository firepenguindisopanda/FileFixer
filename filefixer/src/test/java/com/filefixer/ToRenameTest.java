package com.filefixer;

import com.filefixer.model.ToRename;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToRenameTest {

    @Test
    void toRename_instantiation() {
        ToRename toRename = new ToRename("601725", "John Doe", "81304376", true);

        assertThat(toRename.getPID()).isEqualTo("601725");
        assertThat(toRename.getName()).isEqualTo("John Doe");
        assertThat(toRename.getID()).isEqualTo("81304376");
        assertThat(toRename.getAttendanceStatus()).isTrue();
        assertThat(toRename.getFileCount()).isZero();
    }

    @Test
    void addToList_addsFilename() {
        ToRename toRename = new ToRename("601725", "John Doe", "81304376", true);

        toRename.addToList("myoriginalfilename.pdf");

        assertThat(toRename.getFileCount()).isOne();
        assertThat(toRename.getToBeRenamedList()).contains("myoriginalfilename.pdf");
    }

    @Test
    void addToList_ignoresNullAndBlank() {
        ToRename toRename = new ToRename("601725", "John Doe", "81304376", true);

        toRename.addToList(null);
        toRename.addToList("  ");
        toRename.addToList("valid.pdf");

        assertThat(toRename.getFileCount()).isOne();
    }

    @Test
    void getToBeRenamedList_returnsUnmodifiableList() {
        ToRename toRename = new ToRename("601725", "John Doe", "81304376", true);
        toRename.addToList("file.pdf");

        assertThat(toRename.getToBeRenamedList()).isUnmodifiable();
    }

    @Test
    void setAttendance_changesStatus() {
        ToRename toRename = new ToRename("601725", "John Doe", "81304376", true);
        toRename.setAttendance(false);
        assertThat(toRename.getAttendanceStatus()).isFalse();
    }
}
