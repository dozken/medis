package io.mersys.medis.web.rest;

import static io.mersys.medis.web.rest.util.TestUtil.createFormattingConversionService;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.mersys.medis.MedisApplication;
import io.mersys.medis.documents.Parameter;
import io.mersys.medis.documents.type.ParameterType;
import io.mersys.medis.repository.ParameterRepository;
import io.mersys.medis.service.ParameterService;
import io.mersys.medis.service.dto.ParameterDTO;
import io.mersys.medis.service.dto.ParameterSearchDTO;
import io.mersys.medis.service.mapper.SysParameterMapper;
import io.mersys.medis.web.rest.impl.ParameterResourceImpl;
import io.mersys.medis.web.rest.util.TestUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MedisApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"feign.hystrix.enabled = false" })
@ActiveProfiles("test")
@DisplayName("Parameter Resource API test ...")
@DirtiesContext
public class ParameterResourceTest {

	private static final ParameterType UPDATED_PARAMETER_TYPE = ParameterType.ALLERGY_CATEGORY;
	private static final String UPDATED_PARAMETER_TYPE_VALUE = "Allergen";
	private static final String BASE_API = "/api/parameter";
	private static final ParameterType DEFAULT_PARAMETER_TYPE = ParameterType.REGION;
	private static final String DEFAULT_PARAMETER_TYPE_VALUE = "City";

	private Parameter document;

	private MockMvc restParameterMockMvc;

	@Autowired
	private SysParameterMapper mapper;

	@Autowired
	private ParameterService service;

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
	private MongoTemplate template;

	@Autowired
	private ParameterRepository repository;

	@BeforeEach
	@DisplayName("Parameter test init ...")
	public void initTest() {
		document = createDocument();
	}

	@BeforeEach
	@DisplayName("Parameter test setup ...")
	public void setup() {

		MockitoAnnotations.initMocks(this);

		final ParameterResource resource = new ParameterResourceImpl(service);
		this.restParameterMockMvc = MockMvcBuilders.standaloneSetup(resource)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
				.build();
	}

	@AfterEach
	@DisplayName("Parameter test down ...")
	public void tearDown() {
		template.getMongoDbFactory().getDb().drop();
	}

	private Parameter createDocument() {
		Parameter doc = new Parameter();
		doc.setType(DEFAULT_PARAMETER_TYPE);
		doc.setValue(DEFAULT_PARAMETER_TYPE_VALUE);
		return doc;
	}

	private void prepareDocument() {
		template.save(document);
	}
	
	@Test
	@DisplayName("Create parameter test ...")
	public void create() throws Exception {
		//GIVEN
		final ParameterDTO dto = mapper.toDto(document);
		
		//WHEN
		ResultActions perform = restParameterMockMvc.perform(post(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
								.content(TestUtil.convertObjectToJsonBytes(dto)));
		
		//THEN
		perform.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.type").value(""+document.getType()))
				.andExpect(jsonPath("$.value").value(document.getValue()));
	}

	@Test
	@DisplayName("Update parameter test ...")
	public void update() throws Exception {
		// GIVEN
		prepareDocument();
		document.setType(UPDATED_PARAMETER_TYPE);
		document.setValue(UPDATED_PARAMETER_TYPE_VALUE);
		final ParameterDTO dto = mapper.toDto(document);

		// WHEN
		ResultActions perform = restParameterMockMvc.perform(put(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(dto)));

		// THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(document.getId()))
				.andExpect(jsonPath("$.type").value("" + document.getType()))
				.andExpect(jsonPath("$.value").value(document.getValue()));

	}
	
	@Test
	@DisplayName("Delete parameter test ...")
	public void remove() throws Exception{
		//GIVEN		
		prepareDocument();
		
		//WHEN
		ResultActions perform = restParameterMockMvc.perform(delete(BASE_API + "/" + document.getId()));
		
		//THEN
		perform.andExpect(status().isNoContent()).andExpect(jsonPath("$.id").doesNotExist())
				.andExpect(jsonPath("$.type").doesNotExist())
				.andExpect(jsonPath("$.value").doesNotExist());
	}

	@Test
	@DisplayName("Get all parameters test ...")
	public void findAll() throws Exception{
		// GIVEN
		prepareDocument();
		
		//WHEN
		ResultActions perform = restParameterMockMvc.perform(get(BASE_API));
		
		//THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
				.andExpect(jsonPath("$.[*].type").value(hasItem(""+document.getType())))
				.andExpect(jsonPath("$.[*].value").value(hasItem(document.getValue())));
	}
	
	@Test
	@DisplayName("Search parameter test ...")
	public void search() throws Exception{
		//GIVEN
		prepareDocument();
		ParameterSearchDTO searchDTO = new ParameterSearchDTO();
		searchDTO.setType(document.getType());
		searchDTO.setValue("");
		
		//WHEN
		ResultActions perform = restParameterMockMvc.perform(post(BASE_API + "/search")
								.contentType(TestUtil.API_VERSION1_JSON_UTF8)
								.content(TestUtil.convertObjectToJsonBytes(searchDTO)));
		
		//THEN
		
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
				.andExpect(jsonPath("$.[*].type").value(hasItem(""+document.getType())))
				.andExpect(jsonPath("$.[*].value").value(hasItem(document.getValue())));
	}
	
	@Test
	@DisplayName("Get parameter by id test ...")
	public void find() throws Exception{
		//GIVEN
		prepareDocument();
		
		//WHEN
		ResultActions perform = restParameterMockMvc.perform(get(BASE_API + "/" +document.getId()));
		
		//THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(document.getId()))
				.andExpect(jsonPath("$.type").value(""+document.getType()))
				.andExpect(jsonPath("$.value").value(document.getValue()));
	}
	
	@Test
	public void testRepo() {
		// GIVEN
		List<Parameter> listBefore = repository.findAll();
		int sizeBefore = listBefore.size();
		// WHEN
		Parameter createdData = repository.save(document);

		// THEN
		List<Parameter> listAfter = repository.findAll();
		int sizeAfter = listAfter.size();

		assertEquals(sizeBefore + 1, sizeAfter);
		assertEquals(document.getType(), createdData.getType());
	}

}
