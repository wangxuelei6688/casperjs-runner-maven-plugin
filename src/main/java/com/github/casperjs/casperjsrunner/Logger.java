package com.github.casperjs.casperjsrunner;

import org.apache.maven.plugin.logging.Log;

public class Logger {

    private final Log log;

    private final boolean verbose;

    public Logger(final Log log, final boolean verbose) {
        this.log = log;
        this.verbose = verbose;
    }

    public void debug(final CharSequence msg) {
        if (verbose) {
            log.info(msg);
        }
    }

    public void info(final CharSequence msg) {
        log.info(msg);
    }

    public void warn(final CharSequence msg) {
        log.warn(msg);
    }

    public void error(final CharSequence msg, final Throwable error) {
        log.error(msg, error);
    }
}
