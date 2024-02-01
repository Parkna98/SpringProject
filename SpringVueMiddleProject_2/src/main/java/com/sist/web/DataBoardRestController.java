package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;

// Vue와 데이터 통신
// Vue / React ==> Router => 데이터 통신 (송수신) => 화면 변경 
// Spring기반으로 만들기위해 Router를 사용하지않고 
// 화면만 변경해주는 Controller, 데이터 통신을 위한 RestController로 나눠서 사용한다


@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@GetMapping(value="databoard/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String databoard_list_vue(int page) throws JsonProcessingException {
		int rowSize=10;
		int start=(page*rowSize)-(rowSize-1);
		int end=page*rowSize;
		List<DataBoardVO> list=dao.dataBoardListData(start, end);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value="databoard/page_vue.do",produces="text/plain;charset=UTF-8")
	public String databoard_page(int page) throws JsonProcessingException {
		int totalpage=dao.dataBoardTotalPage();
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map); // {"curpage":1,"totalpage":10}
		return json;
	}
	
	 @PostMapping(value="databoard/insert_vue.do",produces = "text/plain;charset=UTF-8")
	 public String databoard_insert(DataBoardVO vo,HttpServletRequest request) {
		 // RestController에서는 페이지이동 (redirect: 등등)이 안되기때문에
		 // URL로 <scirpt> location.href("") 처럼 이동한다
		 String result="no";
		 try {
			 String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
			 System.out.println(path);
			 path=path.replace("\\", File.separator); // 운영체제의 호환
			 // Hosting => AWS(리눅스)
			 File dir=new File(path);
			 if(!dir.exists())
				 dir.mkdir();
			 
			 List<MultipartFile> list=vo.getFiles(); // 임시저장 상태
			 if(list==null) {
				 // 업로드가 없는 상태
				 vo.setFilename("");
				 vo.setFilesize("");
				 vo.setFilecount(0);
				 
			 }else {
				 // 업로드가 있음
				 String filename="";
				 String filesize="";
				 for(MultipartFile mf:list) {
					 String name=mf.getOriginalFilename();
					 File file=new File(path+name);
					 mf.transferTo(file); // 파일을 가져와서 업로드 시키는 메소드
					 
					 filename+=name+",";
					 filesize+=file.length()+",";
				 }
				 filename=filename.substring(0,filename.lastIndexOf(","));
				 filesize=filesize.substring(0,filesize.lastIndexOf(","));
				 vo.setFilename(filename);
				 vo.setFilesize(filesize);
				 vo.setFilecount(list.size());
			 }
			 dao.databoardInsert(vo);
			 result="yes";
		 }catch(Exception ex) {
			 result=ex.getMessage();
		 }
		 
		 return result;
	 }
	 
	 @GetMapping(value="databoard/detail_vue.do",produces = "text/plain;charset=UTF-8")
	 public String databoard_detail_vue(int no) throws JsonProcessingException {
		 DataBoardVO vo=dao.databoardDetail(no);
		 ObjectMapper mapper=new ObjectMapper();
		 String json=mapper.writeValueAsString(vo);
		 return json;
	 }
	
	 @GetMapping(value="databoard/download.do")
	 public void databoard_download(String fn,HttpServletRequest request,HttpServletResponse response) {
		 String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		 path=path.replace("\\", File.separator);
		 
		 try {
			 File file=new File(path+fn);
			 response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn,"UTF-8"));
			 response.setContentLength((int)file.length());
			 // => 다운로드 창을 보여준다
			 
			 BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			 // 서버에서 파일을 읽어온다
			 BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			 // 클라이언트 복사영역 (다운로드 폴더)에다가 복사를 하겠다
			 int i=0;
			 byte[] buffer=new byte[1024];
			 while((i=bis.read(buffer,0,1024))!=-1) {
				 // i => 읽은 바이트 수
				 // -1 : EOF (End Of File)
				 bos.write(buffer,0,i);
				 
			 }
			 bis.close();
			 bos.close();
			 
		 }catch(Exception ex) {}
	 }
	
}
