public class Snippet__7320330 {

    public Snippet__7320330(
            final Shell parentShell,
            final String label,
            final String initialValue,
            final String dialogTitle,
            final String purpose) {
            super(parentShell);
            this.label = label;
            text = initialValue;
            this.dialogTitle = dialogTitle;

            /*
             * Set the dialog settings key to be "purpose" This allows different
             * uses of the generic DlgInputString to have different settings
             */
            setOptionDialogSettingsKey(StringInputDialog.class.getName() + "." + purpose); //$NON-NLS-1$
        }

}
