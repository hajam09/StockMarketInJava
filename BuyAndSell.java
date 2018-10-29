import java.util.Random;
public class BuyAndSell
{
	private double currentStockPrice;
	private double purchaseUnit;
	private double sellingUnit;
	private double userStock;

	BuyAndSell(double csp, double us)
	{
		//This is the constructor to initialise instance variables from subclass.
		currentStockPrice = csp;
		purchaseUnit = 0.0;
		sellingUnit = 0.0;
		userStock = us;
	}

	public double getCurrentStockPrice()
	{
		//Changes the price for stock market when it is called.
		Random random = new Random();
		int number = -2 + (int) (new Random().nextFloat() * (2 - -2));
		currentStockPrice = currentStockPrice + number;
		while(currentStockPrice<0)
		{
			number = -2 + (int) (new Random().nextFloat() * (2 - -2));
			currentStockPrice = currentStockPrice + number;
		}
		return currentStockPrice;
	}

	public void purchase(int unitsToBuy)
	{
		//This method is called to increase the user stock if he/she buys some item.
		userStock = userStock+unitsToBuy;
		System.out.println("It will cost you: $ "+getCurrentStockPrice()*unitsToBuy);
		return;
	}

	public void purchaseSet(double unitsToBuy)
	{
		userStock = userStock+unitsToBuy;
		System.out.println("It will cost you: $ "+getCurrentStockPrice()*unitsToBuy);
		return;

	}

	public void selling(int unitsToSell)
	{
		//This method is called to decrease the user stock if he/she sell some item.
		userStock = userStock-unitsToSell;
		System.out.println("You earn: $ "+getCurrentStockPrice()*unitsToSell);
		return;
	}

	public void sellingSet(double unitsToSell)
	{
		//This method is called to decrease the user stock if he/she sell some item.
		userStock = userStock-unitsToSell;
		System.out.println("You earn: $ "+getCurrentStockPrice()*unitsToSell);
		return;
	}

	public double getUserStockUnit()
	{
		return userStock;
	}

	public double returnCurrentStockPrice()
	{
		return currentStockPrice;
	}
}