package WBT;

import controller.ClientController;
import junit.framework.TestCase;
import org.junit.*;
import repository.ClientRepository;
import repository.DataManager;
import repository.InvoiceRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class AddInvoiceTest extends TestCase{
    private final static int ASCII_CODE_NEw_LINE = 10;
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
        deleteFileContent(fileInvoice);
    }

    @Test
    public void testWBT1() {
        System.out.println("\ntestAddValidInvoice");
        ctrl.AddClient("mada", "doro");
        addSuccessful("mada", "doro", 2010, 10, 100, 50);
    }

    @Test
    public void testWBT2() {
        System.out.println("\ntestInvoiceForUnExistingClient");
        addUnsuccessful("mada", "do", 2011, 10, 50,50);
    }

    @Test
    public void testAddExistingInvoice() {
        System.out.println("\ntestAddExistingInvoice");
        ctrl.AddClient("mada", "doro");
        addSuccessful("mada", "doro", 2010, 10, 100, 50);
        addUnsuccessful("mada", "doro", 2010, 10, 100, 50);
    }

    @Test
    public void testBadFormatToPay() {
        System.out.println("\ntestBadFormatToPay");
        addUnsuccessful("mada", "doro", 2010, 10, -10, 50);
    }

    @Test
    public void testBadFormatPaid() {
        System.out.println("\ntestBadFormatPaid");
        addUnsuccessful("mada", "doro", 2010, 10, 100, -50);
    }

    @Test
    public void testBadFormatMonth() {
        System.out.println("\ntestBadFormatMonth");
        addUnsuccessful("mada", "doro", 2010, 30, 100, 50);
    }

    @Test
    public void testBadFormatYear() {
        System.out.println("\ntestBadFormatYear");
        addUnsuccessful("mada", "doro", 0, 10, 100, 50);
    }

    private void addSuccessful(String name, String address, int year, int month, float toPay, float paid) {
        int clientsNoBefore = invoiceRepository.getSize();
        ctrl.AddSubscriberInvoice(name, address, year, month, toPay, paid);
        int clientsNoAfter = invoiceRepository.getSize();
        assertEquals(clientsNoBefore+1, clientsNoAfter);
        //clean up

        deleteLastRecord(fileInvoice);
        deleteLastRecord(fileClient);
    }

    private void addUnsuccessful(String name, String address, int year, int month, float toPay, float paid) {
        int clientsNoBefore = invoiceRepository.getSize();
        ctrl.AddSubscriberInvoice(name, address, year, month, toPay, paid);
        int clientsNoAfter = invoiceRepository.getSize();
        assertEquals(clientsNoBefore, clientsNoAfter);
    }

    private static void deleteFileContent(String filename){
        try {
            new PrintWriter(filename).close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void deleteLastRecord(String filename){
        try(RandomAccessFile f = new RandomAccessFile(filename, "rw")){
            long length = f.length() - 1;
            byte b;
            do {
                length -= 1;
                f.seek(length);
                b = f.readByte();
            } while(length>0 && b != ASCII_CODE_NEw_LINE);
            f.setLength(length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
