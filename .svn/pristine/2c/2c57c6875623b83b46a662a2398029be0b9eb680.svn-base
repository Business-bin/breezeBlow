package com.brb.status.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brb.status.service.StatusService;

/**
 *
 * @author back
 *
 */
@Controller
@RequestMapping("/status")
public class StatusController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	StatusService statusService;


	/**
	 * 사용현황표
	 * @param String
	 * @return ModelAndView
	 */
	@RequestMapping(value = "status" )
	public ModelAndView status() {
		ModelAndView view = new ModelAndView();
		view.setViewName("status/status");
		return view;
	}

	/**
	 * 사용현황표
	 * @param String
	 * @return ModelAndView
	 */
	@ResponseBody
	@RequestMapping(value = "locstatus" )
	public ModelAndView locstatus(@RequestParam String location) throws DataAccessException, UnsupportedEncodingException{
		ModelAndView view = new ModelAndView();
		String loc = URLDecoder.decode(location, "UTF-8");
		view.addObject("pMap", statusService.getStatus(loc));
		return view;
	}

}
