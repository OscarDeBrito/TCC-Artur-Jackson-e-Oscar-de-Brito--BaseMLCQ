public class AUTClient extends Client {

	public AUTClient() {
		super(ClientManager.AUT_CLIENT);
	}

	@Override
	public IInParaManager initInParas(InPara[] inParas) {
		IInParaManager im = new DefaultInParaManager(this);
		if (null != inParas)
		{
			for (InPara ip : inParas)
			{
				im.register(ip);
			}
		}

		return im;
	}

	@Override
	public IOutParaManager initOutParas(OutPara[] outParas) {
		IOutParaManager om = new ConnectedOutParaManager(this);
		if (null != outParas)
		{
			for (OutPara op : outParas)
			{
				om.register(op);
			}
		}

		return om;
	}

}
