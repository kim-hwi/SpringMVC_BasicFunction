package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}",username,age);
        response.getWriter().write("ok");
    }

    @ResponseBody//반환값이 String일 경우 viewpage를 가져오게 되지만, @ResponseBody를 통해 body에 값을 전달할 수 있다.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username,
                               @RequestParam("age") int age){
        log.info("username = {} , age = {}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")//변수명이 같을 경우 파라미터명 생략가능
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age){
        log.info("username = {} , age = {}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){//String, int 등의 단순타입일 경우 @RequestParam을 생략할 수 있음.
        log.info("username = {} , age = {}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam() String username,//required의 기본값은 true이다.
                                 @RequestParam(required = false) Integer age){//int를 사용할 경우 null값을 넣을 수 없기 때문에 컴파일 오류가 발생할 수 있음.
        log.info("username = {} , age = {}",username, age);
        // null과 ""값은 다르다. 따라서 username= 으로 사용할 경우 true설정이 제 역할을 하지 못함
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,//required의 기본값은 true이다.
                                       @RequestParam(required = false, defaultValue ="-1") Integer age){//int를 사용할 경우 null값을 넣을 수 없기 때문에 컴파일 오류가 발생할 수 있음.
        log.info("username = {} , age = {}",username, age);
        // defaultValue는 파라미터가 빈문자가 들어올 경우 default값을 넣어준다.
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParaMap(@RequestParam Map<String,String> paramMap){//Map을 통해 값을 가져올 수 있음.
        log.info("username = {} , age = {}",paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){//객체의 프로퍼티를 찾아 바인딩 해줌.

        log.info("username = {} , age = {}",helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){//@ModelAttribute를 생랴할 수 있음. -> 단순타입이 아닐경우 ModelAttribute를로 인식

        log.info("username = {} , age = {}",helloData.getUsername(), helloData.getAge());

        return "ok";
    }





}
