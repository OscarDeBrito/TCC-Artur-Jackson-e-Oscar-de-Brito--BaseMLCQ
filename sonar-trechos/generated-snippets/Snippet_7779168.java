public class DoubleNumericColumnSupplier implements Supplier<NumericColumn>
{
  private final Supplier<ColumnarDoubles> column;
  private final ImmutableBitmap nullValueBitmap;

  DoubleNumericColumnSupplier(Supplier<ColumnarDoubles> column, ImmutableBitmap nullValueBitmap)
  {
    this.column = column;
    this.nullValueBitmap = nullValueBitmap;
  }

  @Override
  public NumericColumn get()
  {
    return DoublesColumn.create(column.get(), nullValueBitmap);
  }
}
