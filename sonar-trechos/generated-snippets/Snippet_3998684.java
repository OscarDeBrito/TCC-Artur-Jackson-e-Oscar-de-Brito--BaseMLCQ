public class Snippet__3998684 {

    protected String getEventType(String eventName) {
                if (eventName.equals("focusin")) {
                    return "DOMFocusIn";
                } else if (eventName.equals("focusout")) {
                    return "DOMFocusOut";
                } else if (eventName.equals("activate")) {
                    return "DOMActivate";
                }
                if (isSVG12) {
                    if (animationEventNames12.contains(eventName)) {
                        return eventName;
                    }
                } else {
                    if (animationEventNames11.contains(eventName)) {
                        return eventName;
                    }
                }
                return null;
            }

}
