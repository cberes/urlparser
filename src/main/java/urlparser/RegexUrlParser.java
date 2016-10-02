package urlparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUrlParser implements UrlParser
{
  private static final String REGEX = "^(([a-z]+):)?/*([\\w.]+):?(\\d*)?(/([\\w\\-\\.]*?))?(\\?([^#?\\s]*?))?(#([\\w\\-]*?))?$";

  private final Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);

  @Override
  public Url parse(String s)
  {
    Matcher matcher = pattern.matcher(s);
    if (!matcher.find())
    {
      throw new IllegalArgumentException("URL " + s + " was invalid.");
    }

    Url url = new Url();
    url.setScheme(coalesce(matcher.group(2), ""));
    url.setHost(matcher.group(3));
    url.setPort(matcher.group(4));
    url.setPath(coalesce(matcher.group(6), ""));
    url.setQuery(coalesce(matcher.group(8), ""));
    url.setFragment(coalesce(matcher.group(10), ""));
    return url;
  }

  private static String coalesce(String value, String dflt)
  {
    return value != null ? value : dflt;
  }

  @Override
  public String getName()
  {
    return "Regex";
  }
}
