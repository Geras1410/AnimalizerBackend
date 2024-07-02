package ipn.escom.animalizer.services;

import ipn.escom.animalizer.models.request.ExampleCreationRequestModel;
import ipn.escom.animalizer.models.response.ExampleResponse;

import java.util.List;

public interface ExampleService {
    public String createExample(ExampleCreationRequestModel model, String email);
    public List<ExampleResponse> getAllExamples(int id);
    ExampleResponse getExample(int exId, int usId);
    ExampleResponse updateExample(int exId, int usId, ExampleCreationRequestModel model);
    void deleteExample(int exId, int usId);
}