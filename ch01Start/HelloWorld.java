@RestController
public class Hello {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World Java!";
    }

}