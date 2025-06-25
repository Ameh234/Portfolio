package com.exam._5.Controller;

import com.exam._5.Models.Comment;
import com.exam._5.Models.FoodOrder;
import com.exam._5.Service.CommentService;
import com.exam._5.Service.FoodOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/foodorders")
public class FoodOrderController {
    @Autowired
    private FoodOrderService foodOrderService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/new")
    public String showNewOrderForm(Model model){
        model.addAttribute("foodorder", new FoodOrder());
        return "new-order";
    }
    @PostMapping("/new")
    public String placeNewOrder(@Valid @ModelAttribute("foodOrder") FoodOrder foodOrder, BindingResult result){
        if (result.hasErrors()){
            return "new-order";
        }
        foodOrderService.saveFoodOrder(foodOrder);
        return "redirect:/foodorders/list";
    }
    @GetMapping("/list")
    public String listFoodOrders(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        Page<FoodOrder> foodOrderPage = foodOrderService.getAllFoodOrdersPaginated(PageRequest.of(page, size));
        model.addAttribute("foodOrderPage", foodOrderPage);

        int totalPages = foodOrderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "food-order-list";
    }
    @GetMapping("/search")
    public String searchFoodOrders(@RequestParam("keyword") String keyword, Model model) {
        List<FoodOrder> searchResults = foodOrderService.searchFoodOrdersByFoodName(keyword);
        model.addAttribute("foodOrders", searchResults);
        return "food-order-search-results";
    }

    @GetMapping("/{orderId}/comment")
    public String showCommentForm(@PathVariable Long orderId, Model model) {
        Optional<FoodOrder> foodOrder = foodOrderService.getFoodOrderById(orderId);
        if (foodOrder.isPresent()) {
            model.addAttribute("foodOrder", foodOrder.get());
            model.addAttribute("comment", new Comment());
            return "add-comment";
        }
        return "redirect:/foodorders/list";
    }

    @PostMapping("/{orderId}/comment")
    public String addComment(@PathVariable Long orderId,
                             @Valid @ModelAttribute("comment") Comment comment,
                             BindingResult result,
                             Model model) {
        Optional<FoodOrder> foodOrder = foodOrderService.getFoodOrderById(orderId);
        if (foodOrder.isPresent()) {
            if (result.hasErrors()) {
                model.addAttribute("foodOrder", foodOrder.get());
                return "add-comment";
            }
            comment.setFoodOrder(foodOrder.get());
            commentService.saveComment(comment);
            return "redirect:/foodorders/list";
        }
        return "redirect:/foodorders/list";
    }
}
