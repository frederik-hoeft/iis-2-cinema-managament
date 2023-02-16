package IIS.Server.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import IIS.Server.models.response.TestResponse;


@RestController
@RequestMapping(path="/JSON", produces="application/json")
@CrossOrigin(origins="*")
public class HomeController {

	@GetMapping("/data")
    public ArrayList<TestResponse> get() {
         
        ArrayList<TestResponse> arr = new ArrayList<>();
         
        TestResponse userOne = new TestResponse();
        userOne.setId(1);
        userOne.setName("@geek");
        userOne.setData("GeeksforGeeks");
         
        TestResponse userTwo = new TestResponse();
        /*userTwo.setId(2);
        userTwo.setName("@drash");
        userTwo.setData("Darshan.G.Pawar");*/
         
        arr.add(userOne);
        arr.add(userTwo);
         
        return arr;
    }
     
    @GetMapping("/{id}/{name}/{data}")
    public ResponseEntity<TestResponse> getData(
    		@PathVariable("id") Integer id,
    		@PathVariable("name") String name,
            @PathVariable("data") String data) {
         
        TestResponse user = new TestResponse();
        /*user.setId(id);
        user.setName(name);
        user.setData(data);*/
         
        HttpHeaders headers = new HttpHeaders();
         
        ResponseEntity<TestResponse> entity = new ResponseEntity<TestResponse>(user,headers,HttpStatus.CREATED);
         
        return entity;
    }	
}
