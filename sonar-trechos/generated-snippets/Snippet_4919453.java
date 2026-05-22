public class Snippet__4919453 {

    private JPanel createPortPanel() {
            portField = new JTextField(HttpMirrorControl.DEFAULT_PORT_S, 8);
            portField.setName(HttpMirrorControl.PORT);

            JLabel label = new JLabel(JMeterUtils.getResString("port")); // $NON-NLS-1$
            label.setLabelFor(portField);

            maxPoolSizeField = new JTextField(Integer.toString(HttpMirrorControl.DEFAULT_MAX_POOL_SIZE), 8);
            maxPoolSizeField.setName(HttpMirrorControl.MAX_POOL_SIZE);

            JLabel mpsLabel = new JLabel(JMeterUtils.getResString("httpmirror_max_pool_size")); // $NON-NLS-1$
            mpsLabel.setLabelFor(maxPoolSizeField);

            maxQueueSizeField = new JTextField(Integer.toString(HttpMirrorControl.DEFAULT_MAX_QUEUE_SIZE), 8);
            maxQueueSizeField.setName(HttpMirrorControl.MAX_QUEUE_SIZE);

            JLabel mqsLabel = new JLabel(JMeterUtils.getResString("httpmirror_max_queue_size")); // $NON-NLS-1$
            mqsLabel.setLabelFor(maxQueueSizeField);

            HorizontalPanel panel = new HorizontalPanel();
            panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    JMeterUtils.getResString("httpmirror_settings"))); // $NON-NLS-1$
        
            panel.add(label);
            panel.add(portField);

            panel.add(mpsLabel);
            panel.add(maxPoolSizeField);

            panel.add(mqsLabel);
            panel.add(maxQueueSizeField);
        
            panel.add(Box.createHorizontalStrut(10));

            return panel;
        }

}
