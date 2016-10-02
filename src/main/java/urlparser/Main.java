package urlparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
  private static final int ITERATIONS = 10000;

  public static void main(String[] args)
  {
    // create the parsers
    // could change this to support more methods than 2, so use array
    UrlParser[] parsers = { new RegexUrlParser(), new StateMachineUrlParser() };
    List<Url> results = new ArrayList<Url>(parsers.length);

    try
    {
      BufferedReader br = new BufferedReader(new FileReader(args[0]));
      String url;
      while ((url = br.readLine()) != null)
      {
        // initial parsing
        results.clear();
        for (UrlParser parser : parsers)
        {
          Url result = parser.parse(url);
          results.add(result);
          System.out.println(parser.getName() + ": " + result);
        }
        System.out.println();

        // initial validity check
        if (!allEntriesEqual(results))
        {
          System.out.println("Results do not match. Exiting.");
          break;
        }

        // perform benchmarks
        System.out.println("Results match. Benchmarking " + ITERATIONS + " iterations of each implementation...");
        System.out.println();
        for (UrlParser parser : parsers)
        {
          System.out.println(parser.getName() + ": " + benchmark(parser, url) + " ms");
        }
        System.out.println();
      }
      br.close();
    } catch (IOException e)
    {
      System.err.println("Error reading file: " + args[0]);
      e.printStackTrace();
    }
  }

  private static boolean allEntriesEqual(List<?> list)
  {
    if (list == null || list.size() < 2)
    {
      return true;
    }

    Object first = list.iterator().next();
    for (Object o : list)
    {
      // obviously, first will equals the first item
      if (!first.equals(o))
      {
        return false;
      }
    }

    return true;
  }

  private static long benchmark(UrlParser parser, String url)
  {
    final long start = System.currentTimeMillis();
    for (int i = 0; i < ITERATIONS; ++i)
    {
      parser.parse(url);
    }
    return System.currentTimeMillis() - start;
  }
}
