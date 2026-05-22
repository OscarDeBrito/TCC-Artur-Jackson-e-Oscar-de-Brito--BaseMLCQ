public class Snippet__9344142 {

    public void stateChanged(ChangeEvent e) {
                if (!isDragging) {
                    calculateThumbLocation();
                    slider.repaint();
                }
                lastValue = slider.getValue();
            }

}
