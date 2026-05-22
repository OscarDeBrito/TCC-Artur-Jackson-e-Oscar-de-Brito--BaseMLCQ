public class Snippet__6537117 {

    @Override
        public SensorEnum getSensorType(Short sensorIndex) {
            if (sensorIndex != null) {
                DeviceSensorValue devSenVal = getDeviceSensorValue(sensorIndex);
                return devSenVal != null ? devSenVal.getSensorType() : null;
            }
            return null;
        }

}
