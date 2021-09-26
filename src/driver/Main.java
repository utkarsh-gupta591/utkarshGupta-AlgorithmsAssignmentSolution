package driver;

import java.util.*;
import company.Company;

class Sort
{
	private void merge(double[] a, int low, int mid, int high)
	{
        int n1 = mid - low + 1;
        int n2 = high - mid;
  
        double L[] = new double[n1];
        double R[] = new double[n2];
  
        for (int i = 0; i < n1; i++)
            L[i] = a[low + i];
        for (int j = 0; j < n2; j++)
            R[j] = a[mid + 1 + j];
  
        int i = 0, j = 0;
        
        int k = low;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                a[k] = L[i];
                i++;
            }
            else
            {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1)
        {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            a[k] = R[j];
            j++;
            k++;
        }
	}
	
	public void mergeSort(double[] a, int low, int high)
	{
		if(low==high) return;
		int mid=(low+high)/2;
		mergeSort(a, low, mid);
		mergeSort(a, mid+1, high);
		merge(a, low, mid, high);
	}
}
public class Main {
	
	private static LinkedList<Company> companies=new LinkedList<Company>();
	
	private static void displayStockPrices(String order)
	{
		int len=companies.size();
		double[] stockPrices=new double[len];
		int i=0;
		for(Company company: companies)
		{
			stockPrices[i]=company.stockPrice;
			i++;
		}
		Sort sort=new Sort();

		//code to sort stock prices in ascending order
		if(order.equals("ascending"))
		{
			sort.mergeSort(stockPrices, 0, len-1);
		}

		//code to sort stock prices in descending order
		if(order.equals("descending"))
		{
			sort.mergeSort(stockPrices, 0, len-1);
			double b;
			for(int l=0, m=len-1;l<len/2;l++, m--)
			{
				b=stockPrices[l];
				stockPrices[l]=stockPrices[m];
				stockPrices[m]=b;
			}
		}

		//code to display stock prices
		for(i=0;i<len;i++)
		{
			if(i>0) System.out.print(", ");
			System.out.print(stockPrices[i]);
		}
		System.out.println();
	}
	
	// function to display the total no of companies for which stock price rose today
	private static void numberOfCompaniesForWhichStockPricesRose()
	{
		int count=0;
		for(Company company: companies)
		{
			if(company.riseInStockPrice==true) count++;
		}
		System.out.println("Total no of companies whose stock price rose today "+count);
	}

	// function to display the total no of companies for which stock price declined today
	private static void numberOfCompaniesForWhichStockPricesDeclined()
	{
		int count=0;
		for(Company company: companies)
		{
			if(company.riseInStockPrice==false) count++;
		}
		System.out.println("Total no of companies whose stock price declined today "+count);
	}

	// function for finding whether a particular stock price present or not
	private static void searchStockPrice(double keyValue)
	{
		boolean present=false;
		for(Company company: companies)
		{
			if(company.stockPrice==keyValue)
			{
				present=true;
				break;
			}
		}
		if(present==true) System.out.println("Stock of value "+keyValue+" is present");
		else System.out.println("Stock of value "+keyValue+" is not present");
	}

	public static void main(String g[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number of companies: ");
		int N=sc.nextInt();
		double stockPrice;
		boolean riseInStockPrice;
		for(int i=1;i<=N;i++)
		{
			System.out.println("Enter the stock price of the company "+i);
			stockPrice=sc.nextDouble();
			System.out.println("Whether company's stock price rose today compare to yesterday?");
			riseInStockPrice=sc.nextBoolean();
			Company company=new Company(stockPrice, riseInStockPrice);
			companies.add(company);
		}
		int input;
		boolean exit=false;
		while(true)
		{
			System.out.println("---------------------------------------------------");
			System.out.println("Enter the operation that you want to perform");
			System.out.println("1. Display the companies stock prices in ascending order");
			System.out.println("2. Display the companies stock prices in descending order");
			System.out.println("3. Display the total number of companies for which stock prices rose today");
			System.out.println("4. Display the total number of companies for which stock prices declined today");
			System.out.println("5. Search a specific stock price");
			System.out.println("6. press 0 to exit");
			System.out.println("---------------------------------------------------");
			input=sc.nextInt();
			switch(input){
			case 1:
				displayStockPrices("ascending");
				break;
			case 2:
				displayStockPrices("descending");
				break;
			case 3:
				numberOfCompaniesForWhichStockPricesRose();
				break;
			case 4:
				numberOfCompaniesForWhichStockPricesDeclined();
				break;
			case 5:
				System.out.println("Enter the key value");
				searchStockPrice(sc.nextDouble());
				break;
			case 0:
				exit=true;
				break;
			default:
				System.out.println("Enter a valid number");
				break;
			}
			if(exit==true)
			{
				System.out.println("Exited successfully");
				break;
			}
		}
		sc.close();
	}
}
