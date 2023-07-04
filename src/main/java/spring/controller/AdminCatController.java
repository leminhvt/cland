package spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.Defines;
import spring.model.bean.Category;
import spring.model.dao.CategoryDAO;
import spring.model.dao.LandDAO;

@Controller
@RequestMapping("/admin/cat")
public class AdminCatController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private LandDAO landDAO;

	@GetMapping("index")
	public String cat(ModelMap modelMap) {
		List<Category> list = categoryDAO.getItems();
		modelMap.addAttribute("list", list);
		return "cland.admin.cat.index";
	}

	@GetMapping("add")
	public String add() {
		return "cland.admin.cat.add";
	}

	@PostMapping("add")
	public String add(@Valid @ModelAttribute("category") Category category, BindingResult br, RedirectAttributes ra) {
		if (br.hasErrors()) {
			return "cland.admin.cat.add";
		}
		if (categoryDAO.add(category) > 0) {
			ra.addFlashAttribute("msg", Defines.MSG_ADD_SUCCESS);
			return "redirect:/admin/cat/index";
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_ERROR);
			return "redirect:/admin/cat/index";
		}

	}

	@GetMapping("del/{id}")
	public String del(@PathVariable int id, RedirectAttributes ra) {
		if (categoryDAO.delItem(id) > 0) {
			landDAO.delCat(id);
			ra.addFlashAttribute("msg", Defines.MSG_DELETE_SUCCESS);
			return "redirect:/admin/cat/index";
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_ERROR);
			return "redirect:/admin/cat/index";
		}

	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable int id, ModelMap modelMap) {
		Category category = categoryDAO.getItem(id);
		modelMap.addAttribute("category", category);
		return "cland.admin.cat.edit";
	}

	@PostMapping("edit/{id}")
	public String edit(@PathVariable int id, @Valid @ModelAttribute("category") Category category, BindingResult br,
			RedirectAttributes ra) {
		category.setCid(id);
		if (br.hasErrors()) {
			return "cland.admin.cat.edit";
		}
		if (categoryDAO.editItem(category) > 0) {
			ra.addFlashAttribute("msg", Defines.MSG_UPDATE_SUCCESS);
			return "redirect:/admin/cat/index";
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_ERROR);
			return "redirect:/admin/cat/index";
		}

	}

}
