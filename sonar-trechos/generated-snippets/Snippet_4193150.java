final class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            ObjAttributeTableModel model = (ObjAttributeTableModel) table.getModel();
            column = table.getColumnModel().getColumn(column).getModelIndex();
            ObjAttribute attribute = model.getAttribute(row).getValue();

            if (!model.isCellEditable(row, column)) {
                setForeground(isSelected ? new Color(0xEEEEEE) : Color.GRAY);
            } else {
                setForeground(isSelected && !hasFocus ? table.getSelectionForeground() : table.getForeground());
            }

            setIcon(null);

            if (attribute.isInherited()) {
                Font font = getFont();
                Font newFont = font.deriveFont(Font.ITALIC);
                setFont(newFont);
                if(column == ObjAttributeTableModel.OBJ_ATTRIBUTE) {
                    setIcon(INHERITANCE_ICON);
                }
            }

            setFont(UIManager.getFont("Label.font"));
            setBorder(BorderFactory.createEmptyBorder(0,5,0,0));

            return this;
        }

        public void mouseClicked(MouseEvent event, int x) {
            Point point = event.getPoint();
            if(point.x - x <= INHERITANCE_ICON.getIconWidth()) {
                ActionManager actionManager = Application.getInstance().getActionManager();
                actionManager.getAction(ObjEntityToSuperEntityAction.class).performAction(null);
            }
        }
    }
