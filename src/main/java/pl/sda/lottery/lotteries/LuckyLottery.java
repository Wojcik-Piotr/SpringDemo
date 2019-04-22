package pl.sda.lottery.lotteries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.gov.Committee;
import pl.gov.TaxOffice;
import pl.sda.audio.AudioPlayer;
import pl.sda.lottery.RandomNumberGenerator;

@Component
public class LuckyLottery implements Lottery {

    private final Logger logger = LoggerFactory.getLogger(SpanishLottery.class);

    private RandomNumberGenerator randomNumberGenerator;
    private TaxOffice taxOffice;
    private Committee committee;


    @Autowired
    public LuckyLottery(RandomNumberGenerator randomNumberGenerator, TaxOffice taxOffice, Committee committee) {
        logger.info("Creating " + getName());
        this.randomNumberGenerator = randomNumberGenerator;
        this.taxOffice = taxOffice;
        this.committee = committee;
    }

    @Override
    public int getTicketCost() {
        return 250;
    }

    @Override
    public String getPrize() {

        if (randomNumberGenerator.randomInt(100_000) == 7) {
            String prize = "Island";
            taxOffice.notifyPrizeWon(prize, 1000_000);
            committee.notifyResolt(true);
            return prize;
        }
        committee.notifyResolt(false);
        return null;
    }
}
