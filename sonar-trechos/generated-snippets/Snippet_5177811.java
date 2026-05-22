public class Snippet__5177811 {

    private FunctionDesc findAggrFuncFromCubeDesc(FunctionDesc aggrFunc) {
            for (MeasureDesc measure : cubeDesc.getMeasures()) {
                if (measure.getFunction().equals(aggrFunc))
                    return measure.getFunction();
            }
            return aggrFunc;
        }

}
