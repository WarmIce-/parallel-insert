package com.springapp.mvc.Controller;

import com.springapp.mvc.DAO.serviceImp.UserServiceImp;
import com.springapp.mvc.models.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class userBeanController {
    private Logger logger = Logger.getLogger(userBeanController.class.getName());
    private UserServiceImp userServiceImp;

    @Autowired
    public userBeanController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView welcome(@ModelAttribute(value = "user") UserBean userBean) {
        boolean isUserBeanInserted = userServiceImp.insertIntoUserBean(userBean);
        boolean isUserInserted = false;
        String message;
        int rowID = 0;

        if (isUserBeanInserted) {
            rowID = userServiceImp.getLastInsertedID(userBean.getFirstName(), userBean.getLastName());
            isUserInserted = userServiceImp.insertIntoUserTable(rowID, userBean);
        }

        ModelAndView model = new ModelAndView("welcome");

        // THIS IS CALLED TERNERY OPERATOR, YO TAILE 'C/C++' MA NI PADEKO THIES.
        // THIS OPERATOR IS HEAVILY USED BY THE PROFESSIONALS.
        message = isUserInserted ? " USER INSERTION SUCCESS" : "USER INSERTION FAILED!!";

        model.addObject("mess", message);

        model.addObject("user", rowID);

        return model;
    }
    @RequestMapping(value="/insert1")
    public ModelAndView get(@RequestParam(value="i") String i)

    {

        ModelAndView model=new ModelAndView("welcome");
        logger.info("id insert:" +i);
        int uId = Integer.valueOf(i);
        UserBean user = userServiceImp.getById(uId);
        logger.info("value get from database are:userId="+user.getUserId() +",password="+user.getPassword());
        model.addObject("user",user);
        return  model;

    }




    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView home()

    {
        ModelAndView model=new ModelAndView("homepage");
        return model;
    }
    @RequestMapping(value = "/addpage",method = RequestMethod.GET)
    public ModelAndView add()
    {
        ModelAndView model = new ModelAndView("addpage");


        return model;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("loginpage");

        return model;
    }

    @RequestMapping(value = "/dologin")
    public ModelAndView get(@Validated @ModelAttribute(value = "user") UserBean user, BindingResult result) {
        logger.info("value get from database are:userId="+user.getUserId() +",password="+user.getPassword());
        ModelAndView model = new ModelAndView();


       boolean user1= userServiceImp.user(user);
            if (!user1)
            {
                model.setViewName("Errorpage");
               model.addObject("error","ERRR:"+user.getUserId()+",this user is not exist in database");

            }else

            {
                model.setViewName("welcome");
              model.addObject("message","login successful:"+ user.getUserId());

            }
        return model;
        }

    }

