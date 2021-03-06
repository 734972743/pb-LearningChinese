package com.pb.weixin.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class StsServiceSample {

	 public static void main(String[] args) {
	        String endpoint = "sts.aliyuncs.com";
	        String accessKeyId = "LTAI4GC82qmbXrEdSmrkWKkM";
	        String accessKeySecret = "quBoezD11bcghnuntxWQVX4KJWRIPu";
	        String roleArn = "acs:ram::1400206564660838:role/ramosstest";
	       // String roleArn = "RamOssTest";
	        String roleSessionName = "AliyunOSSFullAccess1";
	        String policy = "{\n" +
	                "    \"Version\": \"1\", \n" +
	                "    \"Statement\": [\n" +
	                "        {\n" +
	                "            \"Action\": [\n" +
	                "                \"oss:*\"\n" +
	                "            ], \n" +
	                "            \"Resource\": [\n" +
	                "                \"acs:oss:*:*:*\" \n" +
	                "            ], \n" +
	                "            \"Effect\": \"Allow\"\n" +
	                "        }\n" +
	                "    ]\n" +
	                "}";
	        try {
	            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
	            DefaultProfile.addEndpoint("", "", "Sts", endpoint);
	            // 构造default profile（参数留空，无需添加region ID）
	            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
	            // 用profile构造client
	            DefaultAcsClient client = new DefaultAcsClient(profile);
	            final AssumeRoleRequest request = new AssumeRoleRequest();
	            request.setMethod(MethodType.POST);
	            request.setRoleArn(roleArn);
	            request.setRoleSessionName(roleSessionName);
	            request.setPolicy(policy); // 若policy为空，则用户将获得该角色下所有权限
	            request.setDurationSeconds(1000L); // 设置凭证有效时间
	            final AssumeRoleResponse response = client.getAcsResponse(request);
	            System.out.println("Expiration: " + response.getCredentials().getExpiration());
	            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
	            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
	            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
	            System.out.println("RequestId: " + response.getRequestId());
	        } catch (ClientException e) {
	            System.out.println("Failed：");
	            System.out.println("Error code: " + e.getErrCode());
	            System.out.println("Error message: " + e.getErrMsg());
	            System.out.println("RequestId: " + e.getRequestId());
	        }
	    }
}
