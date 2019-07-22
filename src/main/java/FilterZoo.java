import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "FilterZoo")
public class FilterZoo implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterZoo\n");
        Optional<String> animal1 = Optional.ofNullable(((HttpServletRequest) req).getPathInfo());
        String[] pathAnimal1 = animal1.isPresent() ? animal1.get().split("/") : new String[0];

        if (pathAnimal1.length > 1 && ServletZoo.animalList.get(pathAnimal1[1]) == null) {
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" + pathAnimal1[1] + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            ((HttpServletResponse) resp).sendError(400);
            System.out.println("!!!!!!!!!!!!!");
            return;
        }


        if (req.getParameter("animal") != null && ServletZoo.animalList.get(req.getParameter("animal")) == null) {
            ((HttpServletResponse) resp).sendError(400);
            System.out.println("!!!!!!!!!!!!!");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
