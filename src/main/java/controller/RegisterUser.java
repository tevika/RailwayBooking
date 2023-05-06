package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.User;

@WebServlet("/register")
public class RegisterUser  extends  HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname=req.getParameter("01");
		long mobile=Long.parseLong(req.getParameter("02"));
		String  email=req.getParameter("03");
		String gender=req.getParameter("04");
		String password1=req.getParameter("05");
		String password2=req.getParameter("06");
		Date dob=Date.valueOf(req.getParameter("07"));
		int age= Period.between(dob.toLocalDate(),LocalDate.now()).getYears();
		if(password1.equals(password2))
		{
			if(age>18)
			{
				User user=new User();
				user.setAge(age);
				user.setDob(dob);
				user.setEmail(email);
				user.setFirstname(firstname);
				user.setGender(gender);
				user.setMobile(mobile);
				user.setPassword(password1);
				
				UserDao dao=new UserDao();
				dao.save(user);
				resp.getWriter().print("<h1 style='color:red'> Account created sucessfully!!<br> your userid is "+user.getId()+" </h1>");
				resp.getWriter().print("<h1 style='color:green'> your userid </h1>");
				req.getRequestDispatcher("Home.html").include(req, resp);
			
				
				
			}
			else
			{
				resp.getWriter().print("<h1> You are not old enough to fill the form </h1>");
				req.getRequestDispatcher("Register.html").include(req, resp);
			}
			
		}
		else
		{
			resp.getWriter().print("<h1> Wrong password plz enter same as given previously </h1>");
			req.getRequestDispatcher("Register.html").include(req, resp);
		}
		
	}

}
