package com.example.sfs.security.auth;

public enum MemberRoleEnum {
    USER(Authority.USER),  // 일반 사용자 권한
    MASTER(Authority.MASTER); // 마스터 권한

    private final String authority;

    MemberRoleEnum (String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "SFS_USER";
        public static final String MASTER = "SFS_MASTER";
    }
}
