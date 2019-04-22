package pl.sda.lottery.lotteries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.gov.Committee;

@Component
public class PenLottery implements Lottery {

    private Committee committee;

    @Autowired
    public PenLottery(Committee committee){
        this.committee=committee;
    }
    @Override
    public int getTicketCost() {
        return 10;
    }

    @Override
    public String getPrize() {
        committee.notifyResolt(true);
        return "Pen";
    }
}
