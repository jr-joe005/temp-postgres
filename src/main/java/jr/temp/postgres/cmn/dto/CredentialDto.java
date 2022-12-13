package jr.temp.postgres.cmn.dto;

import lombok.Data;

/**
 * Vault凭证信息
 */
@Data
public class CredentialDto {

    /**
     *  凭证ID
     */
    String leaseId;

    /**
     *  凭证有效期
     *   * eg：1h
     */
    String leaseDuration;

    /**
     *  凭证的可再生性
     *   * 作用暂时不明
     */
    boolean leaseRenewable;

    /**
     * 登录用密码
     */
    String password;

    /**
     * 登录用账户
     */
    String username;
}
