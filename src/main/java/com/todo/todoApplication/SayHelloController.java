package com.todo.todoApplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("/say-hello")
    @ResponseBody
    public String greet() {
        return "Hello";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String greetUsingHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>");
        sb.append("Hello Everyone");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>");
        sb.append("Hello Everyone Body changes");
        sb.append("</h1>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    // "say-hello-jsp" => sayHello.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/todos.jsp
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }
}
