@Controller
@Grab('spring-boot-starter-freemarker')
class MyApp {

    @RequestMapping("/greet")
    String home(Model model, @RequestParam String name) {
        
        model.addAttribute("myname", name)
        return "hello"
    }
}