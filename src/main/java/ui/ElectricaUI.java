package ui;

import controller.ClientController;
import exceptions.BadFormatException;
import validations.UIValidations;

import java.util.Scanner;


public class ElectricaUI {
	public ClientController ctrl;
	Scanner in;
	
	public ElectricaUI(ClientController ctrl)
	{
		this.ctrl=ctrl;
		this.in=new Scanner(System.in);
	}
	
	public ClientController getCtrl()
	{
		return this.ctrl;
	}
	
	public Scanner getIn()
	{
		return this.in;
	}
	
	public void setCtrl(ClientController newCtrl)
	{
		this.ctrl=newCtrl;
	}
	
	public void setIn(Scanner newIn)
	{
		this.in=newIn;
	}
	
	public void printMenu()
	{
		String menu;
		menu="Electrica Admin Menu: \n";
		menu +="\t 1 - to add a new client; \n";
		menu +="\t 2 - to add a new index; \n";
		menu +="\t 3 - to list the current invoices (and possible penalties or pending payment) of the subscribers; \n";
		menu +="\t 0 - exit \n";
		
		System.out.println(menu);
	}



	public void Run()
	{
		printMenu();

		int cmd=in.nextInt();
		in.nextLine();
		
		while(cmd!=0)
		{
			if(cmd==1)
			{
				try {
					System.out.println("Add subscriber:");
					System.out.println("Enter name:");
					String name = in.nextLine();
					System.out.println("Enter address:");
					String address = in.nextLine();

					UIValidations.validateClient(name, address);

					if (ctrl.AddClient(name, address))
						System.out.println("Success!");
				}catch(BadFormatException e){
					System.out.println(e.getMessage());
				}
				
			}
			if(cmd==2)
			{
				try {
					System.out.println("Add invoice for a subscriber:");
					System.out.println("Enter name:");
					String name = in.nextLine();
					System.out.println("Enter address:");
					String address = in.nextLine();

					UIValidations.validateClient(name, address);

					System.out.println("Enter the YEAR:");
					String yearS = in.nextLine();
					int year = Integer.parseInt(yearS);
					UIValidations.isValidYear(year);

					System.out.println("Enter the MONTH:");
					String monthS = in.nextLine();
					int month = Integer.parseInt(monthS);
					UIValidations.isValidMonth(month);

					System.out.println("Enter toPay:");
					String sumToPayS = in.nextLine();
					float sumToPay=Float.parseFloat(sumToPayS);
					UIValidations.isValidSum(sumToPay);

					System.out.println("Enter paid:");
					String paidS = in.nextLine();
					float paid=Float.parseFloat(paidS);
					UIValidations.isValidSum(paid);

					if (ctrl.AddSubscriberInvoice(name, address, year, month, sumToPay, paid))
						System.out.println("Success!");

				}catch(NumberFormatException | BadFormatException e) {
					System.out.println("A number should be given as input for the previous field.");
				}
			}
			if(cmd==3)
			{
				System.out.println("Current invoices for subscriber:");
				
				System.out.println("Enter name:");
				String name = in.nextLine();
				System.out.println("Enter address:");
				String address = in.nextLine();
										
				// print the list of invoices
				String subscriberInvoices = ctrl.showInvoicesForClient(name, address);
				if (subscriberInvoices != null) {
					System.out.println(subscriberInvoices);
				}
			}

			printMenu();
			cmd=in.nextInt();
			in.nextLine();
		}
	}
}

