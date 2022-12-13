package jr.temp.postgres.cmn.service;

import jr.temp.postgres.cmn.dto.CredentialDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.support.Versioned;
import org.springframework.vault.core.VaultTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Vault操作
 */
@Slf4j
@Service
class VaultServiceImpl implements VaultService {

    /**
     * 取得认证信息
     * @return 认证信息Dto
     */
    @Override
    public CredentialDto getCredentialInfo() {
        CredentialDto credentialDto = new CredentialDto();

        /* Authenticate to Vault */
        log.info("-- Authenticate to Vault");
        VaultEndpoint vaultEndpoint = new VaultEndpoint();
        vaultEndpoint.setHost("192.168.3.137");
        vaultEndpoint.setPort(8200);
        vaultEndpoint.setScheme("http");
        VaultTemplate vaultTemplate = new VaultTemplate(vaultEndpoint, new TokenAuthentication("dev-only-token"));

        /* Store a secret */
        log.info("-- Store a secret");
        Map<String, String> data = new HashMap<>();
        data.put("password", "Hashi123");
        Versioned.Metadata createResponse = vaultTemplate.opsForVersionedKeyValue("secret").put("my-secret-password", data);
        System.out.println("Secret written successfully.");

        /* Retrieve a secret */
        log.info("-- Retrieve a secret");
        Versioned<Map<String, Object>> readResponse = vaultTemplate.opsForVersionedKeyValue("secret").get("my-secret-password");
        String password = "";
        if (readResponse != null && readResponse.hasData()) {
            password = (String) readResponse.getData().get("password");
        }
        if (!password.equals("Hashi123")) {
            log.error("Unexpected password");
        }
        System.out.println("Access granted!");

        /* edit output */
        credentialDto.setPassword((String)readResponse.getData().get("password"));
        return credentialDto;
    }
}