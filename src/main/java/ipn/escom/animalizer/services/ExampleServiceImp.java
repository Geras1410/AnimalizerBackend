package ipn.escom.animalizer.services;

import ipn.escom.animalizer.Repository.ExampleRepository;
import ipn.escom.animalizer.Repository.UserRepository;
import ipn.escom.animalizer.entities.Example;
import ipn.escom.animalizer.entities.User;
import ipn.escom.animalizer.models.request.ExampleCreationRequestModel;
import ipn.escom.animalizer.models.response.ExampleResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExampleServiceImp implements ExampleService{

    ExampleRepository exampleRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;

    public ExampleServiceImp(ExampleRepository exampleRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.exampleRepository = exampleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String createExample(ExampleCreationRequestModel model, String email) {
        User user = userRepository.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        ModelMapper modelMapper = new ModelMapper();
        Example example = modelMapper.map(model, Example.class);
        example.setUser(user);
        example.setExid(UUID.randomUUID().toString());
        exampleRepository.save(example);
        return example.getExid();
    }

    @Override
    public List<ExampleResponse> getAllExamples(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        int uid = user.getId();
        List<Example> examples =  exampleRepository.findByUser_Id(uid);
        return examples.stream()
                .map(example -> modelMapper.map(example, ExampleResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExampleResponse getExample(int exId, int usId) {
        Example example = exampleRepository.findByIdAndUser_Id(exId, usId);
        return modelMapper.map(example, ExampleResponse.class);
    }

    @Override
    public ExampleResponse updateExample(int exId, int usId, ExampleCreationRequestModel model) {
        Example example = exampleRepository.findByIdAndUser_Id(exId, usId);
        if(example != null) {
            example.setImageName(model.getImageName());
            example.setFPred(model.getFPred());
            example.setSPred(model.getSPred());
            example.setTPred(model.getTPred());
            exampleRepository.save(example);
            return modelMapper.map(example, ExampleResponse.class);
        }
        return null;
    }

    @Override
    public void deleteExample(int exId, int usId) {
        Example example = exampleRepository.findByIdAndUser_Id(exId, usId);
        if(example != null) {
            exampleRepository.delete(example);
        }
    }
}
