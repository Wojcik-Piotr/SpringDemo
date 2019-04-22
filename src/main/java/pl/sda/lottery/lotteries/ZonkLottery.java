package pl.sda.lottery.lotteries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.gov.Committee;
import pl.sda.audio.AudioPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ZonkLottery implements Lottery {

    private final Logger logger = LoggerFactory.getLogger(ZonkLottery.class);

    private AudioPlayer audioPlayer;
    private Committee committee;

    @Autowired
    public ZonkLottery(AudioPlayer audioPlayer, Committee committee) {
        logger.info("Creating " + getName());
        this.audioPlayer = audioPlayer;
        this.committee = committee;
    }

    @Override
    public int getTicketCost() {
        return 5;
    }

    public String getPrize() {
        audioPlayer.play(AudioPlayer.CAT);
        committee.notifyResolt(false);
        return "[zonk]";
    }
}
