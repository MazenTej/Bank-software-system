package banking;

import org.junit.jupiter.api.BeforeEach;

public class TransferProcessorTest {
    TransferProcessor transferProcessor;
    Bank bank;

    String ID1 = "12345678";
    String ID2 = "10101010";

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferProcessor = new TransferProcessor(bank);
    }


}
