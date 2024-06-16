package com.heybcat.docker.pull;

import com.heybcat.docker.pull.core.DockerPull;
import com.heybcat.docker.pull.web.DockerPullWebApplication;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

/**
 * @author Fetters
 */
public class CommandStartup {

    private static final String WEB_FLAG = "--web";
    static Logger log = Logger.getLogger(CommandStartup.class.getName());

    public static void main(String[] args)
        throws URISyntaxException, IOException, InterruptedException {
        handleCommand(args);
    }

    private static void handleCommand(String[] args)
        throws URISyntaxException, IOException, InterruptedException {
        if (args.length == 0) {
            logUsage();
            return;
        }

        if (args.length == 3) {
            DockerPull.pull(args[0], args[1], Integer.parseInt(args[2]));
            return;
        }

        if (args.length == 1) {
            if (WEB_FLAG.equals(args[0])) {
                DockerPullWebApplication.run();
            } else {
                DockerPull.pull(args[0], null, null);
            }
            return;
        }
        logUsage();
    }

    private static void logUsage() {
        log.info("Usage: java -jar docker-pull.jar <image> <proxyUrl> <proxyPort>");
        log.info("       java -jar docker-pull.jar --web");
    }

}
