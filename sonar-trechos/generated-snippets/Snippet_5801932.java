public class Snippet__5801932 {

    public static Asset2 toAsset2(final Asset asset)
        {
            if (asset instanceof Asset2)
                return (Asset2) asset;

            return new Asset2()
            {
                /** Returns false. */
                public boolean isInvariant()
                {
                    return false;
                }

                public Resource getResource()
                {
                    return asset.getResource();
                }

                public String toClientURL()
                {
                    return asset.toClientURL();
                }

                @Override
                public String toString()
                {
                    return asset.toString();
                }
            };
        }

}
