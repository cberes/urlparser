package urlparser;

public class PartialParsedResult
{
  private final String value;
  private final String remainder;

  public PartialParsedResult(String value, String remainder)
  {
    this.value = value;
    this.remainder = remainder;
  }

  public String getValue()
  {
    return value;
  }

  public String getRemainder()
  {
    return remainder;
  }

}
