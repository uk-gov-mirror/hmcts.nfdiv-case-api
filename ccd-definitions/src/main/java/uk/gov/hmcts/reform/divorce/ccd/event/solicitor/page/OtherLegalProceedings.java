package uk.gov.hmcts.reform.divorce.ccd.event.solicitor.page;

import uk.gov.hmcts.ccd.sdk.api.Event;
import uk.gov.hmcts.ccd.sdk.api.FieldCollection;
import uk.gov.hmcts.reform.divorce.ccd.CcdPageConfiguration;
import uk.gov.hmcts.reform.divorce.ccd.model.CaseData;
import uk.gov.hmcts.reform.divorce.ccd.model.State;
import uk.gov.hmcts.reform.divorce.ccd.model.UserRole;

public class OtherLegalProceedings implements CcdPageConfiguration {
    @Override
    public void addTo(FieldCollection.FieldCollectionBuilder<CaseData, Event.EventBuilder<CaseData, UserRole, State>> fieldCollectionBuilder) {
        fieldCollectionBuilder
            .page("OtherLegalProceedings")
            .pageLabel("Other legal proceedings")
            .label(
                "LabelSolAboutEditingApplication-AboutRespondent",
                "You can make changes at the end of your application.")
            .mandatory(CaseData::getLegalProceedings)
            .optional(
                CaseData::getLegalProceedingsDetails,
                "legalProceedings=\"Yes\""
            );
    }
}
