package uk.gov.hmcts.reform.divorce.ccd.event.solicitor.page;

import uk.gov.hmcts.ccd.sdk.api.Event.EventBuilder;
import uk.gov.hmcts.ccd.sdk.api.FieldCollection.FieldCollectionBuilder;
import uk.gov.hmcts.reform.divorce.ccd.CcdPageConfiguration;
import uk.gov.hmcts.reform.divorce.ccd.model.CaseData;
import uk.gov.hmcts.reform.divorce.ccd.model.State;
import uk.gov.hmcts.reform.divorce.ccd.model.UserRole;

public class SolStatementOfTruth implements CcdPageConfiguration {

    @Override
    public void addTo(final FieldCollectionBuilder<CaseData, EventBuilder<CaseData, UserRole, State>> fieldCollectionBuilder) {

        fieldCollectionBuilder
            .page("SolStatementOfTruth")
            .pageLabel("Statement of truth and reconciliation")
            .label("LabelSolStatementOfTruthPara-1", "## The petitioner is applying to the court")
            .label(
                "LabelSolStatementOfTruthPara-1.1",
                "That the marriage be dissolved as it has broken down irretrievably.")
            .label(
                "LabelSolStatementOfTruthPara-1.2",
                "That a costs order may be granted.")
            .label(
                "LabelSolStatementOfTruthPara-1.3",
                "That a financial order may be granted.")
            .mandatory(CaseData::getSolUrgentCase)
            .optional(CaseData::getSolUrgentCaseSupportingInformation, "SolUrgentCase=\"Yes\"")
            .mandatoryNoSummary(CaseData::getD8DivorceCostsClaim, "D8StatementOfTruth=\"Banana\"")
            .mandatoryNoSummary(CaseData::getD8FinancialOrder, "D8StatementOfTruth=\"Banana\"")
            .label("LabelSolServiceMethod", "## Service method")
            .mandatory(CaseData::getSolServiceMethod)
            .label(
                "LabelSolPersonalService",
                "After service is complete you must notify the court by completing the 'Confirm Service' form "
                    + "in CCD. Refer to the information pack for further instruction on how to do this",
                "SolServiceMethod=\"personalService\"")
            .label("LabelSolStatementOTruthPara-3", "## Statement of reconciliation")
            .mandatory(CaseData::getSolStatementOfReconciliationCertify)
            .mandatory(CaseData::getSolStatementOfReconciliationDiscussed)
            .label("LabelSolStatementOfTruthPara-2", "## Statement of truth")
            .mandatory(CaseData::getD8StatementOfTruth)
            .mandatory(CaseData::getSolSignStatementOfTruth)
            .mandatory(CaseData::getSolStatementOfReconciliationName)
            .mandatory(CaseData::getSolStatementOfReconciliationFirm)
            .label(
                "LabelSolStatementOTruthPara-7",
                "You could be fined or imprisoned for contempt of court if you deliberately submit false information.")
            .label(
                "LabelSolStatementOTruthPara-8",
                "If you have any comments you would like to make to the court staff regarding the application "
                    + "you may include them below.")
            .optionalNoSummary(CaseData::getStatementOfReconciliationComments)
            .readonlyNoSummary(CaseData::getSolApplicationFeeInPounds);
    }
}