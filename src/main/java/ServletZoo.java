

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Optional;


public class ServletZoo extends HttpServlet {

    public static HashMap<String, String> animalList = new HashMap<String, String>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//http://localhost:3000/hello/dog?animal=sparrow
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        animalList.put("duck","Quack-quack");
        animalList.put("sheep","Baa-baa");
        animalList.put("dog","Woof-woof");
        animalList.put("sparrow","Cheep-cheep");
        animalList.put("cow","Moo-moo");
        animalList.put("pig","Oink-oink");
        animalList.put("cat","Meow");
        animalList.put("nothing","Boo");

        String nothing = "nothing";

        Optional<String> animal1 = Optional.ofNullable(request.getPathInfo());
        Optional<String> animal2 =  Optional.ofNullable(request.getParameter("animal"));

        String[] pathAnimal1 = animal1.isPresent()?animal1.get().split("/"):new String[0];
        animal1 = Optional.ofNullable(pathAnimal1.length>1?pathAnimal1[1]:nothing);
        String animalSay1 = animal1.orElse(nothing).toLowerCase();
        String animalSay2 = animal2.orElse(nothing).toLowerCase();

        animalSay1 = animalSay1+" say:\t"+animalList.get(animalSay1);
        animalSay2 = animalSay2+" say:\t"+animalList.get(animalSay2);

        pw.println("<html>");
        for (int i = 0; i < 5; i++) {

            pw.println("<h1> \t"+animalSay1+(i%3==0?"?":"")+" </h1>");
            pw.println("<h1> \t"+animalSay2+" </h1>");
        }

        pw.println("</html>");

        System.out.println("Servlet\n!!!!!!!!!\n");
    }
}
