import logic.Parser;
import org.apache.log4j.Logger;

import java.io.IOException;

public class AppMain {
    private static final Logger logger = Logger.getLogger(AppMain.class);

    public static void main(String[] args) {
        logger.info("---------------------------------------------------");
        Parser parser = new Parser();
        try {
            parser.startPars();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}

