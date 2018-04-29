import controller.ClientController;
import junit.framework.TestCase;
import org.junit.*;
import repository.ClientRepository;
import repository.DataManager;
import repository.InvoiceRepository;

public class AddClientTest extends TestCase{
    private static final String fileClient = "src/test/java/clientTest.txt";
    private static final String fileInvoice = "src/test/java/invoiceTest.txt";
    private DataManager dataManager = new DataManager(fileClient, fileInvoice);
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

//    @After
//    public void tearDown() {
//        //clean up
//        TestUtils.deleteFileContent(fileClient);
//    }

    @Test
    public void testAddValidClient() {
        System.out.println("\ntestAddValidClient");
        addSuccessful("mada", "doro");
    }

    @Test
    public void testEmptyNameFormatForClient() {
        System.out.println("\ntestInvalidNameFormatForClient");
        addUnsuccessful("", "doro");
    }

    @Test
    public void testEmptyAddressForClient() {
        System.out.println("\ntestEmptyAddressForClient");
        addUnsuccessful("mada", "");
    }

    @Test
    public void testInvalidCharactersInNameForClient() {
        System.out.println("\ntestInvalidNameLengthForClient");
        addUnsuccessful("mada2", "doro");
    }

    @Test
    public void testInvalidNameLengthForClient() {
        System.out.println("\ntestInvalidNameLengthForClient");
        String name="";
        for (int i=0; i<=256; i++)
            name.concat("a");
        addUnsuccessful(name, "doro");
    }

    @Test
    public void testInvalidCharactersInAddressForClient() {
        System.out.println("\ntestInvalidCharactersInAddressForClient");
        addUnsuccessful("mada", "dor:)");
    }

    @Test
    public void testAddClientDuplicateException() {
        System.out.println("\ntestAddDuplicateClient");
        ctrl.AddClient("mada","doro");
        addUnsuccessful("mada", "doro");
        TestUtils.deleteLastRecord(fileClient);
    }

    private void addSuccessful(String name, String address) {
        int clientsNoBefore = clientRepository.getSize();
        ctrl.AddClient(name, address);
        int clientsNoAfter = clientRepository.getSize();
        assertEquals(clientsNoBefore+1, clientsNoAfter);
        //clean up
        TestUtils.deleteLastRecord(fileClient);
    }

    private void addUnsuccessful(String name, String address) {
        int clientsNoBefore = clientRepository.getSize();
        ctrl.AddClient(name, address);
        int clientsNoAfter = clientRepository.getSize();
        assertEquals(clientsNoBefore, clientsNoAfter);
    }

    @Test
    public void testIncremental() {
        Assert.assertTrue(true);

    }
}
