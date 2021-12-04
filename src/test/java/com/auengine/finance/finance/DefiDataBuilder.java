package com.auengine.finance.finance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefiDataBuilder
{
	@Test
	void generate() throws IOException
	{
		Calendar from=Calendar.getInstance();
		from.set(2021,06,01,0,0,1);
		Calendar to=Calendar.getInstance();
		to.set(2021,12,01,0,0,1);

		String[] exportList = new String[] {"BTC-USD", "ETH-USD", "LINK-USD", "AVAX-USD"
			,"BNB-USD","XRP-USD","UNI3-USD","MKR-USD","COMP-USD","USDTRY=X"
		};

		Map<String,Stock> map = YahooFinance.get(exportList,from,to, Interval.DAILY);

		File file=new File("C:\\Users\\muratay\\Desktop\\tez\\data.csv");
		TableUtil.export(TableUtil.loadData(map, Arrays.asList(exportList)),Arrays.asList(exportList),
			file,",");



	}


}
