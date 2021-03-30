package uk.gov.hmcts.reform.divorce.ccd;

import uk.gov.hmcts.ccd.sdk.api.ConfigBuilder;
import uk.gov.hmcts.reform.divorce.ccd.model.CaseData;
import uk.gov.hmcts.reform.divorce.ccd.model.State;
import uk.gov.hmcts.reform.divorce.ccd.model.UserRole;

import static uk.gov.hmcts.reform.divorce.ccd.Permissions.CREATE_READ_UPDATE;
import static uk.gov.hmcts.reform.divorce.ccd.Permissions.READ;
import static uk.gov.hmcts.reform.divorce.ccd.model.State.Draft;
import static uk.gov.hmcts.reform.divorce.ccd.model.State.SOTAgreementPayAndSubmitRequired;
import static uk.gov.hmcts.reform.divorce.ccd.model.UserRole.CASEWORKER_DIVORCE_COURTADMIN;
import static uk.gov.hmcts.reform.divorce.ccd.model.UserRole.CASEWORKER_DIVORCE_COURTADMIN_BETA;
import static uk.gov.hmcts.reform.divorce.ccd.model.UserRole.CASEWORKER_DIVORCE_COURTADMIN_LA;
import static uk.gov.hmcts.reform.divorce.ccd.model.UserRole.CASEWORKER_DIVORCE_SOLICITOR;
import static uk.gov.hmcts.reform.divorce.ccd.model.UserRole.CASEWORKER_DIVORCE_SUPERUSER;
import static uk.gov.hmcts.reform.divorce.ccd.model.UserRole.CITIZEN;

public class NoFaultDivorce implements CcdConfiguration {

    public static final String CASE_TYPE = "NO_FAULT_DIVORCE4";
    public static final String JURISDICTION = "DIVORCE";

    @Override
    public void applyTo(final ConfigBuilder<CaseData, State, UserRole> configBuilder) {

        configBuilder.caseType(CASE_TYPE, "No Fault Divorce case", "Handling of the dissolution of marriage");
        configBuilder.jurisdiction(JURISDICTION, "Family Divorce", "Family Divorce: dissolution of marriage");

        configBuilder.grant(Draft, CREATE_READ_UPDATE, CITIZEN);
        configBuilder.grant(Draft, READ, CASEWORKER_DIVORCE_COURTADMIN_BETA);
        configBuilder.grant(Draft, READ, CASEWORKER_DIVORCE_COURTADMIN);
        configBuilder.grant(Draft, READ, CASEWORKER_DIVORCE_SOLICITOR);
        configBuilder.grant(Draft, READ, CASEWORKER_DIVORCE_SUPERUSER);
        configBuilder.grant(Draft, READ, CASEWORKER_DIVORCE_COURTADMIN_LA);

        configBuilder.grant(SOTAgreementPayAndSubmitRequired, CREATE_READ_UPDATE, CASEWORKER_DIVORCE_SOLICITOR);
        configBuilder.grant(SOTAgreementPayAndSubmitRequired, CREATE_READ_UPDATE, CASEWORKER_DIVORCE_SUPERUSER);
        configBuilder.grant(SOTAgreementPayAndSubmitRequired, READ, CASEWORKER_DIVORCE_COURTADMIN_LA);
    }
}