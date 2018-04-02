import controller.ClientController;
import repository.ClientRepository;
import repository.DataManager;
import repository.InvoiceRepository;
import ui.ElectricaUI;

public class App {
	public static void main(String[] args) {
		final String fileClient = "client.txt";
		final String fileInvoice = "invoice.txt";

		DataManager dataManager = new DataManager(fileClient, fileInvoice);

		ClientRepository clientRepository = new ClientRepository(dataManager);
		InvoiceRepository invoiceRepository = new InvoiceRepository(dataManager);
		
		ClientController ctrl = new ClientController(clientRepository, invoiceRepository);
		
		ElectricaUI console = new ElectricaUI(ctrl);

		console.Run();
	}
}
