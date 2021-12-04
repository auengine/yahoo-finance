package com.auengine.finance.finance;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TableUtil
{

    public static Map<Calendar, HistoricalQuote[]> loadData(Map<String, Stock> map, List<String> columnList) throws IOException
    {
        Map<Calendar, HistoricalQuote[]> resultData = new HashMap<>();

        for (Map.Entry<String, Stock> e : map.entrySet())
        {
            String name = e.getKey();
            Stock stock = e.getValue();

            int index = columnList.indexOf(name);
            if (index == -1)
            {
                continue;
            }

            List<HistoricalQuote> btcHistQuotes = stock.getHistory();
            for (HistoricalQuote q : btcHistQuotes)
            {
                if (!resultData.containsKey(q.getDate()))
                {
                    resultData.put(q.getDate(), new HistoricalQuote[columnList.size()]);
                }
                resultData.get(q.getDate())[index] = q;
            }
        }
        return new TreeMap<Calendar, HistoricalQuote[]>(resultData);


    }

    public static void export(Map<Calendar, HistoricalQuote[]> data, List<String> columnList,
        File file, String seperator) throws IOException
    {
        String format="dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        FileWriter writer =null;
        try
        {
            writer=new FileWriter(file,false);
            //header
            writer.write("Date(" +format +")" + seperator);
            for (String header : columnList
            )
            {
                writer.write(header + seperator);
            }
            writer.write(System.lineSeparator());
            //body
            for (Map.Entry<Calendar, HistoricalQuote[]> entry : data.entrySet())
            {
                Calendar day = entry.getKey();
                HistoricalQuote[] details = entry.getValue();
                writer.write(sdf.format(day.getTime())+ seperator);

                for (int i =0; i < details.length;i++)

                {
                    if(details[i]==null){
                        writer.write("-" + seperator);
                    }else{
                        writer.write(details[i].getClose() + seperator);
                    }

                }
                writer.write(System.lineSeparator());

            }
            writer.write(System.lineSeparator());
            writer.write(System.lineSeparator());
            writer.write("Based on YahooFinance Data!");

        }catch (Exception e)
        {
            e.printStackTrace();

        }finally
        {
            if(writer!=null)
            {
                writer.flush();
                writer.close();
            }

        }




    }
}
