package com.auengine.finance.finance;

import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CurrencyTests
{

	@Test
	void load_usdeur() throws IOException
	{
		FxQuote fxQuote = YahooFinance.getFx(FxSymbols.USDEUR);
		assertNotNull(fxQuote);
		BigDecimal fxPrice= fxQuote.getPrice();


	}
	@Test
	void load_usdtry() throws IOException
	{
		FxQuote fxQuote = YahooFinance.getFx("USDTRY=X");
		assertNotNull(fxQuote);
		BigDecimal fxPrice= fxQuote.getPrice();


	}
}
