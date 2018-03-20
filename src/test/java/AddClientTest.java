import controller.ClientController;
import junit.framework.TestCase;

import java.util.Scanner;

public class AddClientTest extends TestCase{
//    private ClientController ctrl;
//
//    AddClientTest(){
//        ctrl = new ClientController();
//    }

    public void testAddClientDuplicateException() {
        System.out.println("\ntestAddClientBusinessException");

        try {
            ClientController ctrl = new ClientController();
            ctrl.AddClient("mada", "doro");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(e.getMessage().equals("Client already exists!"));
        }
    }

    public void testAddClientUIException() {
        //TODO
    }

    public void testAddInvoiceBusinessException() {
        System.out.println("\ntestAddInvoiceBusinessException");

        try {
            ClientController ctrl = new ClientController();
            ctrl.AddSubscriberInvoice("mada", "doro", 2010, 10, 100, 90);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(e.getMessage().equals("Invoice already exists!"));
        }
    }

}
