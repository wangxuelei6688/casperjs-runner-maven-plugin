package com.github.casperjs.casperjsrunner;

import static com.github.casperjs.casperjsrunner.IOUtils.closeQuietly;
import static com.github.casperjs.casperjsrunner.LogUtils.getLogger;

import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.plugin.MojoFailureException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CasperJsVersionRetriever {

    private CasperJsVersionRetriever() {
        // only used as static
    }

    public static ArtifactVersion retrieveVersion(final String casperRuntime, final boolean verbose) throws MojoFailureException {
        return retrieveVersion(casperRuntime, verbose, Runtime.getRuntime());
    }

    public static ArtifactVersion retrieveVersion(final String casperRuntime, final boolean verbose, final Runtime runtime)
            throws MojoFailureException {
        getLogger().debug("Check CasperJS version");
        InputStream stream = null;
        try {
            final Process child = runtime.exec(casperRuntime + " --version");
            stream = child.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            final String version = reader.readLine();
            return new DefaultArtifactVersion(version);
        } catch (final IOException e) {
            if (verbose) {
                getLogger().error("Could not run CasperJS command", e);
            }
            throw new MojoFailureException("Unable to determine casperJS version");
        } finally {
            closeQuietly(stream);
        }
    }

}
