package spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.Defines;
import spring.model.bean.Category;
import spring.model.bean.Land;
import spring.model.dao.CategoryDAO;
import spring.model.dao.LandDAO;
import spring.util.FileNameUtil;

@Controller
@RequestMapping("/admin/new")
public class AdminNewController {

	@Autowired
	private LandDAO landDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ServletContext context;

	@GetMapping("index")
	public String index(@RequestParam(name = "page", required = false) Integer page, ModelMap modelMap) {

		int numberOfItems = landDAO.coutItems();
		int numberOfPage = (int) Math.ceil(numberOfItems * 1.0 / Defines.ROW_COUNT);

		if (page == null) {
			page = 1;
		} else if (page < 1) {
			return "redirect:/admin/new/index";
		} else if (page > numberOfPage) {
			return "redirect:/admin/new/index?page=" + numberOfPage;
		}
		int offset = (page - 1) * Defines.ROW_COUNT;

		List<Land> listL = landDAO.getItemsPagination(offset);
		modelMap.addAttribute("listL", listL);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPage", numberOfPage);
		return "cland.admin.new.index";
	}

	@GetMapping("search")
	public String search(@RequestParam("search") String search, ModelMap modelMap) {
		ArrayList<Land> listS = landDAO.getItemSearch(search);
		modelMap.addAttribute("listS", listS);
		return "cland.admin.new.search";
	}

	@GetMapping("add")
	public String add(ModelMap modelMap) {
		List<Category> listC = categoryDAO.getItems();
		modelMap.addAttribute("listC", listC);
		return "cland.admin.new.add";
	}

	@PostMapping("add")
	public String add(@Valid @ModelAttribute Land land, BindingResult br,
			@RequestParam("hinhanh") CommonsMultipartFile cmf, @RequestParam("cid") int cid, ModelMap modelMap,
			RedirectAttributes ra) {

		land.setCategory(new Category(cid, null));

		if (br.hasErrors()) {
			List<Category> listC = categoryDAO.getItems();
			modelMap.addAttribute("listC", listC);
			return "cland.admin.new.add";
		}

		String fileName = FileNameUtil.rename(cmf.getOriginalFilename());
		land.setPicture(fileName);

		if (landDAO.add(land) > 0) {
			String dirPath = context.getRealPath("") + "WEB-INF" + File.separator + Defines.DIR_UPLOAD; // duong dan thu
																										// muc
			System.out.println(dirPath);

			File file = new File(dirPath);

			if (!file.exists()) {
				file.mkdirs();
			}
			String filePath = dirPath + File.separator + fileName; // duong dan anh
			try {
				cmf.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

			ra.addFlashAttribute("msg", Defines.MSG_ADD_SUCCESS);
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_ERROR);
		}
		return "redirect:/admin/new/index";

	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable int id, ModelMap modelMap) {
		Land land = landDAO.getItem(id);
		modelMap.addAttribute("land", land);
		List<Category> listC = categoryDAO.getItems();
		modelMap.addAttribute("listC", listC);
		return "cland.admin.new.edit";
	}

	@PostMapping("edit/{id}")
	public String edit(@Valid @ModelAttribute Land land, BindingResult br,
			@RequestParam("hinhanh") CommonsMultipartFile cmf, @RequestParam("cid") int cid, ModelMap modelMap,
			RedirectAttributes ra, @PathVariable("id") int id) {

		land.setLid(id);
		land.setCategory(new Category(cid, null));

		Land oldLand = landDAO.getItem(land.getLid());

		if (br.hasErrors()) {
			land.setPicture(oldLand.getPicture());
			List<Category> listC = categoryDAO.getItems();
			modelMap.addAttribute("listC", listC);
			return "cland.admin.new.edit";
		}

		String filename = "";
		if ("".equals(cmf.getOriginalFilename())) {
			land.setPicture(oldLand.getPicture());
		} else {
			filename = FileNameUtil.rename(cmf.getOriginalFilename());
			land.setPicture(filename);
		}

		if (landDAO.edit(land) > 0) {
			if (!"".equals(filename)) {
				String dirPath = context.getRealPath("") + "WEB-INF" + File.separator + Defines.DIR_UPLOAD;
				System.out.println(dirPath);
				File file = new File(dirPath);
				if (!file.exists()) {
					file.mkdirs();
				}

				// xoa anh cu
				File oldFile = new File(dirPath + File.separator + oldLand.getPicture());
				oldFile.delete();

				// them anh moi
				try {
					cmf.transferTo(new File(dirPath + File.separator + filename));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

			}
			ra.addFlashAttribute("msg", Defines.MSG_UPDATE_SUCCESS);
			return "redirect:/admin/new/index";
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_UPDATE_SUCCESS);
			return "redirect:/admin/new/index";
		}
	}

	@GetMapping("del/{id}")
	public String del(@PathVariable int id, RedirectAttributes ra) {

		Land land = landDAO.getItem(id);

		if (landDAO.del(id) > 0) {
			File file = new File(context.getRealPath("") + "WEB-INF" + File.separator + Defines.DIR_UPLOAD
					+ File.separator + land.getPicture());
			file.delete();
			ra.addFlashAttribute("msg", Defines.MSG_DELETE_SUCCESS);
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_UPDATE_SUCCESS);
		}
		return "redirect:/admin/new/index";

	}

}
