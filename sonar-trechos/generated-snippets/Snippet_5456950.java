public class Snippet__5456950 {

    public static boolean isBelowLoadLevel(SystemResourceUsage usage, float thresholdPercentage) {
            return (usage.bandwidthOut.percentUsage() < thresholdPercentage
                    && usage.bandwidthIn.percentUsage() < thresholdPercentage
                    && usage.cpu.percentUsage() < thresholdPercentage
                    && usage.directMemory.percentUsage() < thresholdPercentage);
        }

}
