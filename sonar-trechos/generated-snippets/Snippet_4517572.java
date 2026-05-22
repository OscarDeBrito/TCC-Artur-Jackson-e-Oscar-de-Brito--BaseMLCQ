public class ClockFrame extends JFrame implements Runnable 
{
	private final static String TITLE = "Felix UPnP Clock";
	private ClockDevice clockDev;
	private ClockPane clockPane;
	
	public ClockFrame(final BundleContext context)
	{
		super(TITLE);
		try {
			clockDev = new ClockDevice( context);
		}
		catch (Exception e) {
			System.out.println(e);
		}
				
		getContentPane().setLayout(new BorderLayout());

		clockPane = new ClockPane();		
		getContentPane().add(clockPane, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) 
			{
				try {
					context.getBundle().stop();
				} catch (BundleException ex) {
					ex.printStackTrace();
				}
			}
		});			
	       try {
	            URL eventIconUrl = ClockFrame.class.getResource("images/logo.gif");           
	            ImageIcon icon=  new ImageIcon(eventIconUrl,"logo");
	            setIconImage(icon.getImage());
	       }
	        catch (Exception ex){
	                System.out.println("Resource: IMAGES/logo.gif not found : " + ex.toString());
	        }
		
		pack();
		setVisible(true);
	}

	public ClockPane getClockPanel()
	{
		return clockPane;
	}

	public ClockDevice getClockDevice()
	{
		return clockDev;
	}
		
	////////////////////////////////////////////////
	//	run	
	////////////////////////////////////////////////

	private Thread timerThread = null;
		
	public void run()
	{
		Thread thisThread = Thread.currentThread();

		while (timerThread == thisThread) {
			getClockDevice().update();
			getClockPanel().repaint();
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {}
		}
	}
	
	public void start()
	{
		clockDev.start();
		
		timerThread = new Thread(this,"upnp.sample.clock.ClockFrame");
		timerThread.start();
	}
	
	public void stop()
	{
		clockDev.stop();
		timerThread = null;
		dispose();
	}

}
