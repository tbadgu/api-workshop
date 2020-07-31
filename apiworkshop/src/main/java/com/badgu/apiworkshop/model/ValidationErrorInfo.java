package com.badgu.apiworkshop.model;

import com.atlassian.oai.validator.report.ValidationReport;

import java.util.List;

public class ValidationErrorInfo extends ErrorInfo {
    private ValidationErrorReport errorReport;

    protected ValidationErrorInfo() {
        super();
    }

    public ValidationErrorInfo(String requestUrl, String description, ValidationErrorReport errorReport) {
        super(requestUrl, description);
        this.errorReport = errorReport;
    }

    public ValidationErrorReport getErrorReport() {
        return errorReport;
    }

    public static class ValidationErrorReport {
        private final List<ValidationReport.Message> messages;

        public ValidationErrorReport(List<ValidationReport.Message> messages) {
            this.messages = messages;
        }

        public List<ValidationReport.Message> getMessages() {
            return messages;
        }

        @Override
        public String toString() {
            return "ValidationErrorReport{" +
                    "messages=" + messages +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ValidationErrorInfo{" +
                "errorReport=" + errorReport +
                ", requestUrl='" + requestUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
