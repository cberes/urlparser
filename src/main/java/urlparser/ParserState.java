package urlparser;

/*
 * These are ordered such that each enum item can call split once with its own
 * argument. Re-ordering them would be tricky.
 */
public enum ParserState
{
  // scheme is first as it will always be first
  SCHEME
  {
    @Override
    public PartialParsedResult parse(String s)
    {
      String[] parts = s.split(":/{0,2}", 2);
      return parts.length >= 2
          ? new PartialParsedResult(parts[0], parts[1])
          : new PartialParsedResult("", parts[0]);
    }

    @Override
    public void setValue(String value, Url url)
    {
      url.setScheme(value);
    }
  },
  // next things are optional but we know their order
  FRAGMENT
  {
    @Override
    public PartialParsedResult parse(String s)
    {
      String[] parts = s.split("#", 2);
      return new PartialParsedResult(getStringAtIndexOrDefault(parts, 1, ""), parts[0]);
    }

    @Override
    public void setValue(String value, Url url)
    {
      url.setFragment(value);
    }
  },
  QUERY
  {
    @Override
    public PartialParsedResult parse(String s)
    {
      String[] parts = s.split("\\?", 2);
      return new PartialParsedResult(getStringAtIndexOrDefault(parts, 1, ""), parts[0]);
    }

    @Override
    public void setValue(String value, Url url)
    {
      url.setQuery(value);
    }
  },
  PATH
  {
    @Override
    public PartialParsedResult parse(String s)
    {
      String[] parts = s.split("/", 2);
      return new PartialParsedResult(getStringAtIndexOrDefault(parts, 1, ""), parts[0]);
    }

    @Override
    public void setValue(String value, Url url)
    {
      url.setPath(value);
    }
  },
  // host after the optional fields, because it must exist
  HOST
  {
    @Override
    public PartialParsedResult parse(String s)
    {
      String[] parts = s.split(":", 2);
      return new PartialParsedResult(parts[0], getStringAtIndexOrDefault(parts, 1, ""));
    }

    @Override
    public void setValue(String value, Url url)
    {
      url.setHost(value);
    }
  },
  // port finally, because it may not exist but will be immediately after host
  PORT
  {
    @Override
    public PartialParsedResult parse(String s)
    {
      return new PartialParsedResult(s, null);
    }

    @Override
    public void setValue(String value, Url url)
    {
      url.setPort(value);
    }
  };

  public abstract PartialParsedResult parse(String s);

  public abstract void setValue(String value, Url url);

  private static String getStringAtIndexOrDefault(String[] array, int index, String dflt)
  {
    return array.length > index ? array[index] : dflt;
  }
}
