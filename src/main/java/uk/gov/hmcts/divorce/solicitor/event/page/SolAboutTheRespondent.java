package uk.gov.hmcts.divorce.solicitor.event.page;

import uk.gov.hmcts.ccd.sdk.api.Event.EventBuilder;
import uk.gov.hmcts.ccd.sdk.api.FieldCollection.FieldCollectionBuilder;
import uk.gov.hmcts.divorce.ccd.CcdPageConfiguration;
import uk.gov.hmcts.divorce.common.model.CaseData;
import uk.gov.hmcts.divorce.common.model.State;
import uk.gov.hmcts.divorce.common.model.UserRole;

public class SolAboutTheRespondent implements CcdPageConfiguration {

    @Override
    public void addTo(FieldCollectionBuilder<CaseData, EventBuilder<CaseData, UserRole, State>> fieldCollectionBuilder) {

        fieldCollectionBuilder
            .page("SolAboutTheRespondent")
            .pageLabel("About the respondent")
            .label(
                "LabelSolAboutEditingApplication-AboutRespondent",
                "You can make changes at the end of your application.")
            .mandatory(CaseData::getRespondentFirstName)
            .mandatory(CaseData::getRespondentLastName)
            .mandatory(CaseData::getRespondentNameAsOnMarriageCertificate)
            .optional(
                CaseData::getRespNameDifferentToMarriageCertExplain,
                "respondentNameAsOnMarriageCertificate=\"Yes\"")
            .mandatory(CaseData::getInferredRespondentGender);
    }
}
