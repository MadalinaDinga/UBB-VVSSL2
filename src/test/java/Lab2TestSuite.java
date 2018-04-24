import WBT.AddInvoiceTest;
import IntegrationT.SnowInvoicesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AddClientTest.class,
        AddInvoiceTest.class,
        SnowInvoicesTest.class,
})

public class Lab2TestSuite {
} 