public class CayenneCellEditor extends DefaultCellEditor {
    public CayenneCellEditor(final JTextField textField) {
        super(textField);
    }
    
    public CayenneCellEditor(final JCheckBox checkBox) {
        super(checkBox);
    }
    
    public CayenneCellEditor(final JComboBox comboBox) {
        super(comboBox);
    }
    
    @Override
    public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent) {
            //allow multiple selection without 
            
            MouseEvent me = (MouseEvent) e;
            if (me.isControlDown() || me.isShiftDown()) {
                return false;
            }
        }
        
        return super.isCellEditable(e);
    }
}
