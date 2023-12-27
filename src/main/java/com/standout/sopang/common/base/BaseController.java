package com.standout.sopang.common.base;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.standout.sopang.goods.dto.ImageFileDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.standout.sopang.goods.vo.ImageFileVO;


public abstract class BaseController  {
	private static final String CURR_IMAGE_REPO_PATH = "/sopang/file_repo";
	
	@RequestMapping(value="/" ,method={RequestMethod.POST,RequestMethod.GET})
	protected  String viewForm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return "/";
	}
	

	protected String calcSearchPeriod(String fixedSearchPeriod){
		String beginDate=null;
		String endDate=null;
		String endYear=null;
		String endMonth=null;
		String endDay=null;
		String beginYear=null;
		String beginMonth=null;
		String beginDay=null;
		DecimalFormat df = new DecimalFormat("00");
		Calendar cal=Calendar.getInstance();
		
		endYear   = Integer.toString(cal.get(Calendar.YEAR));
		endMonth  = df.format(cal.get(Calendar.MONTH) + 1);
		endDay   = df.format(cal.get(Calendar.DATE));
		endDate = endYear +"-"+ endMonth +"-"+endDay;
		
		if(fixedSearchPeriod == null) {
			cal.add(cal.MONTH, -1);
		}else if(fixedSearchPeriod.equals("today")) {
			cal.add(Calendar.DAY_OF_YEAR,-0);
		}else if(fixedSearchPeriod.equals("one_month")) {
			cal.add(cal.MONTH, -1);
		}else if(fixedSearchPeriod.equals("two_month")) {
			cal.add(cal.MONTH,-2);
		}else if(fixedSearchPeriod.equals("three_month")) {
			cal.add(cal.MONTH,-3);
		}else if(fixedSearchPeriod.equals("six_month")) {
			cal.add(cal.MONTH,-6);
		}
		
		beginYear   = Integer.toString(cal.get(Calendar.YEAR));
		beginMonth  = df.format(cal.get(Calendar.MONTH) + 1);
		beginDay   = df.format(cal.get(Calendar.DATE));
		beginDate = beginYear +"-"+ beginMonth +"-"+beginDay;
		
		return beginDate+","+endDate;
	}
	
	protected List<ImageFileDTO> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		List<ImageFileDTO> fileList= new ArrayList<ImageFileDTO>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()){
			ImageFileDTO imageFileDTO =new ImageFileDTO();
			String fileName = fileNames.next();
			imageFileDTO.setFileType(fileName);
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName=mFile.getOriginalFilename();
			imageFileDTO.setFileName(originalFileName);
			fileList.add(imageFileDTO);
			
			File file = new File(CURR_IMAGE_REPO_PATH +"/"+ fileName);
			if(mFile.getSize()!=0){
				if(! file.exists()){
					if(file.getParentFile().mkdirs()){
							file.createNewFile();
					}
				}
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH +"/"+"temp"+ "/"+originalFileName)); //�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
			}
		}
		return fileList;
	}
}
