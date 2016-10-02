package urlparser;

public class StateMachineUrlParser implements UrlParser
{
  @Override
  public Url parse(final String s)
  {
    Url url = new Url();
    String current = s;

    // apparently, in a proper state machine, one state should explicitly
    // determine its next state. however, in this setup the flow is linear with
    // no branches
    for (ParserState state : ParserState.values())
    {
      PartialParsedResult partialResult = state.parse(current);
      state.setValue(partialResult.getValue(), url);
      current = partialResult.getRemainder();
    }
    return url;
  }

  @Override
  public String getName()
  {
    return "State";
  }
}
