package com.backend.infocare.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.backend.infocare.IntegrationTest;
import com.backend.infocare.domain.Ticket;
import com.backend.infocare.repository.TicketRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TicketResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TicketResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RESOLUTION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESOLUTION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CLOSED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLOSED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LIMIT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LIMIT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_IMPACT = "AAAAAAAAAA";
    private static final String UPDATED_IMPACT = "BBBBBBBBBB";

    private static final String DEFAULT_RESOLUTION = "AAAAAAAAAA";
    private static final String UPDATED_RESOLUTION = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHMENTS = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tickets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTicketMockMvc;

    private Ticket ticket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ticket createEntity(EntityManager em) {
        Ticket ticket = new Ticket()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .createdAt(DEFAULT_CREATED_AT)
            .resolutionDate(DEFAULT_RESOLUTION_DATE)
            .closedAt(DEFAULT_CLOSED_AT)
            .limitDate(DEFAULT_LIMIT_DATE)
            .impact(DEFAULT_IMPACT)
            .resolution(DEFAULT_RESOLUTION)
            .attachments(DEFAULT_ATTACHMENTS);
        return ticket;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ticket createUpdatedEntity(EntityManager em) {
        Ticket ticket = new Ticket()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .createdAt(UPDATED_CREATED_AT)
            .resolutionDate(UPDATED_RESOLUTION_DATE)
            .closedAt(UPDATED_CLOSED_AT)
            .limitDate(UPDATED_LIMIT_DATE)
            .impact(UPDATED_IMPACT)
            .resolution(UPDATED_RESOLUTION)
            .attachments(UPDATED_ATTACHMENTS);
        return ticket;
    }

    @BeforeEach
    public void initTest() {
        ticket = createEntity(em);
    }

    @Test
    @Transactional
    void createTicket() throws Exception {
        int databaseSizeBeforeCreate = ticketRepository.findAll().size();
        // Create the Ticket
        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isCreated());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeCreate + 1);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTicket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTicket.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testTicket.getResolutionDate()).isEqualTo(DEFAULT_RESOLUTION_DATE);
        assertThat(testTicket.getClosedAt()).isEqualTo(DEFAULT_CLOSED_AT);
        assertThat(testTicket.getLimitDate()).isEqualTo(DEFAULT_LIMIT_DATE);
        assertThat(testTicket.getImpact()).isEqualTo(DEFAULT_IMPACT);
        assertThat(testTicket.getResolution()).isEqualTo(DEFAULT_RESOLUTION);
        assertThat(testTicket.getAttachments()).isEqualTo(DEFAULT_ATTACHMENTS);
    }

    @Test
    @Transactional
    void createTicketWithExistingId() throws Exception {
        // Create the Ticket with an existing ID
        ticket.setId(1L);

        int databaseSizeBeforeCreate = ticketRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = ticketRepository.findAll().size();
        // set the field null
        ticket.setTitle(null);

        // Create the Ticket, which fails.

        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isBadRequest());

        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = ticketRepository.findAll().size();
        // set the field null
        ticket.setDescription(null);

        // Create the Ticket, which fails.

        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isBadRequest());

        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ticketRepository.findAll().size();
        // set the field null
        ticket.setCreatedAt(null);

        // Create the Ticket, which fails.

        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isBadRequest());

        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTickets() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        // Get all the ticketList
        restTicketMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticket.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].resolutionDate").value(hasItem(DEFAULT_RESOLUTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].closedAt").value(hasItem(DEFAULT_CLOSED_AT.toString())))
            .andExpect(jsonPath("$.[*].limitDate").value(hasItem(DEFAULT_LIMIT_DATE.toString())))
            .andExpect(jsonPath("$.[*].impact").value(hasItem(DEFAULT_IMPACT)))
            .andExpect(jsonPath("$.[*].resolution").value(hasItem(DEFAULT_RESOLUTION)))
            .andExpect(jsonPath("$.[*].attachments").value(hasItem(DEFAULT_ATTACHMENTS)));
    }

    @Test
    @Transactional
    void getTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        // Get the ticket
        restTicketMockMvc
            .perform(get(ENTITY_API_URL_ID, ticket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ticket.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.resolutionDate").value(DEFAULT_RESOLUTION_DATE.toString()))
            .andExpect(jsonPath("$.closedAt").value(DEFAULT_CLOSED_AT.toString()))
            .andExpect(jsonPath("$.limitDate").value(DEFAULT_LIMIT_DATE.toString()))
            .andExpect(jsonPath("$.impact").value(DEFAULT_IMPACT))
            .andExpect(jsonPath("$.resolution").value(DEFAULT_RESOLUTION))
            .andExpect(jsonPath("$.attachments").value(DEFAULT_ATTACHMENTS));
    }

    @Test
    @Transactional
    void getNonExistingTicket() throws Exception {
        // Get the ticket
        restTicketMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket
        Ticket updatedTicket = ticketRepository.findById(ticket.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTicket are not directly saved in db
        em.detach(updatedTicket);
        updatedTicket
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .createdAt(UPDATED_CREATED_AT)
            .resolutionDate(UPDATED_RESOLUTION_DATE)
            .closedAt(UPDATED_CLOSED_AT)
            .limitDate(UPDATED_LIMIT_DATE)
            .impact(UPDATED_IMPACT)
            .resolution(UPDATED_RESOLUTION)
            .attachments(UPDATED_ATTACHMENTS);

        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTicket.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTicket))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTicket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTicket.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTicket.getResolutionDate()).isEqualTo(UPDATED_RESOLUTION_DATE);
        assertThat(testTicket.getClosedAt()).isEqualTo(UPDATED_CLOSED_AT);
        assertThat(testTicket.getLimitDate()).isEqualTo(UPDATED_LIMIT_DATE);
        assertThat(testTicket.getImpact()).isEqualTo(UPDATED_IMPACT);
        assertThat(testTicket.getResolution()).isEqualTo(UPDATED_RESOLUTION);
        assertThat(testTicket.getAttachments()).isEqualTo(UPDATED_ATTACHMENTS);
    }

    @Test
    @Transactional
    void putNonExistingTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ticket.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticket))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticket))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTicketWithPatch() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket using partial update
        Ticket partialUpdatedTicket = new Ticket();
        partialUpdatedTicket.setId(ticket.getId());

        partialUpdatedTicket
            .createdAt(UPDATED_CREATED_AT)
            .closedAt(UPDATED_CLOSED_AT)
            .limitDate(UPDATED_LIMIT_DATE)
            .attachments(UPDATED_ATTACHMENTS);

        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicket))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTicket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTicket.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTicket.getResolutionDate()).isEqualTo(DEFAULT_RESOLUTION_DATE);
        assertThat(testTicket.getClosedAt()).isEqualTo(UPDATED_CLOSED_AT);
        assertThat(testTicket.getLimitDate()).isEqualTo(UPDATED_LIMIT_DATE);
        assertThat(testTicket.getImpact()).isEqualTo(DEFAULT_IMPACT);
        assertThat(testTicket.getResolution()).isEqualTo(DEFAULT_RESOLUTION);
        assertThat(testTicket.getAttachments()).isEqualTo(UPDATED_ATTACHMENTS);
    }

    @Test
    @Transactional
    void fullUpdateTicketWithPatch() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket using partial update
        Ticket partialUpdatedTicket = new Ticket();
        partialUpdatedTicket.setId(ticket.getId());

        partialUpdatedTicket
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .createdAt(UPDATED_CREATED_AT)
            .resolutionDate(UPDATED_RESOLUTION_DATE)
            .closedAt(UPDATED_CLOSED_AT)
            .limitDate(UPDATED_LIMIT_DATE)
            .impact(UPDATED_IMPACT)
            .resolution(UPDATED_RESOLUTION)
            .attachments(UPDATED_ATTACHMENTS);

        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicket))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTicket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTicket.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTicket.getResolutionDate()).isEqualTo(UPDATED_RESOLUTION_DATE);
        assertThat(testTicket.getClosedAt()).isEqualTo(UPDATED_CLOSED_AT);
        assertThat(testTicket.getLimitDate()).isEqualTo(UPDATED_LIMIT_DATE);
        assertThat(testTicket.getImpact()).isEqualTo(UPDATED_IMPACT);
        assertThat(testTicket.getResolution()).isEqualTo(UPDATED_RESOLUTION);
        assertThat(testTicket.getAttachments()).isEqualTo(UPDATED_ATTACHMENTS);
    }

    @Test
    @Transactional
    void patchNonExistingTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ticket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticket))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticket))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeDelete = ticketRepository.findAll().size();

        // Delete the ticket
        restTicketMockMvc
            .perform(delete(ENTITY_API_URL_ID, ticket.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
