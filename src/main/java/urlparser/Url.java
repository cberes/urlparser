package urlparser;

public class Url
{
  public String scheme;
  public String host;
  public String port;
  public String path;
  public String query;
  public String fragment;

  public String getScheme()
  {
    return scheme;
  }

  public void setScheme(String scheme)
  {
    this.scheme = scheme;
  }

  public String getHost()
  {
    return host;
  }

  public void setHost(String host)
  {
    this.host = host;
  }

  public String getPort()
  {
    return port;
  }

  public void setPort(String port)
  {
    this.port = port;
  }

  public String getPath()
  {
    return path;
  }

  public void setPath(String path)
  {
    this.path = path;
  }

  public String getQuery()
  {
    return query;
  }

  public void setQuery(String query)
  {
    this.query = query;
  }

  public String getFragment()
  {
    return fragment;
  }

  public void setFragment(String fragment)
  {
    this.fragment = fragment;
  }

  @Override
  public String toString()
  {
    return new StringBuilder()
        .append("{scheme=").append(scheme)
        .append(", host=").append(host)
        .append(", port=").append(port)
        .append(", path=").append(path)
        .append(", query=").append(query)
        .append(", fragment=").append(fragment)
        .append("}").toString();
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fragment == null) ? 0 : fragment.hashCode());
    result = prime * result + ((host == null) ? 0 : host.hashCode());
    result = prime * result + ((path == null) ? 0 : path.hashCode());
    result = prime * result + ((port == null) ? 0 : port.hashCode());
    result = prime * result + ((query == null) ? 0 : query.hashCode());
    result = prime * result + ((scheme == null) ? 0 : scheme.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (getClass() != obj.getClass())
    {
      return false;
    }

    Url other = (Url) obj;
    if (fragment == null)
    {
      if (other.fragment != null)
        return false;
    } else if (!fragment.equals(other.fragment))
      return false;
    if (host == null)
    {
      if (other.host != null)
        return false;
    } else if (!host.equals(other.host))
      return false;
    if (path == null)
    {
      if (other.path != null)
        return false;
    } else if (!path.equals(other.path))
      return false;
    if (port == null)
    {
      if (other.port != null)
        return false;
    } else if (!port.equals(other.port))
      return false;
    if (query == null)
    {
      if (other.query != null)
        return false;
    } else if (!query.equals(other.query))
      return false;
    if (scheme == null)
    {
      if (other.scheme != null)
        return false;
    } else if (!scheme.equals(other.scheme))
      return false;
    return true;
  }
}
