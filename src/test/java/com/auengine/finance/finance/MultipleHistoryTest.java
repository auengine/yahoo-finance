package com.auengine.finance.finance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MultipleHistoryTest
{
	@Test
	void multiple() throws IOException
	{
		Calendar from=Calendar.getInstance();
		from.set(2021,06,01,0,0,1);
		Calendar to=Calendar.getInstance();
		to.set(2021,12,01,0,0,1);

		String[] symbols_crypo_and_fx = new String[] {"BTC-USD", "ETH-USD", "USDTRY=X", "LINK-USD", "LINK-ETH","AVAX-USD"
			,"BNB-USD","XRP-USD","UNI3-USD","MKR-USD","COMP-USD"
		};

		Map<String,Stock> map = YahooFinance.get(symbols_crypo_and_fx,from,to, Interval.DAILY);
		assertNotEquals(0,map.entrySet().size());

		for (Map.Entry<String,Stock> e:map.entrySet() )
		{
			String name =e.getKey();
			Stock stock=e.getValue();

			List<HistoricalQuote> btcHistQuotes = stock.getHistory();
			for (HistoricalQuote q:btcHistQuotes )
			{
				assertNotNull( q.getOpen());
				assertNotNull( q.getClose());
				assertNotNull(q.getDate());
			}
		}

	}


	@Test
	void multiple_history_count_for_cypto() throws IOException
	{
		Calendar from=Calendar.getInstance();
		from.set(2021,07,01,0,0,1);
		Calendar to=Calendar.getInstance();
		to.set(2021,12,01,0,0,1);

		String[] crytoList = new String[] {"BTC-USD", "ETH-USD",
			 "LINK-USD", "LINK-ETH","AVAX-USD"
			,"BNB-USD","XRP-USD","UNI3-USD","MKR-USD","COMP-USD"
		};




		Map<String,Stock> map = YahooFinance.get(crytoList,from,to, Interval.DAILY);
		assertNotEquals(0,map.entrySet().size());
		int historyCounts=-1;
		for (Map.Entry<String,Stock> e:map.entrySet() )
		{
			Stock stock=e.getValue();
			if(historyCounts<0){
				historyCounts=stock.getHistory().size();
			}
			assertEquals(historyCounts,stock.getHistory().size());

		}

	}
}
