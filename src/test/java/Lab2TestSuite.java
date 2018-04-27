import WBT.AddInvoiceTest;
import IntegrationT.BigBang;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AddClientTest.class,
        AddInvoiceTest.class,
        BigBang.class,
        Incremental.class
})

public class Lab2TestSuite {
} 