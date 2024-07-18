package dev.cruding.engine.gen.helper;

import java.util.Comparator;

public class ImpSorter implements Comparator<Imp> {

    public int compare(Imp imp1, Imp imp2) {
        String path1 = imp1.getPath();
        String path2 = imp2.getPath();

        boolean isPath1Hidden = path1.startsWith(".");
        boolean isPath2Hidden = path2.startsWith(".");

        if (isPath1Hidden && !isPath2Hidden) {
            return 1;
        } else if (!isPath1Hidden && isPath2Hidden) {
            return -1;
        } else {
            return path1.compareTo(path2);
        }
    }
}
