package jr.temp.postgres.cmn.dto;

import lombok.Data;

@Data
public class CredentialDto {

    String leaseId;
    String leaseDuration;
    boolean leaseRenewable;
    String password;
    String username;
}
