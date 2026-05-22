public class Snippet__4535026 {

    public Snippet__4535026() {
                final StringBuilder sqlBuilder = new StringBuilder(200);

                sqlBuilder.append("lr.id as id, lr.loan_id as loanId, lr.status_enum as statusEnum, ");
                sqlBuilder.append("mc.display_name as clientName, ");
                sqlBuilder.append("mc.id as clientId, ");
                sqlBuilder.append("ml.account_no as loanAccountNumber, ");
                sqlBuilder.append("lr.reschedule_from_installment as rescheduleFromInstallment, ");
                sqlBuilder.append("lr.reschedule_from_date as rescheduleFromDate, ");
                sqlBuilder.append("lr.recalculate_interest as recalculateInterest, ");
                sqlBuilder.append("lr.reschedule_reason_cv_id as rescheduleReasonCvId, ");
                sqlBuilder.append("cv.code_value as rescheduleReasonCvValue, ");
                sqlBuilder.append("lr.reschedule_reason_comment as rescheduleReasonComment, ");

                sqlBuilder.append("lr.submitted_on_date as submittedOnDate, ");
                sqlBuilder.append("sbu.username as submittedByUsername, ");
                sqlBuilder.append("sbu.firstname as submittedByFirstname, ");
                sqlBuilder.append("sbu.lastname as submittedByLastname, ");

                sqlBuilder.append("lr.approved_on_date as approvedOnDate, ");
                sqlBuilder.append("abu.username as approvedByUsername, ");
                sqlBuilder.append("abu.firstname as approvedByFirstname, ");
                sqlBuilder.append("abu.lastname as approvedByLastname, ");

                sqlBuilder.append("lr.rejected_on_date as rejectedOnDate, ");
                sqlBuilder.append("rbu.username as rejectedByUsername, ");
                sqlBuilder.append("rbu.firstname as rejectedByFirstname, ");
                sqlBuilder.append("rbu.lastname as rejectedByLastname, ");
            
                sqlBuilder.append("tv.id as termId,");
                sqlBuilder.append("tv.term_type as termType,");
                sqlBuilder.append("tv.applicable_date as variationApplicableFrom, ");
                sqlBuilder.append("tv.decimal_value as decimalValue, ");
                sqlBuilder.append("tv.date_value as dateValue, ");
                sqlBuilder.append("tv.is_specific_to_installment as isSpecificToInstallment ");

                sqlBuilder.append("from " + loanRescheduleRequestTableName() + " lr ");
                sqlBuilder.append("left join m_code_value cv on cv.id = lr.reschedule_reason_cv_id ");
                sqlBuilder.append("left join m_appuser sbu on sbu.id = lr.submitted_by_user_id ");
                sqlBuilder.append("left join m_appuser abu on abu.id = lr.approved_by_user_id ");
                sqlBuilder.append("left join m_appuser rbu on rbu.id = lr.rejected_by_user_id ");
                sqlBuilder.append("left join m_loan ml on ml.id = lr.loan_id ");
                sqlBuilder.append("left join m_client mc on mc.id = ml.client_id ");
                sqlBuilder.append("join m_loan_reschedule_request_term_variations_mapping rrtvm on lr.id = rrtvm.loan_reschedule_request_id ");
                sqlBuilder.append("join m_loan_term_variations tv on tv.id = rrtvm.loan_term_variations_id and tv.parent_id is null") ;

                this.schema = sqlBuilder.toString();
            }

}
