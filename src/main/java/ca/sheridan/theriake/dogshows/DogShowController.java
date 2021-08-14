package ca.sheridan.theriake.dogshows;

import ca.sheridan.theriake.dogshows.beans.Dog;
import ca.sheridan.theriake.dogshows.beans.Response;
import ca.sheridan.theriake.dogshows.beans.ShowData;
import ca.sheridan.theriake.dogshows.beans.User;
import ca.sheridan.theriake.dogshows.repositories.DogRepository;
import ca.sheridan.theriake.dogshows.repositories.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DogShowController {

    @Autowired
    @Lazy
    private SecurityRepository securityRepo;

    @Autowired
    @Lazy
    private DogRepository dogRepo;

    @GetMapping("/")
    public String getIndex() {
        return "index.html";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login.html";
    }

    @GetMapping("/accessdenied")
    public String getAccessDenied() {
        return "/error/accessdenied.html";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "/register.html";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password) {
        securityRepo.registerUser(username, password);
        long userId = securityRepo.findUser(username).getUserId();
        securityRepo.addRole(userId, 2);
        return "redirect:/";
    }

    @GetMapping("/user")
    public String getUser(Authentication auth) {
        String username = auth.getName();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority ga : auth.getAuthorities()) {
            roles.add(ga.getAuthority());
        }
        return "/user/index.html";
    }

    @GetMapping("/user/adddog")
    public String getAddDog(Model model, Authentication auth) {
        boolean isAdmin = false;
        String username = auth.getName();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority ga : auth.getAuthorities()) {
            roles.add(ga.getAuthority());
        }

        for (String admin : roles) {
            if (admin.contains("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }

        Dog dog = new Dog(username);

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("dog", dog);
        model.addAttribute("breeds", dogRepo.getBreedNameOptions());
        return "/user/adddog.html";
    }

    @PostMapping("/user/adddog")
    public String postAddDog(@ModelAttribute Dog dog) {
        String username = dog.getOwnerName();
        User user = securityRepo.findUser(username);
        dogRepo.addDog(dog, user);
        return "redirect:/user/adddog";
    }

    @GetMapping("/user/userdoglist")
    public String getUserDogList(Model model, Authentication auth) {
        boolean isAdmin = false;
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority ga : auth.getAuthorities()) {
            roles.add(ga.getAuthority());
        }
        for (String admin : roles) {
            if (admin.contains("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }

        Long userId = securityRepo.findUser(auth.getName()).getUserId();

        if(isAdmin){
            model.addAttribute("dogs", dogRepo.getAllDogs());
        }else {
            model.addAttribute("dogs", dogRepo.getUserDogs(userId));
        }


        return "/user/userdoglist.html";
    }

    @GetMapping("/user/deleteconfirm")
    public String getDeleteConfirm(@RequestParam String id, Authentication auth,
                                   Model model) {

//        TODO: Make this a method in security repo.
        boolean isAdmin = false;
        String username = auth.getName();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority ga : auth.getAuthorities()) {
            roles.add(ga.getAuthority());
        }
        for (String admin : roles) {
            if (admin.contains("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        Long userId = securityRepo.findUser(auth.getName()).getUserId();
        model.addAttribute("dog", dogRepo.getDogDetails(Long.valueOf(id)));
//        Ensures that whoever is visiting this page is the owner of the dog.
        if (dogRepo.checkOwner(Long.valueOf(id), userId) || isAdmin) {

            return "/user/deleteconfirm.html";
        } else {
            return "/error/accessdenied.html";
        }
    }

    @PostMapping("/user/deleteconfirm")
    public String postDeleteConfirm(@RequestParam String dogid){
        dogRepo.deleteDog(Long.valueOf(dogid));
        return"redirect:/user/userdoglist";
    }

    @GetMapping("/user/editdog")
    public String getEditDog(@RequestParam String dogid, Model model,
                             Authentication auth){
        boolean isAdmin = false;
        String username = auth.getName();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority ga : auth.getAuthorities()) {
            roles.add(ga.getAuthority());
        }
        for (String admin : roles) {
            if (admin.contains("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        Dog dog = dogRepo.getDogDetails(Long.valueOf(dogid));

        model.addAttribute("dog", dog);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("breeds", dogRepo.getBreedNameOptions());
        return"/user/editdog.html";
    }

    @PostMapping("/user/editdog")
    public String postEditDog(@ModelAttribute Dog dog, @RequestParam String dogId){
        String username = dog.getOwnerName();
        Long userid = securityRepo.findUser(username).getUserId();

        dogRepo.editDog(dog, userid, Long.valueOf(dogId));
        return "redirect:/user/userdoglist";
    }

    @GetMapping("/showlist")
    public String getShowlist(Model model){
        model.addAttribute("types", dogRepo.getBreedTypeOptions());

        return "/showlist.html";
    }

    @PostMapping("/getData")
    @ResponseBody
    public Response getData(@RequestParam String category){
        ArrayList<ShowData> showListByType = dogRepo.getDogByType(category);
        Response response = new Response();

        response.setStatus(true);
        response.setMessage("Retrieval succeeded");
        response.setData(showListByType);

        System.out.println(showListByType);

        return response;
    }
}
