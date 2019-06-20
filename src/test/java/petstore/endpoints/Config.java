package petstore.endpoints;

import petstore.ConfigProperties;


public class Config {
    final static String BASE_URI = ConfigProperties.API_URL;
    final static String CREATE_PET = "pet";
    final static String GET_PET_BY_ID = "pet/{petId}";
    final static String GET_PET_BY_STATUS = "pet/findByStatus";
    final static String DELETE_PET_BY_ID = "pet/{petId}";
    final static String UPDATE_PET_BY_ID = "pet";
    final static String UPLOAD_PET_IMAGE = "pet/{petId}/uploadImage";
}
