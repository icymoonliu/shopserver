package cn.edu.neu.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.neu.core.Constants;
import cn.edu.neu.core.util.Md5Util;
import cn.edu.neu.model.User;
import cn.edu.neu.service.UserService;

@Controller
@RequestMapping("/appuser")
public class AppUserAction extends BaseAction{
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String,String>  login(User user,HttpSession session){
		User dbUser=userService.existsUser(user);
		Map<String,String> m=new HashMap<String,String>();
		if(dbUser==null){//用户非法，登录失败
			m.put("loginFlag", "0");
			m.put("msg", "用户名或密码错误");
		}else{//用户合法，登录成功
			session.setAttribute(Constants.LOGIN_USER, dbUser);
			m.put("loginFlag", "1");
			m.put("msg", "");
		}
		return m;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String,String>  logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		Map<String,String> m=new HashMap<String,String>();
		if(session==null){//已处于登出状态
			m.put("logoutFlag", "0");
		}else{
			session.invalidate();
			m.put("logoutFlag", "1");
		}
		return m;
	}
	@ResponseBody
	@RequestMapping("/autoLogin")
	public Map<String,String> autoLogin(String userName,
			String authority,HttpSession session) {
		System.out.println("username:"+userName+",authority:"+authority);
		User user=userService.getUser(userName);
		Map<String,String> m=new HashMap<String,String>();
		System.out.println("user:"+user);
		if(user!=null){
			String serverAuth=Md5Util.getMD5Str(userName+","+
						Md5Util.getMD5Str(user.getUserPass()));
			
			if(authority.equals(serverAuth)){//自动登录成功
				session.setAttribute(Constants.LOGIN_USER, user);
				m.put("autoLoginFlag", "1");
				m.put("msg", "");
			}else{
				m.put("autoLoginFlag","0");
				m.put("msg", "自动登录失败，请在登录页面进行登录");
			}
		}
		else{//自动登录失败
			m.put("autoLoginFlag","0");
			m.put("msg", "自动登录失败，请在登录页面进行登录");
		}
		return m;
	}
}








