package com.autodl_backend.local.controller.admin;


import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    @Autowired
    private UsersService usersService;

    /**
     * 设置调度黑名单
     */
    @GetMapping("/blacklist")
    public ApiResponse<BlacklistReq> getBlacklist(){
        BlacklistReq blacklist = usersService.getBlacklist();
        return ApiResponse.success(blacklist);
    }
}
