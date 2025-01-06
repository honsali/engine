package dev.cruding.engine.printer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public abstract class Printer {

    protected String getBasePath() {
        return Context.getInstance().getBasePath();
    }

    protected Collection<Entite> entiteList() {
        return Context.getInstance().getEntiteList();
    }

    protected Collection<Page> pageList() {
        return Context.getInstance().getPageList();
    }

    protected void printFile(String content, String path) {
        printFile(content, path, true);
    }

    protected void printFile(String content, String path, boolean force) {
        try {
            Path filePath = Paths.get(path);
            Path parentDir = filePath.getParent();

            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            if (force || !Files.exists(filePath)) {
                Files.write(filePath, content.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}