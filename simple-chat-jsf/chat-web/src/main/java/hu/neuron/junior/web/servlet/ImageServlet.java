package hu.neuron.junior.web.servlet;

import hu.neuron.junior.client.api.service.user.UserService;
import hu.neuron.junior.client.api.vo.UserVo;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        userService = context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getPathInfo().substring(1));
        UserVo user = userService.findById(id);

        response.setContentType("image/jpeg");
        response.setContentLength(user.getImage().length);
        response.getOutputStream().write(user.getImage());
    }
}
