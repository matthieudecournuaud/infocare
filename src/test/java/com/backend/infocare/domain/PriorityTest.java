package com.backend.infocare.domain;

import static com.backend.infocare.domain.PriorityTestSamples.*;
import static com.backend.infocare.domain.TicketTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.backend.infocare.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriorityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Priority.class);
        Priority priority1 = getPrioritySample1();
        Priority priority2 = new Priority();
        assertThat(priority1).isNotEqualTo(priority2);

        priority2.setId(priority1.getId());
        assertThat(priority1).isEqualTo(priority2);

        priority2 = getPrioritySample2();
        assertThat(priority1).isNotEqualTo(priority2);
    }

    @Test
    void ticketTest() throws Exception {
        Priority priority = getPriorityRandomSampleGenerator();
        Ticket ticketBack = getTicketRandomSampleGenerator();

        priority.setTicket(ticketBack);
        assertThat(priority.getTicket()).isEqualTo(ticketBack);
        assertThat(ticketBack.getPriority()).isEqualTo(priority);

        priority.ticket(null);
        assertThat(priority.getTicket()).isNull();
        assertThat(ticketBack.getPriority()).isNull();
    }
}
