package hello;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class GreetingController {

    private static final String template = "Restful API homework";
    private final AtomicLong counter = new AtomicLong();
    private final LinkedList<Greeting> greetingLinkedList = new LinkedList<Greeting>();

    GreetingController(){
        greetingLinkedList.add(new Greeting(0, "error!"));
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="id", defaultValue="0") String id) {
        int id_int = Integer.parseInt(id);

        for (Greeting node: greetingLinkedList) {
            if (node.getId() == id_int){
                return node;
            }
        }
        // not found
        return greetingLinkedList.get(0);
    }

    @RequestMapping(method = POST)
    public void postMethod(@RequestParam(value="id", defaultValue="0") String id){
        int id_int = Integer.parseInt(id);
        if (id_int <= 0){
            return;
        }else {
            greetingLinkedList.add(new Greeting(id_int, template));
        }
    }

    @RequestMapping(method = DELETE)
    public void deleteMethod(@RequestParam(value="id", defaultValue="0") String id){
        int id_int = Integer.parseInt(id);

        if (id_int > 0){
            for (Greeting node: greetingLinkedList) {
                if (node.getId() == id_int){
                    greetingLinkedList.remove(node);
                }
            }
        }
    }
}
