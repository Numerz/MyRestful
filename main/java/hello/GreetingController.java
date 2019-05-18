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

    @RequestMapping("/api/tasks/")
    public LinkedList<Greeting> greeting(@RequestParam(value="id", defaultValue="-1") String id) {
        int id_int = Integer.parseInt(id);

        if (id_int == -1){
            return greetingLinkedList;
        }

        LinkedList<Greeting> result = new LinkedList<Greeting>();

        if (id_int > 0){
            for (Greeting node: greetingLinkedList) {
                if (node.getId() == id_int){
                    result.add(node);
                    return result;
                }
            }
            // not found
            result.add(new Greeting(0, "This id does not exist"));
            return result;
        }else {
            result.add(new Greeting(0, "id <= 0"));
            return result;
        }

    }

    @RequestMapping(value = "/api/tasks/", method = POST)
    public Greeting postMethod(@RequestParam(value="id", defaultValue="0") String id){
        int id_int = Integer.parseInt(id);
        if (id_int <= 0){
            return new Greeting(0, "id <= 0");
        }
        else {
            for (Greeting node: greetingLinkedList) {
                if (node.getId() == id_int){
                    return new Greeting(0, "This id exists");
                }
            }

            Greeting newNode = new Greeting(id_int, template);
            greetingLinkedList.add(newNode);
            return newNode;
        }
    }

    @RequestMapping(value = "/api/tasks/",method = DELETE)
    public Greeting deleteMethod(@RequestParam(value="id", defaultValue="0") String id){
        int id_int = Integer.parseInt(id);

        if (id_int > 0){
            for (Greeting node: greetingLinkedList) {
                if (node.getId() == id_int){
                    greetingLinkedList.remove(node);
                    return new Greeting(0, "Successful delete");
                }
            }
        }else {
            return new Greeting(0, "id <= 0");
        }

        return new Greeting(0, "This id does not exist");
    }
}
