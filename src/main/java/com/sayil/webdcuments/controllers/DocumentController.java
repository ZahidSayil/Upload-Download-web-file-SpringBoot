package com.sayil.webdcuments.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.sayil.webdcuments.entities.Document;
import com.sayil.webdcuments.repo.DocumentRepository;

@Controller
public class DocumentController {

	@Autowired
	DocumentRepository repository;

	@RequestMapping("displayUpload")
	public String displayUpload(ModelMap map) {
		retrieveDocuments(map);
		return "documentUpload";
	}

	private void retrieveDocuments(ModelMap map) {
		List<Document> documents = repository.findAll();
		System.out.println(documents.size());
		map.addAttribute("documents", documents);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	// document in ReqParam is from upload jsp
	public String uploadDocument(@RequestParam("document") MultipartFile file, @RequestParam("id") long id,
			ModelMap map) {
		Document document = new Document();
		document.setId(id);
		document.setName(file.getOriginalFilename());
		try {
			document.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repository.save(document);
		retrieveDocuments(map);

		return "documentUpload";
	}

	@RequestMapping("/download")
	public StreamingResponseBody download(@RequestParam("id") long id, HttpServletResponse response) {

		Document document = repository.getOne(id);
		byte[] data = document.getData();

		response.setHeader("Content-Disposition", "attachment;filename=downloaded.jpeg");
		return outputStream -> {
			outputStream.write(data);
		};
	}

}
