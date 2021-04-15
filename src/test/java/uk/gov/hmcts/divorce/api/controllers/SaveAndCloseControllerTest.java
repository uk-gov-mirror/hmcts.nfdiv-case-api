package uk.gov.hmcts.divorce.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.hmcts.divorce.api.config.WebMvcConfig;
import uk.gov.hmcts.divorce.api.config.interceptors.RequestInterceptor;
import uk.gov.hmcts.divorce.api.exceptions.NotificationException;
import uk.gov.hmcts.divorce.api.notification.handler.SaveAndSignOutNotificationHandler;
import uk.gov.service.notify.NotificationClientException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.gov.hmcts.divorce.api.TestConstants.AUTH_HEADER_VALUE;
import static uk.gov.hmcts.divorce.api.TestConstants.SAVE_AND_CLOSE_API_URL;
import static uk.gov.hmcts.divorce.api.TestConstants.SERVICE_AUTHORIZATION;
import static uk.gov.hmcts.divorce.api.util.TestDataHelper.callbackRequest;
import static uk.gov.hmcts.divorce.api.util.TestDataHelper.caseData;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SaveAndCloseController.class)
public class SaveAndCloseControllerTest {
    @MockBean
    private SaveAndSignOutNotificationHandler saveAndSignOutNotificationHandler;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RequestInterceptor requestInterceptor;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    public void givenValidCaseDataWhenCallbackIsInvokedThenSendEmail() throws Exception {
        mockMvc.perform(
            post(SAVE_AND_CLOSE_API_URL)
                .contentType(APPLICATION_JSON)
                .header(SERVICE_AUTHORIZATION, AUTH_HEADER_VALUE)
                .content(objectMapper.writeValueAsBytes(callbackRequest()))
        ).andExpect(
            status().isOk()
        );

        verify(saveAndSignOutNotificationHandler).notifyApplicant(caseData());
        verifyNoMoreInteractions(saveAndSignOutNotificationHandler);
    }

    @Test
    public void givenHandlerThrowsExceptionWhenCallbackIsInvokedThenReturnBadRequest() throws Exception {
        doThrow(new NotificationException(new NotificationClientException("All template params not passed")))
            .when(saveAndSignOutNotificationHandler).notifyApplicant(eq(caseData()));

        mockMvc.perform(
            post(SAVE_AND_CLOSE_API_URL)
                .contentType(APPLICATION_JSON)
                .header(SERVICE_AUTHORIZATION, AUTH_HEADER_VALUE)
                .content(objectMapper.writeValueAsBytes(callbackRequest()))
        ).andExpect(
            status().isBadRequest()
        ).andExpect(
            content().string("All template params not passed")
        );
    }
}