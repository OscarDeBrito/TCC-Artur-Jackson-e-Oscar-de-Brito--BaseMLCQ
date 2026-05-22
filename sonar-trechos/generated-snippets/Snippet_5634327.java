public class Snippet__5634327 {

    public boolean equals(Object object)
        {
            boolean isEqual = false;

            if (super.equals(object) && (object instanceof DefineShape))
            {
                DefineShape defineShape = (DefineShape) object;

                if ( equals(defineShape.bounds, this.bounds) &&
                     equals(defineShape.shapeWithStyle, this.shapeWithStyle) &&
                     equals(defineShape.edgeBounds, this.edgeBounds) &&
                     (defineShape.usesFillWindingRule == this.usesFillWindingRule) &&
                     (defineShape.usesNonScalingStrokes == this.usesNonScalingStrokes) &&
                      (defineShape.usesScalingStrokes == this.usesScalingStrokes))
                {
                    isEqual = true;
                }
            }

            return isEqual;
        }

}
