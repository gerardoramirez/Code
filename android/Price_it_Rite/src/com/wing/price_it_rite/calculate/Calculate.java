package com.wing.price_it_rite.calculate;

import java.text.DecimalFormat;

public class Calculate {
	double cost;
	double price;
	double percentage;
	double profit;
	
	public Calculate(double cost, double percentage) {
		this.cost = cost;
		this.percentage = percentage/100;
	}
	
	public double getCost() {
		return cost;
	}

	public double getPrice() {
		return price;
	}

	public double getPercentage() {
		return percentage;
	}

	public double getProfit() {
		return profit;
	}

	public double calculatePrice() {
		return (cost * percentage) + cost;
	}
	
	public double calculateProfit() {
		DecimalFormat df = new DecimalFormat("0.00");  
		double newPrice = (calculatePrice() - cost);
		String value = df.format(newPrice);
		return Double.parseDouble(value);
	}
	
	public static void main(String args[]) {
		System.out.println("[Calculate] Testing....");
		Calculate cp = new Calculate(25.40, 25);
		
		System.out.println("[Calculate] cost: " + cp.getCost());
		System.out.println("[Calculate] percentage: " + cp.getPercentage() );

		System.out.println("[Calculate] price: " + cp.calculatePrice() );
		System.out.println("[Calculate] profit: " + cp.calculateProfit());
	}

}
