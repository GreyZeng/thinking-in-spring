// tag::head[]
package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.IngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        //  List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP), new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new Ingredient("GRBF", "Ground Beef", Type.PROTEIN), new Ingredient("CARN", "Carnitas", Type.PROTEIN), new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES), new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE), new Ingredient("SLSA", "Salsa", Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("taco", new Taco());
        return "design";
    }

    //    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
//        // return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
//        return StreamSupport.stream(ingredients.spliterator(), false).filter(i -> i.getType().equals(type)).collect(Collectors.toList());
//    }
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid @ModelAttribute("taco") Taco taco, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing taco: " + taco);
        return "redirect:/orders/current";
    }

}
