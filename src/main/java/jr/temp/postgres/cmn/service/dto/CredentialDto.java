package jr.temp.postgres.cmn.service.dto;

import lombok.Data;

@Data
public class CredentialDto {

    String leaseId;
    String leaseDuration;
    boolean leaseRenewable;
    String password;
    String username;
}
