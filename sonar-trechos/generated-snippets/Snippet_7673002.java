public class ConnectedOutParaManager extends IOutParaManager {

	public ConnectedOutParaManager(Client client) {
		super(client);
	}

	@Override
	public void register(OutPara para) {
		/*
		 * 只有当悬浮框出参为空时，才会更新悬浮框，这样有已关注参数时，新来的AC参数不会打扰用户
		 * 此时后来的参数应该主动设置为非AC状态，否则在将AC参数都拖下去后，后来的AC参数会立即
		 * 增补到悬浮框上，而参数列表不同步，会比较怪异
		 */
		if (null != para && null != para.getKey() && !contains(para.getKey()))
		{
			para.setClient(client.getKey());
			outParaMap.put(para.getKey(), para);

			// 悬浮窗需要立即反应，所以如果是AC参数立即更新UI列表
			OpUIManager.addItemToAC(para);
		}
	}

}
