package spring.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.Defines;
import spring.model.bean.Role;
import spring.model.bean.User;
import spring.model.dao.CategoryDAO;
import spring.model.dao.RoleDAO;
import spring.model.dao.UserDAO;

@Controller
@RequestMapping("admin/user")
public class AdminUserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("index")
	public String index(ModelMap modelMap) {
		List<User> users = userDAO.getItems();
		modelMap.addAttribute("users", users);
		return "cland.admin.user.index";
	}

	@GetMapping("add")
	public String add(ModelMap modelMap) {
		List<Role> list = roleDAO.getItems();
		modelMap.addAttribute("list", list);
		return "cland.admin.user.add";
	}

	@PostMapping("add")
	public String add(@Valid @ModelAttribute("user") User user, BindingResult br, RedirectAttributes ra,
			@RequestParam int roleid, ModelMap modelMap) {
		user.setRole(new Role(roleid, null));

		if (userDAO.hasUser(user.getUsername())) {
			br.rejectValue("username", "usernamehasuser");
		}

		if (br.hasErrors()) {
			List<Role> list = roleDAO.getItems();
			modelMap.addAttribute("list", list);
			return "cland.admin.user.add";
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (userDAO.add(user) > 0) {
			ra.addFlashAttribute("msg", Defines.MSG_ADD_SUCCESS);
			return "redirect:/admin/user/index";
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_ERROR);
			return "redirect:/admin/user/index";
		}
	}

	@GetMapping("del/{id}")
	public String delete(@PathVariable int id, RedirectAttributes ra, Principal principal) {

		User user = userDAO.getItem(id);
		if (user.getRole().getName().equals("ADMIN") && user.getUsername().equals(principal.getName())) {
			ra.addFlashAttribute("msg", "Không được xóa!");
			return "redirect:/admin/user/index";
		}
		if (userDAO.delete(id) > 0) {
			ra.addFlashAttribute("msg", Defines.MSG_DELETE_SUCCESS);
			return "redirect:/admin/user/index";
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_ERROR);
			return "redirect:/admin/user/index";
		}

	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable int id, ModelMap modelMap) {
		User user = userDAO.getItem(id);
		modelMap.addAttribute("user", user);
		List<Role> list = roleDAO.getItems();
		modelMap.addAttribute("list", list);
		return "cland.admin.user.edit";
	}

	@PostMapping("edit/{id}")
	public String edit(@Valid @ModelAttribute("user") User user, BindingResult br, RedirectAttributes ra,
			@RequestParam int roleid, ModelMap modelMap, @PathVariable int id) {
		user.setId(id);
		user.setRole(new Role(roleid, null));

		User user2 = userDAO.getItem(user.getId());

		if ("".equals(user.getPassword())) {
			user.setPassword(user2.getPassword());
		}

		if (br.hasErrors()) {
			List<Role> list = roleDAO.getItems();
			modelMap.addAttribute("list", list);
			return "cland.admin.user.edit";
		}

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		if (userDAO.edit(user) > 0) {
			ra.addFlashAttribute("msg", Defines.MSG_UPDATE_SUCCESS);
			return "redirect:/admin/user/index";
		} else {
			ra.addFlashAttribute("msg", Defines.MSG_ERROR);
			return "redirect:/admin/user/index";
		}

	}

	@GetMapping("profile")
	public String profile(ModelMap modelMap, Principal principal) {

		String username = principal.getName();

		User user = userDAO.getItemByUsername(username);
		modelMap.addAttribute("user", user);

		List<Role> list = roleDAO.getItems();
		modelMap.addAttribute("list", list);
		return "cland.admin.user.profile";
	}

	@PostMapping("profile/{id}")
	public String profile(@Valid @ModelAttribute("user") User user, BindingResult br, RedirectAttributes ra,
			@RequestParam int roleid, ModelMap modelMap, @PathVariable int id) {
		user.setId(id);
		user.setRole(new Role(roleid, null));

		User user2 = userDAO.getItem(user.getId());

		if ("".equals(user.getPassword())) {
			user.setPassword(user2.getPassword());
		}

		if (br.hasErrors()) {
			List<Role> list = roleDAO.getItems();
			modelMap.addAttribute("list", list);
			return "cland.admin.user.profile";
		}

		if (userDAO.edit(user) > 0) {
			ra.addFlashAttribute("msg", "Thay đổi thông tin thành công");
			return "redirect:/admincp";
		} else {
			ra.addFlashAttribute("msg", "Thay đổi thông tin không thành công");
			return "redirect:/admin/user/index";
		}

	}
}
