package chapter05.cookie.example;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LastAccessServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    public void doGet(HttpServletRequest request,HttpServletResponse resposne)throws ServletException,IOException {
        resposne.setContentType("text/html;charset=utf-8");
        String lastAccessTime=null;
        Cookie[] cookies=request.getCookies();
        for (int i=0; cookies !=null && i<cookies.length; i++) {
            if ("lastAccess".equal(cookies[i].getName())) {
                lastAccessTime=cookies[i].getValue();
                break;
            }
        }
        if (lastAccessTime == null) {
            resposne.getWriter().print("您是首次访问本站！");
        }else{
            resposne.getWriter().print("您上次的访问时间是："+ lastAccessTime);
        }
        String currentTime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        Cookie cookie =new Cookie("lastAccess",currentTime);
        resposne.addCookie(cookie);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        this.doPost(req,resp);
    }
}