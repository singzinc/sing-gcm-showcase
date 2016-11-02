package com.singplayground.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
//import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.singplayground.model.MessageDTO;
import com.singplayground.model.NotificationDTO;
import com.singplayground.util.NotificationHandler;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {

	private Logger logger = Logger.getLogger(BasicController.class);

	@RequestMapping(value = "test/{apikey}", method = RequestMethod.GET)
	public ModelAndView testGCMController(@PathVariable String apikey) throws Exception {
		System.out.println("this program for test only------- apkKey : " + apikey);
		logger.info("this program for test only------- apkKey : " + apikey);
		String url = "https://gcm-http.googleapis.com/gcm/send";
		String apiKey = apikey;

		ModelAndView mav = new ModelAndView();
		List<String> registrationIds = new ArrayList();

		registrationIds.add("aaaaaaaaaa_key1");
		registrationIds.add("bbbbbbbbb_key2");

		NotificationHandler notificationHandler = new NotificationHandler(null, null, null, null);
		NotificationDTO notificationDto = new NotificationDTO();
		notificationDto.setMsgId(Long.parseLong("102"));
		notificationDto.setTitle("title");
		notificationDto.setType("test");
		notificationDto.setMessage("msg");
		MessageDTO messageDTO = new MessageDTO();

		messageDTO.setNotification(notificationDto);
		messageDTO.setRegistrationIds(registrationIds);
		logger.info("try to send out the GCM message");

		notificationHandler.sendGoogleNotificationPost(url, apiKey, messageDTO);
		mav.setViewName("basic/index");
		return mav;
	}
}
