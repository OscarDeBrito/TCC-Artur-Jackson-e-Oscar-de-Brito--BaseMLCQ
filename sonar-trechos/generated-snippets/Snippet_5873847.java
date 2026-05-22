public class Snippet__5873847 {

    @Override
        public int getMaxVersion() {

            int max = 0;

            for(DataMigration entityMigration: entityDataMigrations){
                max = Math.max( max, entityMigration.getMaxVersion() );
            }

            return max;
        }

}
