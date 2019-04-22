package pl.sda.lottery.lotteries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.gov.Committee;
import pl.sda.audio.AudioPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sda.lottery.RandomNumberGenerator;

@Component
public class SpanishLottery implements Lottery {

    private final Logger logger = LoggerFactory.getLogger(SpanishLottery.class);

    private RandomNumberGenerator randomNumberGenerator;
    private AudioPlayer audioPlayer;
    private Committee committee;

    @Autowired
    public SpanishLottery(RandomNumberGenerator randomNumberGenerator, AudioPlayer audioPlayer, Committee committee) {
        logger.info("Creating " + getName());
        this.randomNumberGenerator = randomNumberGenerator;
        this.audioPlayer = audioPlayer;
        this.committee = committee;
    }

    @Override
    public int getTicketCost() {
        return 30;
    }

    public String getPrize() {
        int number = randomNumberGenerator.randomInt(10);
        if (number == 7 || number == 8) {
            String prize = number == 7 ? "SALSA" : "TEQUILA";
            audioPlayer.play(AudioPlayer.FANFARE);
            committee.notifyResolt(true);
            return prize;
        }
        committee.notifyResolt(false);
        return "[nada]";
    }
}
