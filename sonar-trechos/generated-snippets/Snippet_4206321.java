public class Snippet__4206321 {

    private static final class FixNegativeLocalDeletionTimeIterator extends AbstractIterator<Unfiltered> implements UnfilteredRowIterator
        {
            /**
             * The decorated iterator.
             */
            private final UnfilteredRowIterator iterator;

            private final OutputHandler outputHandler;
            private final NegativeLocalDeletionInfoMetrics negativeLocalExpirationTimeMetrics;

            public FixNegativeLocalDeletionTimeIterator(UnfilteredRowIterator iterator, OutputHandler outputHandler,
                                                        NegativeLocalDeletionInfoMetrics negativeLocalDeletionInfoMetrics)
            {
                this.iterator = iterator;
                this.outputHandler = outputHandler;
                this.negativeLocalExpirationTimeMetrics = negativeLocalDeletionInfoMetrics;
            }

            public TableMetadata metadata()
            {
                return iterator.metadata();
            }

            public boolean isReverseOrder()
            {
                return iterator.isReverseOrder();
            }

            public RegularAndStaticColumns columns()
            {
                return iterator.columns();
            }

            public DecoratedKey partitionKey()
            {
                return iterator.partitionKey();
            }

            public Row staticRow()
            {
                return iterator.staticRow();
            }

            @Override
            public boolean isEmpty()
            {
                return iterator.isEmpty();
            }

            public void close()
            {
                iterator.close();
            }

            public DeletionTime partitionLevelDeletion()
            {
                return iterator.partitionLevelDeletion();
            }

            public EncodingStats stats()
            {
                return iterator.stats();
            }

            protected Unfiltered computeNext()
            {
                if (!iterator.hasNext())
                    return endOfData();

                Unfiltered next = iterator.next();
                if (!next.isRow())
                    return next;

                if (hasNegativeLocalExpirationTime((Row) next))
                {
                    outputHandler.debug(String.format("Found row with negative local expiration time: %s", next.toString(metadata(), false)));
                    negativeLocalExpirationTimeMetrics.fixedRows++;
                    return fixNegativeLocalExpirationTime((Row) next);
                }

                return next;
            }

            private boolean hasNegativeLocalExpirationTime(Row next)
            {
                Row row = next;
                if (row.primaryKeyLivenessInfo().isExpiring() && row.primaryKeyLivenessInfo().localExpirationTime() < 0)
                {
                    return true;
                }

                for (ColumnData cd : row)
                {
                    if (cd.column().isSimple())
                    {
                        Cell cell = (Cell)cd;
                        if (cell.isExpiring() && cell.localDeletionTime() < 0)
                            return true;
                    }
                    else
                    {
                        ComplexColumnData complexData = (ComplexColumnData)cd;
                        for (Cell cell : complexData)
                        {
                            if (cell.isExpiring() && cell.localDeletionTime() < 0)
                                return true;
                        }
                    }
                }

                return false;
            }

            private Unfiltered fixNegativeLocalExpirationTime(Row row)
            {
                Row.Builder builder = HeapAllocator.instance.cloningBTreeRowBuilder();
                builder.newRow(row.clustering());
                builder.addPrimaryKeyLivenessInfo(row.primaryKeyLivenessInfo().isExpiring() && row.primaryKeyLivenessInfo().localExpirationTime() < 0 ?
                                                  row.primaryKeyLivenessInfo().withUpdatedTimestampAndLocalDeletionTime(row.primaryKeyLivenessInfo().timestamp() + 1, AbstractCell.MAX_DELETION_TIME)
                                                  :row.primaryKeyLivenessInfo());
                builder.addRowDeletion(row.deletion());
                for (ColumnData cd : row)
                {
                    if (cd.column().isSimple())
                    {
                        Cell cell = (Cell)cd;
                        builder.addCell(cell.isExpiring() && cell.localDeletionTime() < 0 ? cell.withUpdatedTimestampAndLocalDeletionTime(cell.timestamp() + 1, AbstractCell.MAX_DELETION_TIME) : cell);
                    }
                    else
                    {
                        ComplexColumnData complexData = (ComplexColumnData)cd;
                        builder.addComplexDeletion(complexData.column(), complexData.complexDeletion());
                        for (Cell cell : complexData)
                        {
                            builder.addCell(cell.isExpiring() && cell.localDeletionTime() < 0 ? cell.withUpdatedTimestampAndLocalDeletionTime(cell.timestamp() + 1, AbstractCell.MAX_DELETION_TIME) : cell);
                        }
                    }
                }
                return builder.build();
            }
        }

}
