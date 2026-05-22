public class Snippet__5912103 {

    public org.apache.xmlbeans.samples.validation.todolist.ItemType insertNewItem(int i)
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.apache.xmlbeans.samples.validation.todolist.ItemType target = null;
                    target = (org.apache.xmlbeans.samples.validation.todolist.ItemType)get_store().insert_element_user(ITEM$0, i);
                    return target;
                }
            }

}
