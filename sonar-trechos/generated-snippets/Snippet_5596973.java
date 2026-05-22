public class Snippet__5596973 {

    private static int[][] getCoordinates(List<ShapeRecord> records)
        {
            int[][] coordinates = new int[records.size()][2];
            ShapeRecord record;
            for(int i=0; i<records.size(); i++)
            {
                record = records.get(i);
                if (record instanceof StyleChangeRecord)
                {
                    StyleChangeRecord scr = (StyleChangeRecord)record;
                    coordinates[i][0] = scr.getMoveDeltaX();
                    coordinates[i][1] = scr.getMoveDeltaY();
                }
                else if (record instanceof StraightEdgeRecord)
                {
                    StraightEdgeRecord ser = (StraightEdgeRecord)record;
                    coordinates[i][0] = coordinates[i-1][0] + ser.getDeltaX();
                    coordinates[i][1] = coordinates[i-1][1] + ser.getDeltaY();
                }
                else if (record instanceof CurvedEdgeRecord)
                {
                    CurvedEdgeRecord cer = (CurvedEdgeRecord)record;                    
                    coordinates[i][0] = coordinates[i-1][0] + cer.getControlDeltaX() + cer.getAnchorDeltaX();
                    coordinates[i][1] = coordinates[i-1][1] + cer.getControlDeltaY() + cer.getAnchorDeltaY();
                }                  
            }
            return coordinates;
        }

}
