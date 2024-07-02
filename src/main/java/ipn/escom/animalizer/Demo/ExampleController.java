package ipn.escom.animalizer.Demo;

import ipn.escom.animalizer.entities.User;
import ipn.escom.animalizer.models.request.ExampleCreationRequestModel;
import ipn.escom.animalizer.models.response.ExampleCreatedResponse;
import ipn.escom.animalizer.models.response.ExampleResponse;
import ipn.escom.animalizer.services.ExampleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examples")
public class ExampleController {

    @Autowired
    ExampleService exampleService;

    @PostMapping("/create")
    public ExampleCreatedResponse createExample(Authentication authentication, @RequestBody @Valid  ExampleCreationRequestModel model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String uuid = exampleService.createExample(model, username);
        return new ExampleCreatedResponse(uuid);
    }

    @GetMapping()
    public List<ExampleResponse> getAllExamples(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        return exampleService.getAllExamples(user.getId());
    }

    @GetMapping("/{id}")
    public ExampleResponse getExample(Authentication authentication, @PathVariable int id){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        return exampleService.getExample(id,user.getId());
    }

    @PutMapping("/{id}")
    public ExampleResponse updateExample(Authentication authentication, @PathVariable int id, @RequestBody @Valid ExampleCreationRequestModel model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        return exampleService.updateExample(id, user.getId(), model);
    }

    @DeleteMapping("/{id}")
    public void deleteExample(Authentication authentication, @PathVariable int id){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        exampleService.deleteExample(id, user.getId());
    }

}
