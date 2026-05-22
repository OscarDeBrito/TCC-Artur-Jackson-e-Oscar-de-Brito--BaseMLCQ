private class AppConfigHandler extends DefaultHandler {

        private String username = null;
        private String vehicleType = null;
        private String uniqueId = null;
        private String sic = null;
        private int port = -1;
        private int positionMessageInterval = -1;
        private int vehicleStatusMessageInterval = -1;
        private LocationMode gpsType = LocationMode.SIMULATOR;
        private String gpx = null;
        private double speedMultiplier = -1;
        private int headingUnits = AngularUnit.Code.DEGREE;
        private String geomessageVersion = "1.1";
        
        private boolean readingUser = false;
        private boolean readingCode = false;
        private boolean readingMessaging = false;
        private boolean readingPort = false;
        private boolean readingPositionMessageInterval = false;
        private boolean readingVehicleStatusMessageInterval = false;
        private boolean readingGps = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("user".equalsIgnoreCase(qName)) {
                readingUser = true;
                username = attributes.getValue("name");
                vehicleType = attributes.getValue("type");
                uniqueId = attributes.getValue("id");
            } else if (readingUser) {
                if ("code".equalsIgnoreCase(qName)) {
                    readingCode = true;
                }
            } else if ("messaging".equalsIgnoreCase(qName)) {
                readingMessaging = true;
            } else if (readingMessaging) {
                if ("port".equalsIgnoreCase(qName)) {
                    readingPort = true;
                } else if ("interval".equalsIgnoreCase(qName) || "positionmessageinterval".equalsIgnoreCase(qName)) {
                    //Vehicle Commander 1.0 used "interval" instead of "positionmessageinterval"; accept either one
                    readingPositionMessageInterval = true;
                } else if ("vehiclestatusmessageinterval".equalsIgnoreCase(qName)) {
                    readingVehicleStatusMessageInterval = true;
                }
            } else if ("gps".equalsIgnoreCase(qName)) {
                readingGps = true;
                gpsType = "onboard".equalsIgnoreCase(attributes.getValue("type"))
                        ? LocationMode.LOCATION_SERVICE : LocationMode.SIMULATOR;
                gpx = attributes.getValue("gpx");
                String speedMultiplierString = attributes.getValue("speedMultiplier");
                if (null != speedMultiplierString) {
                    try {
                        speedMultiplier = Double.parseDouble(speedMultiplierString);
                    } catch (Throwable t) {}
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch, start, length);
            if (readingCode) {
                sic = value;
            } else {
                try {
                    int intValue = Integer.parseInt(value);
                    if (readingPort) {
                        port = intValue;
                    } else if (readingPositionMessageInterval) {
                        positionMessageInterval = intValue;
                    } else if (readingVehicleStatusMessageInterval) {
                        vehicleStatusMessageInterval = intValue;
                    }
                } catch (NumberFormatException nfe) {

                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("user".equalsIgnoreCase(qName)) {
                readingUser = false;
            } else if ("code".equalsIgnoreCase(qName)) {
                readingCode = false;
            } else if ("messaging".equalsIgnoreCase(qName)) {
                readingMessaging = false;
            } else if ("port".equalsIgnoreCase(qName)) {
                readingPort = false;
            } else if ("interval".equalsIgnoreCase(qName) || "positionmessageinterval".equalsIgnoreCase(qName)) {
                //Vehicle Commander 1.0 used "interval" instead of "positionmessageinterval"; accept either one
                readingPositionMessageInterval = false;
            } else if ("vehiclestatusmessageinterval".equalsIgnoreCase(qName)) {
                readingVehicleStatusMessageInterval = false;
            } else if ("gps".equalsIgnoreCase(qName)) {
                readingGps = false;
            }
        }
        
    }
