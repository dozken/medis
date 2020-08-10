package io.mersys.medis.web.rest;

import static io.mersys.medis.web.rest.util.TestUtil.createFormattingConversionService;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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
import io.mersys.medis.documents.patient.Patient;
import io.mersys.medis.documents.type.GenderType;
import io.mersys.medis.service.PatientService;
import io.mersys.medis.service.dto.PatientDTO;
import io.mersys.medis.service.dto.PatientSearchDTO;
import io.mersys.medis.service.mapper.PatientMapper;
import io.mersys.medis.web.rest.impl.PatientResourceImpl;
import io.mersys.medis.web.rest.util.TestUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MedisApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"feign.hystrix.enabled=false" })
@ActiveProfiles("test")
@DisplayName("Patient Resouce API test ...")
@DirtiesContext
public class PatientResourceTest {

	private static final String UPDATED_PATIENT_NAME = "Updated Patient name";
	private static final String BASE_API = "/api/patient";
	private static final String DEFAULT_PATIENT_NAME = "Patient Name";
	private static final String PATIENT_SURNAME = "Patient Surname";
	private static final String PATIENT_PATRONYMIC = "Patient Patronymic";
	private static final String PATIENT_PIN = "Patient Pin";

	private Patient document;

	private MockMvc restPatientMockMvc;

	@Autowired
	private PatientMapper mapper;

	@Autowired
	private PatientService service;

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
	private MongoTemplate template;

	@BeforeEach
	@DisplayName("Patient test init ...")
	public void initTest() {
		document = createDocument();
	}

	@BeforeEach
	@DisplayName("Patient test setup ...")
	public void setup() {
		MockitoAnnotations.initMocks(this);

		final PatientResource resource = new PatientResourceImpl(service);
		this.restPatientMockMvc = MockMvcBuilders.standaloneSetup(resource)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				// .setControllerAdvice(exceptionTranslator)
				.setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
				.build();
	}

	@AfterEach
	@DisplayName("Patient test down ...")
	public void tearDown() {
		template.getMongoDbFactory().getDb().drop();
	}

	private Patient createDocument() {
		Patient doc = new Patient();
		doc.setName(DEFAULT_PATIENT_NAME);
		doc.setSurname(PATIENT_SURNAME);
		doc.setPatronymic(PATIENT_PATRONYMIC);
		doc.setPin(PATIENT_PIN);
		doc.setDateOfBirth(LocalDate.now());
		doc.setGender(GenderType.MALE);
		return doc;
	}

	private void prepareDocument() {
		template.save(document);
	}

	@Test
	@DisplayName("Get all patients test ...")
	public void findAll() throws Exception {
		// GIVEN
		prepareDocument();

		// WHEN
		ResultActions perform = restPatientMockMvc.perform(get(BASE_API));

		// THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
				.andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())))
				.andExpect(jsonPath("$.[*].surname").value(hasItem(document.getSurname())))
				.andExpect(jsonPath("$.[*].patronymic").value(hasItem(document.getPatronymic())))
				.andExpect(jsonPath("$.[*].pin").value(hasItem(document.getPin())))
				.andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(""+document.getDateOfBirth())))
				.andExpect(jsonPath("$.[*].gender").value(hasItem(""+document.getGender())));
	}

	@Test
	@DisplayName("Search patients test ...")
	public void search() throws Exception {
		// GIVEN
		prepareDocument();
		PatientSearchDTO searchDTO = new PatientSearchDTO();
		searchDTO.setFullInfo(document.getName().substring(0, 2));
		searchDTO.setGender(GenderType.MALE);
		searchDTO.setAgeFrom(16);
		searchDTO.setAgeTo(50);

		// WHEN
		ResultActions perform = restPatientMockMvc.perform(post(BASE_API + "/search")
				.contentType(TestUtil.API_VERSION1_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(searchDTO)));

		// THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
				.andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())))
				.andExpect(jsonPath("$.[*].surname").value(hasItem(document.getSurname())))
				.andExpect(jsonPath("$.[*].patronymic").value(hasItem(document.getPatronymic())))
				.andExpect(jsonPath("$.[*].pin").value(hasItem(document.getPin())))
				.andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(""+document.getDateOfBirth())))
				.andExpect(jsonPath("$.[*].gender").value(hasItem(""+document.getGender())));
	}

	@Test
	@DisplayName("Get patient by id test ...")
	public void find() throws Exception {
		// GIVEN
		prepareDocument();

		// WHEN
		ResultActions perform = restPatientMockMvc.perform(get(BASE_API + "/" + document.getId()));

		// THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(document.getId()))
				.andExpect(jsonPath("$.name").value(document.getName()))
				.andExpect(jsonPath("$.surname").value(document.getSurname()))
				.andExpect(jsonPath("$.patronymic").value(document.getPatronymic()))
				.andExpect(jsonPath("$.pin").value(document.getPin()))
				.andExpect(jsonPath("$.dateOfBirth").value(""+document.getDateOfBirth()))
				.andExpect(jsonPath("$.gender").value(""+document.getGender()));	
	}

	@Test
	@DisplayName("Create patient test ...")
	public void create() throws Exception {
		// GIVEN
		final PatientDTO dto = mapper.toDto(document);

		// WHEN
		ResultActions perform = restPatientMockMvc.perform(post(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(dto)));

		// THEN
		perform.andExpect(status().isCreated()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value(document.getName()))
				.andExpect(jsonPath("$.surname").value(document.getSurname()))
				.andExpect(jsonPath("$.patronymic").value(document.getPatronymic()))
				.andExpect(jsonPath("$.pin").value(document.getPin()))
				.andExpect(jsonPath("$.dateOfBirth").value(""+document.getDateOfBirth()))
				.andExpect(jsonPath("$.gender").value(""+document.getGender()));
	}

	@Test
	@DisplayName("Update patient test ...")
	public void update() throws Exception {
		// GIVEN
		prepareDocument();
		document.setName(UPDATED_PATIENT_NAME);
		document.setGender(GenderType.FEMALE);
		final PatientDTO dto = mapper.toDto(document);

		// WHEN
		ResultActions perform = restPatientMockMvc.perform(put(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(dto)));

		// THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(document.getId()))
				.andExpect(jsonPath("$.gender").value("" + GenderType.FEMALE))
				.andExpect(jsonPath("$.name").value(UPDATED_PATIENT_NAME));
	}

	@Test
	@DisplayName("Delete patient test ...")
	public void remove() throws Exception {
		// GIVEN
		prepareDocument();

		// WHEN
		ResultActions perform = restPatientMockMvc.perform(delete(BASE_API + "/" + document.getId()));

		// THEN
		perform.andExpect(status().isNoContent()).andExpect(jsonPath("$.id").doesNotExist())
				.andExpect(jsonPath("$.name").doesNotExist());
	}

}
