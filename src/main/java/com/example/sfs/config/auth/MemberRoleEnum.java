package com.example.sfs.config.auth;

public enum MemberRoleEnum {
    USER(Authority.NORMAL),  // 일반 사용자 권한
    ADMIN(Authority.ADMIN),  // 관리자 권한
    MASTER(Authority.MASTER); // 마스터 권한

    private final String authority;

    MemberRoleEnum (String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String NORMAL = "NORMAL_MEMBER";
        public static final String ADMIN = "ADMIN_MEMBER";
        public static final String MASTER = "MASTER_MEMBER";
    }
}
