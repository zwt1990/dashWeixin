package cjc.web.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cjc.dto.ResFormDTO;
import cjc.entity.reserve.ResForm;
import cjc.entity.reserve.Reserve;
import cjc.service.reserve.ModuleService;
import cjc.service.reserve.ReserveService;
import cjc.web.controller.common.H5Response;

@Controller
@RequestMapping(value="reserve/")
public class ReserveController extends BaseController{

		@Autowired
		private ModuleService	moduleService;
	
		@Autowired
		private ReserveService	reserveService;
		
		@RequestMapping(value = "/queryForms")
	    @ResponseBody
	    public H5Response queryForm(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			List<ResFormDTO> resFormDTO=reserveService.getAllForms();
			return succeed(resFormDTO);
	    }
		
		@RequestMapping(value = "/queryReserve")
	    @ResponseBody
	    public H5Response queryReserve(HttpServletRequest request,
				HttpServletResponse response,Integer formId) throws Exception {
			List<Reserve> res=reserveService.queryReserve(formId);
			return succeed(res);
	    }
		
		
		@RequestMapping("/addForm")
		@ResponseBody
		public H5Response addForm(HttpServletRequest request, MultipartFile file,ResFormDTO resFormDTO) throws IOException {
		
			String url="/img/upload/"+new Date().getTime() + new String(file.getOriginalFilename().getBytes(),"UTF-8");
			resFormDTO.setImgUrl(url);
			ResForm resForm=reserveService.addForm(resFormDTO);
			 //拿到输出流，同时重命名上传的文件  
			String baseUrl= request.getSession().getServletContext()
	                .getRealPath("/")+"sys/static";
			String filePath =baseUrl+url ;
	         FileOutputStream os = new FileOutputStream(filePath);  
	         //拿到上传文件的输入流  
	         FileInputStream in = (FileInputStream) file.getInputStream();  
	         //以写字节的方式写文件  
	         int b = 0;  
	         while((b=in.read()) != -1){  
	             os.write(b);  
	         }  
	         os.flush();  
	         os.close();  
	         in.close();  
	         return succeed();
		}
		
		@RequestMapping(value = "/initForm")
	    @ResponseBody
	    public H5Response initForm(HttpServletRequest request,
				HttpServletResponse response,Integer formId) throws Exception {
			if(formId==null){
				return failed("参数错误");
			}
			ResFormDTO res=reserveService.getResForm(formId);
			return succeed(res);
	    }
		
		@RequestMapping(value = "/submitReserve")
	    @ResponseBody
	    public H5Response submitReserve(HttpServletRequest request,
				HttpServletResponse response,Reserve reserve) throws Exception {
			List<Reserve> res=reserveService.queryByFormIdAndMobile(reserve.getFormId(), reserve.getMobile());
			if(res.size()>0){
				return failed("该手机号码已经预约过了，请勿重复预约！");
			}
			reserveService.submitReserve(reserve);
			return succeed();
	    }
}
