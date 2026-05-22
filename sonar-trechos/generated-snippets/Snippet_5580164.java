public class Snippet__5580164 {

    @PostMapping("fromAttribute")
      public String fromAttribute(@RequestAttribute("input") String inputs,
          @RequestAttribute(value = "input2") String inputs2, @RequestAttribute(name = "input3") String inputs3) {
        return inputs + "," + inputs2 + "," + inputs3;
      }

}
