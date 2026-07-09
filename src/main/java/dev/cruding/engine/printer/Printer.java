package dev.cruding.engine.printer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public abstract class Printer {

    protected String getBasePath() {
        return Context.getInstance().getBasePath();
    }

    protected Collection<Entity> entityList() {
        return Context.getInstance().getEntityList();
    }

    protected Collection<Page> pageList() {
        return Context.getInstance().getPageList();
    }


    protected ArrayList<Page> sortedPageList(dev.cruding.engine.gen.Module module) {
        ArrayList<Page> pageList = new ArrayList<>(Context.getInstance().getPageList(module));
        pageList.sort(Page.ORDER_BY_ACTION_AND_ENTITY);
        return pageList;
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
                String normalizedContent = content.endsWith("\n") ? content : content + "\n";
                if (Files.exists(filePath)) {
                    String existingContent = Files.readString(filePath, StandardCharsets.UTF_8);
                    if (existingContent.contains("\r\n")) {
                        normalizedContent = normalizedContent.replace("\n", "\r\n");
                    }
                }
                Files.write(filePath, normalizedContent.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to write file: " + path, ex);
        }
    }
}
