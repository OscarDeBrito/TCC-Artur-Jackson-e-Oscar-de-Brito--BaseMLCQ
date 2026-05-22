@Deprecated
public interface PrivateIonDatagram
    extends PrivateIonValue, IonDatagram
{
    void appendTrailingSymbolTable(SymbolTable symtab);
}
