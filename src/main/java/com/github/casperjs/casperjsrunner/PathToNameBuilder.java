package com.github.casperjs.casperjsrunner;

import static java.io.File.separator;

import java.io.File;

public class PathToNameBuilder {

    private static final String REPLACEMENT_CHAR = "_";

    private PathToNameBuilder() {
        // only used as static
    }

    public static String buildName(final File rootDir, final File path) {
        String result = path.getAbsolutePath();
        String rootPath = rootDir.getAbsolutePath();
        if (!rootPath.endsWith(separator)) {
            rootPath += separator;
        }

        if (!result.contains(rootPath)) {
            throw new IllegalArgumentException(path + " should be a child of " + rootDir);
        }

        result = result.replace(rootPath, "");
        // for Linux paths
        result = result.replaceAll("/", REPLACEMENT_CHAR);
        // for Windows paths
        result = result.replaceAll("\\\\", REPLACEMENT_CHAR);
        result = result.replaceAll("\\.", REPLACEMENT_CHAR);

        return result;
    }

}
