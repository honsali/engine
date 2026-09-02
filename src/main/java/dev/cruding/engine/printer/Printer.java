package dev.cruding.engine.printer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public abstract class Printer {

    private final Context context;

    protected Printer(Context context) {
        this.context = Objects.requireNonNull(context, "Printer Context cannot be null");
    }

    protected final Context context() {
        return context;
    }

    protected String getBasePath() {
        return context.getBasePath();
    }

    protected Collection<Entity> entityList() {
        return context.getEntityList();
    }

    protected Collection<Page> pageList() {
        return context.getPageList();
    }


    protected ArrayList<Page> sortedPageList(dev.cruding.engine.gen.Module module) {
        ArrayList<Page> pageList = new ArrayList<>(context.getPageList(module));
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
                Files.write(filePath, normalizeContent(content).getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to write file: " + path, ex);
        }
    }

    protected void deleteFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to delete generated file: " + path, ex);
        }
    }

    private static String normalizeContent(String content) {
        String normalizedContent = content.replace("\r\n", "\n").replace('\r', '\n');
        int contentEnd = normalizedContent.length();
        while (contentEnd > 0 && normalizedContent.charAt(contentEnd - 1) == '\n') {
            contentEnd--;
        }
        return normalizedContent.substring(0, contentEnd) + "\n";
    }
}
