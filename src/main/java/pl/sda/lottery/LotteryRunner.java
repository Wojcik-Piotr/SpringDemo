package pl.sda.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gov.TaxOffice;
import pl.sda.audio.AudioPlayer;
import pl.sda.lottery.lotteries.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class LotteryRunner {

    private final RandomNumberGenerator randomNumberGenerator;
    private final AudioPlayer audioPlayer;
    private final TaxOffice taxOffice;

    private Scanner scanner = new Scanner(System.in);

    private List<Lottery> lotteries;

    @Autowired
    public LotteryRunner(List<Lottery> lotteries, RandomNumberGenerator randomNumberGenerator, AudioPlayer audioPlayer, TaxOffice taxOffice) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.audioPlayer = audioPlayer;
        this.taxOffice = taxOffice;
        this.lotteries=lotteries;
    }

    public void run() {
        while (true) {
            Lottery lottery = promptForLottery();
            int budget = promptForBudget();
            if (budget>500){
                taxOffice.notifyLargeTransaction("Large transaction", budget);
            }
            int cost = lottery.getTicketCost();
            while (cost <= budget) {
                String prize = lottery.getPrize();
                System.out.println("You've won: " + prize);
                budget -= cost;
            }
        }
    }

    private Lottery promptForLottery() {
        System.out.println("Which lottery do you want to play?");
        for (int i = 0; i < lotteries.size(); i++) {
            Lottery lottery = lotteries.get(i);
            System.out.printf("%d: %s @ %dPLN%n", i, lottery.getName(), lottery.getTicketCost());
        }
        int lotteryIndex = Integer.parseInt(scanner.nextLine());
        return lotteries.get(lotteryIndex);
    }

    private int promptForBudget() {
        System.out.println("How much money do you want to play (PLN)?");
        return Integer.parseInt(scanner.nextLine());
    }

}
