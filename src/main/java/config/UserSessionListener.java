package config;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
@WebListener
public class UserSessionListener implements HttpSessionListener {
    static final String ONLINE_USERS = "OnlineUsers";
     
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
 
        Integer onlineUsersCount = 0;
         
        Object attributeValue = context.getAttribute(ONLINE_USERS);
         
        if (attributeValue != null) {
            onlineUsersCount = (Integer) attributeValue;       
        }
         
        context.setAttribute(ONLINE_USERS, ++onlineUsersCount);

    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
         
        Integer onlineUsersCount = (Integer) context.getAttribute(ONLINE_USERS);
        context.setAttribute(ONLINE_USERS, --onlineUsersCount);
    }
}