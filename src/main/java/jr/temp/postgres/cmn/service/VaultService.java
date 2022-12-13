package jr.temp.postgres.cmn.service;

import jr.temp.postgres.cmn.dto.CredentialDto;

/**
 * Vault操作
 */
public interface VaultService {

    /**
     * 取得认证信息
     * @return 认证信息Dto
     */
    CredentialDto getCredentialInfo();
}