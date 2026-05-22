public class Snippet__7424164 {

    @Override
        public Value emitFloatConvert(FloatConvert op, Value inputVal) {
            PlatformKind resultPlatformKind = getFloatConvertResultKind(op);
            LIRKind resultLirKind = LIRKind.combine(inputVal).changeType(resultPlatformKind);
            Variable result = getLIRGen().newVariable(resultLirKind);
            getLIRGen().append(new AArch64FloatConvertOp(op, result, asAllocatable(inputVal)));
            return result;
        }

}
