public class Snippet__4404282 {

    private void initListeners()
        {
            importTemplatesButton.addSelectionListener( new SelectionAdapter()
            {
                public void widgetSelected( SelectionEvent e )
                {
                    importTemplatesAction();
                }
            } );

            exportTemplatesButton.addSelectionListener( new SelectionAdapter()
            {
                public void widgetSelected( SelectionEvent e )
                {
                    exportTemplatesAction();
                }
            } );

            removeTemplateButton.addSelectionListener( new SelectionAdapter()
            {
                public void widgetSelected( SelectionEvent e )
                {
                    removeTemplateAction();
                }
            } );

            setDefaultTemplateButton.addSelectionListener( new SelectionAdapter()
            {
                public void widgetSelected( SelectionEvent e )
                {
                    setDefaultTemplateAction();
                }
            } );

            useForAnyEntryButton.addSelectionListener( new SelectionAdapter()
            {
                public void widgetSelected( SelectionEvent e )
                {
                    useForAnyEntryAction();
                }
            } );

            useForOnlyEntriesWithTemplateButton.addSelectionListener( new SelectionAdapter()
            {
                public void widgetSelected( SelectionEvent e )
                {
                    useForOnlyEntriesWithTemplateAction();
                }
            } );
        }

}
