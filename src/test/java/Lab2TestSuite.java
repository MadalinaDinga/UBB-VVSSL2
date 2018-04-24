import WBT.AddInvoiceTest;
import IntegrationT.BBT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AddClientTest.class,
        AddInvoiceTest.class,
        BBT.class,
})

public class Lab2TestSuite {
} 