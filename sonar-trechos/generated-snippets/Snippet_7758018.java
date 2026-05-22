public class PortFinder
{
  private final Set<Integer> usedPorts = new HashSet<>();
  private final int startPort;
  private final int endPort;
  private final List<Integer> candidatePorts;

  public PortFinder(int startPort, int endPort, List<Integer> candidatePorts)
  {
    this.startPort = startPort;
    this.endPort = endPort;
    this.candidatePorts = candidatePorts;
  }

  @VisibleForTesting
  boolean canBind(int portNum)
  {
    try {
      new ServerSocket(portNum).close();
      return true;
    }
    catch (SocketException se) {
      return false;
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized int findUnusedPort()
  {
    if (candidatePorts != null && !candidatePorts.isEmpty()) {
      int port = chooseFromCandidates();
      usedPorts.add(port);
      return port;
    } else {
      int port = chooseNext(startPort);
      while (!canBind(port)) {
        port = chooseNext(port + 1);
      }
      usedPorts.add(port);
      return port;
    }
  }

  public synchronized void markPortUnused(int port)
  {
    usedPorts.remove(port);
  }

  private int chooseFromCandidates()
  {
    for (int port : candidatePorts) {
      if (!usedPorts.contains(port) && canBind(port)) {
        return port;
      }
    }
    throw new ISE("All ports are used...");
  }

  private int chooseNext(int start)
  {
    // up to endPort (which default value is 65535)
    for (int i = start; i <= endPort; i++) {
      if (!usedPorts.contains(i)) {
        return i;
      }
    }
    throw new ISE("All ports are used...");
  }
}
