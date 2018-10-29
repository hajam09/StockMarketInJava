import java.util.Random;
public class StockMarket extends BuyAndSell
{
	private String stockName;
	private double currentStockUnit;

	StockMarket(String sn, double csu, double csp ,double us)
	{
		//This is the constructor to initialise instance variables in this class and superclass
		super(csp, us);
		stockName = sn;
		currentStockUnit = csu;
	}

	public void productInfo()
	{
		//This method prints out the stock name.
		//Prints outs the current stock price as it fluctuates evertime it is called.
		//Prints out the current stock unit as the user may purchase and sell.
		String productName = "Product name is: ";
		String stockPrice = "Current stock price is: $ ";
		String stockUnit = "Current stock unit left is: ";

		System.out.println(productName+getStockName());
		System.out.println(stockPrice+getCurrentStockPrice());
		System.out.println(stockUnit+getCurrentStockUnit());
		System.out.println();
		return;
	}

	public double getCurrentStockPrice()
	{
		//Changes the price for stock market when it is called.
		double currentStockPrice = returnCurrentStockPrice();
		Random random = new Random();
		double number = -3.5 + (3.5 - -3.5) * random.nextDouble();
		currentStockPrice = currentStockPrice + number;
		while(currentStockPrice<0)
		{
			number = -3.5 + (3.5 - -3.5) * random.nextDouble();
			currentStockPrice = currentStockPrice + number;
		}
		//currentStockPrice = super.getCurrentStockPrice();
		return currentStockPrice;
	}

	public String getStockName()
	{
		return stockName;
	}

	public double getCurrentStockUnit()
	{
		return currentStockUnit;
	}

	public void purchase(double unitsToBuy)
	{
		//This method is called to invoke the super class method purchase to change user stock.
		//This method is called to decrease the stock unit by unitsToBuy.
		super.purchaseSet(unitsToBuy);
		currentStockUnit = currentStockUnit - unitsToBuy;
		return;
	}

	public void selling(double unitsToSell)
	{
		//This method is called to invoke the super class method selling to change user stock.
		//This method is called to increase the stock unit by unitsToSell.
		super.sellingSet(unitsToSell);
		currentStockUnit = currentStockUnit + unitsToSell;
		return;
	}
}