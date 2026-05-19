package com.filefixer;

import com.filefixer.service.FileCollection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

class FileCollectionTest {

    @Test
    void setFiles_populatesCollection() {
        FileCollection fc = new FileCollection();
        fc.setFiles(Arrays.asList("a.pdf", "b.pdf", "c.pdf"));

        assertThat(fc.size()).isEqualTo(3);
    }

    @Test
    void iterator_returnsAllFiles() {
        FileCollection fc = new FileCollection();
        fc.setFiles(Arrays.asList("a.pdf", "b.pdf"));

        Iterator<String> it = fc.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo("a.pdf");
        assertThat(it.next()).isEqualTo("b.pdf");
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void forEach_works() {
        FileCollection fc = new FileCollection();
        fc.setFiles(Arrays.asList("x.pdf", "y.pdf", "z.pdf"));

        java.util.List<String> collected = new java.util.ArrayList<>();
        for (String f : fc) {
            collected.add(f);
        }

        assertThat(collected).containsExactly("x.pdf", "y.pdf", "z.pdf");
    }

    @Test
    void setFiles_clearsPrevious() {
        FileCollection fc = new FileCollection();
        fc.setFiles(Arrays.asList("old.pdf"));
        fc.setFiles(Arrays.asList("new.pdf"));

        assertThat(fc.size()).isOne();
        assertThat(fc).containsExactly("new.pdf");
    }

    @Test
    void setFiles_handlesNull() {
        FileCollection fc = new FileCollection();
        fc.setFiles(Arrays.asList("a.pdf"));
        fc.setFiles(null);

        assertThat(fc.size()).isZero();
    }
}
