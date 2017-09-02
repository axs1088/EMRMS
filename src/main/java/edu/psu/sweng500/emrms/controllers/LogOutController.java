package com.pennmutual.preference.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pennmutual.security.common.BaseException;
import com.pennmutual.security.common.Constants;
import com.pennmutual.security.common.HTTPUtils;
import com.pennmutual.security.partner.AssertionManager;

@Controller
@RequestMapping("logout")
public class LogOutController {
    //private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Handle a GET request.  Instantly redirect to the POST form.
     *
     * @return view template name
     */
    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "pages/logout";
    }

    /**
     * Handle a POST request.  Log the user out and return to the home page.
     *
     * @return view template name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String post(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        // invalidateAssertion() does not clear the cookie as expected.  Send it a dummy session, and
        // clear the cookie manually.
        MockHttpServletResponse dummyResponse = new MockHttpServletResponse();
        new AssertionManager().invalidateAssertion(request, dummyResponse);
        Cookie ssoCookie = HTTPUtils.getCookie(request, Constants.SESSIONCOOKIE);
        if(ssoCookie != null) {
            ssoCookie.setValue("");
            ssoCookie.setPath("/");
            ssoCookie.setMaxAge(0);
            ssoCookie.setDomain(".pennmutual.com");
            response.addCookie(ssoCookie);
        }

        request.getSession().invalidate();
        return "redirect:/";
    }
}
