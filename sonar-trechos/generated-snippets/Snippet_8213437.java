public class Snippet__8213437 {

    @RequestMapping("/home")
    	public String home(Model model) {
    		model.addAttribute("name", "spring");
    		return "home";
    	}

}
