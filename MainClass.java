import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.*;
public class MainClass extends JFrame
{
	//ArrayList for storing individual items from the text file.
	private static Scanner x;
	private static ArrayList<String> itemName = new ArrayList<String>();
	private static ArrayList<Double> itemStock = new ArrayList<Double>();
	private static ArrayList<Double> itemPrice = new ArrayList<Double>();
	private static ArrayList<Integer> userStock = new ArrayList<Integer>();
	public BuyAndSell[] items1;
	public BuyAndSell[] items2;
	public BuyAndSell[] items3;
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JButton stockButton;
	private JButton cryptoButton;
	private JButton estateButton;
	private JButton exitButton;


	public static void main(String[] args)
	{
		//Opentext file and store everything from it into the arrayList.
		openFile();

		while(x.hasNext())
		{
			itemName.add(x.next());
			itemStock.add(Double.parseDouble(x.next()));
			itemPrice.add(Double.parseDouble(x.next()));
			userStock.add(Integer.parseInt(x.next()));
		}

		closeFile();

		//Create objects for stockmarket, cryptocurrency and estateproperties and initialize the constructor with values from the arrayList.
		BuyAndSell s1 = new StockMarket(itemName.get(0), itemStock.get(0), itemPrice.get(0), userStock.get(0));
		BuyAndSell s2 = new StockMarket(itemName.get(1), itemStock.get(1), itemPrice.get(1), userStock.get(1));
		BuyAndSell s3 = new StockMarket(itemName.get(2), itemStock.get(2), itemPrice.get(2), userStock.get(2));

		BuyAndSell[] items1 = {s1, s2, s3};

		BuyAndSell c1 = new Cryptocurrency(itemName.get(3), itemStock.get(3), itemPrice.get(3), userStock.get(3));
		BuyAndSell c2 = new Cryptocurrency(itemName.get(4), itemStock.get(4), itemPrice.get(4), userStock.get(4));
		BuyAndSell c3 = new Cryptocurrency(itemName.get(5), itemStock.get(5), itemPrice.get(5), userStock.get(5));

		BuyAndSell[] items2 = {c1, c2, c3};

		BuyAndSell e1 = new Estates(itemName.get(6), itemStock.get(6), itemPrice.get(6), userStock.get(6));
		BuyAndSell e2 = new Estates(itemName.get(7), itemStock.get(7), itemPrice.get(7), userStock.get(7));
		BuyAndSell e3 = new Estates(itemName.get(8), itemStock.get(8), itemPrice.get(8), userStock.get(8));

		BuyAndSell[] items3 = {e1, e2, e3};

		//Ask the user if they want to invest in stocks or cryptocurrency.
		new MainClass(items1, items2, items3);

		/*Scanner input = new Scanner(System.in);
		//System.out.println("Do you want to go for Stock Market (stock) or Cryptocurrency (crypto) or Estates Properties (estate) or quit?");
		//String choice = input.nextLine();
		String choice = JOptionPane.showInputDialog("Do you want to go for Stock Market (stock) or Cryptocurrency (crypto) or Estates Properties (estate) or quit?");
		while(!choice.equals("quit"))
		{
			if(choice.equals("stock"))
			{
				stock(items1);
			}
			else if(choice.equals("crypto"))
			{
				crypto(items2);
			}
			else if(choice.equals("estate"))
			{
				estate(items3);
			}
			//System.out.println("Do you want to go for Stock Market (stock) or Cryptocurrency (crypto) or Estates Properties (estate) or quit?");
			//choice = input.nextLine();
			choice = JOptionPane.showInputDialog("Do you want to go for Stock Market (stock) or Cryptocurrency (crypto) or Estates Properties (estate) or quit?");
		}*/
	}

	MainClass(BuyAndSell[] items1, BuyAndSell[] items2, BuyAndSell[] items3)
	{
		this.items1 = items1;
		this.items2 = items2;
		this.items3 = items3;
		gui(items1, items2, items3);
	}

	public void gui(BuyAndSell[] items1, BuyAndSell[] items2, BuyAndSell[] items3)
	{
		frame = new JFrame("Trading");
		frame.setVisible(true);
		frame.setSize(600, 120);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		label = new JLabel("Do you want to go for Stock Market or Cryptocurrency or Estates Properties or quit?");

		stockButton = new JButton("Stock Market");
		cryptoButton = new JButton("Cryptocurrency");
		estateButton = new JButton("Estate Properties");
		exitButton = new JButton("Exit");

		ActionListener stockActList = new MyActionListener(this, stockButton, cryptoButton, estateButton, exitButton);

		stockButton.addActionListener(stockActList);
		cryptoButton.addActionListener(stockActList);
		estateButton.addActionListener(stockActList);
		exitButton.addActionListener(stockActList);

		panel.add(label);
		panel.add(stockButton);
		panel.add(cryptoButton);
		panel.add(estateButton);
		panel.add(exitButton);

		frame.add(panel);
	}

	public static void openFile()
	{
		//if file is found it will open, else print couldn't find.
		try
		{
			x = new Scanner(new File("Statistics.txt"));
		}
		catch(Exception e)
		{
			System.out.println("Couldn't find the file.");
		}
	}

	public static void closeFile()
	{
		x.close();
	}

	public static void stock(BuyAndSell[] items1, BuyAndSell[] items2, BuyAndSell[] items3)
	{
		//If user chooses stocks then it display what stocks are available

		for(int i = 0; i<items1.length; i++)
		{
			((StockMarket)items1[i]).productInfo();
		}

		//Ask the user if they want to sell some stocks or buy more stocks and send them to appropriate method.
		//Or they can go back and choose cryptocurrency or estate.
		Scanner input = new Scanner(System.in);
		//System.out.println("Do you want to buy or sell shares or back?");
		//String choice = input.nextLine();
		String choice = JOptionPane.showInputDialog("Do you want to buy or sell shares or back?");

		while(!choice.equals("back"))
		{
			if(choice.equals("buy"))
				{
					stockBuyMethod(items1);
				}
			else if(choice.equals("sell"))
				{
					stockSellMethod(items1);
				}
			//System.out.println("Do you want to buy or sell shares or back?");
			//choice = input.nextLine();
			choice = JOptionPane.showInputDialog("Do you want to buy or sell shares or back?");
		}
		if(choice.equals("back"))
		{
			new MainClass(items1, items2, items3);
		}
	}

	public static void crypto(BuyAndSell[] items1, BuyAndSell[] items2, BuyAndSell[] items3)
	{
		//If user chooses crypto then it display what currencies are available.

		for(int i = 0; i<items2.length; i++)
		{
			((Cryptocurrency)items2[i]).productInfo();
		}

		//Ask the user if they want to sell some currencies or buy more currencies and send them to appropriate method.
		//Or they can go back and choose stock market or estate.
		Scanner input = new Scanner(System.in);
		//System.out.println("Do you want to buy or sell shares or back?");
		//String choice = input.nextLine();
		String choice = JOptionPane.showInputDialog("Do you want to buy or sell shares or back?");

		while(!choice.equals("back"))
		{
			if(choice.equals("buy"))
				{
					cryptoBuyMethod(items2);
				}
			else if(choice.equals("sell"))
				{
					cryptoSellMethod(items2);
				}
			//System.out.println("Do you want to buy or sell shares or back?");
			//choice = input.nextLine();
			choice = JOptionPane.showInputDialog("Do you want to buy or sell shares or back?");
		}
		if(choice.equals("back"))
		{
			new MainClass(items1, items2, items3);
		}
	}

	public static void estate(BuyAndSell[] items1, BuyAndSell[] items2, BuyAndSell[] items3)
	{
		//If user chooses estate then it display what type of properties are available.

		for(int i = 0; i<items3.length; i++)
		{
			((Estates)items3[i]).productInfo();

		}

		//Ask the user if they want to sell some properties or buy more properties and send them to appropriate method.
		//Or they can go back and choose stock market or cryptocurrency.
		Scanner input = new Scanner(System.in);
		//System.out.println("Do you want to buy or sell shares or back?");
		//String choice = input.nextLine();
		String choice = JOptionPane.showInputDialog("Do you want to buy or sell shares or back?");

		while(!choice.equals("back"))
		{
			if(choice.equals("buy"))
				{
					estateBuyMethod(items3);
				}
			else if(choice.equals("sell"))
				{
					estateSellMethod(items3);
				}
			//System.out.println("Do you want to buy or sell shares or back?");
			//choice = input.nextLine();
			choice = JOptionPane.showInputDialog("Do you want to buy or sell shares or back?");
		}
		if(choice.equals("back"))
		{
			new MainClass(items1, items2, items3);
		}
	}

	public static void stockBuyMethod(BuyAndSell[] items)
	{
		//This is the method for buying stocks.
		for(int i = 0; i<items.length; i++)
		{
			((StockMarket)items[i]).productInfo();
		}
		//Prints out the stocks and asks the user to buy from the list.
		//System.out.println("What do you want to buy from list above");
		Scanner input = new Scanner(System.in);
		//String choice = input.nextLine();
		String choice = JOptionPane.showInputDialog("What do you want to buy from list?");

		//Using for loop to find out which item name the user has input.
		for(int j = 0; j<items.length; j++)
		{
			//if(choice.equals(items[j].getStockName()))
			if(choice.equals(((StockMarket)items[j]).getStockName()))
			{
				//System.out.println("How much "+ ((StockMarket)items[j]).getStockName()+" do you want to buy?");
				//Try and catch statement, if the user inputs string instead of double or int.
				try
				{
					//double unitstoBuy = input.nextDouble();
					double unitstoBuy = Double.parseDouble(JOptionPane.showInputDialog("How much "+ ((StockMarket)items[j]).getStockName()+" do you want to buy?"));
					//Keep asking the user to enter a value if they are trying to purchase more stocks than available.
					while(unitstoBuy>((StockMarket)items[j]).getCurrentStockUnit())
					{
						//System.out.println("Not enough stocks left, enter again: ");
						//unitstoBuy = input.nextDouble();
						unitstoBuy = Double.parseDouble(JOptionPane.showInputDialog("Not enough stocks left, enter again: "));
					}

					//Calls the purchase method and passes the unitsToBuy variable to amend the stock units.
					((StockMarket)items[j]).purchase(unitstoBuy);


					//Prints out the stock details includng it's current price and current stock unit.
					for(int k = 0; k<items.length; k++)
					{
						((StockMarket)items[k]).productInfo();
					}
					break;
				}
				catch(Exception e)
				{
					//If there are any exception, prints out a message and calls this method again to ask the user again.
					System.out.println("Cannot enter String for purchasing units. So enter an Integer or double.");
					stockBuyMethod(items);
				}
			}
		}
		return;
	}

	public static void stockSellMethod(BuyAndSell[] items)
	{
		//This is the method for selling stocks.
		Scanner input = new Scanner(System.in);
		System.out.println("Below are the Stock shares you own.");
		for(int i = 0; i<items.length; i++)
		{
			System.out.println(((StockMarket)items[i]).getStockName()+" = "+((StockMarket)items[i]).getUserStockUnit());
		}
		//Prints out the stocks and asks the user to sell from the list.
		//System.out.println("What do you want to sell from list above");
		//String choice = input.nextLine();
		String choice = JOptionPane.showInputDialog("What do you want to sell from list?");

		//Using for loop to find out which item name the user has input.
		for(int j = 0; j<items.length; j++)
		{
			if(choice.equals(((StockMarket)items[j]).getStockName()))
			{
				//System.out.println("How much "+ ((StockMarket)items[j]).getStockName()+" do you want to sell?");
				//Try and catch statement, if the user inputs string instead of double or int.
				try
				{
					//double unitstoSell = input.nextDouble();
					double unitstoSell = Double.parseDouble(JOptionPane.showInputDialog("How much "+ ((StockMarket)items[j]).getStockName()+" do you want to sell?"));
					//Keep asking the user to enter a value if they are trying to sell more stocks than they have.
					while(unitstoSell>((StockMarket)items[j]).getUserStockUnit() && unitstoSell !=0)
					{
						//System.out.println("Not enough stocks left, enter again: ");
						//unitstoSell = input.nextDouble();
						unitstoSell = Double.parseDouble(JOptionPane.showInputDialog("Not enough stocks left, enter again: "));
					}

					//Calls the selling method and passes the unitsToSell variable to amend the stock units.
					((StockMarket)items[j]).selling(unitstoSell);

					//Prints out the stock details includng it's current price and current stock unit.
					for(int k = 0; k<items.length; k++)
					{
						((StockMarket)items[k]).productInfo();
					}
					break;
				}
				catch(Exception e)
				{
					//If there are any exception, prints out a message and calls this method again to ask the user again.
					System.out.println("Cannot enter String for selling units. So enter an Integer or double.");
					stockSellMethod(items);
				}
			}
		}
		return;
	}

	public static void cryptoBuyMethod(BuyAndSell[] items)
	{
		//This is the method for buying cryptocurrencies.
		for(int i = 0; i<items.length; i++)
		{
			((Cryptocurrency)items[i]).productInfo();
		}

		//Prints out the currencies and asks the user to buy from the list.
		//System.out.println("What do you want to buy from list above");
		//Scanner input = new Scanner(System.in);
		String choice = JOptionPane.showInputDialog("What do you want to buy from list?");

		//Using for loop to find out which currency the user wants to buy.
		for(int j = 0; j<items.length; j++)
		{
			if(choice.equals(((Cryptocurrency)items[j]).getStockName()))
			{
				//Try and catch statement, if the user inputs string instead of double or int.
				try
				{
					//System.out.println("How much "+ ((Cryptocurrency)items[j]).getStockName()+" do you want to buy?");
					//double unitstoBuy = input.nextDouble();
					double unitstoBuy = Double.parseDouble(JOptionPane.showInputDialog("How much "+ ((Cryptocurrency)items[j]).getStockName()+" do you want to buy?"));
					//Keep asking the user to enter a value if they are trying to purchase more currency than available.
					while(unitstoBuy>((Cryptocurrency)items[j]).getCurrentStockUnit())
					{
						//System.out.println("Not enough stocks left, enter again: ");
						unitstoBuy = Double.parseDouble(JOptionPane.showInputDialog("Not enough stocks left, enter again: "));
					}

					((Cryptocurrency)items[j]).purchase(unitstoBuy);

					//Prints out the currency details includng it's current price and current stock unit.
					for(int k = 0; k<items.length; k++)
					{
						((Cryptocurrency)items[k]).productInfo();
					}
					break;
				}
				catch(Exception e)
				{
					//If there are any exception, prints out a message and calls this method again to ask the user again.
					System.out.println("Cannot enter String for purchasing units. So enter an Integer or double.");
					cryptoBuyMethod(items);
				}
			}
		}
		return;
	}

	public static void cryptoSellMethod(BuyAndSell[] items)
	{
		//This is the method for selling cryptocurrencies.
		Scanner input = new Scanner(System.in);
		System.out.println("Below are the Stock shares you own.");
		for(int i = 0; i<items.length; i++)
		{
			System.out.println(((Cryptocurrency)items[i]).getStockName()+" = "+((Cryptocurrency)items[i]).getUserStockUnit());
		}
		//Prints out the currencies and asks the user to sell from the list.
		//System.out.println("What do you want to sell from list above");
		String choice = JOptionPane.showInputDialog("What do you want to sell from list?");

		//Using for loop to find out which currency the user wants to sell.
		for(int j = 0; j<items.length; j++)
		{
			if(choice.equals(((Cryptocurrency)items[j]).getStockName()))
			{
				//System.out.println("How much "+ ((Cryptocurrency)items[j]).getStockName()+" do you want to sell?");
				//Try and catch statement, if the user inputs string instead of double or int.
				try
				{
					//double unitstoSell = input.nextDouble();
					double unitstoSell = Double.parseDouble(JOptionPane.showInputDialog("How much "+ ((Cryptocurrency)items[j]).getStockName()+" do you want to sell?"));
					//Keep asking the user to enter a value if they are trying to sell more currency than they have.
					while(unitstoSell>((Cryptocurrency)items[j]).getUserStockUnit() && unitstoSell !=0)
					{
						//System.out.println("Not enough stocks left, enter again: ");
						//unitstoSell = input.nextDouble();
						unitstoSell = Double.parseDouble(JOptionPane.showInputDialog("Not enough stocks left, enter again: "));
					}

					//Calls the selling method and passes the unitsToSell variable to amend the currency stock units.
					((Cryptocurrency)items[j]).selling(unitstoSell);

					//Prints out the currency details includng it's current price and current stock unit.
					for(int k = 0; k<items.length; k++)
					{
						((Cryptocurrency)items[k]).productInfo();
					}
					break;
				}
				catch(Exception e)
				{
					//If there are any exception, prints out a message and calls this method again to ask the user again.
					System.out.println("Cannot enter String for selling units. So enter an Integer or double.");
					cryptoSellMethod(items);
				}
			}
		}
		return;
	}

	public static void estateBuyMethod(BuyAndSell[] items)
	{
		//This is the method for buying estate properties.
		for(int i = 0; i<items.length; i++)
		{
			((Estates)items[i]).productInfo();
		}
		//Prints out the currencies and asks the user to buy from the list.

		//System.out.println("What do you want to buy from list above");
		Scanner input = new Scanner(System.in);
		String choice = JOptionPane.showInputDialog("What do you want to buy from list?");

		//Using for loop to find out which property type the user wants to buy.
		for(int j = 0; j<items.length; j++)
		{
			if(choice.equals(((Estates)items[j]).getStockName()))
			{
				//Try and catch statement, if the user inputs string instead of double or int.
				try
				{
					//System.out.println("How much "+ ((Estates)items[j]).getStockName()+" do you want to buy?");
					//double unitstoBuy = input.nextDouble();
					double unitstoBuy = Double.parseDouble(JOptionPane.showInputDialog("How much "+ ((Estates)items[j]).getStockName()+" do you want to buy?"));
					//Keep asking the user to enter a value if they are trying to purchase more currency than available.
					while(unitstoBuy>((Estates)items[j]).getCurrentStockUnit())
					{
						//System.out.println("Not enough stocks left, enter again: ");
						//unitstoBuy = input.nextDouble();
						unitstoBuy = Double.parseDouble(JOptionPane.showInputDialog("Not enough stocks left, enter again: "));
					}

					//Calls the purchase method and passes the unitsToBuy variable to amend the currency stock units.
					((Estates)items[j]).purchase(unitstoBuy);


					//Prints out the currency details includng it's current price and current stock unit.
					for(int k = 0; k<items.length; k++)
					{
						((Estates)items[k]).productInfo();
					}
					break;
				}
				catch(Exception e)
				{
					//If there are any exception, prints out a message and calls this method again to ask the user again.
					System.out.println("Cannot enter String for purchasing units. So enter an Integer or double.");
					estateBuyMethod(items);
				}
			}
		}
		return;
	}

	public static void estateSellMethod(BuyAndSell[] items)
	{
		//This is the method for selling estate properties.
		Scanner input = new Scanner(System.in);
		System.out.println("Below are the Stock shares you own.");
		for(int i = 0; i<items.length; i++)
		{
			System.out.println(((Estates)items[i]).getStockName()+" = "+((Estates)items[i]).getUserStockUnit());
		}
		//Prints out the property types and asks the user to sell from the list.
		//System.out.println("What do you want to sell from list above");
		//String choice = input.nextLine();
		String choice = JOptionPane.showInputDialog("What do you want to sell from list?");

		//Using for loop to find out which property type the user wants to sell.
		for(int j = 0; j<items.length; j++)
		{
			if(choice.equals(((Estates)items[j]).getStockName()))
			{
				//System.out.println("How much "+ ((Estates)items[j]).getStockName()+" do you want to sell?");
				//Try and catch statement, if the user inputs string instead of double or int.
				try
				{
					//double unitstoSell = input.nextDouble();
					double unitstoSell = Double.parseDouble(JOptionPane.showInputDialog("How much "+ ((Estates)items[j]).getStockName()+" do you want to sell?"));
					//Keep asking the user to enter a value if they are trying to sell more currency than they have.
					while(unitstoSell>((Estates)items[j]).getUserStockUnit() && unitstoSell !=0)
					{
						System.out.println("Not enough stocks left, enter again: ");
						unitstoSell = Double.parseDouble(JOptionPane.showInputDialog("Not enough stocks left, enter again: "));
					}

					//Calls the selling method and passes the unitsToSell variable to amend the currency stock units.
					((Estates)items[j]).selling(unitstoSell);

					//Prints out the currency details includng it's current price and current stock unit.
					for(int k = 0; k<items.length; k++)
					{
						((Estates)items[k]).productInfo();
					}
					break;
				}
				catch(Exception e)
				{
					//If there are any exception, prints out a message and calls this method again to ask the user again.
					System.out.println("Cannot enter String for selling units. So enter an Integer or double.");
					estateSellMethod(items);
				}
			}
		}
		return;
	}
}

class MyActionListener implements ActionListener
{
	MainClass mainFrame;
	private JButton stockButton;
	private JButton cryptoButton;
	private JButton estateButton;
	private JButton exitButton;

	public MyActionListener(MainClass frame, JButton s, JButton c, JButton e, JButton ex)
	{
		mainFrame = frame;
		stockButton = s;
		cryptoButton = c;
		estateButton = e;
		exitButton = ex;
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == stockButton)
		{
			mainFrame.dispose();
			mainFrame.stock(mainFrame.items1, mainFrame.items2, mainFrame.items3);
		}
		else if(e.getSource() == cryptoButton)
		{
			mainFrame.dispose();
			mainFrame.crypto(mainFrame.items1, mainFrame.items2, mainFrame.items3);
		}
		else if(e.getSource() == estateButton)
		{
			mainFrame.dispose();
			mainFrame.estate(mainFrame.items1, mainFrame.items2, mainFrame.items3);
		}
		else if(e.getSource() == exitButton)
		{
			System.exit(0);
		}
	}
}