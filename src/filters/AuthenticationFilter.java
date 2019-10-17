package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private ServletContext context;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        System.out.println("Requested Resource::" + uri);
        HttpSession session = request.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("userreg") != null);
        String loginURI = request.getContextPath() + "/callservlet";
        boolean isLoginRequest = request.getRequestURI().equals(loginURI);


        if (isLoggedIn && isLoginRequest) {
            // the user is already logged in and he's trying to login again
            // then forwards to the welcome's homepage
            RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");
            dispatcher.forward(request, response);

        } else if (isLoggedIn || isLoginRequest) {
            // continues the filter chain
            // allows the request to reach the destination
            chain.doFilter(request, response);

        } else {
            // the user is not logged in, so authentication is required
            // forwards to the Login page
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);

        }

    }

    public void init(FilterConfig config) throws ServletException {
        context = config.getServletContext();
    }

}
