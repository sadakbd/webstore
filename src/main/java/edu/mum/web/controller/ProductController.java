package edu.mum.web.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.web.domain.Product;
import edu.mum.web.exception.NoProductsFoundUnderCategoryException;
import edu.mum.web.exception.ProductNotFoundException;
import edu.mum.web.service.ProductService;
import edu.mum.web.validator.ProductValidator;
import edu.mum.web.validator.UnitsInStockValidator;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UnitsInStockValidator unitsInStockValidator;
	
	@Autowired
	private ProductValidator productValidator;

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setDisallowedFields("unitsInOrder", "discontinued");
		/*
		 * binder.setAllowedFields("productId",
		 * "name","unitPrice","description","manufacturer",
		 * "category","unitsInStock",
		 * "productImage","condition","productManual");
		 */
		binder.setAllowedFields("productId", "name", "unitPrice",
				"description", "manufacturer", "category", "unitsInStock",
				"productImage", "condition","language");
		//binder.setValidator(unitsInStockValidator);
		binder.setValidator(productValidator);

	}

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	/*
	 * @RequestMapping("all") public String allProducts(Model model){
	 * model.addAttribute("products",productService.getAllProducts()); return
	 * "products"; }
	 */

	@RequestMapping("all")
	public ModelAndView allProducts() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	}

	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model,
			@PathVariable("category") String productCategory) {

		List<Product> products = productService
				.getProductsByCategory(productCategory);

		if (products == null || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", products);
		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
			Model model) {

		System.out.println(filterParams.toString());

		model.addAttribute("products",
				productService.getProductsByFilter(filterParams));
		return "products";
	}

	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId,
			Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	@RequestMapping("/{category}/{price}")
	public String filterProduct(
			@PathVariable("category") String productCategory,
			@MatrixVariable(pathVar = "price") Map<String, String> priceRange,
			@RequestParam("manufacturer") String manufacturer, Model model) {
		System.out.println("I ame here..");
		// System.out.println("productCategory: "+productCategory);
		// System.out.println("manufacturer: "+manufacturer);
		// System.out.println(priceRange.toString());
		model.addAttribute("products",
				productService.getProductsByManufacturer(manufacturer));
		return "products";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	// public String getAddNewProductForm(Model model){
	public String getAddNewProductForm(
			@ModelAttribute("newProduct") Product newProduct) {
		// Product newProduct = new Product();
		// model.addAttribute("newProduct",newProduct);

		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(
			@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "addProduct";
		}
		String[] suppressedFields = result.getSuppressedFields();

		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext()
				.getRealPath("/");
		System.out.println(rootDirectory);
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(rootDirectory
						+ "resources\\images\\" + newProduct.getProductId()
						+ ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		/*
		 * MultipartFile productManual = newProduct.getProductManual(); if
		 * (productManual != null && !productManual.isEmpty()) { try {
		 * productManual.transferTo(new File(rootDirectory+"resources\\pdf\\" +
		 * newProduct.getProductId() + ".pdf")); } catch (Exception e) { throw
		 * new RuntimeException("Product Manual saving failed", e); } }
		 */

		productService.addProduct(newProduct);
		return "redirect:/products";
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req,
			ProductNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}

	@RequestMapping("/invalidPromoCode")
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}

}
