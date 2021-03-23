package uk.gov.hmcts.reform.divorce.caseapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.divorce.caseapi.clients.FeesAndPaymentsClient;
import uk.gov.hmcts.reform.divorce.caseapi.model.payments.FeeResponse;
import uk.gov.hmcts.reform.divorce.ccd.model.FeeItem;
import uk.gov.hmcts.reform.divorce.ccd.model.FeeValue;
import uk.gov.hmcts.reform.divorce.ccd.model.OrderSummary;

import static java.util.Collections.singletonList;
import static uk.gov.hmcts.reform.divorce.caseapi.enums.NotificationConstants.DEFAULT_CHANNEL;
import static uk.gov.hmcts.reform.divorce.caseapi.enums.NotificationConstants.DIVORCE;
import static uk.gov.hmcts.reform.divorce.caseapi.enums.NotificationConstants.FAMILY;
import static uk.gov.hmcts.reform.divorce.caseapi.enums.NotificationConstants.FAMILY_COURT;
import static uk.gov.hmcts.reform.divorce.caseapi.enums.NotificationConstants.ISSUE_EVENT;
import static uk.gov.hmcts.reform.divorce.ccd.model.FeeValue.getValueInPence;

@Service
@Slf4j
public class SolicitorSubmitPetitionService {
    @Autowired
    private FeesAndPaymentsClient feesAndPaymentsClient;

    public OrderSummary getOrderSummary() {
        FeeResponse feeResponse = feesAndPaymentsClient.getPetitionIssueFee(
            DEFAULT_CHANNEL,
            ISSUE_EVENT,
            FAMILY,
            FAMILY_COURT,
            DIVORCE,
            null
        );

        return OrderSummary
            .builder()
            .fees(singletonList(getFeeItem(feeResponse)))
            .paymentTotal(getValueInPence(feeResponse.getAmount()))
            .build();
    }

    private FeeItem getFeeItem(FeeResponse feeResponse) {
        return FeeItem
            .builder()
            .value(
                FeeValue
                    .builder()
                    .feeAmount(getValueInPence(feeResponse.getAmount()))
                    .feeCode(feeResponse.getFeeCode())
                    .feeDescription(feeResponse.getDescription())
                    .feeVersion(String.valueOf(feeResponse.getVersion()))
                    .build()
            )
            .build();
    }
}