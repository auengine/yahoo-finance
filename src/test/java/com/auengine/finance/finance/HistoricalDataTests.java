package com.auengine.finance.finance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HistoricalDataTests
{
	@Test
	void load_btcusd_history() throws IOException
	{
		Calendar from=Calendar.getInstance();
		from.set(2020,01,01,0,0,1);

		Calendar to=Calendar.getInstance();
		to.set(2021,01,01,0,0,1);
		Stock stock = YahooFinance.get("BTC-USD",from,to, Interval.DAILY);
		List<HistoricalQuote> btcHistQuotes = stock.getHistory();
		for (HistoricalQuote q:btcHistQuotes )
		{
			assertNotNull( q.getOpen());
			assertNotNull( q.getClose());
			assertNotNull(q.getDate());
		}
	}

	@Test
	void load_ethusd_history() throws IOException
	{
		Calendar from=Calendar.getInstance();
		from.set(2020,01,01,0,0,1);

		Calendar to=Calendar.getInstance();
		to.set(2021,01,01,0,0,1);
		Stock stock = YahooFinance.get("ETH-USD",from,to, Interval.DAILY);
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
		Calendar from=Calendar.getInstance();
		from.set(2020,01,01,0,0,1);

		Calendar to=Calendar.getInstance();
		from.set(2021,01,01,0,0,1);
		Stock stock = YahooFinance.get("USDTRY=X",from,to, Interval.DAILY);
		List<HistoricalQuote> btcHistQuotes = stock.getHistory();
		for (HistoricalQuote q:btcHistQuotes )
		{
			assertNotNull( q.getOpen());
			assertNotNull( q.getClose());
			assertNotNull(q.getDate());
		}
	}
}
