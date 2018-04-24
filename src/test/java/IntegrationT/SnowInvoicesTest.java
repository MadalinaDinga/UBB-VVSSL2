package IntegrationT;

import controller.ClientController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.ClientRepository;
import repository.DataManager;
import repository.InvoiceRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class SnowInvoicesTest {
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
    public void testIT1() {
        System.out.println("\ntestAddValidInvoice");
        ctrl.AddClient("mada", "doro");
        ctrl.AddSubscriberInvoice("mada", "doro", 2010, 10, 100, 50);
        testSuccessful("mada", "doro");
    }

    private void testSuccessful(String name, String address) {
        String invoicesForClient = ctrl.showInvoicesForClient(name, address);
        Assert.assertTrue(invoicesForClient.length() > 0);

        //clean up
        deleteLastRecord(fileInvoice);
        deleteLastRecord(fileClient);
    }

    private void testUnsuccessful(String name, String address, int year, int month, float toPay, float paid) {
        ctrl.AddSubscriberInvoice(name, address, year, month, toPay, paid);
        //assertEquals(clientsNoBefore, clientsNoAfter);

        //clean up
        deleteLastRecord(fileInvoice);
        deleteLastRecord(fileClient);
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
