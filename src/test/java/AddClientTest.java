import controller.ClientController;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import repository.ClientRepository;
import repository.DataManager;
import repository.InvoiceRepository;

public class AddClientTest extends TestCase{
    private final String fileClient = "src/test/java/clientTest.txt";
    private final String fileInvoice = "src/test/java/invoiceTest.txt";
    private DataManager dataManager;
    private ClientRepository clientRepository;
    private InvoiceRepository invoiceRepository;
    private ClientController ctrl;

    @Before
    public void setUp() {
        dataManager = new DataManager(fileClient, fileInvoice);
        clientRepository = new ClientRepository(dataManager);
        invoiceRepository = new InvoiceRepository(dataManager);
        ctrl = new ClientController(clientRepository, invoiceRepository);
    }

    @Test
    public void testAddClientDuplicateException() {
        System.out.println("\ntestAddClientBusinessException");

        try {
            ctrl.AddClient("mada", "doro");
        } catch (AssertionFailedError e) {
            e.printStackTrace();
            assertTrue(e.getMessage().equals("Client already exists!"));
        }
    }

}
