public class Snippet__9453064 {

    int[][] burst() {
        int[][] result = new int[4][];

        result[WALLCLOCK_TIME_INDEX] = progressWallclockTime.getValues();
        result[CPU_TIME_INDEX] = progressCPUTime.getValues();
        result[VIRTUAL_MEMORY_KBYTES_INDEX] = progressVirtualMemoryKbytes.getValues();
        result[PHYSICAL_MEMORY_KBYTES_INDEX] = progressPhysicalMemoryKbytes.getValues();

        return result;
      }

}
