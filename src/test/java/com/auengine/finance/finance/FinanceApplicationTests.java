package com.auengine.finance.finance;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
class FinanceApplicationTests {

	@Test
	void load_stock() throws IOException
	{
		Stock stock = YahooFinance.get("INTC");

		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal change = stock.getQuote().getChangeInPercent();
		BigDecimal peg = stock.getStats().getPeg();
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

		stock.print();
	}

	@Test
	void load_stock_refresh() throws IOException
	{

		Stock stock = YahooFinance.get("INTC");
		BigDecimal price = stock.getQuote(true).getPrice();

		stock.print();
	}

	@Test
	void load_btc() throws IOException
	{

		Stock stock = YahooFinance.
			get("BTC-USD");
		BigDecimal price = stock.getQuote(true).getPrice();

		stock.print();


	}

	@Test
	void load_btcusd_history() throws IOException
	{
		java.util.Calendar from=Calendar.getInstance();
		from.set(2020,01,01,0,0,1);

		java.util.Calendar to=Calendar.getInstance();
		from.set(2021,01,01,0,0,1);
		Stock stock = YahooFinance.get("BTC-USD",from,to,yahoofinance.histquotes.Interval.DAILY);
		List<HistoricalQuote> btcHistQuotes = stock.getHistory();
		for (HistoricalQuote q:btcHistQuotes )
		{
			assertNotNull( q.getOpen());
			assertNotNull( q.getClose());
			assertNotNull(q.getDate());
		}
	}

	@Test
	void load_usdtry_history() throws IOException
	{
		java.util.Calendar from=Calendar.getInstance();
		from.set(2020,01,01,0,0,1);

		java.util.Calendar to=Calendar.getInstance();
		to.set(2021,01,01,0,0,1);
		Stock stock = YahooFinance.get("USDTRY=X",from,to,yahoofinance.histquotes.Interval.DAILY);
		List<HistoricalQuote> btcHistQuotes = stock.getHistory();
		for (HistoricalQuote q:btcHistQuotes )
		{
			assertNotNull( q.getOpen());
			assertNotNull( q.getClose());
			assertNotNull(q.getDate());
		}
	}
}
