public class Snippet__8857568 {

    @Override
      public int hashCode() {
        List<Object> list = new ArrayList<Object>();

        boolean present_name = true && (isSetName());
        list.add(present_name);
        if (present_name)
          list.add(name);

        boolean present_value = true && (isSetValue());
        list.add(present_value);
        if (present_value)
          list.add(value);

        boolean present_envPathOrder = true && (isSetEnvPathOrder());
        list.add(present_envPathOrder);
        if (present_envPathOrder)
          list.add(envPathOrder);

        return list.hashCode();
      }

}
