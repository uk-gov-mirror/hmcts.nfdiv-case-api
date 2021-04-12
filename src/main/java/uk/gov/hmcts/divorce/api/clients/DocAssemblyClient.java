package uk.gov.hmcts.divorce.api.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import uk.gov.hmcts.divorce.api.model.docassembly.DocAssemblyRequest;
import uk.gov.hmcts.divorce.api.model.docassembly.DocAssemblyResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static uk.gov.hmcts.divorce.api.constants.ControllerConstants.SERVICE_AUTHORIZATION;

@FeignClient(name = "doc-assembly-api", primary = false, url = "${doc_assembly.url}")
public interface DocAssemblyClient {
    @PostMapping(
        value = "/api/template-renditions",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    DocAssemblyResponse generateAndStoreDraftPetition(
        @RequestHeader(AUTHORIZATION) String authorisation,
        @RequestHeader(SERVICE_AUTHORIZATION) String serviceAuthorisation,
        DocAssemblyRequest request
    );
}