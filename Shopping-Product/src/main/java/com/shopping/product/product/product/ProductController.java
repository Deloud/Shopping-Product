package com.shopping.product.product.product;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ProductController {

//    @Autowired
//    private ProductRepository productRepository;

    //data2.xml 사용방법

    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("data.xml");
    Product resturant = ctx.getBean("resturant1", Product.class);
    static int count = 0;

    //    @GetMapping("")
//    public String retrieveAllProducts() {
//        System.out.println("--- names property ---");
//        resturant.getName().stream().forEach(System.out::println);
//        System.out.println("--- id property ---");
//        resturant.getId().stream().forEach(System.out::println);
//        System.out.println("--- price property ---");
//        resturant.getPrice().stream().forEach(System.out::println);
////        System.out.println("--- menus property ---");
////        resturant.getMenus().forEach((k, v) -> {
////            System.out.println("key : " + k + " " + "value : " + v);
////        });
//        return "test";
//    }
    @GetMapping()
    public ResponseEntity<Integer> home() throws ParseException {

        if (count != 3) {
            count++;
            return ResponseEntity.status(200).body(1);


        }
        return ResponseEntity.status(500).body(-1);

    }

    @GetMapping("/{id}")
    public JSONObject retrieveProduct(@PathVariable int id) throws ParseException {
        String output = "{" + "name:" + resturant.getNametoid(id) + ", price:" + resturant.getPricetoid(id) + "}";

        System.out.println(output);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(output);
        JSONObject jsonObj = (JSONObject) obj;

        return jsonObj;
    }

}