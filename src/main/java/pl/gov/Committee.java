package pl.gov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Committee {

    private final Logger logger = LoggerFactory.getLogger(Committee.class);

    public Committee(){
        this.logger.info("Creating Commite");
    }

    public void notifyResolt(boolean won) {
        String resolt = won ? "wygraną" : "przegraną";
        logger.info("Zaraportowano do komisji " + resolt);
    }
}
