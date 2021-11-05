package com.douzone.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.BlogServiceException;
import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.web.util.ImageUtil;

@Service
public class BlogService {
	private static String SAVE_PATH = "/upload-images/jblog03/admin";
	private static String URL_BASE = "/admin/images";	
	
	@Autowired
	private BlogRepository blogRepository;
	
	public boolean setBlogContent(BlogVo vo) {
		return blogRepository.insert(vo);
	}
	
	public BlogVo getBlogContent(BlogVo vo) {
		return blogRepository.getBlog(vo);
	}
	
	public boolean updateBlogContent(BlogVo vo, MultipartFile file) {
		boolean count = false;
		try {
			File uploadDirectory = new File(SAVE_PATH);
			
			if (!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}
			
			if (file.isEmpty()) {
				String logo = blogRepository.getBlog(vo).getLogo();
				
				BlogVo blogVo = new BlogVo();
				blogVo.setId(vo.getId());
				blogVo.setTitle(vo.getTitle());
				blogVo.setLogo(logo);
				
				count = blogRepository.update(blogVo);
			} else {
				String originFilename = file.getOriginalFilename();
				String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);
				String saveFilename = ImageUtil.generateSaveFilename(extName);
				
				byte[] data = file.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
				os.write(data);
				os.close();
				
				BlogVo blogVo = new BlogVo();
				blogVo.setId(vo.getId());
				blogVo.setTitle(vo.getTitle());
				blogVo.setLogo(URL_BASE + "/" + saveFilename);
				
				count = blogRepository.update(blogVo);
			}			
		} catch(IOException ex) {
			throw new BlogServiceException("file upload error:" + ex);
		}
		
		return count;
	}
}
