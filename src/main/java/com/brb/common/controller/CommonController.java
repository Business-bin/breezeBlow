package com.brb.common.controller;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.brbcom.common.util.RequestParameterUtil;
import com.brb.brbcom.foundation.property.PropertyService;
import com.brb.common.service.CommonService;
import com.brb.etc.service.EtcService;
import com.sun.mail.smtp.SMTPTransport;

/**
 *
 * @author back
 *
 */
@Controller
public class CommonController {
	protected PropertyService propertyService;
	String host     = "";
	String user   = "";
	String password  =  "";
	String port  =  "";
	Properties props = new Properties();

	@Autowired
	EtcService etcService;

	public CommonController(){
	}


	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	CommonService commonService;

	@RequestMapping("common/jusoPopup")
	public ModelAndView juso(@RequestParam int gubun) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("gubun", gubun);
		mav.setViewName("common/jusoPopup") ;
		return mav;
	}

	/**
	 * 메일 전송
	 * R_ADM_EMAIL : test@test.mail
	 * R_AS_STAT : 머시기머시기  접수 완료
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({"unused", "unchecked" })
	@RequestMapping(value = "common/sendEmail" , method = RequestMethod.POST)
	public  @ResponseBody Map<Object, Object>  sendEmail(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> rMap =  new BrbMap<>();

		String emailTo = dMap.getString("R_ADM_EMAIL");
		String asStat = dMap.getString("R_AS_STAT");

		rMap = commonService.getEnvInfo();

		Properties props = System.getProperties();
        props.put("mail.smtps.host", rMap.getString("HOST"));
        props.put("mail.smtps.auth", "true");

        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("senko@"+rMap.getString("ID"))); // 계정@도메인이름
        //msg.setFrom(new InternetAddress("YOU@sandboxe833feeb0e764feeba5f9e9b4498c005.mailgun.org"));
        InternetAddress[] addrs = InternetAddress.parse(emailTo, false); // 수신이메일
        msg.setRecipients(Message.RecipientType.TO, addrs);

        msg.setSubject("AS 상태 발송 메일"); // 메일제목
        msg.setText(asStat); // 메일본문
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
        t.connect("smtp.mailgun.com", "postmaster@"+rMap.getString("ID"), rMap.getString("PWD"));
        // t.connect("smtp.mailgun.com", "postmaster@도메인이름", "SMTP 패스워드");
        t.sendMessage(msg, msg.getAllRecipients());

        System.out.println("Response: " + t.getLastServerResponse());

        t.close();

        rMap.put("mailYn", "Y");
      	return rMap;
	}
}
