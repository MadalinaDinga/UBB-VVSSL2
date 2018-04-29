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

    @After
    public void tearDown() {
        //clean up
        TestUtils.deleteFileContent(fileInvoice);
        TestUtils.deleteFileContent(fileClient);
    }

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

    //--------------------------BigBang------------------------------

    //integration testing: all A, B, C
    @Test
    public void testBigBang() {
        System.out.println("\ntestAddValidInvoice");
        testAddValidClientBB();
        testAddValidInvoice();
        testShowInvoices();
    }


    //unit test C
    @Test
    public void testShowInvoices() {
        System.out.println("\ntestShowInvoices");
        ctrl.AddClient("mada", "doro");
        ctrl.AddSubscriberInvoice("mada", "doro", 2010, 10, 100, 50);
        showSuccessful("mada", "doro");
    }

    //unit test B
    @Test
    public void testAddValidInvoice() {
        System.out.println("\ntestAddValidInvoice");
        ctrl.AddClient("mada", "doro");
        addSuccessfulInvoice("mada", "doro", 2010, 10, 100, 50);

        //clean up
        TestUtils.deleteLastRecord(fileClient);
        TestUtils.deleteLastRecord(fileInvoice);
    }

    //unit test A
    @Test
    public void testAddValidClientBB() {
        System.out.println("\ntestAddValidClient");
        addSuccessfulClient("mada", "doro");

        //clean up
        TestUtils.deleteLastRecord(fileClient);
    }

    private void addSuccessfulClient(String name, String address) {
        int clientsNoBefore = clientRepository.getSize();
        ctrl.AddClient(name, address);
        int clientsNoAfter = clientRepository.getSize();
        Assert.assertEquals(clientsNoBefore+1, clientsNoAfter);
    }

    private void addSuccessfulInvoice(String name, String address, int year, int month, float toPay, float paid) {
        int clientsNoBefore = invoiceRepository.getSize();
        ctrl.AddSubscriberInvoice(name, address, year, month, toPay, paid);
        int clientsNoAfter = invoiceRepository.getSize();
        Assert.assertEquals(clientsNoBefore+1, clientsNoAfter);
    }

    private void showSuccessful(String name, String address) {
        String invoicesForClient = ctrl.showInvoicesForClient(name, address);
        Assert.assertTrue(invoicesForClient.length() > 0);
    }

    //--------------------------Incremental--------------------------

    //integration test C
    @Test
    public void testShowInvoicesIncremental() {
        System.out.println("\ntestShowInvoices");
        testAddValidInvoiceIncremental();
        ctrl.AddClient("mada", "doro");
        ctrl.AddSubscriberInvoice("mada", "doro", 2010, 10, 100, 50);
        showSuccessfulInc("mada", "doro");
    }

    // integration test B
    @Test
    public void testAddValidInvoiceIncremental() {
        System.out.println("\ntestAddValidInvoice");
        //test A
        testAddValidClientIncremental();
        //test B
        ctrl.AddClient("mada", "doro");
        addSuccessfulInvoiceInc("mada", "doro", 2010, 10, 100, 50);

        //clean up
        TestUtils.deleteLastRecord(fileClient);
        TestUtils.deleteLastRecord(fileInvoice);
    }

    //unit test A
    @Test
    public void testAddValidClientIncremental() {
        System.out.println("\ntestAddValidClient");
        addSuccessfulClientInc("mada", "doro");

        //clean up
        TestUtils.deleteLastRecord(fileClient);
    }

    private void addSuccessfulClientInc(String name, String address) {
        int clientsNoBefore = clientRepository.getSize();
        ctrl.AddClient(name, address);
        int clientsNoAfter = clientRepository.getSize();
        Assert.assertEquals(clientsNoBefore+1, clientsNoAfter);
    }

    private void addSuccessfulInvoiceInc(String name, String address, int year, int month, float toPay, float paid) {
        int clientsNoBefore = invoiceRepository.getSize();
        ctrl.AddSubscriberInvoice(name, address, year, month, toPay, paid);
        int clientsNoAfter = invoiceRepository.getSize();
        Assert.assertEquals(clientsNoBefore+1, clientsNoAfter);
    }

    private void showSuccessfulInc(String name, String address) {
        String invoicesForClient = ctrl.showInvoicesForClient(name, address);
        Assert.assertTrue(invoicesForClient.length() > 0);
    }


}
