package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CategoryDao;
import com.example.demo.model.Category;

@Controller
public class CategoryController {
	@Autowired
	private CategoryDao categoryDao;

	/**
	 * query without Pageable
	 *
	 * @return
	 */
//    @RequestMapping("/categorylist")
//    public ModelAndView categorylist() {
//        List<Category> list = categoryDao.findAll();
//
//        ModelAndView mav = new ModelAndView("categorylist");
//        mav.addObject("list", list);
//        return mav;
//    }

	/**
	 * query with Pageable
	 * 
	 * @return
	 */
	@RequestMapping("/categorylist")
	public ModelAndView categorylist(@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "2") Integer limit) {
		start = start < 0 ? 0 : start;

		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "categoryid");
		Pageable pageable = new PageRequest(start, limit, sort);
		Page<Category> page = categoryDao.findAll(pageable);

//        System.out.println(page.getNumber());
//        System.out.println(page.getNumberOfElements());
//        System.out.println(page.getSize());
//        System.out.println(page.getTotalElements());
//        System.out.println(page.getTotalPages());
//        System.out.println(page.isFirst());
//        System.out.println(page.isLast());

		ModelAndView mav = new ModelAndView("categorylist");
		mav.addObject("page", page);
		return mav;
	}

	/**
	 * view for init category
	 * 
	 * @return
	 */
	@RequestMapping("/categoryinit")
	public String categoryinit() {
		return "categoryinit";
	}

	/**
	 * view for add category
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/categoryinsert")
	public String categoryinsert(Category model) {
		categoryDao.save(model);
		return "redirect:categorylist";
	}

	/**
	 * view for delete category
	 * 
	 * @param categoryid
	 * @return
	 */
	@RequestMapping("/categorydelete")
	public String categorydelete(Integer categoryid) {
		categoryDao.deleteById(categoryid);
		return "redirect:categorylist";
	}

	/**
	 * view for edit category
	 * 
	 * @param categoryid
	 * @return
	 */
	@RequestMapping("/categoryedit")
	public ModelAndView categoryedit(Integer categoryid) {
		Category model = categoryDao.getOne(categoryid);

		ModelAndView mav = new ModelAndView("categoryedit");
		mav.addObject("category", model);
		return mav;
	}

	/**
	 * view for update category
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/categoryupdate")
	public String categoryupdate(Category model) {
		categoryDao.save(model);
		return "redirect:categorylist";
	}
}