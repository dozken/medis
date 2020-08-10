package io.mersys.medis.web.rest;

import static io.mersys.medis.web.rest.util.TestUtil.createFormattingConversionService;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import io.mersys.medis.documents.Sample;
import io.mersys.medis.documents.doctor.Doctor;
import io.mersys.medis.documents.type.DepartmentType;
import io.mersys.medis.documents.type.RoleType;
import io.mersys.medis.documents.type.SampleType;
import io.mersys.medis.service.DoctorService;
import io.mersys.medis.service.dto.DoctorDTO;
import io.mersys.medis.service.dto.DoctorSearchDTO;
import io.mersys.medis.service.dto.SampleDTO;
import io.mersys.medis.service.mapper.DoctorMapper;
import io.mersys.medis.web.rest.impl.DoctorResourceImpl;
import io.mersys.medis.web.rest.impl.SampleResourceImpl;
import io.mersys.medis.web.rest.util.TestUtil;
import io.mersys.medis.documents.doctor.Role;
import io.mersys.medis.documents.patient.Address;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MedisApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"feign.hystrix.enabled = false" })
@ActiveProfiles("test")
@DisplayName("Doctor Resource API test ...")
@DirtiesContext
public class DoctorResourceTest {
	private Doctor document;
	private MockMvc restDoctorMockMvc;
	private static final String NAME = "DOCTOR_NAME";
	private static final String UPDATED_NAME = "DOCTOR_UPDATED_NAME";
	
	private static final String SURNAME = "DOCTOR_SURNAME";
	private static final String PATRONYMIC = "DOCTOR_PATRONYMIC";
	private static final String PIN = "DOCTOR_PIN";
	private static final String BASE_API = "/api/doctor";
	@Autowired
	private DoctorMapper mapper;
	
	@Autowired
	private DoctorService service;

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
	private MongoTemplate template;

	@BeforeEach
	@DisplayName("Doctor test init ...")
	public void initTest() {
		document = createDocument();
	}
	@BeforeEach
	@DisplayName("Doctor test setup ...")
	public void setup() {
		MockitoAnnotations.initMocks(this);

		final DoctorResource resource = new DoctorResourceImpl(service);
		this.restDoctorMockMvc = MockMvcBuilders.standaloneSetup(resource)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				// .setControllerAdvice(exceptionTranslator)
				.setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
				.build();
	}



	private Doctor createDocument() {
		Doctor doc = new Doctor();
		doc.setName(NAME);
		doc.setSurname(SURNAME);
		doc.setPatronymic(PATRONYMIC);
		doc.setPin(PIN);
		doc.setDateOfBirth(LocalDate.now());
		doc.setAddress(new Address(null,null,null,null,"87473494616"));
		//doc.setRole(new Role(document.getName(),RoleType.NURSE,DepartmentType.HEMODIALYSIS,""));
		doc.setRole(new Role(DepartmentType.HEMODIALYSIS,RoleType.NURSE,"",""));
		return doc;
	}

	private void prepareDocument() {
		template.save(document);
	}
	@Test
	@DisplayName("Get all doctor test ...")
	public void findAll() throws Exception {
		// GIVEN
		prepareDocument();

		// WHEN
		ResultActions perform = restDoctorMockMvc.perform(get(BASE_API));

		// THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
				.andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())))
				.andExpect(jsonPath("$.[*].surname").value(hasItem(document.getSurname())))

				.andExpect(jsonPath("$.[*].patronymic").value(hasItem(document.getPatronymic())))
				.andExpect(jsonPath("$.[*].pin").value(hasItem(document.getPin())))
				.andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(""+document.getDateOfBirth())))
				.andExpect(jsonPath("$.[*].address.phoneNumber").value(hasItem(document.getAddress().getPhoneNumber())));
	}
	@Test
	@DisplayName("Get doctor by id test ...")
	public void find() throws Exception {
		// GIVEN
		prepareDocument();

		// WHEN
		ResultActions perform = restDoctorMockMvc.perform(get(BASE_API + "/" + document.getId()));

		// THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
		.andExpect(jsonPath("$.id").value(document.getId()))
		.andExpect(jsonPath("$.name").value(document.getName()))
		.andExpect(jsonPath("$.surname").value(document.getSurname()))
		.andExpect(jsonPath("$.patronymic").value(document.getPatronymic()))
		.andExpect(jsonPath("$.pin").value(document.getPin()))
		.andExpect(jsonPath("$.dateOfBirth").value(""+document.getDateOfBirth()))
		.andExpect(jsonPath("$.address").value(document.getAddress()))
		.andExpect(jsonPath("$.role").value(document.getRole()));
	}
	@Test
	@DisplayName("Create doctor test ...")
	public void create() throws Exception {
		// GIVEN
		final DoctorDTO dto = mapper.toDto(document);

		// WHEN
		ResultActions perform = restDoctorMockMvc.perform(post(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(dto)));

		// THEN
		perform.andExpect(status().isCreated()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value(document.getName()))
				.andExpect(jsonPath("$.surname").value(document.getSurname()))

				.andExpect(jsonPath("$.patronymic").value(document.getPatronymic()))
				.andExpect(jsonPath("$.pin").value(document.getPin()))
				.andExpect(jsonPath("$.dateOfBirth").value(""+document.getDateOfBirth()))
				.andExpect(jsonPath("$.address").value(document.getAddress()))
				.andExpect(jsonPath("$.role").value(document.getRole()));
	}
	@Test
	@DisplayName("Update doctor test ...")
	public void update() throws Exception {
		// GIVEN
		prepareDocument();
		document.setName(UPDATED_NAME);
		final DoctorDTO dto = mapper.toDto(document);

		// WHEN
		ResultActions perform = restDoctorMockMvc.perform(put(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(dto)));

		// THEN
		perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(document.getId()))
				.andExpect(jsonPath("$.name").value(UPDATED_NAME));
	}
	@Test
	@DisplayName("Delete doctor test ...")
	public void remove() throws Exception {
		// GIVEN
		prepareDocument();

		// WHEN
		ResultActions perform = restDoctorMockMvc.perform(delete(BASE_API + "/" + document.getId()));

		// THEN
		perform.andExpect(status().isNoContent()).andExpect(jsonPath("$.id").doesNotExist());
	}
	@Test
	@DisplayName("Search doctor test ...")
	public void search() throws Exception {
		// GIVEN
		prepareDocument();
		DoctorSearchDTO searchDTO=new DoctorSearchDTO();
		searchDTO.setFullInfo(document.getName().substring(0, 2));
		searchDTO.setRoleType(RoleType.NURSE);
		searchDTO.setDepType(DepartmentType.HEMODIALYSIS);
		// WHEN
				ResultActions perform = restDoctorMockMvc.perform(post(BASE_API + "/search")
						.contentType(TestUtil.API_VERSION1_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(searchDTO)));

				// THEN
				perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
				.andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
				
				.andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())))
				
				.andExpect(jsonPath("$.[*].surname").value(hasItem(document.getSurname())))

				.andExpect(jsonPath("$.[*].patronymic").value(hasItem(document.getPatronymic())))

				.andExpect(jsonPath("$.[*].pin").value(hasItem(document.getPin())))

				.andExpect(jsonPath("$.[*].address.phoneNumber").value(hasItem(document.getAddress().getPhoneNumber())))
				
				.andExpect(jsonPath("$.[*].role.roleType").value(hasItem(""+document.getRole().getRoleType().getValue())))
				.andExpect(jsonPath("$.[*].role.depType").value(hasItem(document.getRole().getDepType().getValue())));

	}	
}
