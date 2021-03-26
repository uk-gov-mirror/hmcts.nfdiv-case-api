package uk.gov.hmcts.reform.divorce.ccd.event.solicitor.page;

import uk.gov.hmcts.ccd.sdk.api.Event.EventBuilder;
import uk.gov.hmcts.ccd.sdk.api.FieldCollection.FieldCollectionBuilder;
import uk.gov.hmcts.reform.divorce.ccd.CcdPageConfiguration;
import uk.gov.hmcts.reform.divorce.ccd.model.CaseData;
import uk.gov.hmcts.reform.divorce.ccd.model.State;
import uk.gov.hmcts.reform.divorce.ccd.model.UserRole;

public class SolAboutTheRespondent implements CcdPageConfiguration {

    @Override
    public void addTo(FieldCollectionBuilder<CaseData, EventBuilder<CaseData, UserRole, State>> fieldCollectionBuilder) {

        fieldCollectionBuilder
            .page("SolAboutTheRespondent")
            .pageLabel("About the respondent")
            .label(
                "LabelSolAboutEditingApplication-AboutRespondent",
                "You can make changes at the end of your application.")
            .mandatory(CaseData::getD8RespondentFirstName)
            .mandatory(CaseData::getD8RespondentLastName)
            .mandatory(CaseData::getD8RespondentNameAsOnMarriageCertificate)
            .optional(
                CaseData::getRespNameDifferentToMarriageCertExplain,
                "D8RespondentNameAsOnMarriageCertificate=\"Yes\"")
            .mandatory(CaseData::getD8InferredRespondentGender);
    }
}