package dev.cruding.engine.printer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PrinterTest {

    @TempDir
    Path tempDir;

    @Test
    void writesLfWithExactlyOneFinalNewline() throws IOException {
        Path output = tempDir.resolve("generated.ts");
        Files.writeString(output, "previous\r\ncontent\r\n", StandardCharsets.UTF_8);

        new TestPrinter().write("first\r\nsecond\rthird\n\n", output);

        assertArrayEquals(
                "first\nsecond\nthird\n".getBytes(StandardCharsets.UTF_8),
                Files.readAllBytes(output));
    }

    private static final class TestPrinter extends Printer {

        private void write(String content, Path path) {
            printFile(content, path.toString());
        }
    }
}
