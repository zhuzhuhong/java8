package com.mo.java8.entity;

import java.util.Arrays;
import java.util.List;

public class Transaction {

	private Trader trader;
	private int year;
	private int value;

	public Transaction(Trader trader, int year, int value)
	{
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader(){ 
		return this.trader;
	}

	public int getYear(){
		return this.year;
	}

	public int getValue(){
		return this.value;
	}

	@Override
	public String toString(){
	    return "{" + this.trader + ", " +
	           "year: "+this.year+", " +
	           "value:" + this.value +"}";
	}

	public static final Trader raoul = new Trader("Raoul", "Cambridge");
	public static final Trader mario = new Trader("Mario","Milan");
	public static final Trader alan = new Trader("Alan","Cambridge");
	public static final Trader brian = new Trader("Brian","Cambridge");

	public static final List<Transaction> TRANSACTIONS = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)

	);
}