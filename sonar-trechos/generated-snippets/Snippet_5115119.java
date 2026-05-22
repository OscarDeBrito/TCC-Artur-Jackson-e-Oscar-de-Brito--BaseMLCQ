protected static class PermissionsWS extends com.microsoft.schemas.sharepoint.soap.directory.PermissionsLocator
  {
    /**
    *
    */
    private static final long serialVersionUID = -2542430113046450050L;
    private java.net.URL endPoint;
    private String userName;
    private String password;
    private HttpClient httpClient;

    public PermissionsWS ( String siteUrl, String userName, String password, EngineConfiguration configuration, HttpClient httpClient )
      throws java.net.MalformedURLException
    {
      super(configuration);
      endPoint = new java.net.URL(siteUrl + "/_vti_bin/Permissions.asmx");
      this.userName = userName;
      this.password = password;
      this.httpClient = httpClient;
    }

    public com.microsoft.schemas.sharepoint.soap.directory.PermissionsSoap getPermissionsSoapHandler( )
      throws javax.xml.rpc.ServiceException, org.apache.axis.AxisFault
    {
      com.microsoft.schemas.sharepoint.soap.directory.PermissionsSoapStub _stub = new com.microsoft.schemas.sharepoint.soap.directory.PermissionsSoapStub(endPoint, this);
      _stub.setPortName(getPermissionsSoapWSDDServiceName());
      _stub.setUsername( userName );
      _stub.setPassword( password );
      _stub._setProperty( HTTPCLIENT_PROPERTY, httpClient);
      return _stub;
    }
  }
