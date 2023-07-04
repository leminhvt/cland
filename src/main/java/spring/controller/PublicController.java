package spring.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.Defines;
import spring.model.bean.Category;
import spring.model.bean.Contact;
import spring.model.bean.Land;
import spring.model.bean.Like;
import spring.model.bean.User;
import spring.model.dao.CategoryDAO;
import spring.model.dao.ContactDAO;
import spring.model.dao.LandDAO;
import spring.model.dao.LikeDAO;
import spring.model.dao.UserDAO;

@Controller
public class PublicController {

	@Autowired
	private ContactDAO contactDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private LandDAO landDAO;

	@Autowired
	private LikeDAO likeDAO;

	@Autowired
	private UserDAO userDAO;

	@ModelAttribute
	public void getCat(ModelMap modelMap) {
		List<Category> categories = categoryDAO.getItems();
		modelMap.addAttribute("caterogy", categories);

		List<Land> lands = landDAO.getItemsee();
		modelMap.addAttribute("lands", lands);

		modelMap.addAttribute("landDAO", landDAO);
	}

	@GetMapping("")
	public String index(@RequestParam(name = "page", required = false) Integer page, ModelMap modelMap) {

		int numberOfItems = landDAO.coutItems();
		int numberOfPage = (int) Math.ceil(numberOfItems * 1.0 / Defines.ROW_COUNT);

		if (page == null) {
			page = 1;
		} else if (page < 1) {
			return "redirect:/?page=1";
		} else if (page > numberOfPage) {
			return "redirect:/?page=" + numberOfPage;
		}

		int offset = (page - 1) * Defines.ROW_COUNT;

		List<Land> lands = landDAO.getItemsPagination(offset);
		modelMap.addAttribute("landsL", lands);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPage", numberOfPage);
		return "cland.public.index";
	}

	@GetMapping("cat")
	public String cat() {
		return "cland.public.cat";
	}

	@GetMapping("cat/{id}")
	public String cat(@PathVariable int id, ModelMap modelMap) {

		List<Land> lands = landDAO.getItemsById(id);
		modelMap.addAttribute("lands", lands);

		Category category = categoryDAO.getItem(id);
		modelMap.addAttribute("category", category);
		return "cland.public.cat";
	}

	@GetMapping("single")
	public String single() {
		return "cland.public.single";
	}

	@GetMapping("single/{id}")
	public String single(@PathVariable int id, ModelMap modelMap, HttpSession session) {
		Land land = landDAO.getItem(id);
		modelMap.addAttribute("land", land);
		List<Land> list = landDAO.getRelatedItems(land, 3);
		modelMap.addAttribute("list", list);
		return "cland.public.single";
	}

	@PostMapping("updateLike")
	@ResponseBody
	public int single(@RequestParam("alandId") int alandId, ModelMap modelMap, HttpServletRequest request) {
		int coust_view = landDAO.countLand(alandId);
		int ketqua =  landDAO.getCount(alandId);
		return ketqua;
	}

	@GetMapping("contact")
	public String contact() {
		return "cland.public.contact";
	}

	@PostMapping("contact")
	public String contact(@Valid @ModelAttribute("contact") Contact contact, BindingResult br, RedirectAttributes ra) {
		if (br.hasErrors()) {
			return "cland.public.contact";
		}

		if (contactDAO.add(contact) > 0) {
			ra.addFlashAttribute("msg", Defines.MSG_ADD_SUCCESS);
			return "redirect:/contact";
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_ERROR);
			return "redirect:/contact";

		}

	}

}
