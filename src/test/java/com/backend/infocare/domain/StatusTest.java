package com.backend.infocare.domain;

import static com.backend.infocare.domain.StatusTestSamples.*;
import static com.backend.infocare.domain.TicketTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.backend.infocare.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Status.class);
        Status status1 = getStatusSample1();
        Status status2 = new Status();
        assertThat(status1).isNotEqualTo(status2);

        status2.setId(status1.getId());
        assertThat(status1).isEqualTo(status2);

        status2 = getStatusSample2();
        assertThat(status1).isNotEqualTo(status2);
    }

    @Test
    void ticketTest() throws Exception {
        Status status = getStatusRandomSampleGenerator();
        Ticket ticketBack = getTicketRandomSampleGenerator();

        status.setTicket(ticketBack);
        assertThat(status.getTicket()).isEqualTo(ticketBack);
        assertThat(ticketBack.getStatus()).isEqualTo(status);

        status.ticket(null);
        assertThat(status.getTicket()).isNull();
        assertThat(ticketBack.getStatus()).isNull();
    }
}
