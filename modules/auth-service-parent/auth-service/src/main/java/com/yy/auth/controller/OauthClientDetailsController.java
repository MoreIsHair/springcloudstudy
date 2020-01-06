package com.yy.auth.controller;

import com.yy.auth.api.OauthClientDetails;
import com.yy.auth.service.OauthClientDetailsService;
import com.yy.common.core.model.ResponseBean;
import com.yy.common.log.annotation.Log;
import com.yy.common.security.constant.SecurityConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Oauth2客户端信息管理
 */
@Slf4j
@AllArgsConstructor
@Api("Oauth2客户端信息管理")
@RestController
@RequestMapping("/v1/client")
public class OauthClientDetailsController  {

    private final OauthClientDetailsService oauthClientDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据ID获取
     *
     * @param id id
     * @return ResponseBean
     */
    @ApiOperation(value = "获取客户端信息", notes = "根据客户端id获取客户端详细信息")
    @ApiImplicitParam(name = "id", value = "客户端ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/{id}")
    public ResponseBean<OauthClientDetails> oauthClient(@PathVariable String id) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        if (StringUtils.isNotBlank(id)) {
            oauthClientDetails.setId(id);
            oauthClientDetails = oauthClientDetailsService.get(oauthClientDetails);
        }
        return new ResponseBean<>(oauthClientDetails);
    }


    /**
     * 查询客户端列表
     *
     * @param oauthClientDetails oauthClientDetails
     * @return ResponseBean
     */
    @GetMapping("clients")
    @ApiOperation(value = "查询客户端列表", notes = "查询客户端列表")
    @ApiImplicitParam(name = "oauthClient", value = "客户端实体oauthClient", required = true, dataType = "OauthClientDetails")
    public ResponseBean<List<OauthClientDetails>> findOauthClientList(@RequestBody OauthClientDetails oauthClientDetails) {
        return new ResponseBean<>(oauthClientDetailsService.findList(oauthClientDetails));
    }

    /**
     * 创建客户端
     *
     * @param oauthClientDetails oauthClientDetails
     * @return ResponseBean
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys:client:add') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN + "')")
    @ApiOperation(value = "创建客户端", notes = "创建客户端")
    @ApiImplicitParam(name = "oauthClientDetails", value = "客户端实体oauthClientDetails", required = true, dataType = "OauthClientDetails")
    @Log("新增客户端")
    public ResponseBean<Boolean> oauthClient(@RequestBody OauthClientDetails oauthClientDetails) {
        // 加密密钥
        oauthClientDetails.setClientSecret(bCryptPasswordEncoder.encode(oauthClientDetails.getClientSecretPlainText()));
        return new ResponseBean<>(oauthClientDetailsService.insert(oauthClientDetails) > 0);
    }

    /**
     * 修改客户端
     *
     * @param oauthClientDetails oauthClientDetails
     * @return ResponseBean
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys:client:edit') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN + "')")
    @ApiOperation(value = "更新客户端信息", notes = "根据客户端id更新客户端的基本信息")
    @ApiImplicitParam(name = "oauthClientDetails", value = "客户端实体oauthClientDetails", required = true, dataType = "OauthClientDetails")
    @Log("修改客户端")
    public ResponseBean<Boolean> updateOauthClient(@RequestBody OauthClientDetails oauthClientDetails) {
        OauthClientDetails tempOauthClientDetails = oauthClientDetailsService.get(oauthClientDetails);
        // 有调整过明文则重新加密密钥
        if (tempOauthClientDetails != null && !tempOauthClientDetails.getClientSecretPlainText().equals(oauthClientDetails.getClientSecretPlainText()))
            oauthClientDetails.setClientSecret(bCryptPasswordEncoder.encode(oauthClientDetails.getClientSecretPlainText()));
        return new ResponseBean<>(oauthClientDetailsService.update(oauthClientDetails) > 0);
    }

    /**
     * 根据id删除客户端
     *
     * @param id id
     * @return ResponseBean
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:client:del') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN + "')")
    @ApiOperation(value = "删除客户端", notes = "根据ID删除客户端")
    @ApiImplicitParam(name = "id", value = "客户端ID", required = true, paramType = "path")
    @Log("删除客户端")
    public ResponseBean<Boolean> deleteOauthClient(@PathVariable String id) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setId(id);
        return new ResponseBean<>(oauthClientDetailsService.delete(oauthClientDetails) > 0);
    }
}
