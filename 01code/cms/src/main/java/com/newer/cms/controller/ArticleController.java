package com.newer.cms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newer.cms.mapper.ArticleMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Article;
import com.newer.cms.service.ArticleService;
import com.newer.cms.utils.LoadUtils;

@RestController
public class ArticleController {
	
	@Autowired
	private ArticleMapper  articleMapper;
	
	@Autowired
	private ArticleService  articleService;
	
	@GetMapping("/queryListArticle")
	public ResponseEntity<?> queryListArticle(){
		List<Article> queryListArticle = articleMapper.queryListArticle();
		return new ResponseEntity<List<Article>>(queryListArticle,HttpStatus.OK);
	}

	
	/**
	 * 得到用户信息
	 * 
	 * @param pageNoStr
	 * @param PageSizeStr
	 * @return 分页效果的用户数据集合
	 */
	@SuppressWarnings("unused")
	@GetMapping("/pageArticle")
	public ResponseEntity<?> getPageByArticle(
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSizeStr", required = false, defaultValue = "10") Integer PageSizeStr) {
		Page<Article> page = articleService.getPageByArticle(pageNoStr, PageSizeStr);
		page.setStatus(HttpStatus.OK);
		if (page == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Page<Article>>(page, HttpStatus.OK);
	}
	
	
	/**
	 * 根据id删Article
	 * @param aid
	 * @return
	 */
	@RequestMapping(value="/deleteA/{aid}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteArticle(@PathVariable("aid") Integer aid){
		if(aid==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		String deleteArticle = articleService.deleteArticle(aid);
		return new ResponseEntity<String>(deleteArticle,HttpStatus.OK);
	}
	
	
	/**
	 * 添加Article
	 * @param aid
	 * @return
	 */
	@RequestMapping(value="/inserArticle",method=RequestMethod.POST)
	public ResponseEntity<?> inserArticle(@RequestBody Article article){
		Integer inserArticle = articleMapper.inserArticle(article);
		return new ResponseEntity<>(inserArticle>0?"1":"0",HttpStatus.OK);
	}
	
	/**
	 * 修改Article
	 * @param id
	 * @param article
	 * @return
	 */
	@PutMapping(value="/inserArticle/{aid}")
	public ResponseEntity<?> updateArti(@PathVariable("aid") Integer aid,@RequestBody Article article){
		System.out.println(article);
		article.setAid(aid);
		int updateArticle = articleMapper.updateArticle(article);
		return new ResponseEntity<String>(updateArticle>0?"1":"0",HttpStatus.OK);
	}
	
	/**
	 * .上传文件
	 * @return 图片地址
	 */
	@PostMapping("/uploadImg")
	public ResponseEntity<?> uploadImg(MultipartFile file, HttpServletRequest request){
		Map<String,String> map=new HashMap<>();
			String upload;
			try {
				upload = LoadUtils.upload(file, request);
				map.put("code", "0");
				map.put("data", upload);
				System.out.println(upload);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		return  new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
    } 
	
	/**
	 * .根据id查询文章
	 */
	@GetMapping("/article/{aid}")
	public ResponseEntity<?> findArticleById(@PathVariable("aid") Integer aid
			){
		Article act=articleService.findArticleById(aid);
		return new ResponseEntity<Article>(act,HttpStatus.OK);
	}
	
	/**
	 * .得到读取图片的流
	 */
	@GetMapping("/getimg/{aid}")
	public void getimg(@PathVariable("aid") Integer aid,HttpServletResponse rep){
		Article act=articleService.findArticleById(aid);
		String str=act.getAboutimg();
		File file=new File(str);
		 InputStream fis;
		try {
			fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
            fis.close();
            rep.reset();
            OutputStream toClient = new BufferedOutputStream(rep.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
         
		
	}
	
	@DeleteMapping("/deleteArticleAll")
	public ResponseEntity<?> deleteArticleAll(@RequestParam("id[]") Integer[] id){
		String msg=articleService.deleteArticleAll(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/updateArticleById/{aid}")
	public ResponseEntity<?> updateArticleById(@PathVariable("aid") Integer aid){
		System.out.println(aid);
		int i=articleService.updateArticleById(aid);
		return new ResponseEntity<String>(i>0?"1":"2",HttpStatus.OK);
	}
	
	@PutMapping("/updateArticleById2/{aid}")
	public ResponseEntity<?> updateArticleById2(@PathVariable("aid") Integer aid){
		System.out.println("updateArticleById2"+aid);
		int i=articleService.updateArticleById2(aid);
		return new ResponseEntity<String>(i>0?"1":"2",HttpStatus.OK);
	}
	
	@GetMapping("/getStatus/{aid}")
	public ResponseEntity<?> getStatus(@PathVariable("aid") Integer aid){
		int i=articleService.getStatus(aid);
		return new ResponseEntity<String>(i==0?"0":(i==1?"1":"2"),HttpStatus.OK);
	}
	
}
