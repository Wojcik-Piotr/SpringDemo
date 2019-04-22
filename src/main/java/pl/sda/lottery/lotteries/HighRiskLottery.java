package pl.sda.lottery.lotteries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.gov.Committee;
import pl.sda.audio.AudioPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.gov.TaxOffice;
import pl.sda.lottery.RandomNumberGenerator;

@Component
public class HighRiskLottery implements Lottery {

    private final Logger logger = LoggerFactory.getLogger(HighRiskLottery.class);

    private RandomNumberGenerator randomNumberGenerator;
    private AudioPlayer audioPlayer;
    private TaxOffice taxOffice;
    private Committee committee;

    @Autowired
    public HighRiskLottery(RandomNumberGenerator randomNumberGenerator, AudioPlayer audioPlayer, TaxOffice taxOffice, Committee committee) {
        logger.info("Creating " + getName());
        this.randomNumberGenerator = randomNumberGenerator;
        this.audioPlayer = audioPlayer;
        this.taxOffice = taxOffice;
        this.committee = committee;
    }

    @Override
    public int getTicketCost() {
        return 100;
    }

    public String getPrize() {
        if (randomNumberGenerator.randomInt(20_000) == 7) {
            String prize = "CAR";
            audioPlayer.play(AudioPlayer.FANFARE);
            taxOffice.notifyPrizeWon(prize, 100_000);
            committee.notifyResolt(true);
            return prize;
        }
        committee.notifyResolt(false);
        return "[nothing]";
    }
}
