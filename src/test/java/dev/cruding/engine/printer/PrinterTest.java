package dev.cruding.engine.printer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.flow.JavaFlow;

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

    @Test
    void wrapsLongJavaMethodDeclarations() {
        JavaFlow flow = new JavaFlow();

        flow.addMethodDeclaration(
                4,
                "public void update(",
                List.of(
                        "String matricule",
                        "String nom",
                        "String prenom",
                        "String adresse",
                        "String fonction",
                        "String description"));

        String generated = flow.toString();
        assertTrue(generated.contains("\n    public void update(\n"));
        assertTrue(generated.contains("\n            String description) {"));
        assertTrue(generated.lines().allMatch(line -> line.length() <= 120));
    }

    private static final class TestPrinter extends Printer {

        private void write(String content, Path path) {
            printFile(content, path.toString());
        }
    }
}
